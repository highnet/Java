import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Deque;
import java.util.LinkedList;


/**
 * Created by bokense on 24-May-16.
 */
class DuelHandler{

      boolean drawPhase1 = true;
      boolean playphase1 = false;
      boolean playphase1_waitingOnPlay = false;
      boolean playphase1_revealCards_0 = false;
      boolean playphase1_revealCards_1 = false;
      boolean playphase1_revealCards_2 = false;
      boolean playphase1_revealCards_3 = false;
      boolean playphase1_revealCards_4 = false;
      boolean playphase1_revealCards_5 = false;
      boolean playphase1_revealCards_6 = false;
      boolean playphase1_revealCards_7 = false;
      boolean playphase1_revealCards_8 = false;
      boolean drawPhase2 = false;
      boolean playPhase2 = false;
      boolean mulliganOptionPhase = false;
      boolean mulliganOptionPhase_waitingOnOption = false;

    int cardsDrawn = 0;
    int cardsDrawn2 = 0;

    private Deque<Card> decklist_p1_human;
    private Card[] board_p1_human;
    private Card[] hand_p1_human;
    private int score_p1_human = 0;
    private int blueCount_p1_human;
    private int whiteCount_p1_human;
    private int blackCount_p1_human;
    private int redCount_p1_human;
    private int greenCount_p1_human;
    private int yellowCount_p1_human;

 //   private Deque<Card> decklist_p2_cpu;
    private Card[] board_p2_cpu;
    private Card[] hand_p2_cpu;
    private int score_p2_cpu = 0;
    private int blueCount_p2_cpu;
    private int whiteCount_p2_cpu;
    private int blackCount_p2_cpu;
    private int redCount_p2_cpu;
    private int greenCount_p2_cpu;
    private int yellowCount_p2_cpu;

    public AI AIhandler;
    public boolean playPhase2_waitingOnPlay;
    public boolean playPhase2_revealCards_0;
    public boolean playPhase2_revealCards_1;
    public boolean playPhase2_revealCards_2;
    public boolean playPhase2_revealCards_3;
    public boolean playPhase2_revealCards_4;
    public boolean playPhase2_revealCards_5;
    public boolean playPhase2_revealCards_6;
    public boolean resolveGamePhase;


    public boolean splashAnimation1Request = false;
    public boolean splashAnimation2Request = false;
    public boolean splashAnimation3Request = false;
    public boolean splashAnimation4Request = false;
    public boolean splashAnimation5Request = false;
    public boolean splashAnimation6Request = false;
    public boolean splashAnimation7Request = false;
    public boolean splashAnimation8Request = false;
    public boolean splashAnimation9Request = false;
    public boolean splashAnimation10Request = false;
    public boolean splashAnimation11Request = false;
    public boolean splashAnimation12Request = false;
    public boolean splashAnimation13Request = false;
    public boolean splashAnimation14Request = false;


    public DuelHandler(Deque<Card> set_decklist_p1_human) {
        this.board_p1_human = new Card[7];
        this.board_p2_cpu = new Card[7];

        for (int i = 0; i < 7; i++) {
            this.board_p1_human[i] = new Card("null");
            this.board_p2_cpu[i] = new Card("null");

        }

        this.hand_p1_human = new Card[6];
        this.hand_p2_cpu = new Card[8];
        for (int i = 0; i < 6; i++) {
            this.hand_p1_human[i] = new Card("null");

        }
        for (int i = 0; i < 8; i++) {
            this.hand_p2_cpu[i] = new Card("null");
        }
        this.decklist_p1_human = set_decklist_p1_human;


        AIhandler = new AI();
    }
    public Deque<Card> getDecklist_p1_human() {
        return decklist_p1_human;
    }

    public void setDecklist_p1_human(Deque<Card> decklist_p1_human) {
        this.decklist_p1_human = decklist_p1_human;
    }

    public Card[] getHand_p1_human() {
        return hand_p1_human;
    }

    public void setHand_p1_human(Card[] hand_p1_human) {
        this.hand_p1_human = hand_p1_human;
    }

    public Card[] getHand_p2_cpu() {
        return hand_p2_cpu;
    }

    public void setHand_p2_cpu(Card[] hand_p2_cpu) {
        this.hand_p2_cpu = hand_p2_cpu;
    }

    public int getYellowCount_p2_cpu() {
        return yellowCount_p2_cpu;
    }

    public void setYellowCount_p2_cpu(int yellowCount_p2_cpu) {
        this.yellowCount_p2_cpu = yellowCount_p2_cpu;
    }

    public Card[] getBoard_p1_human() {
        return board_p1_human;
    }

    public void setBoard_p1_human(Card[] board_p1_human) {
        this.board_p1_human = board_p1_human;
    }

    public int getScore_p1_human() {
        return score_p1_human;
    }

    public void setScore_p1_human(int score_p1_human) {
        this.score_p1_human = score_p1_human;
    }

    public int getBlueCount_p1_human() {
        return blueCount_p1_human;
    }

    public void setBlueCount_p1_human(int blueCount_p1_human) {
        this.blueCount_p1_human = blueCount_p1_human;
    }

    public int getWhiteCount_p1_human() {
        return whiteCount_p1_human;
    }

    public void setWhiteCount_p1_human(int whiteCount_p1_human) {
        this.whiteCount_p1_human = whiteCount_p1_human;
    }

    public int getBlackCount_p1_human() {
        return blackCount_p1_human;
    }

    public void setBlackCount_p1_human(int blackCount_p1_human) {
        this.blackCount_p1_human = blackCount_p1_human;
    }

    public int getRedCount_p1_human() {
        return redCount_p1_human;
    }

    public void setRedCount_p1_human(int redCount_p1_human) {
        this.redCount_p1_human = redCount_p1_human;
    }

    public int getGreenCount_p1_human() {
        return greenCount_p1_human;
    }

    public void setGreenCount_p1_human(int greenCount_p1_human) {
        this.greenCount_p1_human = greenCount_p1_human;
    }

