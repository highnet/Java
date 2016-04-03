/**
 * Created by Manu on 03/04/16.
 */
public class Npc extends Entity {

        public String orientation = "EAST";
        public Float HP;

        public Npc(int setID,int setxPos, int setyPos, float setHP){

            super(setID,setxPos,setyPos);
            this.HP = setHP;

        }



    }

