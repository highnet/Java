import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Main extends JFrame {

    final static int WIDTH = 1250;
    final static int HEIGHT = 650;


    public Main() {
        this.initialize();

    }

    public static void main(String[] args) throws FileNotFoundException {

        EventQueue.invokeLater(new Runnable() {
            public void run() {


                Main window = new Main();

                window.setSize(WIDTH, HEIGHT);

                GameEngine worldMap = new GameEngine();

                window.add(worldMap);


            }
        });


    }

    private void initialize() {

        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Risk");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 4)); // Sucks D.
        this.setVisible(true);

    }
}
