import java.util.ArrayList;

/**
 * Created by bokense on 15-May-16.
 */
public class Quest {

    int questID;
    String questName;
    String questTextFull;
    String questTextAbridged;
    Item[] itemRequirementsToComplete;
    ArrayList<Integer> requiredQuestTriggersToComplete;

    public Quest(int setQuestId, String setQuestName,
                 String setQuestTextFull, String setQuestTestAbridged,
                 Item[] setItemRequirementsToComplete,
                 ArrayList<Integer> setRequiredQuestTriggersToComplete) {

        this.questID = setQuestId;
        this.questName = setQuestName;
        this.questTextFull = setQuestTextFull;
        this.questTextAbridged = setQuestTestAbridged;
        this.itemRequirementsToComplete = setItemRequirementsToComplete;
        this.requiredQuestTriggersToComplete = setRequiredQuestTriggersToComplete;

    }

    @Override
    public String toString() {

        String str = "";
        String bkspc = "\n";

        str += "[" + questID + "]=== " + questName + " ===" + bkspc;
        str += "-> " + questTextFull + bkspc;
        for (Item i : itemRequirementsToComplete) {
            str += "[" + i.ID + "] ";
        }
        str += bkspc;
        str += requiredQuestTriggersToComplete;


        return str;
    }

}
