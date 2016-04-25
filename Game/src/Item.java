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
    ID : 5 : RATSKINHOOD
    ID : 6 : RATSKINCHEST
    ID : 7 : RATSKINPANTS
    ID : 8 : RATSKIN_BOOTS // NOT YET IMPLEMENTED
    ID : 9 : WOODEN_CLUB
    ID : 10 : WOODEN_SHIELD
    ID : 11 : OFFHAND : BLUE_BUCKLER
    ID : 12 : BLUE_BUCKLER_TRIMMED
    ID : 13 : BLUE_DAGGER
    ID : 14 : BLUE_LEGGUARDS
    ID : 15 : BLUE_LEGGUARDS_TRIMMED
    ID : 16 : BLUE_PLATEBODY
    ID : 17 : BLUE_PLATEBODY_TRIMMED
    ID : 18 : GREEN_BUCKLER
    ID : 19 : GREEN_BUCKLER_TRIMMED
    ID : 20 : GREEN_DAGGER
    ID : 21 : GREEN_LEGGUARDS
    ID : 22 : GREEN_LEGGUARDS_TRIMMED
    ID : 23 : GREEN_PLATEBODY
    ID : 24 : GREEN_PLATEBODY_TRIMMED
    ID : 25 : JUNKSCRAP_BUCKLER
    ID : 26 : JUNKSCRAP_BUCKLER_TRIMMED
    ID : 27 : JUNKSCRAP_LEGGUARDS
    ID : 28 : JUNKSCRAP_LEGGUARDS_TRIMMED
    ID : 29 : BROWN_PLATEBODY
    ID : 30 : BROWN_PLATEBODY_TRIMMED
    ID : 31 : RED_DAGGER
     */


    int ID;

    public Item(int setID){
        this.ID = setID;
    }

    public Item(){
        this.ID = 0;
    }
}