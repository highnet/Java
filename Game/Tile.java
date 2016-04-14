import java.io.Serializable;

/**
 * Created by bokense on 25-Mar-16.
 */
public class Tile implements Serializable {

    String type;
    boolean farmable;
    boolean occupied;
    int growth;

    public Tile(String type, boolean farmable, boolean occupied) { // Constructor for a custom tile type
        this.type = type;
        this.farmable = farmable;
        this.occupied = occupied;
    }

    public Tile() {
        this("grass",false,false);
    } // Constructor for default tiles of tiletype grass

}
