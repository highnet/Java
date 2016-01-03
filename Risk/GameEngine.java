import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/*
 *
 *
 *
 */


public class GameEngine extends JPanel implements MouseListener, MouseMotionListener, ActionListener, KeyListener {


    private final Font currentlySelectedLabelFont = new Font("Consola", Font.BOLD, 23);
    private final Font territoryArmyCountLabelFont = new Font("Consola", Font.ITALIC, 18);
    private final Font turnNumberLabelFont = new Font("Consola", Font.BOLD, 22);
    private final Font newTurnBoxInfoLabelFont = new Font("Consola", Font.BOLD, 36);
    private final Font turnPhaseLabelFont = new Font("Consola", Font.BOLD, 14);

    private final Timer timer = new Timer(16, this);
    public Territory currentlySelected = null;
    public Territory shiftSelected = null;
    public String currentlySelectedName = "";
    PlayField gameData;
    Gamer humanPlayer1 = new Gamer(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)), true, "Human Player 1");
    Gamer computerPlayer1 = new Gamer(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)), false, "CPU Player 1");


    int turnNumber = 0;

    boolean displayWelcomeSplashBox = false;
    int welcomeSplashBoxTickTimer = 0;

    boolean paintNewTurnBox = false;
    int newTurnBoxTickTimer = 0;

    boolean newTurn = false;

    boolean reinforceMentPhase = false;
    boolean attackPhase = false;

    boolean computerAttackPhase = false;

    boolean humanIsDoneReinforcing = false;

    boolean shiftKeyIsPressed = false;

    JButton attackBttn = new JButton("Attack/Reinforce");
    JButton endTurnBttn = new JButton("End Turn");

    String loadedMap;

    String pngFileLocation;


    public GameEngine() {

        gameData = new PlayField();

        try {
            File mapData = new File("/Users/bokense1/Desktop/Risk 2/src/world.map");
            //loadedMap = "azeroth";
            loadedMap = "world";
            //   File mapData = new File(fileArgument1);
            gameData.loadData(mapData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //  this.pngFileLocation = fileArgument2;

        System.out.println("[Dev] Done loading all gamedata...");

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this); // Adds keyboard listener.

        this.add(attackBttn);
        this.add(endTurnBttn);

        attackBttn.setVisible(false);
        attackBttn.setVisible(false);


        attackBttn.addActionListener(this);
        endTurnBttn.addActionListener(this);

        attackBttn.setFocusable(true);
        endTurnBttn.setFocusable(true);


        setFocusable(true); // Setting required for keyboard listener.

        timer.start();


    }

    private void fight(Territory currentlySelected, Territory shiftSelected) {
        String str = JOptionPane.showInputDialog(this, "How many units to attack with? (1-" + (currentlySelected.army - 1) + ")", null);
        int numberOfAttackers = str == null ? 0 : Integer.parseInt(str);

        if (numberOfAttackers != 0 && numberOfAttackers < currentlySelected.getArmyCount()) {

            if (numberOfAttackers > shiftSelected.getArmyCount()) {
                System.out.println(currentlySelected.getName() + " wins");

                String inputStr = null;
                int numberOfTransfer = -1;

                // Prompts the user until they make a valid post-combat-victory transfer.
                while (numberOfTransfer < 0 || numberOfTransfer > numberOfAttackers) {
                    inputStr = JOptionPane.showInputDialog(this, "How many units to take transfer with? (1-" + (numberOfAttackers - 1) + ")", null);
                    numberOfTransfer = inputStr == null ? 0 : Integer.parseInt(inputStr);
                }

                Gamer.getOwner(currentlySelected, computerPlayer1, humanPlayer1).captureTerritory(shiftSelected, true, numberOfTransfer);
                currentlySelected.army -= numberOfTransfer;
                Gamer.getOwner(shiftSelected, computerPlayer1, humanPlayer1).removeTerritory(shiftSelected);
                System.out.println(shiftSelected.getName() + " loses");

            } else if (numberOfAttackers <= shiftSelected.getArmyCount()) {
                System.out.println(shiftSelected.getName() + " wins");
                Gamer.getOwner(currentlySelected, computerPlayer1, humanPlayer1).removeTerritory(currentlySelected);
                Gamer.getOwner(shiftSelected, computerPlayer1, humanPlayer1).captureTerritory(currentlySelected, true);
                int randomTransfer = (int) (Math.random() * shiftSelected.army);
                currentlySelected.army += randomTransfer;
                shiftSelected.army -= randomTransfer;
                System.out.println(currentlySelected.getName() + " loses");

            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintBackgroundImage(g, Color.white);

        if (loadedMap == "world") {
            paintIndicatorLines(g);
        }


        // Prints all territory landmass as unclaimed.
        for (int i = 0; i < gameData.territory.size(); i++) {
            gameData.territory.get(i).printTerritory(g, "fill", currentlySelectedName, Color.lightGray);

        }


        // Draw all territories owned by human player
        for (int i = 0; i < humanPlayer1.myTerritory.size(); i++) {
            humanPlayer1.myTerritory.get(i).printTerritory(g, "fill", "", humanPlayer1.color);
        }


        // Draw all territories owned by PC player
        for (int i = 0; i < computerPlayer1.myTerritory.size(); i++) {
            computerPlayer1.myTerritory.get(i).printTerritory(g, "fill", "", computerPlayer1.color);
        }

        // Print all capital cities in black.
        for (int i = 0; i < gameData.territory.size(); i++) {
            gameData.territory.get(i).printTerritoryCapital(g, Color.black);
        }


        // Prints the outline of all territories

        for (int i = 0; i < gameData.territory.size(); i++) {
            gameData.territory.get(i).printTerritory(g, "outline", currentlySelectedName, Color.black);
        }

        // Changes the color of the capital city of the neighbours of currentlyselected to display to the user
        // the new possibilities of attack
        if (currentlySelected != null) {
            paintCurrentlySelectedCapitalHighlighted(g);
            paintCurrentlySelectedCapitalContourHighlighted(g);
            paintNeighbours(g);

        }

        if (shiftSelected != null) {
            paintShiftSelectedCapitalHighlighted(g);
            paintShiftSelectedCapitalContourHighlighted(g);

        }


        // Draws the number of units of each territory.
        for (int i = 0; i < gameData.territory.size(); i++) {
            g.setColor(Color.white);
            g.setFont(territoryArmyCountLabelFont);
            g.drawString(" " + gameData.territory.get(i).getArmyCount(), (int) gameData.territory.get(i).getCapital().getX(), (int) gameData.territory.get(i).getCapital().getY());
        }


        paintCurrentlySelectedLabel(g);
        paintshiftSelectedLabel(g);

        paintTurnPhaseLabels(g);

        if (turnNumber != 0) {
            paintTurnNumberLabel(g);
        }

        if (currentlySelected != null && turnNumber != 0 && reinforceMentPhase) {
            int reinforcementsLeftToPlace = humanPlayer1.reinforcements - humanPlayer1.reinforcementsPlacedThisTurn;
            g.setFont(turnPhaseLabelFont);
            g.setColor(Color.black);
            g.drawString("Reinforcements: " + reinforcementsLeftToPlace, 609, 580);

        }

        if (newTurn) {
            paintGraphicsNewTurnBox(g);
        }

        if (displayWelcomeSplashBox) {
            paintDisplayWelcomeSplashBox(g);
        }


    }

    private void paintIndicatorLines(Graphics g) {


        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.lightGray);
        g2d.drawLine(409, 393, 489, 309);
        g2d.drawLine(414, 95, 355, 121);
        g2d.drawLine(380, 31, 317, 61);
        g2d.drawLine(498, 57, 504, 74);
        g2d.drawLine(519, 75, 608, 75);
        g2d.drawLine(511, 84, 543, 118);
        g2d.drawLine(1156, 78, 1260, 76);
        g2d.drawLine(46, 82, 0, 79);


    }

    private void paintshiftSelectedLabel(Graphics g) {
        g.setColor(Color.BLACK);
        if (humanPlayer1 != null && currentlySelected != null && !humanPlayer1.myTerritory.isEmpty() && shiftSelected != null) {
            for (Territory t : gameData.territory) {
                if (shiftSelected == t) {
                    g.setColor(Gamer.getOwner(shiftSelected, computerPlayer1, humanPlayer1).color); // set the color of the currentlyselected String label to that of the owner of currentlyselected territory
                }
            }

            g.setFont(currentlySelectedLabelFont);
            g.drawString("> " + shiftSelected.getName(), 600, 545);
        }
    }

    private void paintShiftSelectedCapitalHighlighted(Graphics g) {

        int index = 0; // Iterate through all territories until we reach currentlySelected.
        while (index < gameData.territory.size()) {
            if (gameData.territory.get(index).getName().equals(shiftSelected.getName())) { // if we find a match
                gameData.territory.get(index).printTerritoryCapital(g, Color.orange);
            }

            index++;

        }
    }

    private void paintShiftSelectedCapitalContourHighlighted(Graphics g) {
        int index = 0; // Iterate through all territories until we reach currentlySelected.
        while (index < gameData.territory.size()) {
            if (gameData.territory.get(index).getName().equals(shiftSelected.getName())) { // if we find a match

                gameData.territory.get(index).printTerritory(g, "outline", shiftSelected.getName(), Color.ORANGE);
            }

            index++;

        }

    }

    private void paintDisplayWelcomeSplashBox(Graphics g) {
        if (welcomeSplashBoxTickTimer < 100) {
            g.setColor(Color.lightGray);
            g.fillRect(0, 0, 1650, 650);
            g.setFont(newTurnBoxInfoLabelFont);
            g.setColor(Color.white);
            g.drawString("Welcome. Setup Phase. Capture territories.", 100, 160);

        }
        if (welcomeSplashBoxTickTimer == 100) {
            welcomeSplashBoxTickTimer = 0;
            displayWelcomeSplashBox = false;
        }

    }

    private void paintTurnPhaseLabels(Graphics g) {
        g.setFont(turnPhaseLabelFont);
        g.setColor(Color.black);

        if (reinforceMentPhase) {
            g.drawString("Reinforcement Phase", 416, 580);
        }
        if (attackPhase) {
            g.drawString("Attack Phase", 416, 580);
        }
    }

    private void paintGraphicsNewTurnBox(Graphics g) {

        System.out.println("[Dev]Painting new Turn Splash Screen");

        if (newTurnBoxTickTimer < 100) {
            g.setColor(Color.lightGray);
            g.fillRect(0, 0, 1650, 650);
            g.setFont(newTurnBoxInfoLabelFont);
            g.setColor(Color.white);
            g.drawString(humanPlayer1.playerName + " - Your Turn", 100, 160);

        }
        if (newTurnBoxTickTimer == 100) {
            paintNewTurnBox = false;
            newTurnBoxTickTimer = 0;
            newTurn = false;

        }


    }

    private void paintTurnNumberLabel(Graphics g) {

        g.setFont(turnNumberLabelFont); // set font
        g.setColor(humanPlayer1.color); // set color of string to that of the currently active player
        g.drawString(humanPlayer1.playerName + " - Turn: " + turnNumber, 910, 25); // draw the string
    }

    private void paintCurrentlySelectedCapitalContourHighlighted(Graphics g) {


        int index = 0; // Iterate through all territories until we reach currentlySelected.
        while (index < gameData.territory.size()) {
            if (gameData.territory.get(index).getName().equals(currentlySelected.getName())) { // if we find a match

                gameData.territory.get(index).printTerritory(g, "outline", currentlySelected.getName(), Color.yellow);
            }

            index++;

        }
    }

    private void paintCurrentlySelectedCapitalHighlighted(Graphics g) {
        int index = 0; // Iterate through all territories until we reach currentlySelected.
        while (index < gameData.territory.size()) {
            if (gameData.territory.get(index).getName().equals(currentlySelected.getName())) { // if we find a match
                gameData.territory.get(index).printTerritoryCapital(g, Color.yellow);
            }

            index++;

        }


    }

    private void paintCurrentlySelectedLabel(Graphics g) {
        g.setColor(Color.BLACK);
        if (humanPlayer1 != null && currentlySelected != null && !humanPlayer1.myTerritory.isEmpty()) {
            for (Territory t : gameData.territory) {
                if (currentlySelected == t) {
                    g.setColor(Gamer.getOwner(currentlySelected, computerPlayer1, humanPlayer1).color); // set the color of the currentlyselected String label to that of the owner of currentlyselected territory
                }
            }

            g.setFont(currentlySelectedLabelFont);
            g.drawString(currentlySelectedName, 415, 545);
        }
    }

    private void paintNeighbours(Graphics g) {
        int index = 0; // Iterate through all territories until we reach currentlySelected.
        while (index < gameData.territory.size()) {
            if (gameData.territory.get(index).getName().equals(currentlySelected.getName())) { // if we find a match
                ArrayList<Territory> neighbours = gameData.territory.get(index).getNeighbours(); // retrieve the neighbours of the currentlySelected territory
                for (Territory neighbour : neighbours) { // for each neighbour, print the capital city in magenta color.
                    neighbour.printTerritoryCapital(g, Color.magenta);

                }
            }

            index++;

        }
    }

    private void paintBackgroundImage(Graphics g, Color col) {

        //   g.setColor(col);
        //g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT); //alternative empty background
        // load the png background image
        Image i = Toolkit.getDefaultToolkit().getImage("/Users/bokense1/Desktop/Risk 2/src/JT-1 3.png");
        // Image i = Toolkit.getDefaultToolkit().getImage(pngFileLocation);
        g.drawImage(i, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == attackBttn) {
            System.out.println("Attack Button Pressed");


            /* Instanciate combat within two territories
                works if and only if: currentlySelected exist
                                      shiftselected exists
                                       the owner of currentlyselected is the human player
                                      the owner of both territories are not the same
                                      both territories are neighbours of each other
             */

            // FIGHT
            if (currentlySelected != null &&
                    shiftSelected != null &&
                    currentlySelected.army > 1 &&
                    Gamer.getOwner(currentlySelected, computerPlayer1, humanPlayer1) == humanPlayer1 &&
                    Gamer.getOwner(currentlySelected, computerPlayer1, humanPlayer1) !=
                            Gamer.getOwner(shiftSelected, computerPlayer1, humanPlayer1) &&
                    currentlySelected.checkNeighbour(shiftSelected)) {

                System.out.println("[Dev] Fight Requested per Button");

                fight(currentlySelected, shiftSelected);


            }

              /* Instanciate REINFORCE within two territories
                works if and only if: currentlySelected exist
                                      shiftselected exists
                                      the owner of currentlyselected is the human player
                                      the owner of both territories are the same
                                      both territories are neighbours of each other
             */

            // REINFORCE
            else if (currentlySelected != null &&
                    shiftSelected != null &&
                    Gamer.getOwner(currentlySelected, computerPlayer1, humanPlayer1) == humanPlayer1 &&
                    Gamer.getOwner(currentlySelected, computerPlayer1, humanPlayer1) ==
                            Gamer.getOwner(shiftSelected, computerPlayer1, humanPlayer1) &&
                    currentlySelected.checkNeighbour(shiftSelected)) {

                System.out.println("[Dev] Reinforce Requested per Button");

                reinforce(currentlySelected, shiftSelected);


            }

            attackBttn.setText("Attack/Reinforce");
            currentlySelected = null;
            shiftSelected = null;


        }

        if (e.getSource() == endTurnBttn) {
            System.out.println("End Turn Button Pressed");


            attackPhase = false;
            computerAttackPhase = true;


        }

        if (e.getSource() == timer) {


            if (computerAttackPhase) {
                newTurn = true;
                System.out.println("COMPUTER ATTACK PHASE");
                System.out.println("NEW TURN");


                // COMPUTER ATTACKS
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }


                turnNumber++; // Numerical count of turns.
                reinforceMentPhase = true;
                humanPlayer1.calculateReinforcements();
                computerPlayer1.calculateReinforcements();
                humanPlayer1.reinforcementsPlacedThisTurn = 0;
                computerPlayer1.reinforcementsPlacedThisTurn = 0;
                computerAttackPhase = false;


            }

            if (attackPhase) {
                attackBttn.setVisible(true);
                endTurnBttn.setVisible(true);
            } else {
                attackBttn.setVisible(false);
                endTurnBttn.setVisible(false);
            }

            // @trigger computer-only reinforcing.
            if (reinforceMentPhase && humanIsDoneReinforcing) { // if human is done reinforcing but computer is still not done reinforcing. CPU spams reinforcements until done.

                while (computerPlayer1.reinforcements >= computerPlayer1.reinforcementsPlacedThisTurn) {
                    System.out.println("[Dev] CPU Reinforcements:" + computerPlayer1.reinforcements + "/" + computerPlayer1.reinforcementsPlacedThisTurn);

                    // Computer Reinforcement inner trigger
                    boolean reAttempt = true;

                    while (reAttempt) {
                        int randomTerritoryFinder = 0;
                        if (loadedMap == "world") {
                            randomTerritoryFinder = (int) (Math.random() * 42); // Chose a random number for a territory
                        } else if (loadedMap == "azeroth") {
                            randomTerritoryFinder = (int) (Math.random() * 25);
                        }

                        Territory randomTerritory = gameData.territory.get(randomTerritoryFinder); // Make a pointer to the territory
                        if (Gamer.getOwner(randomTerritory, computerPlayer1, humanPlayer1) == computerPlayer1) { // if and only if the territory is owned by the computer
                            randomTerritory.addArmy(1); // reinforce the territory
                            computerPlayer1.reinforcementsPlacedThisTurn++; // increment the number of reinforcements placed this turn by the computer
                            reAttempt = false; // once a reinforcement, do not reattempt to capture again
                        }


                    }


                }

                // @endof reinforcement phase
                reinforceMentPhase = false;
                // @startof attack phase
                attackPhase = true;
                // @endoftrigger computer-only-reinforcement
                humanIsDoneReinforcing = false;

            }

            //@trigger welcome screen splash screen
            if (displayWelcomeSplashBox) { // check if welcome screen splash screen trigger is active
                welcomeSplashBoxTickTimer++; // begin ticker for the trigger
            }

            //@trigger new turn splash screen
            if (paintNewTurnBox) { // check if paint new turn splash screen trigger is active
                newTurnBoxTickTimer++; // begin ticker for the trigger
            }

            //@trigger new turn
            checkIfNewTurn(); // check if new turn trigger is active


            // Repaint screen
            repaint();
        }

    }

    private void reinforce(Territory currentlySelected, Territory shiftSelected) {

        String str = JOptionPane.showInputDialog(this, "How many units to reinforce? (1-" + (currentlySelected.army - 1) + ")", 0);

        int input = (str == null ? 0 : Integer.parseInt(str));


        if (input != 0 && currentlySelected.army > input) {
            currentlySelected.army -= input;
            shiftSelected.army += input;
        } else {
            System.out.println("INVALID REINFORCEMENT");
        }

    }

    private void checkIfNewTurn() {
        if (newTurn) {
            paintNewTurnBox = true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        requestFocusInWindow();

        // Retrieves clicked coordinates.
        int x = e.getX();
        int y = e.getY();

        System.out.println("Click:" + x + "," + y);

        //  @onclick Make primary selection
        if (!shiftKeyIsPressed) {
            // Sets/Changes the currentlySelected pointer.
            shiftSelected = null; // resets secondary selection
            for (int i = 0; i < gameData.territory.size(); i++) {
                if (gameData.territory.get(i).check_isInsideTerritory(x, y)) {
                    System.out.println("Clicked on " + gameData.territory.get(i).getName());
                    currentlySelected = gameData.territory.get(i);
                    currentlySelectedName = currentlySelected.getName(); //

                    break;
                }
            }
        }


        // @onclick Make secondary Selection
        if (attackPhase && shiftKeyIsPressed && currentlySelected != null) {

            for (int i = 0; i < gameData.territory.size(); i++) {
                if (gameData.territory.get(i).check_isInsideTerritory(x, y)) {
                    System.out.println("Shift-Clicked on " + gameData.territory.get(i).getName());
                    if (currentlySelected.getNeighbours().contains(gameData.territory.get(i))) {
                        shiftSelected = gameData.territory.get(i);
                        break;
                    }
                }

            }
            // does not allow 2 selections of same territory
            if (shiftSelected == currentlySelected) {
                shiftSelected = null;
            }

        }

        // Changes the text of the attack button to indicate what kind of action will take place on click.
        if (attackPhase && currentlySelected != null && shiftSelected != null) {
            if (Gamer.getOwner(currentlySelected, computerPlayer1, humanPlayer1) == humanPlayer1 && Gamer.getOwner(shiftSelected, computerPlayer1, humanPlayer1) == humanPlayer1) {
                attackBttn.setText("REINFORCE");
            } else if (Gamer.getOwner(currentlySelected, computerPlayer1, humanPlayer1) == humanPlayer1 && Gamer.getOwner(shiftSelected, computerPlayer1, humanPlayer1) != humanPlayer1) {
                attackBttn.setText("ATTACK");
            } else {
                if (attackPhase) {
                    attackBttn.setText("Attack/Reinforce");
                }
            }
        }

        // Special case for turn 0. Territory Selection Phase.
        if (turnNumber == 0) {

            // if player clicks on an unoccupied territory. Capture it.
            if (currentlySelected != null && !currentlySelected.alreadyOccupied(humanPlayer1, computerPlayer1)) {
                humanPlayer1.captureTerritory(currentlySelected);

                boolean reAttempt = true; // Boolean condition used for reattempting AI placement when the randomly generated territory is already occupied

                while (reAttempt) {
                    int randomTerritoryFinder = 0;
                    if (loadedMap == "world") {
                        randomTerritoryFinder = (int) (Math.random() * 42); // Chose a random number for a territory
                    } else if (loadedMap == "azeroth") {
                        randomTerritoryFinder = (int) (Math.random() * 25);
                    } // Chose a random territory
                    Territory randomTerritory = gameData.territory.get(randomTerritoryFinder); // Make a pointer to the territory
                    if (!randomTerritory.alreadyOccupied(humanPlayer1, computerPlayer1)) { // if and only if the territory is unoccupied
                        computerPlayer1.captureTerritory(randomTerritory); // capture the territory
                        reAttempt = false; // once a capture is sucessful, do not reattempt to capture again.


                    }


                }

            }

            boolean unclaimedTerritoriesExist = false; // Assume there are no territories unclaimed and counterprove it.
            for (int i = 0; i < gameData.territory.size(); i++) { // iterate through all territories
                // if any territory is still unclaimed, continue, if all territories are claimed. end turn 0.
                if (!gameData.territory.get(i).alreadyOccupied(humanPlayer1, computerPlayer1)) {
                    unclaimedTerritoriesExist = true;
                }
            }

            // If there are no unclaimed territories left. Begin turn 1.
            if (!unclaimedTerritoriesExist) {
                newTurn = true; // Boolean condition used for events that are triggered only during new turn transitions.

                System.out.println("NEW TURN");
                turnNumber++; // Numerical count of turns.
                attackPhase = false;
                reinforceMentPhase = true;
                humanPlayer1.calculateReinforcements();
                computerPlayer1.calculateReinforcements();
                humanPlayer1.reinforcementsPlacedThisTurn = 0;
                computerPlayer1.reinforcementsPlacedThisTurn = 0;

            }


        } // End of turn 0 placement phase.


        placementLoop:
        // Capture Placement phase loop. Only active during reinforcement phase
        if (turnNumber != 0 && reinforceMentPhase) { // Activates only during reinforcement phase
            boolean humanCapturedSuccesfully = false;


            System.out.println("Total Re: " + humanPlayer1.reinforcements);
            System.out.println("Re placed so far: " + humanPlayer1.reinforcementsPlacedThisTurn);

            //Adds an army to the territory that is clicked.
            if (humanPlayer1.reinforcements != humanPlayer1.reinforcementsPlacedThisTurn && currentlySelected.check_isInsideTerritory(x, y) && Gamer.getOwner(currentlySelected, computerPlayer1, humanPlayer1) == humanPlayer1) {
                currentlySelected.addArmy(1);
                humanPlayer1.reinforcementsPlacedThisTurn++;
                System.out.println("triggering humancapturedsuccesfully");
                humanCapturedSuccesfully = true;
            }


            // When you are done reinforcing but the computer is not done reinforcing let the computer finish @triggers computer-only-reinforcing
            if (humanPlayer1.reinforcements == humanPlayer1.reinforcementsPlacedThisTurn && computerPlayer1.reinforcements != computerPlayer1.reinforcementsPlacedThisTurn) {
                System.out.println("human is done reinforcing");
                // @trigger computer-only reinforcement
                humanIsDoneReinforcing = true;
            }

            // End condition: whenever you and the computer run out of reinforcements to place at the same click.
            if (humanPlayer1.reinforcements == humanPlayer1.reinforcementsPlacedThisTurn && computerPlayer1.reinforcements == computerPlayer1.reinforcementsPlacedThisTurn) {
                reinforceMentPhase = false;
                attackPhase = true;
                humanIsDoneReinforcing = false;
                break placementLoop;
            }


            if (humanCapturedSuccesfully && computerPlayer1.reinforcements != computerPlayer1.reinforcementsPlacedThisTurn) { // Computer Reinforcement after human click.
                boolean reAttempt = true;

                while (reAttempt) {
                    int randomTerritoryFinder = 0;
                    if (loadedMap == "world") {
                        randomTerritoryFinder = (int) (Math.random() * 42); // Chose a random number for a territory
                    } else if (loadedMap == "azeroth") {
                        randomTerritoryFinder = (int) (Math.random() * 25);
                    } // Chose a random number for a territory
                    Territory randomTerritory; // Make a pointer to the territory
                    randomTerritory = gameData.territory.get(randomTerritoryFinder);
                    if (Gamer.getOwner(randomTerritory, computerPlayer1, humanPlayer1) == computerPlayer1) { // if and only if the territory is owned by the computer
                        randomTerritory.addArmy(1); // reinforce the territory
                        computerPlayer1.reinforcementsPlacedThisTurn++;
                        reAttempt = false; // once a reinforcement, do not reattempt to capture again.


                    }


                }
            }


        }


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

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("Escape Typed");
            currentlySelected = null;
            shiftSelected = null;
            if (attackPhase) {
                attackBttn.setText("Attack/Reinforce");
            }
        }


        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            System.out.println("Shift Pressed");
            shiftKeyIsPressed = true;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            System.out.println("Shift Released");
            shiftKeyIsPressed = false;
        }

    }
}
