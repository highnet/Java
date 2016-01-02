import java.util.ArrayList;

public class Continent {
    private ArrayList<Territory> territory = new ArrayList<Territory>();
    private String name = "";
    private int reinforcement = 0;

    public Continent(String name, int reinforcement) {
        this.name = name;
        this.reinforcement = reinforcement;
    }

    public Continent(Territory data) {
        territory = new ArrayList<Territory>();
        territory.add(data);
    }

    public void addTerritory(Territory data) {
        this.territory.add(data);
    }


    public String toString() {
        String ret = name + "   " + reinforcement + "\n";
        for (int i = 0; i < territory.size(); i++) {
            ret += "\n" + territory.get(i) + "\n";
        }

        return ret;
    }
}

