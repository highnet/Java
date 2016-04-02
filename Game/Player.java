/**
 * Created by bokense on 25-Mar-16.
 */
public class Player {

    public int ID;
    public int xPos;
    public int yPos;
    public String orientation = "EAST";
    Inventory playerInventory;

    public Player(int setID,int setxPos, int setyPos, int setInventorySize){
        this.ID = setID;
        this.xPos = setxPos * 25 + 3;
        this.yPos = setyPos * 25 + 3;

        playerInventory = new Inventory(setInventorySize);

    }



}
