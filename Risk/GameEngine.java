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
    Font territoryArmyCountLabelFont = new Font("Bell MT", Font.ITALIC, 18);


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

        // Prints the outline of all territories
        for (int i = 0; i < gameData.territory.size(); i++) {
            gameData.territory.get(i).printTerritory(g, "outline", currentlySelectedName, Color.black);
        }

        // Print all capital cities in black.
        for (int i = 0; i < gameData.territory.size(); i++) {
            gameData.territory.get(i).printTerritoryCapital(g, Color.black);
        }

        // Draws the number of units of each territory.
        for (int i = 0; i < gameData.territory.size(); i++) {
            g.setColor(Color.white);
            g.setFont(territoryArmyCountLabelFont);
            g.drawString(" " + gameData.territory.get(i).getArmyCount(), (int) gameData.territory.get(i).getCapital().getX(), (int) gameData.territory.get(i).getCapital().getY());
        }

        // Changes the color of the capital city of the neighbours of currentlyselected to display to the user
        // the new possibilities of attack

        if (currentlySelected != null) {
            paintCurrentlySelectedCapitalHighlighted(g);
            paintCurrentlySelectedCapitalContourHighlighted(g);
            paintNeighbours(g);

        }

        paintCurrentlySelectedLabel(g);


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
            repaint();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();


        System.out.println("Click:" + x + "," + y);

        // if currentlyselected == null
        // if click polygon -> currentlyselected


        for (int i = 0; i < gameData.territory.size(); i++) {
            if (gameData.territory.get(i).check_isInsideTerritory(x, y)) {
                System.out.println("Clicked on " + gameData.territory.get(i).getName());
                currentlySelected = gameData.territory.get(i);
                currentlySelectedName = currentlySelected.getName();
                currentlySelected.addArmy(1);
                break;
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
