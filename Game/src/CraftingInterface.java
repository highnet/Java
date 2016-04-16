/**
 * Created by bokense on 26-Mar-16.
 */
public class CraftingInterface {
    Item[] itemArray;

    public CraftingInterface(int size) {

        itemArray = new Item[size];

        for (int i = 0; i < itemArray.length; i++) {
            itemArray[i] = new Item();
        }

    }

    public boolean isFull() {
        for (int i = 0; i < itemArray.length; i++) {
            if (itemArray[i].ID == 0) {
                return false;
            }
        }
        return true;
    }

}