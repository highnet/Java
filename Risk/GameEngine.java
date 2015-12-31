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


public class GameEngine extends JPanel implements MouseListener, MouseMotionListener, ActionListener {


    private final Font currentlySelectedLabelFont = new Font("Consola", Font.BOLD, 23);
    private final Font territoryArmyCountLabelFont = new Font("Consola", Font.ITALIC, 18);
    private final Font turnNumberLabelFont = new Font("Consola", Font.BOLD, 22);
    private final Font newTurnBoxInfoLabelFont = new Font("Consola", Font.BOLD, 36);
    public Territory currentlySelected = null;
    public String currentlySelectedName = "";
    private final Timer timer = new Timer(16, this);
    PlayField gameData;
    Gamer humanPlayer1 = new Gamer(Color.blue, true, "Human Player 1");
    Gamer computerPlayer1 = new Gamer(Color.red, false, "CPU Player 1");

    Gamer currentActivePlayerTurn = humanPlayer1;


    int turnNumber = 0;

    boolean displayWelcomeSplashBox = true;
    int welcomeSplashBoxTickTimer = 0;

    boolean newTurn = false;
    boolean paintNewTurnBox = false;
    int newTurnBoxTickTimer = 0;

    boolean reinforceMentPhase = false;
    boolean attackPhase = false;


    public GameEngine() {

        gameData = new PlayField();

        try {
            File mapData = new File("/Users/bokense1/Desktop/Risk 2/src/world.map");
            gameData.loadData(mapData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("[Dev] Done loading all gamedata...");


        addMouseListener(this);
        addMouseMotionListener(this);

        timer.start();


    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintBackgroundImage(g, Color.WHITE);


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

        // Draws the number of units of each territory.
        for (int i = 0; i < gameData.territory.size(); i++) {
            g.setColor(Color.black);
            g.setFont(territoryArmyCountLabelFont);
            g.drawString(" " + gameData.territory.get(i).getArmyCount(), (int) gameData.territory.get(i).getCapital().getX(), (int) gameData.territory.get(i).getCapital().getY());
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


        paintCurrentlySelectedLabel(g);

        paintTurnPhaseLabels(g);

        if (turnNumber != 0) {
            paintTurnNumberLabel(g);
        }

        if (currentlySelected != null && turnNumber != 0 && reinforceMentPhase) {
                int reinforcementsLeftToPlace = currentActivePlayerTurn.reinforcements - currentActivePlayerTurn.reinforcementsPlacedThisTurn+1;
            g.drawString("Reinforcements: " + reinforcementsLeftToPlace , 650, 590);

        }

        if (newTurn) {
            paintGraphicsNewTurnBox(g);
        }

        if (displayWelcomeSplashBox) {
            paintDisplayWelcomeSplashBox(g);
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
            displayWelcomeSplashBox = false;
        }

    }

    private void paintTurnPhaseLabels(Graphics g) {
        g.setFont(currentlySelectedLabelFont);

        if (reinforceMentPhase) {
            g.drawString("Reinforcement Phase", 360, 600);
        }
        if (attackPhase) {
            g.drawString("Attack Phase", 360, 600);
        }
    }

    private void paintGraphicsNewTurnBox(Graphics g) {

        if (newTurnBoxTickTimer < 100) {
            g.setColor(Color.lightGray);
            g.fillRect(0, 0, 1650, 650);
            g.setFont(newTurnBoxInfoLabelFont);
            g.setColor(Color.white);
            g.drawString(currentActivePlayerTurn.playerName + " - Your Turn", 100, 160);

        }
        if (newTurnBoxTickTimer == 100) {
            newTurnBoxTickTimer = 0;
            paintNewTurnBox = false;
            newTurn = false;
        }


    }

    private void paintTurnNumberLabel(Graphics g) {

        g.setFont(turnNumberLabelFont);
        g.drawString(currentActivePlayerTurn.playerName + " - Turn: " + turnNumber, 910, 25);
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
        g.setFont(currentlySelectedLabelFont);
        g.drawString(currentlySelectedName + "   > ", 435, 565);
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

        g.setColor(col);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer) {


            if (currentlySelected != null && turnNumber != 0 && reinforceMentPhase) {
                currentActivePlayerTurn.calculateReinforcements();
            }


            if (displayWelcomeSplashBox) {
                welcomeSplashBoxTickTimer++;
            }

            checkIfNewTurn();
            if (paintNewTurnBox) {
                newTurnBoxTickTimer++;
            }

            repaint();
        }

    }

    private void checkIfNewTurn() {
        if (newTurn) {
            paintNewTurnBox = true;
            attackPhase = false;
            reinforceMentPhase = true;

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        // Retrieves clicked coordinates.
        int x = e.getX();
        int y = e.getY();


        System.out.println("Click:" + x + "," + y);

        // Changes the currentlySelected pointer.
        for (int i = 0; i < gameData.territory.size(); i++) {
            if (gameData.territory.get(i).check_isInsideTerritory(x, y)) {
                System.out.println("Clicked on " + gameData.territory.get(i).getName());
                currentlySelected = gameData.territory.get(i);
                currentlySelectedName = currentlySelected.getName();

                break;
            }
        }

        placementLoop:
        // Reinforcement Placement clean loop
        if (turnNumber != 0 && reinforceMentPhase) { // Activates only during reinforcement phase
            System.out.println("Total Re: " + currentActivePlayerTurn.reinforcements);
            System.out.println("Re placed so far: " + currentActivePlayerTurn.reinforcementsPlacedThisTurn);

            // End condition: whenever you run out of reinforcements to place.
            if (currentActivePlayerTurn.reinforcements == currentActivePlayerTurn.reinforcementsPlacedThisTurn) {
                reinforceMentPhase = false;
                attackPhase = true;
                break placementLoop;
            }

            //Adds an army to the territory that is clicked.
            for (int i = 0; i < gameData.territory.size(); i++) {
                if (gameData.territory.get(i).check_isInsideTerritory(x, y)) {
                    System.out.println("Clicked on " + gameData.territory.get(i).getName() + " for reinforcement");
                    gameData.territory.get(i).addArmy(1);
                    currentActivePlayerTurn.reinforcementsPlacedThisTurn++;
                    break;
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
                    int randomTerritoryFinder = (int) (Math.random() * 41 - 1) + 1; // Chose a random territory
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
                turnNumber++; // Numerical count of turns.
                newTurn = true; // Boolean condition used for events that are triggered only during new turn transitions.

            }


        } // End of turn 0 placement phase.


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
}
