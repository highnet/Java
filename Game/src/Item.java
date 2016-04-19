/**
 * Created by bokense on 26-Mar-16.
 */
public class Item {


    /*

    ID : 0 : EMPTY
    ID : 1 : LUMBER
    ID : 2 : COBBLESTONE
    ID : 3 : SAND
    ID : 4 : PLANKS_WALL
    ID : 5 : RATSKIN_HOOD
    ID : 6 : RATSKIN_CHEST
    ID : 7 : RATSKIN_LEGS
    ID : 8 : RATSKIN_BOOTS
    ID : 9 : WOODEN_CLUB
    ID : 10 : WOODEN_SHIELD

     */


    int ID;

    public Item(int setID){
        this.ID = setID;
    }

    public Item(){
        this.ID = 0;
    }
}