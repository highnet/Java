import javax.swing.*;
import java.awt.*;
import java.util.Scanner;


/**
 * created by Joaquin Telleria
 */
public class Main extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;


    public Main() {
        System.out.println("Attempting to initialize game window...");
        this.initialize();
    }

    public static void main(String[] args) {

        System.out.println("Launching TicTacToe v0.1");


        //  Scanner sc = new Scanner(args[0]);
        //  boolean iStartFirst = Boolean.parseBoolean(sc.next());

        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to start first?");
        int iStartFirst = sc.nextInt();

        EventQueue.invokeLater(new Runnable() {
            public void run() {

                Window window = new Main();
                GameEngine tictactoe = new GameEngine(iStartFirst == 1);
                window.add(tictactoe);


            }
        });


    }

    private void initialize() {
        this.setSize(WIDTH, HEIGHT);
        String versionName = "Tic-Tac-Toe v0.1 by Joaquin Telleria)";
        this.setTitle(versionName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //      setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 4)); // Sucks D.
        this.setVisible(true);
        System.out.println("Succesfully initialized game window...");
    }
}
