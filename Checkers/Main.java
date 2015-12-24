import javax.swing.*;
import java.awt.*;
import java.util.Scanner;


/**
 * created by Joaquin Telleria
 */
public class Main extends JFrame {

    public static final int WIDTH = 650;
    public static final int HEIGHT = 650;

    public Main() {
        System.out.println("Attempting to initialize game window...");
        this.initialize();
    }

    public static void main(String[] args) {

        System.out.println("Launching Checkers v0.1");


        //  Scanner sc = new Scanner(args[0]);
        //  boolean iStartFirst = Boolean.parseBoolean(sc.next());

        EventQueue.invokeLater(new Runnable() {
            public void run() {

                Window window = new Main();
                GameEngine checkers = new GameEngine();
                window.add(checkers);


            }
        });

    }

    private void initialize() {
        this.setSize(WIDTH, HEIGHT);
        String versionName = "Checkers v0.2 by Joaquin Telleria";
        this.setTitle(versionName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //      setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1)); // Sucks D.
        this.setVisible(true);
        System.out.println("Succesfully initialized game window...");
    }
}
