/**
 * Created by Manu on 02/04/16.
 */

import java.awt.Color;
import java.io.Serializable;

public class Entity implements Serializable {

    public int ID;
    public int xPos;
    public int yPos;
    public Color pallete;


    public Entity(int setID,int setxPos, int setyPos, Color setColor) {
        this.ID = setID;
        this.xPos = setxPos * 25 + 3;
        this.yPos = setyPos * 25 + 3;
        this.pallete = setColor;
    }

}