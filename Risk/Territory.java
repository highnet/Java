import java.awt.*;
import java.util.ArrayList;


public class Territory {
    private ArrayList<Patch> patch = new ArrayList<Patch>();
    private String name;
    private Point capital;
    private ArrayList<Territory> neighbour = new ArrayList<Territory>();
    private ArrayList<Polygon> polygonArrayList = new ArrayList<Polygon>();
    private boolean newPatchData = false;
    private int army = 0; // counter for armys on this Territory the information


    public Territory() {
    }
    // which Army it is is in the Class Gamer

    public Territory(String name, Point capital) {
        this.name = name;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getCapital() {
        return capital;
    }

    public void setCapital(Point capital) {
        this.capital = capital;
    }

    /**
     * this will be used on the first stage of the game where the capture of the territory is the business
     */


    public void addArmy(int count) {
        army += count;
    }

    public void removeArmy(int count) {
        if (army - count >= 0)
            army -= count;
    }

    public int getArmyCount() {
        return this.army;
    }

    public void addPatch(Patch data) {
        this.patch.add(data);
        newPatchData = true;

    }

    public void addNeighbour(Territory data) {
        this.neighbour.add(data);
    }

    public ArrayList<Territory> getNeighbours() {
        return this.neighbour;
    }

    public boolean checkNeighbour(Territory data) {
        return this.neighbour.contains(data);
    }


    public void createPolygon() {
        polygonArrayList.clear();
        for (int i = 0; i < this.patch.size(); i++) {
            polygonArrayList.add(patch.get(i).get_Polygon());

        }
    }

    public void printTerritory(Graphics g, String printStyle, String currentlySelectedName, Color col) {
        /* check if there are new Data for this Territory
         * if this is true we need to rebuild the Polygon ArrayList
		 */
        if (newPatchData) {
            createPolygon();
            newPatchData = false;
            System.out.println("created new polygons");
        }

        if (printStyle == "fill") {


            for (Polygon poly : this.polygonArrayList) {
                g.setColor(col);
                /*if (currentlySelectedName == this.name) {
                    g.setColor(Color.green);
                }*/
                g.fillPolygon(poly);
            }

        } else if (printStyle == "outline") {
            for (Polygon poly : this.polygonArrayList) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(col);
                g2d.drawPolygon(poly);
            }
        }
    }

    public void printTerritoryCapital(Graphics g, Color col) {

        // Determines the size of the capital cities(oval).
        final int capitalCityDimension = 8;
        g.setColor(col);

        g.fillOval((int) this.capital.getX(), (int) this.capital.getY(), capitalCityDimension, capitalCityDimension);
    }


    public boolean check_isInsideTerritory(int x, int y) {

        for (int i = 0; i < this.polygonArrayList.size(); i++) {
            if (polygonArrayList.get(i).contains(x, y))
                return true;
        }

        return false;
    }


    public String toString() {
        String ret = name + "   " + capital.toString() + "\n";
        ret += "neighbours: ";
        for (int i = 0; i < neighbour.size(); i++) {
            ret += neighbour.get(i).name;
        }
        ret += "\n \n patch: ";
        for (int i = 0; i < patch.size(); i++) {
            ret += patch.get(i);
        }
        ret += "\n";
        return ret;
    }


    public boolean alreadyOccupied(Gamer humanPlayer1, Gamer computerPlayer1) {


        if (this == null) {
            return false;
        }
        // check if humanplayer1 already owns territory to capture
        for (int i = 0; i < humanPlayer1.myTerritory.size(); i++){
            if (this.getName() == humanPlayer1.myTerritory.get(i).getName()){
                return true;
            }
        }

        // check if computerplayer1 already owns territory to capture
        for (int i = 0; i < computerPlayer1.myTerritory.size(); i++){
            if (this.getName() == computerPlayer1.myTerritory.get(i).getName()){
                return true;
            }
        }

        return false;
    }
}
