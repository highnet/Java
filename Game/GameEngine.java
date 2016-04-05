import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by bokense on 25-Mar-16.
 */


public class GameEngine extends JPanel implements MouseListener, MouseMotionListener, ActionListener, KeyListener {

    private int movementSpeed = 25;

    private Vector<Integer> rnglist;

    private int gameSpeed = 1;

    private int worldSize = 5;

    private int currentMap = 0;

    private int actionTick = 0;

    private final Timer timer = new Timer(gameSpeed, this);


    private Player player1;


    private Vector<Npc> npcList = new Vector<>();

    private boolean debugMenuVisible = false;

    private boolean inventoryMenuVisible = false;

    private boolean startMenuVisible = true;

    private boolean mapVisible = false;

    private Font font1 = new Font("Consola", Font.PLAIN, 8);
    private Font font2 = new Font("Consola", Font.BOLD, 16);
    private Font font3 = new Font("Consola", Font.BOLD, 24);

    Tile[][] tilemap = new Tile[32][24];




    public GameEngine() {


        rnglist = rngSeeder();


        genereateWorldImproved();

        fillWord();


        generatePlayer();
        generateNpc(0, 14, 7, 20, Color.yellow);
        generateNpc(1, 20, 10, 20, Color.black);


        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this); // Adds keyboard listener.

        setFocusable(true); // Setting required for keyboard listener.

