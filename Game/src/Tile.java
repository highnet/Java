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
    boolean activeState;

    public Tile(String type, boolean farmable, boolean occupied,boolean activeState) { // Constructor for a custom tile type


        this.type = type;
        this.farmable = farmable;
        this.occupied = occupied;



        if (this.type.equals("t0stone") || this.type.equals("t1stone") || this.type.equals("t2stone") || this.type.equals("t3stone") || this.type.equals("t4stone") || this.type.equals("t4stone") || this.type.equals("t0stone_1x2_a") || this.type.equals("t1stone_1x2_a") || this.type.equals("t2stone_1x2_a")) {
            rockPermutation = (int) (Math.random() * 2);
        } else if (this.type.equals("grass")) {
            this.grassPermutation = (int) (Math.random() * 12);
        } else if (this.type.equals("furnace")){
            this.activeState = activeState;
        }
    }

    public Tile() {
        this("grass", false, false,false);
    } // Constructor for default tiles of tiletype grass

}