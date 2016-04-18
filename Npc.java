/**
 * Created by Manu on 03/04/16.
 */


import java.awt.Color;
import java.io.Serializable;


public class Npc extends Entity implements Serializable{

    public String orientation = "EAST";
    public Float HP;
    public String ai;



    public Npc(int setID,int setxPos, int setyPos, float setHP, Color setColor, String setAi){

        super(setID,setxPos,setyPos,setColor);
        this.HP = setHP;
        this.ai = setAi;

    }



}