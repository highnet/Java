import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


/**
 * Created by bokense on 25-Mar-16.
 */


public class GameEngine extends JPanel implements MouseListener, MouseMotionListener, ActionListener, KeyListener {

    private int movementSpeed = 25;

    private Vector<Integer> rnglist;

    private String playerInput = null;

    private int gameSpeed = 1;

    private int worldSize = 2;  // Defines Overworld dimensions.

    private Overworld currentOverWorld = null;

    private Tile currentTile = null;
    private int currentTileX = 0;
    private int currentTileY = 0;

    private Item currentItem = null;
    private int currentItemIndex = 0;
    private int currentItemRow = 0;
    private int currentItemColumn = 0;


    private int actionTick = 0;  // Ticker for player actions.

    private final Timer timer = new Timer(gameSpeed, this);

    private Player player1;

    private Npc currentNpc;    // Selected Npc pointer

    private boolean debugMenuVisible = false;

    private boolean inventoryMenuVisible = false;

    private boolean startMenuVisible = true;

    private boolean mapVisible = false;

    private Font font1 = new Font("Consola", Font.PLAIN, 8);
    private Font font2 = new Font("Consola", Font.BOLD, 16);
    private Font font3 = new Font("Consola", Font.BOLD, 24);

    private Overworld[][] overWorld = new Overworld[worldSize][worldSize];

    private boolean runFlag = false;

    private boolean paintScreen;

    private double delta = 0.04;

    double nextTime = (double) System.nanoTime() / 1000000000.0;

    Map<String, BufferedImage> bufferedImageMap;

    int windDirection = 1;
    private boolean raining;
    Point[] rainDrops;
    int numberOfRainDrops = 10;
    Deque<Point> bufferSplashAnimations = new LinkedList<>();

    AudioInputStream audioInputStream;
    Clip movementSound;

    private boolean craftingMenuVisible = false;

    int mouseDragX = 0;
    int mouseDragY = 0;


    public GameEngine() {


        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this); // Adds keyboard listener.

        setFocusable(true); // Setting required for keyboard listener.

