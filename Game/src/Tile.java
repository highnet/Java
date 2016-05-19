import java.io.Serializable;

/**
 * Created by bokense on 25-Mar-16.
 */
public class Tile implements Serializable {

    String type;
    boolean farmable;
    boolean occupied;
    int growth;
    int grassPermutation;
    int rockPermutation;

    public Tile(String type, boolean farmable, boolean occupied) { // Constructor for a custom tile type


        this.type = type;
        this.farmable = farmable;
        this.occupied = occupied;

        if (this.type.equals("t0stone") || this.type.equals("t1stone") || this.type.equals("t2stone") || this.type.equals("t3stone") || this.type.equals("t4stone")) {
            rockPermutation = (int) (Math.random() * 2);
        } else if (this.type.equals("grass")) {
            this.grassPermutation = (int) (Math.random() * 12);
        }
    }

    public Tile() {
        this("grass", false, false);
    } // Constructor for default tiles of tiletype grass

}