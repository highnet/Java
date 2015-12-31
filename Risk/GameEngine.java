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


    public Territory currentlySelected = null;
    public String currentlySelectedName = "";
    Timer timer = new Timer(16, this);
    PlayField gameData;

    Font currentlySelectedLabelFont = new Font("Consola", Font.BOLD, 23);
    Font territoryArmyCountLabelFont = new Font("Consola", Font.ITALIC, 18);
    Font turnNumberLabelFont = new Font("Consola", Font.BOLD, 22);
    Font newTurnBoxInfoLabelFont = new Font ("Consola", Font.BOLD, 36);

    Gamer humanPlayer1 = new Gamer(Color.blue, true);
    Gamer computerPlayer1 = new Gamer(Color.red, false);


    int turnNumber = 0;
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

        paintTurnNumberLabel(g);
        if (newTurn) {
            paintGraphicsNewTurnBox(g);
        }


    }

    private void paintGraphicsNewTurnBox(Graphics g) {

        if (newTurnBoxTickTimer < 100) {
            g.setColor(Color.lightGray);
            g.fillRect(0, 0, 1650, 650);
            g.setFont(newTurnBoxInfoLabelFont);
            g.setColor(Color.white);
            g.drawString("computerplayer 1 - Your Turn", 100, 160);

        }
        if (newTurnBoxTickTimer == 101) {
            newTurnBoxTickTimer = 0;
            paintNewTurnBox = false;
            newTurn = false;
        }

    }

    private void paintTurnNumberLabel(Graphics g) {

        g.setFont(turnNumberLabelFont);
        g.drawString("Turn: " + turnNumber, 910, 25);
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
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();


        System.out.println("Click:" + x + "," + y);

        for (int i = 0; i < gameData.territory.size(); i++) {
            if (gameData.territory.get(i).check_isInsideTerritory(x, y)) {
                System.out.println("Clicked on " + gameData.territory.get(i).getName());
                currentlySelected = gameData.territory.get(i);
                currentlySelectedName = currentlySelected.getName();

                break;
            }
        }


        if (turnNumber == 0) {


            if (currentlySelected != null && !currentlySelected.alreadyOccupied(humanPlayer1, computerPlayer1)) {
                humanPlayer1.captureTerritory(currentlySelected);

                boolean reAttempt = true;

                while (reAttempt) {
                    int randomTerritoryFinder = (int) (Math.random() * 41 - 1) + 1;
                    Territory randomTerritory = gameData.territory.get(randomTerritoryFinder);
                    if (!randomTerritory.alreadyOccupied(humanPlayer1, computerPlayer1)) {
                        computerPlayer1.captureTerritory(randomTerritory);
                        reAttempt = false;


                    }


                }

            }

            boolean unclaimedTerritoriesExist = false;
            for (int i = 0; i < gameData.territory.size(); i++) { // iterate through all territories
                // if any territory is still unclaimed, continue, if all territories are claimed. end turn 0.
                if (!gameData.territory.get(i).alreadyOccupied(humanPlayer1, computerPlayer1)) {
                    unclaimedTerritoriesExist = true;
                }
            }

            if (!unclaimedTerritoriesExist) {
                turnNumber++;
                newTurn = true;
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
}
