import java.awt.*;
import java.util.ArrayList;


public class Territory {
    private ArrayList<Patch> patch = new ArrayList<Patch>();
    private String name;
    private Point capital;
    private ArrayList<Territory> neighbour = new ArrayList<Territory>();

    private ArrayList<Polygon> p = new ArrayList<Polygon>();
    private boolean newPatchData = false;


    private int army = 0; // counter for armys on this Territory the information
    // which Army it is is in the Class Gamer

    public Territory() {
    }

    public Territory(String name, Point capital) {
        this.name = name;
        this.capital = capital;
    }



    /**
     * this will be used on the first stage of the game where the capture of the territory is the business
     */
    public void captureTerritory() {
        army = 1;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(Point capital) {
        this.capital = capital;
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
        p.clear();
        for (int i = 0; i < this.patch.size(); i++) {
            p.add(patch.get(i).get_Polygon());

        }
    }

    public void printTerritory(Graphics g) {
        /* check if there are new Data for this Territory
         * if this is true we need to rebuild the Polygon ArrayList
		 */
        if (newPatchData) {
            createPolygon();
            newPatchData = false;
        }

        for (int i = 0; i < this.p.size(); i++) {
            g.drawPolygon(p.get(i));
        }
    }


    public boolean check_isInsideTerritory(int x, int y) {

        for (int i = 0; i < this.p.size(); i++) {
            if (p.get(i).contains(x, y))
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
}
