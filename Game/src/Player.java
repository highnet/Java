/**
 * Created by bokense on 25-Mar-16.
 */
import java.awt.Color;
import java.util.ArrayList;

public class Player extends Entity {


    public String orientation = "EAST";
    public Float HP;
    Inventory playerInventory;
    CraftingInterface playerCrafter;
    PlayerGearInterface gearInterface;
    ArrayList<Quest> personalQuestLog;
    ArrayList<Integer> personalQuestTriggers;
    ArrayList<Integer> personalQuestsCompleted;

    public Player(int setID,int setxPos, int setyPos, int setInventorySize, float setHP, Color setColor){

        super(setID,setxPos,setyPos,setColor);
        this.HP = setHP;

        playerInventory = new Inventory(setInventorySize);
        playerCrafter = new CraftingInterface(10);
        gearInterface = new PlayerGearInterface();
        personalQuestLog = new ArrayList<>();
        personalQuestTriggers = new ArrayList<>();
        personalQuestsCompleted = new ArrayList<>();

    }



}