        run();


    }

    private void startUp() {
        rnglist = rngSeeder();          // Loads pre-generated RNG numbers from file to a  Vector.

        generatePlayer();               // Player is created.

        //   currentItem = player1.playerInventory.itemArray[0];

        buildOverworld();               // adds worldSize x worldSize OverWorlds to the Overworld array.

        currentOverWorld = overWorld[0][0];     // Points currentOverWorld pointer to start map.


        dummyWorld();               // initializes currentOverWorld.tilemap list and fills it with an empty grass world.

        //  currentTile = currentOverWorld.tilemap[0][0];   // points to the currently selected tile.

        System.out.println("Enter 1 to GENERATE WORLD. /n Enter 0 TO LOAD WORLD ");

        playerInput = getUserInput();

        if (playerInput.equals("1")){
            fillWorld();
        } else if (playerInput.equals("0")){

                    reloadOverWorld();

        }




        loadSprites();

        loadRainSound();

        raining = true;

        generateRainPattern();

        runFlag = true;

        timer.start();

    }


    private void loadRainSound() {

        File rain = new File("Data/Sound/Rain.wav");

        try {
            audioInputStream = AudioSystem.getAudioInputStream(rain);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

        try {
            Clip rainSound = AudioSystem.getClip();
            rainSound.open(audioInputStream);
            rainSound.start();
            rainSound.loop(999);

        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }


    }

    private void generateRainPattern() {

        rainDrops = new Point[numberOfRainDrops];

        for (int i = 0; i < rainDrops.length; i++) {
            int x = (int) (Math.random() * (Main.WIDTH));
            int y = (int) (Math.random() * (Main.HEIGHT));

            rainDrops[i] = new Point(x, y);
        }

        for (Point p : rainDrops) {
            System.out.println("" + p.x + "," + p.y);
        }
    }

    private void loadSprites() {
        bufferedImageMap = new HashMap<>();

        BufferedImage northAdventurer;
        BufferedImage southAdventurer;
        BufferedImage eastAdventurer;
        BufferedImage westAdventurer;
        BufferedImage errorImg;
        BufferedImage northFrog;
        BufferedImage southFrog;
        BufferedImage eastFrog;
        BufferedImage westFrog;
        BufferedImage grass;
        BufferedImage dirt;
        BufferedImage rakedDirt;
        BufferedImage plankWall;
        BufferedImage tree;
        BufferedImage stone;
        BufferedImage inventoryLumber;
        BufferedImage northZombie;
        BufferedImage southZombie;
        BufferedImage eastZombie;
        BufferedImage westZombie;
        BufferedImage northSheep;
        BufferedImage southSheep;
        BufferedImage eastSheep;
        BufferedImage westSheep;


        try {
            northAdventurer = ImageIO.read(new File("Data/GFX/NorthAdventurer.png"));
            eastAdventurer = ImageIO.read(new File("Data/GFX/EastAdventurer.png"));
            southAdventurer = ImageIO.read(new File("Data/GFX/SouthAdventurer.png"));
            westAdventurer = ImageIO.read(new File("Data/GFX/WestAdventurer.png"));
            errorImg = ImageIO.read(new File("Data/GFX/ErrorImg.jpg"));
            northFrog = ImageIO.read(new File("Data/GFX/NorthFroggy.png"));
            southFrog = ImageIO.read(new File("Data/GFX/SouthFroggy.png"));
            eastFrog = ImageIO.read(new File("Data/GFX/EastFroggy.png"));                 // reads tree sprite
            westFrog = ImageIO.read(new File("Data/GFX/WestFroggy.png"));
            grass = ImageIO.read(new File("Data/GFX/Grass.png"));
            dirt = ImageIO.read(new File("Data/GFX/Dirt.png"));
            rakedDirt = ImageIO.read(new File("Data/GFX/RakedDirt.png"));
            plankWall = ImageIO.read(new File("Data/GFX/PlanksWall.png"));
            tree = ImageIO.read(new File("Data/GFX/Tree.png"));                 // reads tree sprite
            stone = ImageIO.read(new File("Data/GFX/Rock.gif"));                // reads stone sprite.
            inventoryLumber = ImageIO.read(new File("Data/GFX/InventoryLumber.png"));                // reads stone sprite.
            northSheep = ImageIO.read(new File("Data/GFX/NorthSheep.png"));
            southSheep = ImageIO.read(new File("Data/GFX/SouthSheep.png"));
            eastSheep = ImageIO.read(new File("Data/GFX/EastSheep.png"));
            westSheep = ImageIO.read(new File("Data/GFX/WestSheep.png"));
            northZombie = ImageIO.read(new File("Data/GFX/NorthZombie.png"));
            southZombie = ImageIO.read(new File("Data/GFX/SouthZombie.png"));
            eastZombie = ImageIO.read(new File("Data/GFX/EastZombie.png"));
            westZombie = ImageIO.read(new File("Data/GFX/WestZombie.png"));

            bufferedImageMap.put("NORTH_ADVENTURER", northAdventurer);
            bufferedImageMap.put("SOUTH_ADVENTURER", southAdventurer);
            bufferedImageMap.put("EAST_ADVENTURER", eastAdventurer);
            bufferedImageMap.put("WEST_ADVENTURER", westAdventurer);
            bufferedImageMap.put("INVENTORY_LUMBER", inventoryLumber);
            bufferedImageMap.put("ERROR", errorImg);
            bufferedImageMap.put("NORTH_FROG", northFrog);
            bufferedImageMap.put("SOUTH_FROG", southFrog);
            bufferedImageMap.put("EAST_FROG", eastFrog);
            bufferedImageMap.put("WEST_FROG", westFrog);
            bufferedImageMap.put("GRASS", grass);
            bufferedImageMap.put("DIRT", dirt);
            bufferedImageMap.put("RAKED_DIRT", rakedDirt);
            bufferedImageMap.put("PLANK_WALL", plankWall);
            bufferedImageMap.put("TREE", tree);
            bufferedImageMap.put("STONE", stone);
            bufferedImageMap.put("NORTH_SHEEP",northSheep);
            bufferedImageMap.put("SOUTH_SHEEP",southSheep);
            bufferedImageMap.put("EAST_SHEEP",eastSheep);
            bufferedImageMap.put("WEST_SHEEP",westSheep);
            bufferedImageMap.put("NORTH_CHASER",northZombie);
            bufferedImageMap.put("SOUTH_CHASER",southZombie);
            bufferedImageMap.put("EAST_CHASER",eastZombie);
            bufferedImageMap.put("WEST_CHASER",westZombie);


        } catch (IOException e) {

            errorImg = null;
            northFrog = null;
            southFrog = null;
            eastFrog = null;
            westFrog = null;
            grass = null;
            dirt = null;
            rakedDirt = null;
            plankWall = null;
            tree = null;
            stone = null;
            inventoryLumber = null;
            northZombie = null;
            southZombie = null;
            eastZombie = null;
            westZombie = null;
            northSheep = null;
            southSheep = null;
            eastSheep = null;
            westSheep = null;

        }


    }

    private boolean paint() {
        // convert the time to seconds
        double currTime = (double) System.nanoTime() / 1000000000.0;

        if (currTime >= nextTime) {
            // assign the time for the next update
            nextTime += delta;
            paintScreen = true;


        } else {
            int sleepTime = (int) (1000.0 * (nextTime - currTime));
            // sanity check
            paintScreen = false;

            if (sleepTime > 0) {
                // sleep until the next update
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    // do nothing
                }
            }

        }
        return paintScreen;

    }


    private void run() {
        startUp();
    }


    private Vector<Integer> rngSeeder() {


        FileReader file = null;
        try {
            file = new FileReader("Data/RNG.txt");          // creats a pointer to the the file Data/RNG.txt
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int rng;

        Vector<Integer> rnglist = new Vector<>();         // creates a Vector of type int to store RNG values.

        try {
            Scanner input = new Scanner(file);              // creates a scanning stream pointing to file.
            while (input.hasNext()) {                               // loops until file has no more text numbers.
                rng = input.nextInt();                              // loads next integer in list.

                rnglist.add(rng);                                           // adds the loaded integer to vector.
            }
            input.close();                                  // closes stream.

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rnglist;                     // Returns the int list.
    }

    private int rotateRng() {

        int r = rnglist.firstElement();

        rnglist.remove(0);
        rnglist.addElement(r);                                                // Uses the rnglist vector as a circular buffer and rotates it.

        return r;                                                             //returns rotated integer.
    }

    private void dummyWorld() {


        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 24; j++) {
                currentOverWorld.tilemap[i][j] = new Tile();                            // creates a default tile on every coordinate of the current Overworld.
            }
        }

    }

    private void generateWorldImproved() {

        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 24; j++) {

                //_________________________________WorldGen____________________________________________

                int r = rotateRng();                // sets r to a new rng value.


                if (r > 99) {                                     // Dirt spawn rate.
                    currentOverWorld.tilemap[i][j].type = "dirt";
                }

                r = rotateRng();

                if (currentOverWorld.tilemap[i][j].type.equals("grass") && r > 96) {              // wood spawn rate/condition.
                    currentOverWorld.tilemap[i][j].type = "wood";
                } else if ((currentOverWorld.tilemap[i][j].type.equals("dirt") && r > 96)) { // sand spawn rate/condition.
                    currentOverWorld.tilemap[i][j].type = "sand";
                }

                //______________________________Resource Generation____________________________________

                r = rotateRng();


                if (r > 98) {
                    currentOverWorld.tilemap[i][j].type = "stone";
                }

                if (currentOverWorld.tilemap[i][j].type.equals("rakedDirt")) {                   // Makes all rakedDirt farmable
                    currentOverWorld.tilemap[i][j].farmable = true;
                }
            }

            collisionMeshGenerator();           // generates a collision mesh for the current Overworld.
        }

    }

    private void collisionMeshGenerator() {
        int i;
        int j;
        for (i = 0; i < 32; i++) {                          // First, iterate through the entire tilemap of the current Overworld
            for (j = 0; j < 24; j++) {                      // and flag any non passable tiles as occupied. flag every passable tile as !occupied.

                currentOverWorld.tilemap[i][j].occupied = (currentOverWorld.tilemap[i][j].type.equals("wood")) ||
                        (currentOverWorld.tilemap[i][j].type.equals("stone")) ||
                        (currentOverWorld.tilemap[i][j].type.equals("water")) ||
                        (currentOverWorld.tilemap[i][j].type.equals("plankWall"));
            }


            currentOverWorld.tilemap[player1.xPos / 25][player1.yPos / 25].occupied = true; // flags current player position as occupied.

            for (Npc n : currentOverWorld.npcList) {                   // flags coordinate of every npc in the currentOverworld.npclist vector as occupied.
                currentOverWorld.tilemap[n.xPos / 25][n.yPos / 25].occupied = true;
            }


        }
    }

    private void naturalProcesses() {
        int i;
        int j;


        for (i = 0; i < 32; i++) {                          // First, iterate through the entire tilemap of the current Overworld
            for (j = 0; j < 24; j++) {

                if (currentOverWorld.tilemap[i][j].type.equals("dirt")) {
                    currentOverWorld.tilemap[i][j].growth++;
                }

                if (currentOverWorld.tilemap[i][j].growth == 150) {
                    if (currentOverWorld.tilemap[i][j].type.equals("dirt")) {
                        currentOverWorld.tilemap[i][j].type = "grass";
                        currentOverWorld.tilemap[i][j].growth = 0;
                    }
                }


            }
        }
    }

    private void fillWorld() {


        int x;
        int y;


        for (x = 0; x < worldSize; x++) {             // iterates through the entire overWorlds array.
            for (y = 0; y < worldSize; y++) {

                currentOverWorld = overWorld[x][y];         // moves currentOverWorlds pointer.
                dummyWorld();                               // initializes current Overworld tilemap.
                generateWorldImproved();                    // generates RNG world and serializes to file.
                System.out.println("World" + currentOverWorld.idX + currentOverWorld.idY + " generated");
                populateWorld();                        // initializes and populates currentOverWorld.npclist with RNG Npc's.
                saveWorld();
            }

        }
        currentOverWorld = overWorld[0][0];  // resets currentOverWorld pointer.
    }


    private void saveWorld() {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(                      // First create a new textfile.
                    new FileOutputStream("Data/Maps/WORLD" + currentOverWorld.idX + currentOverWorld.idY + ".ser"), "utf-8"));
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        try {                                                                                       // Then serialize an Overworld object to it.
            FileOutputStream fileOut = new FileOutputStream("Data/Maps/WORLD" + currentOverWorld.idX + currentOverWorld.idY + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);           // creates output stream pointed to file.
            out.writeObject(overWorld[currentOverWorld.idX][currentOverWorld.idY]);                                  // serialize currentOverWorld.
            out.close();
            fileOut.close();                                // closes stream and file pointers.
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void saveCustomWorld(String name){
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(                      // First create a new textfile.
                    new FileOutputStream("Data/CustomMaps/"+name+".ser"), "utf-8"));
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        try {                                                                                       // Then serialize an Overworld object to it.
            FileOutputStream fileOut = new FileOutputStream("Data/CustomMaps/" +name+ ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);           // creates output stream pointed to file.
            out.writeObject(overWorld[currentOverWorld.idX][currentOverWorld.idY]);                                  // serialize currentOverWorld.
            out.close();
            fileOut.close();                                // closes stream and file pointers.
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void reloadOverWorld() {
        int x;
        int y;
        for (x = 0; x < worldSize; x++) {
            for (y = 0; y < worldSize; y++) {
                currentOverWorld = overWorld[x][y];         // moves currentOverWorlds pointer.
                dummyWorld();
                readWorld(x, y);
            }
        }

        currentOverWorld = overWorld[0][0];


    }


    public void readWorld(int idX, int idY) {

        try {
            FileInputStream fileIn = new FileInputStream("Data/Maps/WORLD" + idX + idY + ".ser");      // point to file.
            ObjectInputStream in = new ObjectInputStream(fileIn);                           // open stream.
            overWorld[idX][idY] = (Overworld) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("World" + idX + idY + " loaded");
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();

        }

    }


    public void readCustomWorld(String name) {

        try {
            FileInputStream fileIn = new FileInputStream("Data/CustomMaps/"+name+".ser");      // point to file.
            ObjectInputStream in = new ObjectInputStream(fileIn);                           // open stream.
            overWorld[currentOverWorld.idX][currentOverWorld.idY] = (Overworld) in.readObject();                                 //read Overworld object from file and write to currentOverWorld pointer.
            in.close();
            fileIn.close();
            System.out.println("Data/"+name+".ser");
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }

    }

    private void buildOverworld() {

        for (int i = 0; i < worldSize; i++) {
            for (int j = 0; j < worldSize; j++) {
                overWorld[i][j] = new Overworld(i, j);             // iterates through Overworld array and intializes it.

            }
        }

    }

    private void generatePlayer() {

        player1 = new Player(0, 14, 9, 66, 100, Color.RED);


        System.out.println("Created new player1 - ID: " + player1.ID + " - X: " + player1.xPos + " - Y: " + player1.yPos + " Empty Inventory Slots: " + player1.playerInventory.itemArray.length);

    }

    private void generateNpc(int setID, int setxPos, int setyPos, float setHP, Color setColor, String setAi) {


        Npc n = new Npc(setID, setxPos, setyPos, setHP, setColor, setAi);

        System.out.println("Created new " + setAi + " - ID: " + n.ID + " - X: " + n.xPos + " - Y: " + n.yPos);


        currentOverWorld.npcList.addElement(n);             // works just like generate player but adds generated Npc to currentOverWorld.npclist.


    }

    private void removeNpc(int ID) {

        int i;

        currentOverWorld.npcList.removeElementAt(ID);   // deletes selected npc.

        for (i = ID; i < currentOverWorld.npcList.size(); i++) {                  // fixes ID/index of remaining npc's in npclist.
            currentNpc = currentOverWorld.npcList.elementAt(i);
            currentNpc.ID--;

        }

    }

    private void populateWorld() {
        int r;
        int counter;
        int pop = 4;            // amount of npc's generated per Overworld.
        int x;                  // position.
        int y;                  // position
        Color color;            // npc color.
        String type;            // ai type.


        for (counter = 0; counter < pop; counter++) {      // run as many times as population allows.

            r = rotateRng();
            x = rotateRng() % 29 + 1;     // generates RNG value between 1 and 30
            y = rotateRng() % 21 + 1;     // generates RNG value bet
            // ween 1 and 22.   ( edge protection. )

            if (r < 50) {            // cloin flip between Sheep and Chaser npc.
                type = "SHEEP";
                color = Color.yellow;

            } else {
                type = "CHASER";
                color = Color.black;
            }


            generateNpc(counter, x, y, 50, color, type);         // creates npc with RNG generated values as attributes.

        }


    }


    @Override
    public void paintComponent(Graphics g) {            // paints and controls what is currently painted on screen.

        super.paintComponent(g);


        if (startMenuVisible) {
            paintStartMenu(g);
        }
        if (mapVisible) {
            paintTilesLayer0(g);
            paintTilesLayer1(g);
            if (raining) {
                paintRain(g);
                if (!bufferSplashAnimations.isEmpty()){
                    paintSplash(g);
                }
            }

        }

        if (debugMenuVisible) {
            paintTileCoordinates(g);
            paintTileLines(g);
            paintDebugMenu(g);
        }

        if (inventoryMenuVisible) {
            paintInventory(g);
        }

        if (craftingMenuVisible) {
            paintCraftingMenu(g);
        }
        if (currentTile != null) {
            paintCurrentlySelectedTileHighlights(g);
        }
        if (currentItem != null) {
            paintCurrentlySelectedItemHighlights(g);
            paintCurrentlySelectedItemOnMouse(g);
        }


    }

    private void paintSplash(Graphics g) {

        Point p = bufferSplashAnimations.pollFirst();

      /*  for(int i = 0; i < 100; i++){
            g.fillOval(p.x,p.y,i,i);
        }
        */

       // System.out.println("Paintdrop Destroyed @" +p.x + ", " + p.y);
    }

    private void paintCurrentlySelectedItemOnMouse(Graphics g) {
        if (currentItem.ID == 1) {
            g.drawImage(bufferedImageMap.get("INVENTORY_LUMBER"), mouseDragX, mouseDragY, 20, 20, this);
        }
        if (currentItem.ID == 4) {
            g.drawImage(bufferedImageMap.get("PLANK_WALL"), mouseDragX, mouseDragY, 20, 20, this);
        }
    }

    private void paintCraftingMenu(Graphics g) {


        g.setColor(Color.lightGray);
        g.fillRect(25, 125, 200, 200);

        g.setColor(Color.white);
        g.fillRect(34, 149, 90, 90);
        g.fillRect(157, 183, 30, 30);

        g.setColor(Color.black);
        g.fillRect(151,233,40,40);

        g.setFont(font2);
        g.drawString("Crafting", 34, 142);
        int counter = 0;
        int row = 0;

        for (int i = 0; i < player1.playerCrafter.itemArray.length - 1; i++) {

            if (counter == 3) {
                counter = 0;
                row++;
            }

            g.drawRect(34 + (counter * 30), 149 + (row * 30), 30, 30);


            if (player1.playerCrafter.itemArray[i].ID == 1) {
                g.drawImage(bufferedImageMap.get("INVENTORY_LUMBER"), 34 + (counter * 30), 149 + (row * 30), 25, 22, this);
            }

            counter++;
        }



        g.drawRect(157, 183, 30, 30);
        g.setColor(Color.white);
        g.setFont(font2);
        g.drawString("CRAFT",153,247);

        if (player1.playerCrafter.itemArray[9].ID == 4){
            g.drawImage(bufferedImageMap.get("PLANK_WALL"),157,183,30,30,this);
        }
    }


    private void paintTilesLayer0(Graphics g) { // Tile Rendering System

        assert bufferedImageMap != null : "ERROR: bufferedImageMap is null";


        for (int i = 0; i < 32; i++) { // foreach tile outer loop
            for (int j = 0; j < 24; j++) { // foreach tile inner loop

                String tileTypeToPaint = currentOverWorld.tilemap[i][j].type; // store tile type as string
                switch (tileTypeToPaint) { // Rendering unit for each tile type
                    case "grass":
                        g.setColor(Color.green);
                        g.fillRect(i * 25, j * 25, 25, 25);
                        g.drawImage(bufferedImageMap.get("GRASS"), i * 25, j * 25, 25, 25, this);     // draws a grass on top of each "grass" ti
                        break;
                    case "water":
                        g.setColor(Color.blue);
                        g.fillRect(i * 25, j * 25, 25, 25);
                        break;
                    case "wood":
                        g.setColor(Color.green);
                        g.fillRect(i * 25, j * 25, 25, 25);
                        g.drawImage(bufferedImageMap.get("GRASS"), i * 25, j * 25, 25, 25, this);     // draws a grass
                        g.drawImage(bufferedImageMap.get("TREE"), i * 25 - 19, j * 25 - 80, 65, 100, this);     // draws a tree
                        break;
                    case "stone":
                        g.setColor(Color.green);
                        g.fillRect(i * 25, j * 25, 25, 25);
                        g.drawImage(bufferedImageMap.get("GRASS"), i * 25, j * 25, 25, 25, this);     // draws a grass
                        g.drawImage(bufferedImageMap.get("STONE"), i * 25 - 5, j * 25 - 10, 40, 40, this);     // draws a tree
                        break;
                    case "sand":
                        g.setColor(Color.orange);
                        g.fillRect(i * 25, j * 25, 25, 25);
                        break;
                    case "rakedDirt":
                        g.setColor(new Color(100, 40, 19));
                        g.fillRect(i * 25, j * 25, 25, 25);
                        g.drawImage(bufferedImageMap.get("RAKED_DIRT"), i * 25, j * 25, 25, 25, this);

                        break;
                    case "dirt":
                        g.setColor(new Color(100, 80, 30));
                        g.fillRect(i * 25, j * 25, 25, 25);
                        g.drawImage(bufferedImageMap.get("DIRT"), i * 25, j * 25, 25, 25, this);
                        break;
                    case "plankWall":
                        g.drawImage(bufferedImageMap.get("GRASS"), i * 25, j * 25, 25, 25, this);     // draws a grass
                        g.drawImage(bufferedImageMap.get("PLANK_WALL"), i * 25, j * 25, 25, 25, this);
                        break;

                    default:
                        g.setColor(Color.red);
                        g.drawString("ERR", i * 25, j * 25 + 25);
                        break;
                }

            }

        }
    }

    private void paintTilesLayer1(Graphics g) {

        assert bufferedImageMap != null : "ERROR: bufferedImageMap is null";


        for (int j = 0; j < 24; j++) { // foreach tile outer loop
            for (int i = 0; i < 32; i++) { // foreach tile inner loop

                String tileTypeToPaint = currentOverWorld.tilemap[i][j].type; // store tile type as string
                switch (tileTypeToPaint) { // Rendering unit for each tile type
                    case "wood":
                        g.drawImage(bufferedImageMap.get("TREE"), i * 25 - 19, j * 25 - 80, 65, 100, this);     // draws a tree
                        break;
                    case "stone":
                        g.drawImage(bufferedImageMap.get("STONE"), i * 25 - 5, j * 25 - 10, 40, 40, this);     // draws a tree
                        break;
                }


                if (j == player1.yPos / 25 && i == player1.xPos / 25){
                    paintPlayer(g);
                }

                for (Npc n: currentOverWorld.npcList){
                    if (j == n.yPos / 25 && i == n.xPos / 25){
                        paintNpcs(g,n);

                    }
                }



            }

        }
    }

    private void paintRain(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(2));


        g.setColor(Color.blue);
        for (Point p : rainDrops) {
            g2d.drawLine(p.x, p.y, p.x + 10, p.y + 10);
        }
        moveRain();
    }

    private void moveRain() {

        for (int i = 0; i < rainDrops.length; i++) {
            int gravity = (rotateRng() % (12));
            rainDrops[i].x += gravity;
            rainDrops[i].y += gravity;
        }

        for (int i = 0; i < rainDrops.length; i++) {
            int wind = (rotateRng() % (6));
            rainDrops[i].x += wind * windDirection;
        }

        destroyRandomRaindrops();
        replaceOutOfScreenRain();
    }

    private void destroyRandomRaindrops() {

        int rng = (int) (Math.random() * (5000));
        if (rng > 4990){
            windDirection = -windDirection;
            System.out.println(windDirection);
        }
        if (rng > 2500) {
            int rainDropIndexToDestroy = (int) (Math.random() * (rainDrops.length));

            bufferSplashAnimations.offerFirst(new Point(rainDrops[rainDropIndexToDestroy].x,rainDrops[rainDropIndexToDestroy].y));
            rainDrops[rainDropIndexToDestroy].x = (int) (Math.random() * (Main.WIDTH));
            rainDrops[rainDropIndexToDestroy].y = (int) (Math.random() * (Main.WIDTH));
        }
    }

    private void replaceOutOfScreenRain() {

        for (int i = 0; i < rainDrops.length; i++) {
            if (rainDrops[i].x > Main.WIDTH || rainDrops[i].y > Main.HEIGHT) {
                rainDrops[i].x = (int) (Math.random() * (Main.WIDTH));
                rainDrops[i].y = (int) (Math.random() * (Main.HEIGHT));
            }
        }
    }


    private void paintCurrentlySelectedItemHighlights(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke(2));

        if (inventoryMenuVisible && currentItem != null && currentItem.equals(player1.playerCrafter.itemArray[9])){
            g2d.drawRect(156,183,30,30);
        }
        else if (inventoryMenuVisible && currentItem != null) {
            g2d.drawRect(587 + ((currentItemColumn - 1) * 30), 176 + ((currentItemRow - 1) * 30), 30, 30);
        }

    }

    private void paintCurrentlySelectedTileHighlights(Graphics g) {


        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(currentTileX * 25, currentTileY * 25, 25, 25);
    }

    private void paintInventory(Graphics g) {

        g.setColor(Color.lightGray);
        g.fillRect(575, 149, 200, 600);
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
                //              g.setColor(new Color(138, 69, 19));
//                g.fillOval(593 + (counter * 30), 183 + (row * 30), 20, 20);
                g.drawImage(bufferedImageMap.get("INVENTORY_LUMBER"), 593 + (counter * 30) - 5, 183 + (row * 30) - 5, 25, 25, this);

            }

            if (player1.playerInventory.itemArray[i].ID == 2) {
                g.setColor(Color.gray);
                g.fillOval(593 + (counter * 30), 183 + (row * 30), 20, 20);
            }

            if (player1.playerInventory.itemArray[i].ID == 4) {

                g.drawImage(bufferedImageMap.get("PLANK_WALL"),593 + (counter * 30), 183 + (row * 30), 20, 20,this);
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
        g.drawString("player1 standing on tile: " + currentOverWorld.tilemap[player1_TileCoordinated_xPos][player1_TileCoordinated_yPos].type, 37, 515);
        g.drawString("Farmable? " + (currentOverWorld.tilemap[player1_TileCoordinated_xPos][player1_TileCoordinated_yPos].farmable ? "yes" : "no"), 88, 532);


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

    private void paintNpcs(Graphics g, Npc n) {





            if (n.ai.equals("SHEEP")) {

                int xOffset = 0;
                int yOffset = 0;

                if (n.orientation.equals("NORTH")) {

                    xOffset = 4;
                    yOffset = 6;

                } else if (n.orientation.equals("SOUTH")) {


                    xOffset = 5;
                    yOffset = 6;

                } else if (n.orientation.equals("WEST")) {


                    xOffset = 6;
                    yOffset = 5;

                } else if (n.orientation.equals("EAST")) {


                    xOffset = 4;
                    yOffset = 3;

                }

                g.drawImage(bufferedImageMap.get(n.orientation+"_"+n.ai), n.xPos - xOffset, n.yPos - yOffset, 30, 30, this);

            } else if (n.ai.equals("CHASER")) {

                int xOffset = 0;
                int yOffset = 0;

                if (n.orientation.equals("NORTH")) {

                    xOffset = 4;
                    yOffset = 20;

                } else if (n.orientation.equals("SOUTH")) {

                    xOffset = 5;
                    yOffset = 20;

                } else if (n.orientation.equals("WEST")) {


                    xOffset = 6;
                    yOffset = 19;

                } else if (n.orientation.equals("EAST")) {


                    xOffset = 4;
                    yOffset = 19;

                }

                g.drawImage(bufferedImageMap.get(n.orientation+"_"+n.ai), n.xPos - xOffset, n.yPos - yOffset, 30, 45, this);

            }



    }

    private void paintPlayer(Graphics g) {

        assert bufferedImageMap != null : "ERROR: bufferedImageMap is null";
        // paintOrientationArrow(g);
        switch (player1.orientation) {

            case "NORTH":
                g.drawImage(bufferedImageMap.get("NORTH_ADVENTURER"), player1.xPos - 4, player1.yPos - 20, 22, 40, this);
                break;
            case "SOUTH":
                g.drawImage(bufferedImageMap.get("SOUTH_ADVENTURER"), player1.xPos - 3, player1.yPos - 20, 22, 40, this);
                break;
            case "EAST":
                g.drawImage(bufferedImageMap.get("EAST_ADVENTURER"), player1.xPos - 3, player1.yPos  -20, 22, 40, this);
                break;
            case "WEST":
                g.drawImage(bufferedImageMap.get("WEST_ADVENTURER"), player1.xPos - 3, player1.yPos - 20, 22, 40, this);
                break;
            default:
                g.setColor(player1.pallete);
                g.fillOval(player1.xPos, player1.yPos, 20, 20);
                break;
        }

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


    private void npcBehaviour() {


        int r;


        for (Npc n : currentOverWorld.npcList) {   // iterater through current Overworld npc list.


            int counter;

            switch (n.ai) {         // reads ai type from each Npc.


                case "SHEEP":   // Sheep ai. moves every 5 actions. random walks through passable tiles. wont leave edge of map.

                    counter = (actionTick % 5);

                    if (counter == 0) {


                        r = rotateRng();

                        if (r <= 25) {

                            if (n.yPos / 25 != 22) {
                                if (!currentOverWorld.tilemap[n.xPos / 25][n.yPos / 25 + 1].occupied) {
                                    n.yPos += movementSpeed;
                                    n.orientation = "SOUTH";
                                }
                            }

                        } else if (r > 25 && r < 50) {
                            if (n.yPos / 25 != 1) {
                                if (!currentOverWorld.tilemap[n.xPos / 25][n.yPos / 25 - 1].occupied) {
                                    n.yPos -= movementSpeed;
                                    n.orientation = "NORTH";
                                }
                            }


                        } else if (r > 50 && r < 75) {
                            if (n.xPos / 25 != 31) {
                                if (!currentOverWorld.tilemap[n.xPos / 25 + 1][n.yPos / 25].occupied) {
                                    n.xPos += movementSpeed;
                                    n.orientation = "EAST";
                                }
                            }


                        } else if (r >= 75) {
                            if (n.xPos / 25 != 1) {
                                if (!currentOverWorld.tilemap[n.xPos / 25 - 1][n.yPos / 25].occupied) {
                                    n.xPos -= movementSpeed;
                                    n.orientation = "WEST";
                                }
                            }


                        }

                    }

                    r = rotateRng();

                    if (r > 98) {
                        currentOverWorld.tilemap[n.xPos / 25][n.yPos / 25].type = "dirt";
                        currentOverWorld.tilemap[n.xPos / 25][n.yPos / 25].growth = 0;
                    }
                    break;

                case "CHASER":      // Chaser ai. moves every 4 actions. compares own position to player's and attempt to equalize y and x coordinates. Won't leave map.

                    counter = (actionTick % 4);

                    if (counter == 0) {

                        if (player1.yPos / 25 < n.yPos / 25) {

                            if (n.yPos / 25 != 1) {
                                if (!currentOverWorld.tilemap[n.xPos / 25][n.yPos / 25 - 1].occupied) {
                                    n.yPos -= movementSpeed;
                                    n.orientation = "NORTH";
                                } else if (n.yPos / 25 - 1 == player1.yPos / 25 && n.xPos / 25 == player1.xPos / 25) {
                                    System.out.println("the chaser hits you for 10 damage");
                                    player1.HP = player1.HP - 10;

                                    if (player1.HP < 0) {
                                        System.out.println("GAME OVER, resseting HP !!!!!");
                                        player1.HP = 100F;
                                    }

                                }
                            }

                        } else if (player1.yPos / 25 > n.yPos / 25) {

                            if (n.yPos / 25 != 22) {
                                if (!currentOverWorld.tilemap[n.xPos / 25][n.yPos / 25 + 1].occupied) {
                                    n.yPos += movementSpeed;
                                    n.orientation = "SOUTH";
                                } else if (n.yPos / 25 + 1 == player1.yPos / 25 && n.xPos / 25 == player1.xPos / 25) {
                                    System.out.println("the chaser hits you for 10 damage");
                                    player1.HP = player1.HP - 10;

                                    if (player1.HP < 0) {
                                        System.out.println("GAME OVER, resseting HP !!!!!");
                                        player1.HP = 100F;
                                    }

                                }
                            }


                        }

                        if (player1.xPos / 25 < n.xPos / 25) {

                            if (n.xPos / 25 != 1) {
                                if (!currentOverWorld.tilemap[n.xPos / 25 - 1][n.yPos / 25].occupied) {
                                    n.xPos -= movementSpeed;
                                    n.orientation = "WEST";
                                }
                            } else if (n.xPos / 25 - 1 == player1.xPos / 25 && n.yPos / 25 == player1.yPos / 25) {
                                System.out.println("the chaser hits you for 10 damage");
                                player1.HP = player1.HP - 10;

                                if (player1.HP < 0) {
                                    System.out.println("GAME OVER, resseting HP !!!!!");
                                    player1.HP = 100F;
                                }

                            }

                        } else if (player1.xPos / 25 > n.xPos / 25) {

                            if (n.xPos / 25 != 22) {
                                if (!currentOverWorld.tilemap[n.xPos / 25 + 1][n.yPos / 25].occupied) {
                                    n.xPos += movementSpeed;
                                    n.orientation = "EAST";
                                } else if (n.xPos / 25 + 1 == player1.xPos / 25 && n.yPos / 25 == player1.yPos / 25) {
                                    System.out.println("the chaser hits you for 10 damage");
                                    player1.HP = player1.HP - 10;

                                    if (player1.HP < 0) {
                                        System.out.println("GAME OVER, resseting HP !!!!!");
                                        player1.HP = 100F;
                                    }

                                }
                            }


                        }
                    }

                    break;

            }
            collisionMeshGenerator(); // Re-generates collision mesh after each Npc takes action.
        }
    }

    private void mapChange(int direction) {      // controls the change of map as player reaches map edge.


        if (direction == 0) {                 // 0=up. 1=right 2=down 3=left

            if (currentOverWorld.idY == worldSize - 1)      // checks for top edge of Overworld.
            {
                currentOverWorld = overWorld[currentOverWorld.idX][0];      // load bottom edge of Overworld. ( world is currently round. )
                player1.yPos = 22 * 25;                                      // sets player y coordinate to bottom edge of tilemap

            } else {

                player1.yPos = 22 * 25;
                currentOverWorld = overWorld[currentOverWorld.idX][currentOverWorld.idY + 1];  // Otherwise loads next Overworld up.
            }

        } else if (direction == 1) {
            if (currentOverWorld.idX == worldSize - 1)       // same concept for every direction.
            {
                currentOverWorld = overWorld[0][currentOverWorld.idY];
                player1.xPos = 1 * 25;
            } else {
                player1.xPos = 1 * 25;
                currentOverWorld = overWorld[currentOverWorld.idX + 1][currentOverWorld.idY];
            }
        } else if (direction == 2) {
            if (currentOverWorld.idY == 0) {
                player1.yPos = 1 * 25;
                currentOverWorld = overWorld[currentOverWorld.idX][worldSize - 1];
            } else {

                player1.yPos = 1 * 25;
                currentOverWorld = overWorld[currentOverWorld.idX][currentOverWorld.idY - 1];
            }
        } else if (direction == 3) {
            if (currentOverWorld.idX == 0) {
                currentOverWorld = overWorld[worldSize - 1][currentOverWorld.idY];
                player1.xPos = 30 * 25;
            } else {

                player1.xPos = 30 * 25;
                currentOverWorld = overWorld[currentOverWorld.idX - 1][currentOverWorld.idY];
            }
        }

        System.out.println("Overworld" + currentOverWorld.idX + +currentOverWorld.idY + " loaded");
    }


    private void tick() {
        actionTick++;               // ticks action counter and runs any subroutines that should run for each tick.

        npcBehaviour();
        naturalProcesses();
        collisionMeshGenerator();


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer && paint()) {
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

                currentTile = null;

                if (startMenuVisible) {
                    startMenuVisible = false;               // this is how the menu hides other windows.
                    mapVisible = true;

                    break;
                } else {
                    startMenuVisible = true;

                    mapVisible = false;
                    inventoryMenuVisible = false;
                    debugMenuVisible = false;
                    craftingMenuVisible = false;

                    break;
                }
            case KeyEvent.VK_9:

                readWorld(1, 1);    // loads world11.
                System.out.println(currentOverWorld.idX + currentOverWorld.idY);

                break;
            case KeyEvent.VK_C:

                craftingMenuVisible = !craftingMenuVisible;

                break;

            case KeyEvent.VK_UP: // User presses the up key

                loadMovementSound();

                player1.orientation = "NORTH"; // set the player1 orientation state to "NORTH"

                if (!currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25) - 1].occupied) {


                    if (player1.yPos / 25 != 1) {
                        player1.yPos -= movementSpeed; //update ypos
                        currentOverWorld.tilemap[player1.xPos / 25][player1.yPos / 25].occupied = true; // set's player position to occupied.
                        // ( needed for npc actions that might occur before a new collision mesh is generated)
                    } else {
                        mapChange(0);       // edge detection and map scrolling.
                    }


                }
                break;
            case KeyEvent.VK_DOWN: // Tries to move down

                loadMovementSound();
                player1.orientation = "SOUTH";

                if (!currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25) + 1].occupied) {


                    if (player1.yPos / 25 != 22) {
                        player1.yPos += movementSpeed; //update ypos
                        currentOverWorld.tilemap[player1.xPos / 25][player1.yPos / 25].occupied = true;
                    } else {
                        mapChange(2);       // edge detection.
                    }

                }
                break;
            case KeyEvent.VK_LEFT: // Tries to move left
                loadMovementSound();

                player1.orientation = "WEST";

                if (!currentOverWorld.tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].occupied) {


                    if (player1.xPos / 25 != 1) {
                        player1.xPos -= movementSpeed; //update ypos
                        currentOverWorld.tilemap[player1.xPos / 25][player1.yPos / 25].occupied = true;
                    } else {
                        mapChange(3);          // edge detection.
                    }


                }
                break;
            case KeyEvent.VK_RIGHT: // Tries to move right
                loadMovementSound();

                player1.orientation = "EAST";

                if (!currentOverWorld.tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].occupied) {


                    if (player1.xPos / 25 != 30) {
                        player1.xPos += movementSpeed; //update ypos
                        currentOverWorld.tilemap[player1.xPos / 25][player1.yPos / 25].occupied = true;
                    } else {
                        mapChange(1);
                    }


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
                    System.out.println(printTileSet(currentOverWorld.tilemap));
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
                    if (player1.orientation.equals("EAST") && currentOverWorld.tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type.equals("wood")) {
                        currentOverWorld.tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type = "grass";
                        harvestedItem = "lumber";

                        harvestedSuccessfully = true;

                    }
                    if (player1.orientation.equals("EAST") && currentOverWorld.tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type.equals("stone")) {
                        currentOverWorld.tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type = "dirt";
                        harvestedItem = "cobblestone";
                        currentOverWorld.tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].farmable = true;
                        harvestedSuccessfully = true;
                    }


                    if (player1.orientation.equals("WEST") && currentOverWorld.tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type.equals("wood")) {
                        currentOverWorld.tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type = "grass";
                        harvestedItem = "lumber";
                        harvestedSuccessfully = true;

                    }
                    if (player1.orientation.equals("WEST") && currentOverWorld.tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type.equals("stone")) {
                        currentOverWorld.tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type = "dirt";
                        harvestedItem = "cobblestone";
                        currentOverWorld.tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].farmable = true;
                        harvestedSuccessfully = true;

                    }
                    if (player1.orientation.equals("NORTH") && currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].type.equals("wood")) {
                        currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].type = "grass";
                        harvestedSuccessfully = true;
                        harvestedItem = "lumber";
                    }
                    if (player1.orientation.equals("NORTH") && currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].type.equals("stone")) {
                        currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].type = "dirt";
                        currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].farmable = true;
                        harvestedSuccessfully = true;
                        harvestedItem = "cobblestone";
                    }
                    if (player1.orientation.equals("SOUTH") && currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].type.equals("wood")) {
                        currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].type = "grass";
                        harvestedItem = "lumber";
                        harvestedSuccessfully = true;
                    }
                    if (player1.orientation.equals("SOUTH") && currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].type.equals("stone")) {
                        currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].type = "dirt";
                        harvestedItem = "cobblestone";
                        currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].farmable = true;
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
            ITEM PLACEMENT
             */


            case KeyEvent.VK_2:

                if (currentItem != null && currentItem.ID == 2) {
                    if (player1.orientation.equals("NORTH") && !currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].occupied) {
                        currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].type = "stone";
                        player1.playerInventory.itemArray[currentItemIndex].ID = 0;
                        tick();

                    } else if (player1.orientation.equals("EAST") && !currentOverWorld.tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].occupied) {
                        currentOverWorld.tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type = "stone";
                        player1.playerInventory.itemArray[currentItemIndex].ID = 0;
                        tick();

                    }

                    if (player1.orientation.equals("SOUTH") && !currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].occupied) {
                        currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].type = "stone";
                        player1.playerInventory.itemArray[currentItemIndex].ID = 0;
                        tick();

                    } else if (player1.orientation.equals("WEST") && !currentOverWorld.tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].occupied) {
                        currentOverWorld.tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type = "stone";
                        player1.playerInventory.itemArray[currentItemIndex].ID = 0;
                        tick();
                    }

                } else if (currentItem != null && currentItem.ID == 4) {
                    if (player1.orientation.equals("NORTH") && !currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].occupied) {
                        currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 - 1)].type = "plankWall";
                        player1.playerInventory.itemArray[currentItemIndex].ID = 0;
                        tick();

                    } else if (player1.orientation.equals("EAST") && !currentOverWorld.tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].occupied) {
                        currentOverWorld.tilemap[player1.xPos / 25 + 1][(player1.yPos / 25)].type = "plankWall";
                        player1.playerInventory.itemArray[currentItemIndex].ID = 0;
                        tick();

                    }

                    if (player1.orientation.equals("SOUTH") && !currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].occupied) {
                        currentOverWorld.tilemap[player1.xPos / 25][(player1.yPos / 25 + 1)].type = "plankWall";
                        player1.playerInventory.itemArray[currentItemIndex].ID = 0;
                        tick();

                    } else if (player1.orientation.equals("WEST") && !currentOverWorld.tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].occupied) {
                        currentOverWorld.tilemap[player1.xPos / 25 - 1][(player1.yPos / 25)].type = "plankWall";
                        player1.playerInventory.itemArray[currentItemIndex].ID = 0;
                        tick();

                    }

                }

                break;

            case KeyEvent.VK_R:
                raining = !raining;
                break;

            case KeyEvent.VK_K:
               windDirection = -windDirection;
                break;

            case KeyEvent.VK_F:

                System.out.println("F- fighting");

                boolean engagedSuccessfully = false; // flag to determine real-time whether the key press triggers a successful harvest action
                String harvestedItem = "";
                if (player1.orientation.equals("EAST")) {

                    for (Npc n : currentOverWorld.npcList) {
                        if (player1.xPos / 25 + 1 == n.xPos / 25 && player1.yPos / 25 == n.yPos / 25) {
                            currentNpc = n;
                            engagedSuccessfully = true;
                        }


                    }


                }
                if (player1.orientation.equals("WEST")) {

                    for (Npc n : currentOverWorld.npcList) {
                        if (player1.xPos / 25 - 1 == n.xPos / 25 && player1.yPos / 25 == n.yPos / 25) {
                            currentNpc = n;
                            engagedSuccessfully = true;
                        }


                    }


                }
                if (player1.orientation.equals("NORTH")) {

                    for (Npc n : currentOverWorld.npcList) {
                        if (player1.yPos / 25 - 1 == n.yPos / 25 && player1.xPos / 25 == n.xPos / 25) {
                            currentNpc = n;
                            engagedSuccessfully = true;
                        }


                    }


                }
                if (player1.orientation.equals("SOUTH")) {

                    for (Npc n : currentOverWorld.npcList) {
                        if (player1.yPos / 25 + 1 == n.yPos / 25 && player1.xPos / 25 == n.xPos / 25) {
                            currentNpc = n;

                            engagedSuccessfully = true;
                        }


                    }

                }

                System.out.println(engagedSuccessfully);
                if (engagedSuccessfully) { // Iff the tile is flagged to be successfully harvested, find an empty slot and fill it with a given item.
                    System.out.println("engaged");


                    System.out.println("you hit the " + currentNpc.ai + " for 20 damage");
                    currentNpc.HP = currentNpc.HP - 20;
                    tick();

                    if (currentNpc.HP < 0) {
                        System.out.println("the " + currentNpc.ai + " dies");
                        removeNpc(currentNpc.ID);

                    }


                }


                break;

            case KeyEvent.VK_I:

                currentTile = null;

                if (!startMenuVisible) {
                    inventoryMenuVisible = !inventoryMenuVisible;
                    System.out.println(player1.playerInventory);
                    System.out.println("Inventory is full: " + player1.playerInventory.isFull());
                    break;
                }

            case KeyEvent.VK_L:
                currentOverWorld.npcList = new Vector<>();    // overwrites current Overworld npclist with an empty one.
                break;

            case KeyEvent.VK_W:
                tick();
                break;

            case KeyEvent.VK_3 :

                String nameR;

                nameR = getUserInput();
                readCustomWorld(nameR);
                break;

            case KeyEvent.VK_4 :

                String nameW;

                nameW = getUserInput();
                saveCustomWorld(nameW);
                break;

            case KeyEvent.VK_5 :

                reloadOverWorld();

                break;

            case KeyEvent.VK_ESCAPE:
                currentItem = null;
                currentTile = null;
                currentTileX = 0;
                currentTileY = 0;
                break;

        }
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            tick();
        }
    }

    private void loadMovementSound() {
        File blop = new File("Data/Sound/Blop.wav");

        try {
            audioInputStream = AudioSystem.getAudioInputStream(blop);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

        try {
            movementSound = AudioSystem.getClip();
            movementSound.open(audioInputStream);

            movementSound.start();

        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        requestFocusInWindow();

        int x = e.getX();
        int y = e.getY();

        System.out.println("==CLICK==");
        System.out.println(x + ", " + y);
        System.out.println("" + (x / 25) + ", " + (y / 25));
        System.out.println("=========");

        if (e.getButton() == MouseEvent.BUTTON3) { // ON LEFT MOUSE CLICK
            currentItem = null;
            currentItemIndex = 0;
            currentItemColumn = 0;
            currentItemRow = 0;
            System.out.println("MouseEvent.BUTTON3");
        } else if (e.getButton() == MouseEvent.BUTTON1) { // ON RIGHT MOUSE CLICK

            if (inventoryMenuVisible && x > 151 && x < 151 + 40 && y > 233 && y < 233+40){


                for (int i = 0; i < 64; i++) {
                    if (player1.playerInventory.itemArray[i].ID == 0) {
                            player1.playerInventory.itemArray[i].ID = player1.playerCrafter.itemArray[9].ID;
                            break;
                        }

                    }
                player1.playerCrafter = new CraftingInterface(10);
                }


            }
            if (craftingMenuVisible && currentItem != null) {
                putCurrentItemIntoCraftingInterface(x, y);
            }

            if (!inventoryMenuVisible) {
                currentTile = onMouseClickSelectTile(x, y);
            }
            if (inventoryMenuVisible) {
                currentItem = onMouseClickSelectItem(x, y);
            }
        }





    private void putCurrentItemIntoCraftingInterface(int x, int y) {

        int craftingSlotIndex = -1;

        if (x > 34 && x < 34 + 30 && y > 149 && y < 149 + 30) {
            craftingSlotIndex = 0;
        } else if (x > 64 && x < 64 + 30 && y > 149 && y < 149 + 30) {
            craftingSlotIndex = 1;
        } else if (x > 94 && x < 94 + 30 && y > 149 && y < 149 + 30) {
            craftingSlotIndex = 2;
        } else if (x > 34 && x < 34 + 30 && y > 179 && y < 179 + 30) {
            craftingSlotIndex = 3;
        } else if (x > 64 && x < 64 + 30 && y > 179 && y < 179 + 30) {
            craftingSlotIndex = 4;
        } else if (x > 94 && x < 94 + 30 && y > 179 && y < 179 + 30) {
            craftingSlotIndex = 5;
        } else if (x > 34 && x < 34 + 30 && y > 209 && y < 209 + 30) {
            craftingSlotIndex = 6;
        } else if (x > 64 && x < 64 + 30 && y > 209 && y < 209 + 30) {
            craftingSlotIndex = 7;
        } else if (x > 94 && x < 94 + 30 && y > 209 && y < 209 + 30) {
            craftingSlotIndex = 8;
        }

        if (craftingSlotIndex != -1) {
            player1.playerCrafter.itemArray[craftingSlotIndex].ID = currentItem.ID;
            currentItem.ID = 0;
            currentItemIndex = 0;
            currentItemRow = 0;
            currentItemColumn = 0;
        }

        updateCrafterOutputSlot();

    }

    private void updateCrafterOutputSlot() {

        if (player1.playerCrafter.itemArray[0].ID == 1 &&
                player1.playerCrafter.itemArray[1].ID == 1 &&
                player1.playerCrafter.itemArray[2].ID == 1) {
            player1.playerCrafter.itemArray[9].ID = 4;
        } else {
            player1.playerCrafter.itemArray[9].ID = 0;

        }
    }

    private Item onMouseClickSelectItem(int x, int y) {

        currentItem = null;
        currentItemIndex = -1;
        currentItemColumn = -1;
        currentItemRow = -1;

        if (inRange(x, 587, 617, true)) {

            currentItemColumn = 1;
            System.out.println(currentItemColumn);

            if (inRange(y, 176, 206, true)) {


                currentItemRow = 1;
                currentItemIndex = 0;
                System.out.println(currentItemRow);

            } else if (inRange(y, 207, 237, true)) {

                currentItemRow = 2;
                currentItemIndex = 6;
                System.out.println(currentItemRow);
            } else if (inRange(y, 238, 268, true)) {

                currentItemRow = 3;
                currentItemIndex = 12;
                System.out.println(currentItemRow);

            } else if (inRange(y, 269, 299, true)) {

                currentItemRow = 4;
                currentItemIndex = 18;
                System.out.println(currentItemRow);

            } else if (inRange(y, 300, 330, true)) {

                currentItemRow = 5;
                currentItemIndex = 24;
                System.out.println(currentItemRow);

            } else if (inRange(y, 331, 361, true)) {

                currentItemRow = 6;
                currentItemIndex = 30;
                System.out.println(currentItemRow);

            } else if (inRange(y, 362, 392, true)) {

                currentItemRow = 7;
                currentItemIndex = 36;
                System.out.println(currentItemRow);

            } else if (inRange(y, 393, 423, true)) {

                currentItemRow = 8;
                currentItemIndex = 42;
                System.out.println(currentItemRow);

            } else if (inRange(y, 424, 454, true)) {

                currentItemRow = 9;
                currentItemIndex = 48;
                System.out.println(currentItemRow);

            } else if (inRange(y, 455, 485, true)) {
                currentItemRow = 10;
                currentItemIndex = 54;
                System.out.println(currentItemRow);

            } else if (inRange(y, 486, 516, true)) {

                currentItemRow = 11;
                currentItemIndex = 60;
                System.out.println(currentItemRow);

            }

        } else if (inRange(x, 618, 648, true)) {

            currentItemColumn = 2;
            System.out.println(currentItemColumn);

            if (inRange(y, 176, 206, true)) {


                currentItemRow = 1;
                currentItemIndex = 1;
                System.out.println(currentItemRow);

            } else if (inRange(y, 207, 237, true)) {

                currentItemRow = 2;
                currentItemIndex = 7;
                System.out.println(currentItemRow);
            } else if (inRange(y, 238, 268, true)) {

                currentItemRow = 3;
                currentItemIndex = 13;
                System.out.println(currentItemRow);

            } else if (inRange(y, 269, 299, true)) {

                currentItemRow = 4;
                currentItemIndex = 19;
                System.out.println(currentItemRow);

            } else if (inRange(y, 300, 330, true)) {

                currentItemRow = 5;
                currentItemIndex = 25;
                System.out.println(currentItemRow);

            } else if (inRange(y, 331, 361, true)) {

                currentItemRow = 6;
                currentItemIndex = 31;
                System.out.println(currentItemRow);

            } else if (inRange(y, 362, 392, true)) {

                currentItemRow = 7;
                currentItemIndex = 37;
                System.out.println(currentItemRow);

            } else if (inRange(y, 393, 423, true)) {

                currentItemRow = 8;
                currentItemIndex = 43;
                System.out.println(currentItemRow);

            } else if (inRange(y, 424, 454, true)) {

                currentItemRow = 9;
                currentItemIndex = 49;
                System.out.println(currentItemRow);

            } else if (inRange(y, 455, 485, true)) {

                currentItemRow = 10;
                currentItemIndex = 55;
                System.out.println(currentItemRow);

            } else if (inRange(y, 486, 516, true)) {

                currentItemRow = 11;
                currentItemIndex = 61;
                System.out.println(currentItemRow);

            }

        } else if (inRange(x, 649, 679, true)) {

            currentItemColumn = 3;
            System.out.println(currentItemColumn);

            if (inRange(y, 176, 206, true)) {


                currentItemRow = 1;
                currentItemIndex = 2;
                System.out.println(currentItemRow);

            } else if (inRange(y, 207, 237, true)) {

                currentItemRow = 2;
                currentItemIndex = 8;
                System.out.println(currentItemRow);
            } else if (inRange(y, 238, 268, true)) {

                currentItemRow = 3;
                currentItemIndex = 14;
                System.out.println(currentItemRow);

            } else if (inRange(y, 269, 299, true)) {

                currentItemRow = 4;
                currentItemIndex = 20;
                System.out.println(currentItemRow);

            } else if (inRange(y, 300, 330, true)) {

                currentItemRow = 5;
                currentItemIndex = 26;
                System.out.println(currentItemRow);

            } else if (inRange(y, 331, 361, true)) {

                currentItemRow = 6;
                currentItemIndex = 32;
                System.out.println(currentItemRow);

            } else if (inRange(y, 362, 392, true)) {

                currentItemRow = 7;
                currentItemIndex = 38;
                System.out.println(currentItemRow);

            } else if (inRange(y, 393, 423, true)) {

                currentItemRow = 8;
                currentItemIndex = 44;
                System.out.println(currentItemRow);

            } else if (inRange(y, 424, 454, true)) {

                currentItemRow = 9;
                currentItemIndex = 50;
                System.out.println(currentItemRow);

            } else if (inRange(y, 455, 485, true)) {


                currentItemRow = 10;
                currentItemIndex = 56;
                System.out.println(currentItemRow);

            } else if (inRange(y, 486, 516, true)) {

                currentItemRow = 11;
                currentItemIndex = 62;
                System.out.println(currentItemRow);

            }

        } else if (inRange(x, 680, 710, true)) {

            currentItemColumn = 4;
            System.out.println(currentItemColumn);

            if (inRange(y, 176, 206, true)) {


                currentItemRow = 1;
                currentItemIndex = 3;
                System.out.println(currentItemRow);

            } else if (inRange(y, 207, 237, true)) {

                currentItemRow = 2;
                currentItemIndex = 9;
                System.out.println(currentItemRow);
            } else if (inRange(y, 238, 268, true)) {

                currentItemRow = 3;
                currentItemIndex = 15;
                System.out.println(currentItemRow);

            } else if (inRange(y, 269, 299, true)) {

                currentItemRow = 4;
                currentItemIndex = 21;
                System.out.println(currentItemRow);

            } else if (inRange(y, 300, 330, true)) {

                currentItemRow = 5;
                currentItemIndex = 27;
                System.out.println(currentItemRow);

            } else if (inRange(y, 331, 361, true)) {

                currentItemRow = 6;
                currentItemIndex = 33;
                System.out.println(currentItemRow);

            } else if (inRange(y, 362, 392, true)) {

                currentItemRow = 7;
                currentItemIndex = 39;
                System.out.println(currentItemRow);

            } else if (inRange(y, 393, 423, true)) {

                currentItemRow = 8;
                currentItemIndex = 45;
                System.out.println(currentItemRow);

            } else if (inRange(y, 424, 454, true)) {

                currentItemRow = 9;
                currentItemIndex = 51;
                System.out.println(currentItemRow);

            } else if (inRange(y, 455, 485, true)) {


                currentItemRow = 10;
                currentItemIndex = 57;
                System.out.println(currentItemRow);

            } else if (inRange(y, 486, 516, true)) {

                currentItemRow = 11;
                currentItemIndex = 63;
                System.out.println(currentItemRow);

            }
        } else if (inRange(x, 711, 741, true)) {

            currentItemColumn = 5;
            System.out.println(currentItemColumn);

            if (inRange(y, 176, 206, true)) {


                currentItemRow = 1;
                currentItemIndex = 4;
                System.out.println(currentItemRow);

            } else if (inRange(y, 207, 237, true)) {

                currentItemRow = 2;
                currentItemIndex = 10;
                System.out.println(currentItemRow);
            } else if (inRange(y, 238, 268, true)) {

                currentItemRow = 3;
                currentItemIndex = 16;
                System.out.println(currentItemRow);

            } else if (inRange(y, 269, 299, true)) {

                currentItemRow = 4;
                currentItemIndex = 22;
                System.out.println(currentItemRow);

            } else if (inRange(y, 300, 330, true)) {

                currentItemRow = 5;
                currentItemIndex = 28;
                System.out.println(currentItemRow);

            } else if (inRange(y, 331, 361, true)) {

                currentItemRow = 6;
                currentItemIndex = 34;
                System.out.println(currentItemRow);

            } else if (inRange(y, 362, 392, true)) {

                currentItemRow = 7;
                currentItemIndex = 40;
                System.out.println(currentItemRow);

            } else if (inRange(y, 393, 423, true)) {

                currentItemRow = 8;
                currentItemIndex = 46;
                System.out.println(currentItemRow);

            } else if (inRange(y, 424, 454, true)) {

                currentItemRow = 9;
                currentItemIndex = 52;
                System.out.println(currentItemRow);

            } else if (inRange(y, 455, 485, true)) {


                currentItemRow = 10;
                currentItemIndex = 58;
                System.out.println(currentItemRow);

            } else if (inRange(y, 486, 516, true)) {

                currentItemRow = 11;
                currentItemIndex = 64;
                System.out.println(currentItemRow);

            }


        } else if (inRange(x, 742, 772, true)) {

            currentItemColumn = 6;
            System.out.println(currentItemColumn);

            if (inRange(y, 176, 206, true)) {


                currentItemRow = 1;
                currentItemIndex = 5;
                System.out.println(currentItemRow);

            } else if (inRange(y, 207, 237, true)) {

                currentItemRow = 2;
                currentItemIndex = 11;
                System.out.println(currentItemRow);
            } else if (inRange(y, 238, 268, true)) {

                currentItemRow = 3;
                currentItemIndex = 17;
                System.out.println(currentItemRow);

            } else if (inRange(y, 269, 299, true)) {

                currentItemRow = 4;
                currentItemIndex = 23;
                System.out.println(currentItemRow);

            } else if (inRange(y, 300, 330, true)) {

                currentItemRow = 5;
                currentItemIndex = 29;
                System.out.println(currentItemRow);

            } else if (inRange(y, 331, 361, true)) {

                currentItemRow = 6;
                currentItemIndex = 35;
                System.out.println(currentItemRow);

            } else if (inRange(y, 362, 392, true)) {

                currentItemRow = 7;
                currentItemIndex = 41;
                System.out.println(currentItemRow);

            } else if (inRange(y, 393, 423, true)) {

                currentItemRow = 8;
                currentItemIndex = 47;
                System.out.println(currentItemRow);

            } else if (inRange(y, 424, 454, true)) {

                currentItemRow = 9;
                currentItemIndex = 53;
                System.out.println(currentItemRow);

            } else if (inRange(y, 455, 485, true)) {


                currentItemRow = 10;
                currentItemIndex = 59;
                System.out.println(currentItemRow);

            } else if (inRange(y, 486, 516, true)) {

                currentItemRow = 11;
                currentItemIndex = 65;
                System.out.println(currentItemRow);

            }


        }
        if (x > 156 && x < 156 + 30 && y > 183 && y < 183 + 30){
           return player1.playerCrafter.itemArray[9];
        }

        if (currentItemIndex < 0) {
            return null;
        }
        System.out.println(currentItemIndex);
        return player1.playerInventory.itemArray[currentItemIndex];

    }

    private Tile onMouseClickSelectTile(int x, int y) {

        currentTileX = x / 25;
        currentTileY = y / 25;

        return currentOverWorld.tilemap[x / 25][y / 25];

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

        mouseDragX = e.getX();
        mouseDragY = e.getY();


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

    public static String getUserInput(){
        Scanner stringIn = new Scanner(System.in);

        System.out.println("please enters string:");

        while (stringIn.hasNext()){
            if (stringIn.hasNextLine()){
                return stringIn.next();
            } else {
                System.out.println("invalid input");
                stringIn.next();
            }
        }
return null;
    }

    public boolean inRange(int i, int lower, int upper, boolean inclusive) {

        if (inclusive) {
            return (i <= upper && i >= lower);
        }
        return (i < upper && i > lower);
    }

}

