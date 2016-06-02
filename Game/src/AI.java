/**
 * Created by bokense on 25-May-16.
 */
public class AI {
    public AI() {
    }


    public void generatePhase1Play(Card[] hand_p2_cpu, Card[] board_p2_cpu) {



        board_p2_cpu[0] = GameEngine.generateRandomCard();
        board_p2_cpu[1] = GameEngine.generateRandomCard();
        board_p2_cpu[2] = GameEngine.generateRandomCard();
        board_p2_cpu[3] = GameEngine.generateRandomCard();

        System.out.println(board_p2_cpu[0]);
        System.out.println(board_p2_cpu[1]);
        System.out.println(board_p2_cpu[2]);
        System.out.println(board_p2_cpu[3]);



    }

    public void generatePhase2Play(Card[] hand_p2_cpu, Card[] board_p2_cpu) {

        board_p2_cpu[4] = GameEngine.generateRandomCard();
        board_p2_cpu[5] = GameEngine.generateRandomCard();
        board_p2_cpu[6] = GameEngine.generateRandomCard();


        System.out.println(board_p2_cpu[4]);
        System.out.println(board_p2_cpu[5]);
        System.out.println(board_p2_cpu[6]);
      /*
        board_p2_cpu[4] = new Card("Guard");
        board_p2_cpu[5] = new Card("Guard");
        board_p2_cpu[6] = new Card("Guard");
*/

    }
}
