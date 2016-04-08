import java.io.Serializable;
import java.util.Vector;

/**
 * Created by Manu on 07/04/16.
 */
public class Overworld implements Serializable{

    public Tile[][] tilemap;
    public Vector<Npc> npcList;
    public int idX;
    public int idY;

    public Overworld(int idX, int idY){

        this.npcList = new Vector<>();
        this.tilemap = new Tile[32][24];
        this.idX = idX;
        this.idY = idY;

    }


}
