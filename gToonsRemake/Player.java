
import java.util.Deque;


public class Player {

    private String name;
    private Deque<Card> decklist;
    int playerIconID;


    public Deque<Card> getDecklist() {
        return decklist;
    }

    public void setDecklist(Deque<Card> decklist) {
        this.decklist = decklist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Player(String setName, Deque<Card> setDeckList,int setPlayerIconID) {
        this.name = setName;
        this.decklist = setDeckList;
        this.playerIconID = setPlayerIconID;
    }
}