    public int getYellowCount_p1_human() {
        return yellowCount_p1_human;
    }

    public void setYellowCount_p1_human(int yelloweCount_p1_human) {
        this.yellowCount_p1_human = yelloweCount_p1_human;
    }

    public Card[] getBoard_p2_cpu() {
        return board_p2_cpu;
    }

    public void setBoard_p2_cpu(Card[] board_p2_cpu) {
        this.board_p2_cpu = board_p2_cpu;
    }

    public int getScore_p2_cpu() {
        return score_p2_cpu;
    }

    public void setScore_p2_cpu(int score_p2_cpu) {
        this.score_p2_cpu = score_p2_cpu;
    }

    public int getBlueCount_p2_cpu() {
        return blueCount_p2_cpu;
    }

    public void setBlueCount_p2_cpu(int blueCount_p2_cpu) {
        this.blueCount_p2_cpu = blueCount_p2_cpu;
    }

    public int getWhiteCount_p2_cpu() {
        return whiteCount_p2_cpu;
    }

    public void setWhiteCount_p2_cpu(int whiteCount_p2_cpu) {
        this.whiteCount_p2_cpu = whiteCount_p2_cpu;
    }

    public int getBlackCount_p2_cpu() {
        return blackCount_p2_cpu;
    }

    public void setBlackCount_p2_cpu(int blackCount_p2_cpu) {
        this.blackCount_p2_cpu = blackCount_p2_cpu;
    }

    public int getRedCount_p2_cpu() {
        return redCount_p2_cpu;
    }

    public void setRedCount_p2_cpu(int redCount_p2_cpu) {
        this.redCount_p2_cpu = redCount_p2_cpu;
    }

    public int getGreenCount_p2_cpu() {
        return greenCount_p2_cpu;
    }

    public void setGreenCount_p2_cpu(int greenCount_p2_cpu) {
        this.greenCount_p2_cpu = greenCount_p2_cpu;
    }

    public int getYelloweCount_p2_cpu() {
        return yellowCount_p2_cpu;
    }

    public void setYelloweCount_p2_cpu(int yelloweCount_p2_cpu) {
        this.yellowCount_p2_cpu = yelloweCount_p2_cpu;
    }


    public boolean checkForConfirmPlayButtonPhase1() {


        for (int i = 0; i < 4; i++) {


            if (this.getBoard_p1_human()[i].getName().equals("null")) {
                return false;
            }
        }
        return true;
    }

    public boolean checkForConfirmPlayButtonPhase2() {

        for (int i = 4; i < 7; i++) {

            if (this.getBoard_p1_human()[i].getName().equals("null")) {
                return false;
            }
        }
        return true;
    }

    public void assignPoints(String player, int revealCardsPhase) {
        executeScript(player, revealCardsPhase);

    }


    private void executeScript(String player, int revealCardsPhase) {

        if (revealCardsPhase == 0) {
            Card cardPlayed = this.getBoard_p1_human()[0];
            cardScriptFork(cardPlayed, player, revealCardsPhase);
            executeRecalculateBoardValues(revealCardsPhase);
        } else if (revealCardsPhase == 1) {
            Card cardPlayed = this.getBoard_p2_cpu()[0];
            cardScriptFork(cardPlayed, player, revealCardsPhase);
            executeRecalculateBoardValues(revealCardsPhase);
        } else if (revealCardsPhase == 2) {
            Card cardPlayed = this.getBoard_p1_human()[1];
            cardScriptFork(cardPlayed, player, revealCardsPhase);
            executeRecalculateBoardValues(revealCardsPhase);
        } else if (revealCardsPhase == 3) {

            Card cardPlayed = this.getBoard_p2_cpu()[1];
            cardScriptFork(cardPlayed, player, revealCardsPhase);
            executeRecalculateBoardValues(revealCardsPhase);
        } else if (revealCardsPhase == 4) {
            Card cardPlayed = this.getBoard_p1_human()[2];
            cardScriptFork(cardPlayed, player, revealCardsPhase);
            executeRecalculateBoardValues(revealCardsPhase);
        } else if (revealCardsPhase == 5) {
            Card cardPlayed = this.getBoard_p2_cpu()[2];
            cardScriptFork(cardPlayed, player, revealCardsPhase);
            executeRecalculateBoardValues(revealCardsPhase);
        } else if (revealCardsPhase == 6) {
            Card cardPlayed = this.getBoard_p1_human()[3];
            cardScriptFork(cardPlayed, player, revealCardsPhase);
            executeRecalculateBoardValues(revealCardsPhase);
        } else if (revealCardsPhase == 7) {
            Card cardPlayed = this.getBoard_p2_cpu()[3];
            cardScriptFork(cardPlayed, player, revealCardsPhase);
            executeRecalculateBoardValues(revealCardsPhase);
        } else if (revealCardsPhase == 8) {
            Card cardPlayed = this.getBoard_p2_cpu()[4];
            cardScriptFork(cardPlayed, player, revealCardsPhase);
            executeRecalculateBoardValues(revealCardsPhase);
        } else if (revealCardsPhase == 9) {
            Card cardPlayed = this.getBoard_p1_human()[4];
            cardScriptFork(cardPlayed, player, revealCardsPhase);
            executeRecalculateBoardValues(revealCardsPhase);
        } else if (revealCardsPhase == 10) {
            Card cardPlayed = this.getBoard_p2_cpu()[5];
            cardScriptFork(cardPlayed, player, revealCardsPhase);
            executeRecalculateBoardValues(revealCardsPhase);
        } else if (revealCardsPhase == 11) {
            Card cardPlayed = this.getBoard_p1_human()[5];
            cardScriptFork(cardPlayed, player, revealCardsPhase);
            executeRecalculateBoardValues(revealCardsPhase);
        } else if (revealCardsPhase == 12) {
            Card cardPlayed = this.getBoard_p2_cpu()[6];
            cardScriptFork(cardPlayed, player, revealCardsPhase);
            executeRecalculateBoardValues(revealCardsPhase);
        } else if (revealCardsPhase == 13) {
            Card cardPlayed = this.getBoard_p1_human()[6];
            cardScriptFork(cardPlayed, player, revealCardsPhase);
            executeRecalculateBoardValues(revealCardsPhase);
        }
    }

