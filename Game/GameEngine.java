import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by bokense on 25-Mar-16.
 */


public class GameEngine extends JPanel implements MouseListener, MouseMotionListener, ActionListener, KeyListener {

    private int movementSpeed = 25;

    private int gameSpeed = 1;

    private final Timer timer = new Timer(gameSpeed, this);


    private Player player1;

    private boolean debugMenuVisible = true;

    private boolean inventoryMenuVisible = true;

    private Font font1 = new Font("Consola", Font.PLAIN, 8);
    private Font font2 = new Font("Coinsola", Font.BOLD, 16);
    private Font font3 = new Font("Consola", Font.BOLD,24);

    Tile[][] tilemap = new Tile[32][24];


    public GameEngine() {

   //     generateWorld();
        genereateWorldImproved();
        generatePlayer();

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this); // Adds keyboard listener.

        setFocusable(true); // Setting required for keyboard listener.

        timer.start();


    }

    private void genereateWorldImproved() {
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 24; j++) {
                tilemap[i][j] = new Tile();
            }
        }

        tilemap[0][0].type = "wall";
        tilemap[1][1].type = "rakedDirt";
        tilemap[1][1].farmable = true;


    }

    private void generatePlayer() {

        player1 = new Player(0, 14, 9, 66);

        System.out.println("Created new player1 - ID: " + player1.ID + " - X: " + player1.xPos + " - Y: " + player1.yPos + " Empty Inventory Slots: " + player1.playerInventory.itemArray.length);
    }



    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        paintTileSet(g);

        if (debugMenuVisible) {
            paintTileCoordinates(g);
            paintTileLines(g);
            paintDebugMenu(g);
        }

        if (inventoryMenuVisible){
            paintInventory(g);
        }

        paintEntities(g);
        paintOrientationArrow(g);





    }

    private void paintInventory(Graphics g) {

        g.setColor(Color.lightGray);
        g.fillRect(575,149,200,400);
        g.setFont(font3);
        g.setColor(Color.black);
       g.drawString("Inventory",585,167);





        g.setColor(Color.white);
        g.fillRect(588,176,180,329);


        g.setColor(Color.black);
        int counter = 0;
        int row = 0;

            for (int i = 0; i < player1.playerInventory.itemArray.length; i++) {
                if (counter == 6){
                    counter = 0;
                    row++;
                }
                if (player1.playerInventory.itemArray[i].ID == 1){
                    g.setColor(new Color(138, 69, 19));
                    g.fillOval(593 + (counter*30),183 + (row * 30),20,20);

                }

                if (player1.playerInventory.itemArray[i].ID == 2){
                    g.setColor(Color.gray);
                    g.fillOval(593 + (counter*30),183 + (row * 30),20,20);
                }
                g.setColor(Color.black);
                g.drawRect(587 + (counter*30), 176 + (row * 30), 30, 30);
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
        g.drawString("Farmable? "+ (tilemap[player1_TileCoordinated_xPos][player1_TileCoordinated_yPos].farmable ? "yes" : "no"),88,532);

    }

    private void paintEntities(Graphics g) {

        int player1xPos = player1.xPos;
        int player1yPos = player1.yPos;
        g.setColor(Color.red);
        g.fillOval(player1xPos, player1yPos, 20, 20);
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
                        g.drawString("Tree",i * 25, j * 25);
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
            case KeyEvent.VK_UP: // User presses the up key

                player1.orientation = "NORTH"; // set the player1 orientation state to "NORTH"

                if (!tilemap[player1.xPos / 25][(player1.yPos / 25) - 1].type.equals("wood") && // check if the tile belongs to one of the "impassable" tiles
                        !tilemap[player1.xPos / 25][(player1.yPos / 25) - 1].type.equals("water") &&
                        !tilemap[player1.xPos / 25][(player1.yPos / 25) - 1].type.equals("wall")) {
                    player1.yPos -= movementSpeed; //update ypos
                }
                break;
            case KeyEvent.VK_DOWN: // Tries to move down

                player1.orientation = "SOUTH";

                if (!tilemap[player1.xPos / 25][(player1.yPos / 25) + 1].type.equals("wood") && // check if the tile belongs to one of the "impassable" tiles
                        !tilemap[player1.xPos / 25][(player1.yPos / 25) + 1].type.equals("water") &&
                        !tilemap[player1.xPos / 25][(player1.yPos / 25) + 1].type.equals("wall")) {
                    player1.yPos += movementSpeed; //update ypos
                }
                break;
            case KeyEvent.VK_LEFT: // Tries to move left

                player1.orientation = "WEST";

                if (!tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type.equals("wood") && // check if the tile belongs to one of the "impassable" tiles
                        !tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type.equals("water") &&
                        !tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type.equals("wall")) {
                    player1.xPos -= movementSpeed; //update ypos
                }
                break;
            case KeyEvent.VK_RIGHT: // Tries to move right
                player1.orientation = "EAST";

                if (!tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type.equals("wood") && // check if the tile belongs to one of the "impassable" tiles
                        !tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type.equals("water") &&
                        !tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type.equals("wall")) {
                    player1.xPos += movementSpeed; //update ypos
                }
                break;

            /*
            DEBUG MENU/INDICATORS OPEN/CLOSE
            Open all debug menus and indicators
             */
            case KeyEvent.VK_X: // keyboard press X -> Shows debug menu
                debugMenuVisible = !debugMenuVisible; // reverse the debug menu boolean state
                System.out.println("Debug Menu Visible: " + debugMenuVisible); // print to console the boolean state of "debugmenuVisible"
                System.out.println(printTileSet(tilemap));
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
                        harvestedSuccessfully = true;
                    }
                    if (player1.orientation.equals("EAST") && tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type.equals("wall")) {
                        tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type = "grass";
                        harvestedItem = "cobblestone";
                        harvestedSuccessfully = true;
                    }


                    if (player1.orientation.equals("WEST") && tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type.equals("wood")) {
                        tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type = "grass";
                        harvestedItem = "lumber";
                        harvestedSuccessfully = true;

                    }
                    if (player1.orientation.equals("WEST") && tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type.equals("wall")) {
                        tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type = "grass";
                        harvestedItem = "cobblestone";
                        harvestedSuccessfully = true;

                    }
                    if (player1.orientation.equals("NORTH") && tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].type.equals("wood")) {
                        tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].type = "grass";
                        harvestedSuccessfully = true;
                        harvestedItem = "lumber";
                    }
                    if (player1.orientation.equals("NORTH") && tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].type.equals("wall")) {
                        tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].type = "grass";
                        harvestedSuccessfully = true;
                        harvestedItem = "cobblestone";
                    }
                    if (player1.orientation.equals("SOUTH") && tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].type.equals("wood")) {
                        tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].type = "grass";
                        harvestedItem = "lumber";
                        harvestedSuccessfully = true;
                    }
                    if (player1.orientation.equals("SOUTH") && tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].type.equals("wall")) {
                        tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].type = "grass";
                        harvestedItem = "cobblestone";
                        harvestedSuccessfully = true;
                    }

                    if (harvestedSuccessfully) { // Iff the tile is flagged to be successfully harvested, find an empty slot and fill it with a given item.
                        for (int i = 0; i < 64; i++) {
                            if (player1.playerInventory.itemArray[i].ID == 0) {
                                if (harvestedItem.equals("lumber")) {
                                    player1.playerInventory.itemArray[i].ID = 1;
                                    break;
                                }
                                if (harvestedItem.equals("cobblestone")){
                                    player1.playerInventory.itemArray[i].ID = 2;
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
             /*
            Inventory

            Displays the inventory

             */

            case KeyEvent.VK_I:
                inventoryMenuVisible = !inventoryMenuVisible;
                System.out.println(player1.playerInventory);
                System.out.println("Inventory is full: " + player1.playerInventory.isFull());

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

    public static String printTileSet(Tile[][] tilemap){
            String ans = "";
            for (int i = 0; i < 32; i++) {
                ans += "\n";
                for (int j = 0; j < 24; j++) {
                    ans +=  " - " + tilemap[i][j].type + " - ";

                }
            }

            return ans;
        }


}
