import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main extends JFrame {
    public final static int WIDTH = 1024;
    public final static int HEIGHT = 768;

    public Main() {
        this.initialize();

    }

    public static void main(String[] args) throws FileNotFoundException {

        EventQueue.invokeLater(() -> {




            Main window = new Main();

            window.setSize(WIDTH, HEIGHT);

            GUIEngine game = new GUIEngine();

            window.add(game);


        });


    }

    private void initialize() {

        this.setSize(WIDTH, HEIGHT);
        this.setTitle("2WayChat v 0.1 Development");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 1));
        this.setVisible(true);
        this.setResizable(false);

    }


}