    private void cardScriptFork(Card cardPlayed, String player, int revealCardsPhase) {

        if (cardPlayed.getName().equals("Tree")) {
            executeTreeScript(player, cardPlayed,revealCardsPhase);

        } else if (cardPlayed.getName().equals("Lumberjack")) {
            executeLumberjackScript(player, cardPlayed, revealCardsPhase);

        } else if (cardPlayed.getName().equals("Guard")) {
            executeGuardScript(player, cardPlayed, revealCardsPhase);
        } else if (cardPlayed.getName().equals("Randiq")) {
            executeRandiqScript(player, cardPlayed, revealCardsPhase);

        } else if (cardPlayed.getName().equals("LumberjackAxe")) {
            executeLumberjackAxeScript(player, cardPlayed, revealCardsPhase);

        } else if (cardPlayed.getName().equals("Toolmaker")) {
            executeToolmakerScript(player, cardPlayed, revealCardsPhase);

        } else if (cardPlayed.getName().equals("Shredder")) {
            executeShredderScript(player, cardPlayed, revealCardsPhase);

        } else if (cardPlayed.getName().equals("GreenDragon")) {
            executeGreenDragonScript(player, cardPlayed, revealCardsPhase);

        }
    }

    private void executeGreenDragonScript(String player, Card cardPlayed, int revealCardsPhase) {
        if (player.equals("p1_human")) {
            greenCount_p1_human++;

            Deque <Card> newDeck = new LinkedList<>();

            for(int i = 0; i < decklist_p1_human.size(); i++){
                newDeck.add(new Card("Tree"));
            }

            decklist_p1_human = newDeck;
            executeAnimationScript_splash(revealCardsPhase);

        } else if (player.equals("p2_cpu")) {
            greenCount_p2_cpu++;
        }


        switch (revealCardsPhase) {
            case 0:
                break;
            case 1:
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[0].currentValue += 4;
                }
                ///

                break;
            case 2:
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[0].currentValue += 4;
                }
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[0].currentValue += 4;
                }
                ///

                break;
            case 3:
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[0].currentValue  += 4;
                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[0].currentValue  += 4;
                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[1].currentValue  += 4;
                }
                ///

                break;
            case 4:

                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[0].currentValue  += 4;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[1].currentValue  += 4;
                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[0].currentValue  += 4;
                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[1].currentValue  += 4;
                }
                ///

                break;
            case 5:

                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[0].currentValue  += 4;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[1].currentValue  += 4;
                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[0].currentValue  += 4;
                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[1].currentValue  += 4;
                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[2].currentValue  += 4;
                }
