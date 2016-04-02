/**
 * Created by bokense on 25-Mar-16.
 */
public class Player extends Entity {


    public String orientation = "EAST";
    Inventory playerInventory;

    public Player(int setID,int setxPos, int setyPos, int setInventorySize){

        super(setID,setxPos,setyPos);

        playerInventory = new Inventory(setInventorySize);

    }



}
