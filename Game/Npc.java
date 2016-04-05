/**
 * Created by Manu on 03/04/16.
 */
import java.awt.Color;

public class Npc extends Entity {

        public String orientation = "EAST";
        public Float HP;
        public int ai;


        public Npc(int setID,int setxPos, int setyPos, float setHP, Color setColor){

            super(setID,setxPos,setyPos,setColor);
            this.HP = setHP;
            this.ai = 0;

        }



    }