        timer.start();
    }

    private Vector<Integer> rngSeeder() {


        FileReader file = null;
        try {
            file = new FileReader("Data/RNG.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int rng;

        Vector<Integer> rnglist = new Vector<>();

        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                rng = input.nextInt();

                rnglist.add(rng);
            }
            input.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rnglist;
    }

    private int rotateRng() {

        int r = rnglist.firstElement();

        rnglist.remove(0);
        rnglist.addElement(r);

        return r;
    }


    private void genereateWorldImproved() {
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 24; j++) {
                tilemap[i][j] = new Tile();
            }
        }

        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 24; j++) {

                //_________________________________WorldGen____________________________________________

                int r = rotateRng();


                if (r > 80) {                                     // Dirt spawn rate.
                    tilemap[i][j].type = "rakedDirt";
                }

                r = rotateRng();

                if (tilemap[i][j].type.equals("grass") && r > 96) {              // wood spawn rate/condition.
                    tilemap[i][j].type = "wood";
                } else if ((tilemap[i][j].type.equals("rakedDirt") && r > 95)) { // sand spawn rate/condition.
                    tilemap[i][j].type = "sand";
                }

                //______________________________Resource Generation____________________________________

                r = rotateRng();


                if (r > 98) {
                    tilemap[i][j].type = "wall";
                }

                if (tilemap[i][j].type.equals("rakedDirt")) {                   // Makes all rakedDirt farmable
                    tilemap[i][j].farmable = true;
                }

                if (tilemap[i][j].type.equals("wall") || tilemap[i][j].type.equals("water") || tilemap[i][j].type.equals("wood")) {                   // Makes occupied terrain unwalkable
                    tilemap[i][j].occupied = true;
                }
            }
        }
        saveWorld(0);
    }

    private void fillWord(){

        int size = 5;
        int i = 0;

        for (i= 0; i < size;i++) {

            saveWorld(i);

            genereateWorldImproved();
        }
        tilemap = readWorld(0);
    }

    private void saveWorld(int id) {

        try {
            PrintWriter writer = new PrintWriter("Data/WORLD" + id +".txt", "UTF-8");
            writer.close();

            FileOutputStream fout = new FileOutputStream("Data/WORLD" + id +".txt");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(tilemap);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Tile[][] readWorld(int id) {
        Tile[][] world;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("Data/WORLD" + id +".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while (true) {
                try {
                    world = (Tile[][]) ois.readObject();
                    return world;
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void generatePlayer() {

        player1 = new Player(0, 14, 9, 66, 100, Color.RED);

        tilemap[player1.xPos / 25][player1.yPos / 25].occupied = true;

        System.out.println("Created new player1 - ID: " + player1.ID + " - X: " + player1.xPos + " - Y: " + player1.yPos + " Empty Inventory Slots: " + player1.playerInventory.itemArray.length);

    }

    private void generateNpc(int setID, int setxPos, int setyPos, float setHP, Color setColor) {


        Npc n = new Npc(setID, setxPos, setyPos, setHP, setColor);

        System.out.println("Created new npc1 - ID: " + n.ID + " - X: " + n.xPos + " - Y: " + n.yPos);


        tilemap[n.xPos / 25][n.yPos / 25].occupied = true;

        appendNpc(n);


    }

    private void appendNpc(Npc npc) {
        npcList.add(npc);
    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);


        if (startMenuVisible) {
            paintStartMenu(g);
        }
        if (mapVisible) {
            paintTileSet(g);

            paintPlayer(g);           // player painter
            paintOrientationArrow(g);

            paintEntity(g);
        }

        if (debugMenuVisible) {
            paintTileCoordinates(g);
            paintTileLines(g);
            paintDebugMenu(g);
        }

        if (inventoryMenuVisible) {
            paintInventory(g);
        }


    }

    private void paintInventory(Graphics g) {

        g.setColor(Color.lightGray);
        g.fillRect(575, 149, 200, 400);
        g.setFont(font3);
        g.setColor(Color.black);
        g.drawString("Inventory", 585, 167);


        g.setColor(Color.white);
        g.fillRect(588, 176, 180, 329);


        g.setColor(Color.black);
        int counter = 0;
        int row = 0;

        for (int i = 0; i < player1.playerInventory.itemArray.length; i++) {
            if (counter == 6) {
                counter = 0;
                row++;
            }
            if (player1.playerInventory.itemArray[i].ID == 1) {
                g.setColor(new Color(138, 69, 19));
                g.fillOval(593 + (counter * 30), 183 + (row * 30), 20, 20);

            }

            if (player1.playerInventory.itemArray[i].ID == 2) {
                g.setColor(Color.gray);
                g.fillOval(593 + (counter * 30), 183 + (row * 30), 20, 20);
            }
            g.setColor(Color.black);
            g.drawRect(587 + (counter * 30), 176 + (row * 30), 30, 30);
            counter++;
        }


    }

    private void paintOrientationArrow(Graphics g) {
        g.setColor(Color.black);


        switch (player1.orientation) {
            case "EAST":
                g.drawLine(player1.xPos + 10, player1.yPos + 10, player1.xPos + 20, player1.yPos + 10);
                break;
            case "WEST":
                g.drawLine(player1.xPos + 10, player1.yPos + 10, player1.xPos, player1.yPos + 10);
                break;
            case "NORTH":
                g.drawLine(player1.xPos + 10, player1.yPos + 10, player1.xPos + 10, player1.yPos);
                break;
            case "SOUTH":
                g.drawLine(player1.xPos + 10, player1.yPos + 10, player1.xPos + 10, player1.yPos + 20);
                break;

        }
    }

    private void paintDebugMenu(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(24, 450, 300, 124);
        g.setColor(Color.black);
        g.setFont(font2);
        g.drawString("player1 TrueCoords: (" + player1.xPos + ", " + player1.yPos + ")", 37, 465);
        g.drawString("player1 TileCoords: (" + (player1.xPos / 25) + ", " + (player1.yPos / 25) + ")", 37, 490);


        int player1_TileCoordinated_xPos = (player1.xPos / 25);
        int player1_TileCoordinated_yPos = (player1.yPos / 25);
        g.drawString("player1 standing on tile: " + tilemap[player1_TileCoordinated_xPos][player1_TileCoordinated_yPos].type, 37, 515);
        g.drawString("Farmable? " + (tilemap[player1_TileCoordinated_xPos][player1_TileCoordinated_yPos].farmable ? "yes" : "no"), 88, 532);


        g.drawString("action ticker: (" + actionTick + ")", 39, 556);

    }

    private void paintStartMenu(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(24, 1, 750, 600);
        g.setColor(Color.black);
        g.setFont(font2);
        g.drawString("AWESOME GAME PRE-ALPHA", 246, 41);
        g.drawString("0 : Start New Game", 87, 88);
        g.drawString("9 : Load Map", 87, 157);
        g.drawString("Options", 87, 254);
    }

    private void paintEntity(Graphics g) {


        for (Npc n : npcList) {
            g.setColor(n.pallete);
            g.fillOval(n.xPos, n.yPos, 20, 20);
        }
    }

    private void paintPlayer(Graphics g) {

        g.setColor(player1.pallete);
        g.fillOval(player1.xPos, player1.yPos, 20, 20);
    }

    private void paintTileCoordinates(Graphics g) {
        g.setFont(font1);
        g.setColor(Color.black);

        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 24; j++) {
                g.drawString("" + i + ", " + j, i * 25 + 2, j * 25 + 13);
            }
        }
    }

    private void paintTileLines(Graphics g) {
        int counter = 0;
        int row = 0;
        for (int i = 0; i < 768; i++) {

            if (counter == 32) {
                row++;
                counter = 0;

            }

            g.setColor(Color.black);
            g.drawRect(counter * 25, row * 25, 25, 25);

            counter++;
        }
    }


    private void paintTileSet(Graphics g) { // Tile Rendering System


        for (int i = 0; i < 32; i++) { // foreach tile outer loop
            for (int j = 0; j < 24; j++) { // foreach tile inner loop

                String tileTypeToPaint = tilemap[i][j].type; // store tile type as string
                switch (tileTypeToPaint) { // Rendering unit for each tile type
                    case "grass":
                        g.setColor(Color.green);
                        g.fillRect(i * 25, j * 25, 25, 25);
                        break;
                    case "water":
                        g.setColor(Color.blue);
                        g.fillRect(i * 25, j * 25, 25, 25);
                        break;
                    case "wood":
                        g.setColor(new Color(138, 69, 19));
                        g.fillRect(i * 25, j * 25, 25, 25);
                        g.drawString("Tree", i * 25, j * 25);
                        break;
                    case "wall":
                        g.setColor(Color.gray);
                        g.fillRect(i * 25, j * 25, 25, 25);
                        break;
                    case "sand":
                        g.setColor(Color.orange);
                        g.fillRect(i * 25, j * 25, 25, 25);
                        break;
                    case "rakedDirt":
                        g.setColor(new Color(100, 40, 19));
                        g.fillRect(i * 25, j * 25, 25, 25);
                        break;
                    default:
                        g.setColor(Color.red);
                        g.drawString("ERR", i * 25, j * 25 + 25);
                        break;
                }


            }

        }
    }

    private void npcBehaviour() {

        int counter = (actionTick % 5);
        int r = rotateRng();
        int index = 0;

        for (Npc n : npcList) {

            index = n.ID;

            switch (npcList.elementAt(index).ai) {

                case 0:

                    if (counter == 0) {

                        tilemap[n.xPos / 25][n.yPos / 25].occupied = false;

                        r = rotateRng();

                        if (r <= 25) {
                            if (!tilemap[npcList.firstElement().xPos / 25 - 1][(n.yPos / 25)].occupied) {
                                n.xPos -= movementSpeed; //update ypos
                            }
                        } else if (r > 25 && r < 50) {
                            if (!tilemap[n.xPos / 25 + 1][(n.yPos / 25)].occupied) {
                                n.xPos += movementSpeed; //update ypos
                            }
                        } else if (r > 50 && r < 75) {
                            if (!tilemap[n.xPos / 25][(n.yPos / 25 - 1)].occupied) {
                                n.yPos -= movementSpeed; //update ypos
                            }
                        } else if (r >= 75) {
                            if (!tilemap[n.xPos / 25][(n.yPos / 25 + 1)].occupied) {
                                n.yPos += movementSpeed; //update ypos
                            }

                        }
                        tilemap[n.xPos / 25][n.yPos / 25].occupied = true;
                    }
            }
        }
    }


    private void tick() {
        actionTick++;

        npcBehaviour();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer) {
            this.repaint();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {


        switch (e.getKeyCode()) { // Keyboard switch -> e.getKeyCode() returns a virtual keyboard int value

            /*
            MOVEMENT AND ORIENTATION
            */

            case KeyEvent.VK_0:

                if (startMenuVisible) {
                    startMenuVisible = false;
                    mapVisible = true;

                    break;
                } else {
                    startMenuVisible = true;

                    mapVisible = false;
                    inventoryMenuVisible = false;
                    debugMenuVisible = false;

                    break;
                }
            case KeyEvent.VK_9:



                if (!mapVisible) {
                    tilemap = readWorld(currentMap);
                    currentMap++;
                    if (currentMap == worldSize)
                    {
                        currentMap = 0;
                    }
                    System.out.println("World Loaded.");
                }
                break;

            case KeyEvent.VK_UP: // User presses the up key

                player1.orientation = "NORTH"; // set the player1 orientation state to "NORTH"

                if (!tilemap[player1.xPos / 25][(player1.yPos / 25) - 1].occupied) {

                    tilemap[player1.xPos / 25][player1.yPos / 25].occupied = false;
                    player1.yPos -= movementSpeed; //update ypos
                    tilemap[player1.xPos / 25][player1.yPos / 25].occupied = true;

                }
                break;
            case KeyEvent.VK_DOWN: // Tries to move down

                player1.orientation = "SOUTH";

                if (!tilemap[player1.xPos / 25][(player1.yPos / 25) + 1].occupied) {
                    tilemap[player1.xPos / 25][player1.yPos / 25].occupied = false;
                    player1.yPos += movementSpeed; //update ypos
                    tilemap[player1.xPos / 25][player1.yPos / 25].occupied = true;
                }
                break;
            case KeyEvent.VK_LEFT: // Tries to move left

                player1.orientation = "WEST";

                if (!tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].occupied) {
                    tilemap[player1.xPos / 25][player1.yPos / 25].occupied = false;
                    player1.xPos -= movementSpeed; //update ypos
                    tilemap[player1.xPos / 25][player1.yPos / 25].occupied = true;
                }
                break;
            case KeyEvent.VK_RIGHT: // Tries to move right
                player1.orientation = "EAST";

                if (!tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].occupied) {
                    tilemap[player1.xPos / 25][player1.yPos / 25].occupied = false;
                    player1.xPos += movementSpeed; //update ypos
                    tilemap[player1.xPos / 25][player1.yPos / 25].occupied = true;
                }
                break;

            /*
            DEBUG MENU/INDICATORS OPEN/CLOSE
            Open all debug menus and indicators
             */
            case KeyEvent.VK_X: // keyboard press X -> Shows debug menu

                if (!startMenuVisible) {
                    debugMenuVisible = !debugMenuVisible; // reverse the debug menu boolean state
                    System.out.println("Debug Menu Visible: " + debugMenuVisible); // print to console the boolean state of "debugmenuVisible"
                    System.out.println(printTileSet(tilemap));
                }
                break;
            /*
            Player Actions
             */
                /*

                Harvesting:
                            Requirements -> 1. Player's inventory is not full
                                            2. Player's orientation faces a harvestable tile/entity.
                                            3. The tile harvested by the player is harvestable.
                            Outcomes -> 1. (IFF Successful) The player receives an item to his inventory on his next free slot.
                                            -> "receives an item" means the ID state of the player's item inventory array changes from 0 ("empty") to another ID related to the harvested tile.
                                        2. (IFF Successful) The tile harvested by the player can be modified (eg: if cut tree -> tile becomes grass)
                                        3. (IFF Failure) The tile the player attempted to harvest remains unchanged.


                 */
            case KeyEvent.VK_1: // keyboard press 1 -> attempt to harvest block
                System.out.println("1- Block Harvesting");

                if (!player1.playerInventory.isFull()) { // Allows harvesting process to happen only if the inventory isnt full.

                    boolean harvestedSuccessfully = false; // flag to determine real-time whether the key press triggers a successful harvest action
                    String harvestedItem = "";
                    if (player1.orientation.equals("EAST") && tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type.equals("wood")) {
                        tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type = "grass";
                        harvestedItem = "lumber";
                        tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].occupied = false;
                        harvestedSuccessfully = true;

                    }
                    if (player1.orientation.equals("EAST") && tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type.equals("wall")) {
                        tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type = "rakedDirt";
                        harvestedItem = "cobblestone";
                        tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].occupied = false;
                        tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].farmable = true;
                        harvestedSuccessfully = true;
                    }


                    if (player1.orientation.equals("WEST") && tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type.equals("wood")) {
                        tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type = "grass";
                        harvestedItem = "lumber";
                        tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].occupied = false;
                        harvestedSuccessfully = true;

                    }
                    if (player1.orientation.equals("WEST") && tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type.equals("wall")) {
                        tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type = "rakedDirt";
                        harvestedItem = "cobblestone";
                        tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].occupied = false;
                        tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].farmable = true;
                        harvestedSuccessfully = true;

                    }
                    if (player1.orientation.equals("NORTH") && tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].type.equals("wood")) {
                        tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].type = "grass";
                        tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].occupied = false;
                        harvestedSuccessfully = true;
                        harvestedItem = "lumber";
                    }
                    if (player1.orientation.equals("NORTH") && tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].type.equals("wall")) {
                        tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].type = "rakedDirt";
                        tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].occupied = false;
                        tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].farmable = true;
                        harvestedSuccessfully = true;
                        harvestedItem = "cobblestone";
                    }
                    if (player1.orientation.equals("SOUTH") && tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].type.equals("wood")) {
                        tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].type = "grass";
                        harvestedItem = "lumber";
                        tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].occupied = false;
                        harvestedSuccessfully = true;
                    }
                    if (player1.orientation.equals("SOUTH") && tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].type.equals("wall")) {
                        tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].type = "rakedDirt";
                        harvestedItem = "cobblestone";
                        tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].occupied = false;
                        tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].farmable = true;
                        harvestedSuccessfully = true;
                    }

                    if (harvestedSuccessfully) { // Iff the tile is flagged to be successfully harvested, find an empty slot and fill it with a given item.
                        for (int i = 0; i < 64; i++) {
                            if (player1.playerInventory.itemArray[i].ID == 0) {
                                if (harvestedItem.equals("lumber")) {
                                    player1.playerInventory.itemArray[i].ID = 1;
                                    break;
                                }
                                if (harvestedItem.equals("cobblestone")) {
                                    player1.playerInventory.itemArray[i].ID = 2;
                                    break;
                                }
                            }
                        }
                        tick();
                    }
                }
                break;
             /*
            Inventory

            Displays the inventory

             */

            case KeyEvent.VK_I:

                if (!startMenuVisible) {
                    inventoryMenuVisible = !inventoryMenuVisible;
                    System.out.println(player1.playerInventory);
                    System.out.println("Inventory is full: " + player1.playerInventory.isFull());
                }

        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        requestFocusInWindow();


        System.out.println(e.getX() + ", " + e.getY());

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public static String printTileSet(Tile[][] tilemap) {
        String ans = "";
        for (int i = 0; i < 32; i++) {
            ans += "\n";
            for (int j = 0; j < 24; j++) {
                ans += " - " + tilemap[i][j].type + " - ";

            }
        }

        return ans;
    }
}


