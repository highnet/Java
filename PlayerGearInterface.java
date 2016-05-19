/**
 * Created by bokense on 26-Mar-16.
 */
public class PlayerGearInterface {
    Item[] itemArray;

    public PlayerGearInterface() {

        itemArray = new Item[6];
        itemArray[0] = new Item(0); // HEAD ZONE Todo: add Items
        itemArray[1] = new Item(6); // CHEST ZONE
        itemArray[2] = new Item(7); // PANTS ZONE
        itemArray[3] = new Item(0); // Todo: BOOTS ZONE (NOT YET IMPLEMENTED) / add items
        itemArray[4] = new Item(0); // OFFHAND
        itemArray[5] = new Item(0); // MAINHAND

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

}