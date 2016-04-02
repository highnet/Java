/**
 * Created by bokense on 25-Mar-16.
 */
public class Tile {

    String type;
    boolean farmable;

    public Tile(String type, boolean farmable
    ) { // Constructor for a custom tile type
        this.type = type;
        this.farmable = farmable;
    }

    public Tile() {
        this("grass",false);
    } // Constructor for default tiles of tiletype grass

}
