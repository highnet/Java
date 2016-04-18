/**
 * Created by bokense on 26-Mar-16.
 */
public class Inventory {
    Item[] itemArray;

    public Inventory(int size){

        itemArray = new Item[size];

        for(int i = 0; i < itemArray.length; i++){
            itemArray[i] = new Item();
        }

    }

    public boolean isFull(){
        for(int i = 0; i < itemArray.length; i++){
            if (itemArray[i].ID == 0){
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString(){
        String sb = "";

        for(int i = 0; i < itemArray.length; i++){
            if ( i % 8 == 0){ sb += "\n"; }
            switch (itemArray[i].ID){
                case 0:
                    sb += " - Empty - ";
                    break;
                case 1:
                    sb += " - Lumber - ";
                    break;
                case 2:
                    sb += " - Cobblestone - ";
                    break;
                case 3:
                    sb += " - Sand - ";
                    break;
                default:
                    sb += "- ERROR -";
                    break;
            }
        }
        return sb;
    }

}