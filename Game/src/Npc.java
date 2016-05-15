/**
 * Created by Manu on 03/04/16.
 */

import java.awt.Color;
import java.io.Serializable;



public class Npc extends Entity implements Serializable{

    public String orientation = "EAST";
    public Float HP;
    public String ai;
    public String[] dialogue;

    public Npc(int setID,int setxPos, int setyPos, float setHP, Color setColor, String setAi){

        super(setID,setxPos,setyPos,setColor);
        this.HP = setHP;
        this.ai = setAi;

        this.setDialogue();

   }

    private void setDialogue() {

        dialogue = new String[12];

        switch (this.ai){
            case "DUDE":
                dialogue[0] = "Hello traveller.";
                dialogue[1] = "Hello.";
                dialogue[2] = "Actually, never mind.";
                dialogue[3] = "I have a quest for you!";
                dialogue[4] = "Sure, what is it?";
                dialogue[5] = "I'm not interested";
                dialogue[6] = "Gather me 10 Wood";
                dialogue[7] = "Sure";
                dialogue[8] = "Thanks!";
                dialogue[9] = "Thanks for the wood, here is your reward";
                dialogue[10] = "Bye!";
                dialogue[11] = "Have you brought my wood yet?";
                break;
        }
    }


}