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

        dialogue = new String[22];



        switch (this.ai){
            case "LUMBERJACK":


                dialogue[0] = "Hello traveller.";
                dialogue[1] = "Hello. ";
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

                dialogue[18] = "I need you to take the wood you collected to the castle. The guards await it by the door";

                dialogue[19] = "Just follow the road eastwards until you reach the bridge crossing the stream. the castle is past the bridge.";

                dialogue[20] = "Good to see you again !!";
                dialogue[21] = "Go deliver that wood before I get in trouble with lord Randiq!!";
                break;
            case "CASTLEGUARD":
                dialogue[0] = "Get lost";
                dialogue[1] = "Ok.....";
                dialogue[2] = "What do you want here ?";
                dialogue[3] = "I have a delivery of wood.";
                dialogue[4] = "Actually, never mind.";
                dialogue[5] = "Well, what took you so long. \n Hand it over !! \n Here is your payment";
                dialogue[6] = "Here you go.";
                break;
        }
    }


}