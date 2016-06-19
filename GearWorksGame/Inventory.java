/**
 * Created by bokense on 26-Mar-16.
 */
public class Inventory {
    Item[] itemArray;

    public Inventory(int size) {

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

    @Override
    public String toString() {
        String sb = "";

        for (int i = 0; i < itemArray.length; i++) {
            if (i % 8 == 0) {
                sb += "\n";
            }
            switch (itemArray[i].ID) {
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

    public boolean hasItem(int itemID, int quantity) {

        System.out.println("Checking if player has at least " + quantity + " of itemID: " + itemID);
        int counter = 0;
        for (Item it : itemArray) {
            if (it.ID == itemID) {
                counter++;
            }
        }
        return counter >= quantity;
    }

    public void removeItem(int itemID, int quantity) {
        for (int i = 0; i < itemArray.length; i++) {
            System.out.println(quantity);
            if (quantity == 0) {
                break;
            } else if (itemArray[i].ID == itemID) {
                itemArray[i] = new Item();
                quantity--;
            }

        }
    }


    public void addItem(int itemID) {
        for (int i = 0 ; i < this.itemArray.length-1; i++){
            if (this.itemArray[i].ID == 0){
                this.itemArray[i].ID = itemID;
                break;
            }
        }
    }
}
