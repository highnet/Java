import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;


/**
 * Created by bokense on 25-Mar-16.
 */
public class Main extends JFrame {
    public final static int WIDTH = 807;
    public final static int HEIGHT = 630;

    public Main() {
        this.initialize();

    }

    public static void main(String[] args) throws FileNotFoundException {

        EventQueue.invokeLater(() -> {


            Main window = new Main();

            window.setSize(WIDTH, HEIGHT);

            GameEngine game = new GameEngine();

            window.add(game);


        });


    }

    private void initialize() {

        this.setSize(WIDTH, HEIGHT);
        this.setTitle("v 0.35 Development");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1));
        this.setVisible(true);
        this.setResizable(false);

    }
}