import javax.swing.*;
import java.awt.*;
/**
 * created by Joaquin Telleria
 */
public class Main extends JFrame {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;

    public Main() {
        System.out.println("Attempting to initialize game window...");
        this.initialize();
    }

    public static void main(String[] args) {

        System.out.println("Launching Dice v0.1");
        ;

        EventQueue.invokeLater(new Runnable() {
            public void run() {

                Window window = new Main();
                GameEngine rollDie = new GameEngine();
                window.add(rollDie);

            }
        });

    }

    private void initialize() {
        this.setSize(WIDTH, HEIGHT+22);
        this.setResizable(false);
        String versionName = "Dice v0.1 by Joaquin Telleria";
        this.setTitle(versionName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //      setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1)); // Sucks D.
        this.setVisible(true);
        System.out.println("Succesfully initialized game window...");
    }
}