///

                break;
            case 6:

                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[0].currentValue  += 4;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[1].currentValue  += 4;
                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[2].currentValue  += 4;
                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[0].currentValue  += 4;
                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[1].currentValue  += 4;
                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[2].currentValue  += 4;

                }
                ///

            case 7:

                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[0].currentValue  += 4;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[1].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[2].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[0].currentValue += 4;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[1].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[2].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[3].currentValue  += 4;

                }
                ///

                break;
            case 8:
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[0].currentValue  += 4;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[1].currentValue  += 4;

                }

                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[2].currentValue  += 4;

                }

                if (this.getBoard_p2_cpu()[3].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[3].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[0].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[1].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[2].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[3].currentValue  += 4;

                }
                ///

                break;
            case 9:
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[0].currentValue  += 4;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[1].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[2].currentValue += 4;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[3].currentValue  += 4;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[0].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[1].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[2].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[3].currentValue  += 4;

                }
                ///


                break;
            case 10:
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[0].currentValue  += 4;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[1].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[2].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[3].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[4].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[4].currentValue  += 4;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[0].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[1].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[2].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[3].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[4].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[4].currentValue  += 4;

                }
                ///

                break;
            case 11:
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[0].currentValue  += 4;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[1].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[2].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[3].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[4].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[4].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[5].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[5].currentValue  += 4;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[0].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[1].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[2].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[3].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[4].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[4].currentValue += 4;

                }
                ///

                break;
            case 12:
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[0].currentValue  += 4;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[1].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[2].currentValue += 4;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[3].currentValue += 4;

                }
                if (this.getBoard_p2_cpu()[4].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[4].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[5].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[5].currentValue  += 4;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[0].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[1].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[2].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[3].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[4].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[4].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[5].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[5].currentValue  += 4;

                }
                ///

                break;
            case 13:
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[0].currentValue += 4;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[1].currentValue += 4;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[2].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[3].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[4].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[4].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[5].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[5].currentValue  += 4;

                }
                if (this.getBoard_p2_cpu()[6].getArchetype().equals("forestry")) {
                    this.getBoard_p2_cpu()[6].currentValue  += 4;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[0].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[1].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[2].currentValue += 4;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[3].currentValue += 4;

                }
                if (this.getBoard_p1_human()[4].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[4].currentValue  += 4;

                }
                if (this.getBoard_p1_human()[5].getArchetype().equals("forestry")) {
                    this.getBoard_p1_human()[5].currentValue  += 4;

                }
                ///

                break;
        }


    }

    private void executeShredderScript(String player, Card cardPlayed, int revealCardsPhase)  {

        if (player.equals("p1_human")) {
            redCount_p1_human++;

        } else if (player.equals("p2_cpu")) {
            redCount_p2_cpu++;
        }

        executeAnimationScript_splash(revealCardsPhase);
        switch (revealCardsPhase) {
            case 0:
                break;
            case 1:
                if (this.getBoard_p1_human()[0].getColor().equals("green")) {
                    this.getBoard_p1_human()[0].currentValue -= 3;
                }

                ///


                break;
            case 2:
                if (this.getBoard_p1_human()[0].getColor().equals("green")) {
                    this.getBoard_p1_human()[0].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[0].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 3;
                }
                ///
                if (this.getBoard_p1_human()[0].getSubtype().equals("mech")){
                    cardPlayed.currentValue *= 2;
                }
                break;
            case 3:
                if (this.getBoard_p2_cpu()[0].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[0].getColor().equals("green")) {
                    this.getBoard_p1_human()[0].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[1].getColor().equals("green")) {
                    this.getBoard_p1_human()[1].currentValue -= 3;
                }
                ///

                if (this.getBoard_p2_cpu()[0].getSubtype().equals("mech")){
                    cardPlayed.currentValue *= 2;
                }
                break;
            case 4:
                if (this.getBoard_p2_cpu()[0].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[0].getColor().equals("green")) {
                    this.getBoard_p1_human()[0].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[1].getColor().equals("green")) {
                    this.getBoard_p1_human()[1].currentValue -= 3;
                }
                ///
                if (this.getBoard_p1_human()[1].getSubtype().equals("mech")){
                    cardPlayed.currentValue *= 2;

                }
                break;
            case 5:
                if (this.getBoard_p2_cpu()[0].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[0].getColor().equals("green")) {
                    this.getBoard_p1_human()[0].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[1].getColor().equals("green")) {
                    this.getBoard_p1_human()[1].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[2].getColor().equals("green")) {
                    this.getBoard_p1_human()[2].currentValue -= 3;
                }
                ///

                if (this.getBoard_p2_cpu()[1].getSubtype().equals("mech")){
                    cardPlayed.currentValue *= 2;
                }
                break;
            case 6:
                if (this.getBoard_p2_cpu()[0].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[0].getColor().equals("green")) {
                    this.getBoard_p1_human()[0].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[1].getColor().equals("green")) {
                    this.getBoard_p1_human()[1].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[2].getColor().equals("green")) {
                    this.getBoard_p1_human()[2].currentValue -= 3;
                }
                ///

                if (this.getBoard_p1_human()[2].getSubtype().equals("mech")){
                    cardPlayed.currentValue *= 2;
                }
                break;


            case 7:
                if (this.getBoard_p2_cpu()[0].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[0].getColor().equals("green")) {
                    this.getBoard_p1_human()[0].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[1].getColor().equals("green")) {
                    this.getBoard_p1_human()[1].currentValue -= 3;
                }

                if (this.getBoard_p1_human()[2].getColor().equals("green")) {
                    this.getBoard_p1_human()[2].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[3].getColor().equals("green")) {
                    this.getBoard_p1_human()[3].currentValue -= 3;
                }
                ///
                if (this.getBoard_p2_cpu()[2].getSubtype().equals("mech")){
                    cardPlayed.currentValue *= 2;
                }

                break;
            case 8:
                if (this.getBoard_p2_cpu()[0].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[3].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[3].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[0].getColor().equals("green")) {
                    this.getBoard_p1_human()[0].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[1].getColor().equals("green")) {
                    this.getBoard_p1_human()[1].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[2].getColor().equals("green")) {
                    this.getBoard_p1_human()[2].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[3].getColor().equals("green")) {
                    this.getBoard_p1_human()[3].currentValue -= 3;
                }
                ///

                break;
            case 9:
                if (this.getBoard_p2_cpu()[0].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[3].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[3].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[0].getColor().equals("green")) {
                    this.getBoard_p1_human()[0].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[1].getColor().equals("green")) {
                    this.getBoard_p1_human()[1].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[2].getColor().equals("green")) {
                    this.getBoard_p1_human()[2].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[3].getColor().equals("green")) {
                    this.getBoard_p1_human()[3].currentValue -= 3;
                }
                break;
            case 10:
                if (this.getBoard_p2_cpu()[0].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[3].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[3].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[4].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[4].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[0].getColor().equals("green")) {
                    this.getBoard_p1_human()[0].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[1].getColor().equals("green")) {
                    this.getBoard_p1_human()[1].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[2].getColor().equals("green")) {
                    this.getBoard_p1_human()[2].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[3].getColor().equals("green")) {
                    this.getBoard_p1_human()[3].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[4].getColor().equals("green")) {
                    this.getBoard_p1_human()[4].currentValue -= 3;
                }
                ///
                if (this.getBoard_p2_cpu()[4].getSubtype().equals("mech")){
                    cardPlayed.currentValue *= 2;
                }
                break;
            case 11:
                if (this.getBoard_p2_cpu()[0].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[3].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[3].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[4].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[4].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[5].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[5].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[0].getColor().equals("green")) {
                    this.getBoard_p1_human()[0].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[1].getColor().equals("green")) {
                    this.getBoard_p1_human()[1].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[2].getColor().equals("green")) {
                    this.getBoard_p1_human()[2].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[3].getColor().equals("green")) {
                    this.getBoard_p1_human()[3].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[4].getColor().equals("green")) {
                    this.getBoard_p1_human()[4].currentValue -= 3;
                }
                ///
                if (this.getBoard_p1_human()[4].getSubtype().equals("mech")){
                    cardPlayed.currentValue *= 2;
                }
                break;
            case 12:
                if (this.getBoard_p2_cpu()[0].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[3].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[3].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[4].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[4].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[5].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[5].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[0].getColor().equals("green")) {
                    this.getBoard_p1_human()[0].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[1].getColor().equals("green")) {
                    this.getBoard_p1_human()[1].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[2].getColor().equals("green")) {
                    this.getBoard_p1_human()[2].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[3].getColor().equals("green")) {
                    this.getBoard_p1_human()[3].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[4].getColor().equals("green")) {
                    this.getBoard_p1_human()[4].currentValue -= 3;
                }
                if (this.getBoard_p1_human()[5].getColor().equals("green")) {
                    this.getBoard_p1_human()[5].currentValue -= 3;
                }

             ///

                if (this.getBoard_p2_cpu()[5].getSubtype().equals("mech")){
                    cardPlayed.currentValue *= 2;
                }
                break;
            case 13:
                if (this.getBoard_p2_cpu()[0].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 3;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 3;

                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 3;

                }
                if (this.getBoard_p2_cpu()[3].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[3].currentValue -= 3;

                }
                if (this.getBoard_p2_cpu()[4].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[4].currentValue -= 3;

                }
                if (this.getBoard_p2_cpu()[5].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[5].currentValue -= 3;

                }
                if (this.getBoard_p2_cpu()[6].getColor().equals("green")) {
                    this.getBoard_p2_cpu()[6].currentValue -= 3;

                }

                if (this.getBoard_p1_human()[0].getColor().equals("green")) {
                    this.getBoard_p1_human()[0].currentValue -= 3;

                }
                if (this.getBoard_p1_human()[1].getColor().equals("green")) {
                    this.getBoard_p1_human()[1].currentValue -= 3;

                }
                if (this.getBoard_p1_human()[2].getColor().equals("green")) {
                    this.getBoard_p1_human()[2].currentValue -= 3;

                }
                if (this.getBoard_p1_human()[3].getColor().equals("green")) {
                    this.getBoard_p1_human()[3].currentValue -= 3;

                }
                if (this.getBoard_p1_human()[4].getColor().equals("green")) {
                    this.getBoard_p1_human()[4].currentValue -= 3;

                }
                if (this.getBoard_p1_human()[5].getColor().equals("green")) {
                    this.getBoard_p1_human()[5].currentValue -= 3;

                }
                ///

                if (this.getBoard_p1_human()[5].getSubtype().equals("mech")){
                    cardPlayed.currentValue *= 2;
                }
                break;
                
        }
    }

    private void executeToolmakerScript(String player, Card cardPlayed, int revealCardsPhase) {

        if (player.equals("p1_human")) {
            greenCount_p1_human++;

        } else if (player.equals("p2_cpu")) {
            greenCount_p2_cpu++;
        }

        executeAnimationScript_splash(revealCardsPhase);
        switch (revealCardsPhase) {
            case 0:
                break;
            case 1:
                if (this.getBoard_p1_human()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                break;
            case 2:
                if (this.getBoard_p1_human()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p2_cpu()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                break;
            case 3:
                if (this.getBoard_p2_cpu()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p1_human()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p1_human()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }

                break;
            case 4:

                if (this.getBoard_p2_cpu()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p2_cpu()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p1_human()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p1_human()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }

                break;
            case 5:

                if (this.getBoard_p2_cpu()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p2_cpu()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p1_human()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p1_human()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p1_human()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                break;
            case 6:

                if (this.getBoard_p2_cpu()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p2_cpu()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p2_cpu()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p1_human()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p1_human()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p1_human()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                break;
            case 7:

                if (this.getBoard_p2_cpu()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p2_cpu()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[3].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                break;
            case 8:
                if (this.getBoard_p2_cpu()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p2_cpu()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[3].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[3].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                break;
            case 9:
                if (this.getBoard_p2_cpu()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p2_cpu()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[3].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }

                if (this.getBoard_p1_human()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[3].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                break;
            case 10:
                if (this.getBoard_p2_cpu()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p2_cpu()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[3].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[4].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }

                if (this.getBoard_p1_human()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[3].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[4].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                break;
            case 11:
                if (this.getBoard_p2_cpu()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p2_cpu()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[3].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[4].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[5].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }

                if (this.getBoard_p1_human()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[3].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[4].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                break;
            case 12:
                if (this.getBoard_p2_cpu()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p2_cpu()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[3].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[4].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[5].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }

                if (this.getBoard_p1_human()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[3].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[4].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[5].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                break;
            case 13:
                if (this.getBoard_p2_cpu()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;
                }
                if (this.getBoard_p2_cpu()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[3].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[4].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[5].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p2_cpu()[6].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }

                if (this.getBoard_p1_human()[0].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[1].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[2].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[3].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[4].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                if (this.getBoard_p1_human()[5].getSubtype().equals("tool")) {
                    cardPlayed.currentValue *= 2;

                }
                break;
        }
    }

    private void executeLumberjackAxeScript(String player, Card cardPlayed, int revealCardsPhase) {
        if (player.equals("p1_human")) {
            greenCount_p2_cpu++;

        } else if (player.equals("p2_cpu")) {
            greenCount_p2_cpu++;
        }
        executeAnimationScript_splash(revealCardsPhase);
        switch (revealCardsPhase) {
            case 0:
                break;
            case 1:
                if (this.getBoard_p1_human()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                ///
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }

                break;
            case 2:
                if (this.getBoard_p1_human()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                ///
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                break;
            case 3:
                if (this.getBoard_p2_cpu()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                ///
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }

                break;
            case 4:

                if (this.getBoard_p2_cpu()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                ///
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }

                break;
            case 5:

                if (this.getBoard_p2_cpu()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                ///

                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                break;
            case 6:

                if (this.getBoard_p2_cpu()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                break;
            case 7:

                if (this.getBoard_p2_cpu()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                ///

                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                break;
            case 8:
                if (this.getBoard_p2_cpu()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                break;
            case 9:
                if (this.getBoard_p2_cpu()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p1_human()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                break;
            case 10:
                if (this.getBoard_p2_cpu()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[4].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p1_human()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[4].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[4].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[4].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                break;
            case 11:
                if (this.getBoard_p2_cpu()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[4].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[5].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p1_human()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[4].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[4].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[5].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[4].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                break;
            case 12:
                if (this.getBoard_p2_cpu()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[4].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[5].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p1_human()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[4].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[5].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[4].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[5].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[4].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[5].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                break;
            case 13:
                if (this.getBoard_p2_cpu()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[4].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[5].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[6].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p1_human()[0].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[4].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[5].getName().equals("Lumberjack")) {
                    cardPlayed.currentValue += 3;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[4].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[5].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p2_cpu()[6].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[4].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                if (this.getBoard_p1_human()[5].getArchetype().equals("forestry")) {
                    cardPlayed.currentValue += 1;

                }
                break;
        }
    }

    private void executeRandiqScript(String player, Card cardPlayed, int revealCardsPhase) {
        if (player.equals("p1_human")) {
            blackCount_p1_human++;

        } else if (player.equals("p2_cpu")) {
            blackCount_p2_cpu++;
        }
        executeAnimationScript_splash(revealCardsPhase);
        switch (revealCardsPhase) {
            case 0:
                break;
            case 1:
                if (this.getBoard_p1_human()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[0].currentValue += 2;
                }
                ///
                if (this.getBoard_p1_human()[0].getColor().equals("white")) {
                    this.getBoard_p1_human()[0].currentValue -= 2;
                }
                break;
            case 2:
                if (this.getBoard_p1_human()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[0].currentValue += 2;
                }
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[0].currentValue += 2;
                }
                ///

                if (this.getBoard_p1_human()[0].getColor().equals("white")) {
                    this.getBoard_p1_human()[0].currentValue -= 2;
                }
                if (this.getBoard_p2_cpu()[0].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 2;
                }
                break;
            case 3:
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[0].currentValue += 2;
                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[0].currentValue += 2;
                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[1].currentValue += 2;
                }
                ///
                if (this.getBoard_p2_cpu()[0].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 2;
                }
                if (this.getBoard_p1_human()[0].getColor().equals("white")) {
                    this.getBoard_p1_human()[0].currentValue -= 2;
                }
                if (this.getBoard_p1_human()[1].getColor().equals("white")) {
                    this.getBoard_p1_human()[1].currentValue -= 2;
                }

                break;
            case 4:

                if (this.getBoard_p2_cpu()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[0].currentValue += 2;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[1].currentValue += 2;
                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[0].currentValue += 2;
                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[1].currentValue += 2;
                }
                ///

                if (this.getBoard_p2_cpu()[0].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 2;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 2;
                }
                if (this.getBoard_p1_human()[0].getColor().equals("white")) {
                    this.getBoard_p1_human()[0].currentValue -= 2;
                }
                if (this.getBoard_p1_human()[1].getColor().equals("white")) {
                    this.getBoard_p1_human()[1].currentValue -= 2;
                }

                break;
            case 5:

                if (this.getBoard_p2_cpu()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[0].currentValue += 2;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[1].currentValue += 2;
                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[0].currentValue += 2;
                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[1].currentValue += 2;
                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[2].currentValue += 2;
                }
                ///
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("white")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 2;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("white")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 2;
                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("white")) {
                    this.getBoard_p1_human()[0].currentValue -= 2;
                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("white")) {
                    this.getBoard_p1_human()[1].currentValue -= 2;
                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("white")) {
                    this.getBoard_p1_human()[2].currentValue -= 2;
                }
                break;
            case 6:

                if (this.getBoard_p2_cpu()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[0].currentValue += 2;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[1].currentValue += 2;
                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[2].currentValue += 2;
                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[0].currentValue += 2;
                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[1].currentValue += 2;
                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[2].currentValue += 2;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 2;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 2;
                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 2;
                }
                if (this.getBoard_p1_human()[0].getColor().equals("white")) {
                    this.getBoard_p1_human()[0].currentValue -= 2;
                }
                if (this.getBoard_p1_human()[1].getColor().equals("white")) {
                    this.getBoard_p1_human()[1].currentValue -= 2;
                }
                if (this.getBoard_p1_human()[2].getColor().equals("white")) {
                    this.getBoard_p1_human()[2].currentValue -= 2;

                }
                break;
            case 7:

                if (this.getBoard_p2_cpu()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[0].currentValue += 2;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[1].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[2].currentValue += 2;

                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[0].currentValue += 2;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[1].currentValue += 2;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[2].currentValue += 2;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[3].currentValue += 2;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 2;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[0].getColor().equals("white")) {
                    this.getBoard_p1_human()[0].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[1].getColor().equals("white")) {
                    this.getBoard_p1_human()[1].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[2].getColor().equals("white")) {
                    this.getBoard_p1_human()[2].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[3].getColor().equals("white")) {
                    this.getBoard_p1_human()[3].currentValue -= 2;

                }
                break;
            case 8:
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[0].currentValue += 2;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[1].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[2].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[3].currentValue += 2;

                }
                if (this.getBoard_p1_human()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[0].currentValue += 2;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[1].currentValue += 2;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[2].currentValue += 2;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[3].currentValue += 2;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 2;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[3].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[3].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[0].getColor().equals("white")) {
                    this.getBoard_p1_human()[0].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[1].getColor().equals("white")) {
                    this.getBoard_p1_human()[1].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[2].getColor().equals("white")) {
                    this.getBoard_p1_human()[2].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[3].getColor().equals("white")) {
                    this.getBoard_p1_human()[3].currentValue -= 2;

                }
                break;
            case 9:
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[0].currentValue += 2;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[1].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[2].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[3].currentValue += 2;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[0].currentValue += 2;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[1].currentValue += 2;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[2].currentValue += 2;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[3].currentValue += 2;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 2;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[3].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[3].currentValue -= 2;

                }

                if (this.getBoard_p1_human()[0].getColor().equals("white")) {
                    this.getBoard_p1_human()[0].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[1].getColor().equals("white")) {
                    this.getBoard_p1_human()[1].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[2].getColor().equals("white")) {
                    this.getBoard_p1_human()[2].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[3].getColor().equals("white")) {
                    this.getBoard_p1_human()[3].currentValue -= 2;

                }
                break;
            case 10:
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[0].currentValue += 2;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[1].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[2].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[3].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[4].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[4].currentValue += 2;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[0].currentValue += 2;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[1].currentValue += 2;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[2].currentValue += 2;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[3].currentValue += 2;

                }
                if (this.getBoard_p1_human()[4].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[4].currentValue += 2;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 2;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[3].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[3].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[4].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[4].currentValue -= 2;

                }

                if (this.getBoard_p1_human()[0].getColor().equals("white")) {
                    this.getBoard_p1_human()[0].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[1].getColor().equals("white")) {
                    this.getBoard_p1_human()[1].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[2].getColor().equals("white")) {
                    this.getBoard_p1_human()[2].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[3].getColor().equals("white")) {
                    this.getBoard_p1_human()[3].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[4].getColor().equals("white")) {
                    this.getBoard_p1_human()[4].currentValue -= 2;

                }
                break;
            case 11:
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[0].currentValue += 2;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[1].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[2].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[3].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[4].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[4].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[5].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[5].currentValue += 2;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[0].currentValue += 2;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[1].currentValue += 2;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[2].currentValue += 2;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[3].currentValue += 2;

                }
                if (this.getBoard_p1_human()[4].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[4].currentValue += 2;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 2;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[3].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[3].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[4].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[4].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[5].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[5].currentValue -= 2;

                }

                if (this.getBoard_p1_human()[0].getColor().equals("white")) {
                    this.getBoard_p1_human()[0].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[1].getColor().equals("white")) {
                    this.getBoard_p1_human()[1].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[2].getColor().equals("white")) {
                    this.getBoard_p1_human()[2].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[3].getColor().equals("white")) {
                    this.getBoard_p1_human()[3].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[4].getColor().equals("white")) {
                    this.getBoard_p1_human()[4].currentValue -= 2;

                }
                break;
            case 12:
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[0].currentValue += 2;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[1].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[2].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[3].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[4].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[4].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[5].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[5].currentValue += 2;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[0].currentValue += 2;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[1].currentValue += 2;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[2].currentValue += 2;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[3].currentValue += 2;

                }
                if (this.getBoard_p1_human()[4].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[4].currentValue += 2;

                }
                if (this.getBoard_p1_human()[5].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[5].currentValue += 2;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 2;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[3].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[3].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[4].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[4].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[5].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[5].currentValue -= 2;

                }

                if (this.getBoard_p1_human()[0].getColor().equals("white")) {
                    this.getBoard_p1_human()[0].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[1].getColor().equals("white")) {
                    this.getBoard_p1_human()[1].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[2].getColor().equals("white")) {
                    this.getBoard_p1_human()[2].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[3].getColor().equals("white")) {
                    this.getBoard_p1_human()[3].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[4].getColor().equals("white")) {
                    this.getBoard_p1_human()[4].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[5].getColor().equals("white")) {
                    this.getBoard_p1_human()[5].currentValue -= 2;

                }
                break;
            case 13:
                if (this.getBoard_p2_cpu()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[0].currentValue += 2;
                }
                if (this.getBoard_p2_cpu()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[1].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[2].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[3].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[3].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[4].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[4].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[5].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[5].currentValue += 2;

                }
                if (this.getBoard_p2_cpu()[6].getArchetype().equals("imperial")) {
                    this.getBoard_p2_cpu()[6].currentValue += 2;

                }

                if (this.getBoard_p1_human()[0].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[0].currentValue += 2;

                }
                if (this.getBoard_p1_human()[1].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[1].currentValue += 2;

                }
                if (this.getBoard_p1_human()[2].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[2].currentValue += 2;

                }
                if (this.getBoard_p1_human()[3].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[3].currentValue += 2;

                }
                if (this.getBoard_p1_human()[4].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[4].currentValue += 2;

                }
                if (this.getBoard_p1_human()[5].getArchetype().equals("imperial")) {
                    this.getBoard_p1_human()[5].currentValue += 2;

                }
                ///
                if (this.getBoard_p2_cpu()[0].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[0].currentValue -= 2;
                }
                if (this.getBoard_p2_cpu()[1].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[1].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[2].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[2].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[3].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[3].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[4].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[4].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[5].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[5].currentValue -= 2;

                }
                if (this.getBoard_p2_cpu()[6].getColor().equals("white")) {
                    this.getBoard_p2_cpu()[6].currentValue -= 2;

                }

                if (this.getBoard_p1_human()[0].getColor().equals("white")) {
                    this.getBoard_p1_human()[0].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[1].getColor().equals("white")) {
                    this.getBoard_p1_human()[1].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[2].getColor().equals("white")) {
                    this.getBoard_p1_human()[2].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[3].getColor().equals("white")) {
                    this.getBoard_p1_human()[3].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[4].getColor().equals("white")) {
                    this.getBoard_p1_human()[4].currentValue -= 2;

                }
                if (this.getBoard_p1_human()[5].getColor().equals("white")) {
                    this.getBoard_p1_human()[5].currentValue -= 2;

                }
                break;
        }
    }

    private void executeGuardScript(String player, Card cardPlayed, int revealCardsPhase) {

        if (player.equals("p1_human")) {
            blackCount_p1_human++;

        } else if (player.equals("p2_cpu")) {
            blackCount_p2_cpu++;
        }

        switch (revealCardsPhase) {
            case 0:
                break;
            case 1:
                if (this.getBoard_p1_human()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                ///

                break;
            case 2:
                if (this.getBoard_p1_human()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                ///

                break;
            case 3:
                if (this.getBoard_p2_cpu()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                ///

                break;
            case 4:

                if (this.getBoard_p2_cpu()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                ///

                break;
            case 5:

                if (this.getBoard_p2_cpu()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
///

                break;
            case 6:

                if (this.getBoard_p2_cpu()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                ///

            case 7:

                if (this.getBoard_p2_cpu()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                ///

                break;
            case 8:
                if (this.getBoard_p2_cpu()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p2_cpu()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p2_cpu()[3].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                ///

                break;
            case 9:
                if (this.getBoard_p2_cpu()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p1_human()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                ///


                break;
            case 10:
                if (this.getBoard_p2_cpu()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[4].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p1_human()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[4].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                ///

                break;
            case 11:
                if (this.getBoard_p2_cpu()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[4].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[5].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p1_human()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[4].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                ///

                break;
            case 12:
                if (this.getBoard_p2_cpu()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[4].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[5].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p1_human()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[4].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[5].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                ///

                break;
            case 13:
                if (this.getBoard_p2_cpu()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[4].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[5].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[6].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p1_human()[0].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[4].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[5].getName().equals("Guard")) {
                    cardPlayed.currentValue += 3;

                }
                ///

                break;
        }
        executeAnimationScript_splash(revealCardsPhase);
    }

    private void executeRecalculateBoardValues(int revealCardsPhase) {



        score_p2_cpu = 0;
        score_p1_human = 0;
        switch (revealCardsPhase) {
            case 0:
                score_p1_human += this.getBoard_p1_human()[0].getCurrentValue();
                break;
            case 1:
                score_p1_human += this.getBoard_p1_human()[0].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[0].getCurrentValue();
                break;
            case 2:
                score_p1_human += this.getBoard_p1_human()[0].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[1].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[0].getCurrentValue();

                break;
            case 3:
                score_p1_human += this.getBoard_p1_human()[0].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[1].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[0].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[1].getCurrentValue();


                break;
            case 4:
                score_p1_human += this.getBoard_p1_human()[0].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[1].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[2].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[0].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[1].getCurrentValue();


                break;
            case 5:
                score_p1_human += this.getBoard_p1_human()[0].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[1].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[2].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[0].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[1].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[2].getCurrentValue();

                break;
            case 6:
                score_p1_human += this.getBoard_p1_human()[0].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[1].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[2].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[3].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[0].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[1].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[2].getCurrentValue();

                break;
            case 7:
                score_p1_human += this.getBoard_p1_human()[0].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[1].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[2].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[3].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[0].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[1].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[2].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[3].getCurrentValue();

                break;
            case 8:
                score_p1_human += this.getBoard_p1_human()[0].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[1].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[2].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[3].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[0].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[1].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[2].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[3].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[4].getCurrentValue();
                break;
            case 9:
                score_p1_human += this.getBoard_p1_human()[0].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[1].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[2].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[3].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[4].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[0].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[1].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[2].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[3].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[4].getCurrentValue();
                break;
            case 10:
                score_p1_human += this.getBoard_p1_human()[0].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[1].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[2].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[3].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[4].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[0].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[1].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[2].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[3].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[4].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[5].getCurrentValue();
                break;
            case 11:
                score_p1_human += this.getBoard_p1_human()[0].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[1].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[2].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[3].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[4].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[5].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[0].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[1].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[2].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[3].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[4].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[5].getCurrentValue();
                break;
            case 12:
                score_p1_human += this.getBoard_p1_human()[0].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[1].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[2].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[3].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[4].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[5].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[0].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[1].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[2].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[3].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[4].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[5].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[6].getCurrentValue();
                break;
            case 13:
                score_p1_human += this.getBoard_p1_human()[0].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[1].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[2].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[3].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[4].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[5].getCurrentValue();
                score_p1_human += this.getBoard_p1_human()[6].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[0].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[1].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[2].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[3].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[4].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[5].getCurrentValue();
                score_p2_cpu += this.getBoard_p2_cpu()[6].getCurrentValue();
                break;
        }
    }

    private void executeTreeScript(String player, Card cardPlayed, int revealCardsPhase) {
        if (player.equals("p1_human")) {
            greenCount_p1_human++;
        } else if (player.equals("p2_cpu")) {
            greenCount_p2_cpu++;
        }
        cardPlayed.currentValue += (this.greenCount_p2_cpu + this.greenCount_p1_human);

        executeAnimationScript_splash(revealCardsPhase);



    }

    private void executeAnimationScript_splash(int revealCardsPhase) {
        switch (revealCardsPhase){
            case 0:
                splashAnimation1Request = true;
                break;
            case 1:
                splashAnimation2Request = true;
                break;
            case 2:
                splashAnimation3Request = true;
                break;
            case 3:
                splashAnimation4Request = true;
                break;
            case 4:
                splashAnimation5Request = true;
                break;
            case 5:
                splashAnimation6Request = true;
                break;
            case 6:
                splashAnimation7Request = true;
                break;
            case 7:
                splashAnimation8Request = true;
                break;
            case 8:
                splashAnimation10Request = true;
                break;
            case 9:
                splashAnimation9Request = true;
                break;
            case 10:
                splashAnimation12Request = true;
                break;
            case 11:
                splashAnimation11Request = true;
                break;
            case 12:
                splashAnimation14Request = true;
                break;
            case 13:
                splashAnimation13Request = true;
                break;
        }
    }

    private void executeLumberjackScript(String player, Card cardPlayed, int revealCardsPhase) {
        if (player.equals("p1_human")) {
            redCount_p1_human++;

        } else if (player.equals("p2_cpu")) {
            redCount_p2_cpu++;
        }

        switch (revealCardsPhase) {
            case 0:
                break;
            case 1:
                if (this.getBoard_p1_human()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                break;
            case 2:
                if (this.getBoard_p1_human()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                break;
            case 3:
                if (this.getBoard_p2_cpu()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }

                break;
            case 4:

                if (this.getBoard_p2_cpu()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }

                break;
            case 5:

                if (this.getBoard_p2_cpu()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                break;
            case 6:

                if (this.getBoard_p2_cpu()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                break;
            case 7:

                if (this.getBoard_p2_cpu()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                break;
            case 8:
                if (this.getBoard_p2_cpu()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                break;
            case 9:
                if (this.getBoard_p2_cpu()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p1_human()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[3].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                break;
            case 10:
                if (this.getBoard_p2_cpu()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[4].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p1_human()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[4].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                break;
            case 11:
                if (this.getBoard_p2_cpu()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[4].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[5].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }

                if (this.getBoard_p1_human()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p1_human()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[4].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                break;
            case 12:
                if (this.getBoard_p2_cpu()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[4].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[5].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }

                if (this.getBoard_p1_human()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[4].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[5].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                break;
            case 13:
                if (this.getBoard_p2_cpu()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;
                }
                if (this.getBoard_p2_cpu()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[3].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[4].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[5].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p2_cpu()[6].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }

                if (this.getBoard_p1_human()[0].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[1].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[2].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[3].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[4].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                if (this.getBoard_p1_human()[5].getName().equals("Tree")) {
                    cardPlayed.currentValue += 3;

                }
                break;
        }
        executeAnimationScript_splash(revealCardsPhase);
    }


}

