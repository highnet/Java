/**
 * Created by bokense on 25-May-16.
 */
public class AI {
    public AI() {
    }


    public void generatePhase1Play(Card[] hand_p2_cpu, Card[] board_p2_cpu) {


        int rand1 = (int) (Math.random() * 5);
        int rand2 = (int) (Math.random() * 5);
        int rand3 = (int) (Math.random() * 5);
        int rand4 = (int) (Math.random() * 5);


        if (rand1 == 0) {
            board_p2_cpu[0] = new Card("Lumberjack");
        } else if (rand1 == 1) {
            board_p2_cpu[0] = new Card("Tree");
        } else if (rand1 == 2) {
            board_p2_cpu[0] = new Card("Guard");
        } else if (rand1 == 3) {
            board_p2_cpu[0] = new Card("Randiq");
        } else if (rand1 == 4) {
            board_p2_cpu[0] = new Card("LumberjackAxe");
        }

        if (rand2 == 0) {
            board_p2_cpu[1] = new Card("Lumberjack");
        } else if (rand1 == 1) {
            board_p2_cpu[1] = new Card("Tree");
        } else if (rand1 == 2) {
            board_p2_cpu[1] = new Card("Guard");
        } else if (rand1 == 3) {
            board_p2_cpu[1] = new Card("Randiq");
        } else if (rand1 == 4) {
            board_p2_cpu[1] = new Card("LumberjackAxe");
        }

        if (rand3 == 0) {
            board_p2_cpu[2] = new Card("Lumberjack");
        } else if (rand1 == 1) {
            board_p2_cpu[2] = new Card("Tree");
        } else if (rand1 == 2) {
            board_p2_cpu[2] = new Card("Guard");
        } else if (rand1 == 3) {
            board_p2_cpu[2] = new Card("Randiq");
        } else if (rand1 == 4) {
            board_p2_cpu[2] = new Card("LumberjackAxe");
        }

        if (rand4 == 0) {
            board_p2_cpu[3] = new Card("Lumberjack");
        } else if (rand1 == 1) {
            board_p2_cpu[3] = new Card("Tree");
        } else if (rand1 == 2) {
            board_p2_cpu[3] = new Card("Guard");
        } else if (rand1 == 3) {
            board_p2_cpu[3] = new Card("Randiq");
        } else if (rand1 == 4) {
            board_p2_cpu[3] = new Card("LumberjackAxe");
        }

        System.out.println(board_p2_cpu[0]);
        System.out.println(board_p2_cpu[1]);
        System.out.println(board_p2_cpu[2]);
        System.out.println(board_p2_cpu[3]);


  /*
        board_p2_cpu[1] = new Card("Guard");
        board_p2_cpu[1] = new Card("Guard");
        board_p2_cpu[2] = new Card("Guard");
        board_p2_cpu[3] = new Card("Guard");
*/

    }

    public void generatePhase2Play(Card[] hand_p2_cpu, Card[] board_p2_cpu) {

        int rand1 = (int) (Math.random() * 5);
        int rand2 = (int) (Math.random() * 5);
        int rand3 = (int) (Math.random() * 5);

        if (rand1 == 0) {
            board_p2_cpu[4] = new Card("Lumberjack");
        } else if (rand1 == 1) {
            board_p2_cpu[4] = new Card("Tree");
        } else if (rand1 == 2) {
            board_p2_cpu[4] = new Card("Guard");
        } else if (rand1 == 3) {
            board_p2_cpu[4] = new Card("Randiq");
        } else if (rand1 == 4) {
            board_p2_cpu[4] = new Card("LumberjackAxe");
        }

        if (rand2 == 0) {
            board_p2_cpu[5] = new Card("Lumberjack");
        } else if (rand1 == 1) {
            board_p2_cpu[5] = new Card("Tree");
        } else if (rand1 == 2) {
            board_p2_cpu[5] = new Card("Guard");
        } else if (rand1 == 3) {
            board_p2_cpu[5] = new Card("Randiq");
        } else if (rand1 == 4) {
            board_p2_cpu[5] = new Card("LumberjackAxe");
        }

        if (rand3 == 0) {
            board_p2_cpu[6] = new Card("Lumberjack");
        } else if (rand1 == 1) {
            board_p2_cpu[6] = new Card("Tree");
        } else if (rand1 == 2) {
            board_p2_cpu[6] = new Card("Guard");
        } else if (rand1 == 3) {
            board_p2_cpu[6] = new Card("Randiq");
        } else if (rand1 == 4) {
            board_p2_cpu[6] = new Card("LumberjackAxe");
        }

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
