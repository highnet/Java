/**
 * Created by Manu on 02/04/16.
 */
public class Entity {

    public int ID;
    public int xPos;
    public int yPos;


    public Entity(int setID,int setxPos, int setyPos) {
        this.ID = setID;
        this.xPos = setxPos * 25 + 3;
        this.yPos = setyPos * 25 + 3;
    }

    }
