/**
 * Created by bokense on 25-May-16.
 */
public class AI {
    public AI() {
    }


    public void generatePhase1Play(Card[] hand_p2_cpu, Card[] board_p2_cpu) {


        int rand1 = (int) (Math.random() * 6);
        int rand2 = (int) (Math.random() * 6);
        int rand3 = (int) (Math.random() * 6);
        int rand4 = (int) (Math.random() * 6);

        System.out.println("" + rand1 + "," + rand2 + "," + rand3 + "," + rand4);


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
        } else if (rand1 == 5) {
            board_p2_cpu[0] = new Card("Toolmaker");
        }

        if (rand2 == 0) {
            board_p2_cpu[1] = new Card("Lumberjack");
        } else if (rand2 == 1) {
            board_p2_cpu[1] = new Card("Tree");
        } else if (rand2 == 2) {
            board_p2_cpu[1] = new Card("Guard");
        } else if (rand2 == 3) {
            board_p2_cpu[1] = new Card("Randiq");
        } else if (rand2 == 4) {
            board_p2_cpu[1] = new Card("LumberjackAxe");
        }else if (rand2 == 5) {
            board_p2_cpu[1] = new Card("Toolmaker");
        }

        if (rand3 == 0) {
            board_p2_cpu[2] = new Card("Lumberjack");
        } else if (rand3 == 1) {
            board_p2_cpu[2] = new Card("Tree");
        } else if (rand3 == 2) {
            board_p2_cpu[2] = new Card("Guard");
        } else if (rand3 == 3) {
            board_p2_cpu[2] = new Card("Randiq");
        } else if (rand3 == 4) {
            board_p2_cpu[2] = new Card("LumberjackAxe");
        } else if (rand3 == 5) {
            board_p2_cpu[2] = new Card("Toolmaker");
        }

        if (rand4 == 0) {
            board_p2_cpu[3] = new Card("Lumberjack");
        } else if (rand4 == 1) {
            board_p2_cpu[3] = new Card("Tree");
        } else if (rand4 == 2) {
            board_p2_cpu[3] = new Card("Guard");
        } else if (rand4 == 3) {
            board_p2_cpu[3] = new Card("Randiq");
        } else if (rand4 == 4) {
            board_p2_cpu[3] = new Card("LumberjackAxe");
        } else if (rand4 == 5) {
            board_p2_cpu[3] = new Card("Toolmaker");
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

        int rand1 = (int) (Math.random() * 6);
        int rand2 = (int) (Math.random() * 6);
        int rand3 = (int) (Math.random() * 6);

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
        } else if (rand1 == 5) {
            board_p2_cpu[4] = new Card("Toolmaker");
        }

        if (rand2 == 0) {
            board_p2_cpu[5] = new Card("Lumberjack");
        } else if (rand2 == 1) {
            board_p2_cpu[5] = new Card("Tree");
        } else if (rand2 == 2) {
            board_p2_cpu[5] = new Card("Guard");
        } else if (rand2 == 3) {
            board_p2_cpu[5] = new Card("Randiq");
        } else if (rand2 == 4) {
            board_p2_cpu[5] = new Card("LumberjackAxe");
        } else if (rand2 == 5) {
            board_p2_cpu[5] = new Card("Toolmaker");
        }

        if (rand3 == 0) {
            board_p2_cpu[6] = new Card("Lumberjack");
        } else if (rand3 == 1) {
            board_p2_cpu[6] = new Card("Tree");
        } else if (rand3 == 2) {
            board_p2_cpu[6] = new Card("Guard");
        } else if (rand3 == 3) {
            board_p2_cpu[6] = new Card("Randiq");
        } else if (rand3 == 4) {
            board_p2_cpu[6] = new Card("LumberjackAxe");
        } else if (rand3 == 5) {
            board_p2_cpu[6] = new Card("Toolmaker");
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
