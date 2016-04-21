/**
 * Created by bokense on 26-Mar-16.
 */
public class PlayerGearInterface {
    Item[] itemArray;

    public PlayerGearInterface() {

        itemArray = new Item[6];
        itemArray[0] = new Item(5); // HEAD ZONE
        itemArray[1] = new Item(29); // CHEST ZONE
        itemArray[2] = new Item(15); // PANTS ZONE
        itemArray[3] = new Item(8); // Todo: BOOTS ZONE (NOT YET IMPLEMENTED)
        itemArray[4] = new Item(9); // MAINHAND ZONE
        itemArray[5] = new Item(10); // OFFHAND ZONE

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