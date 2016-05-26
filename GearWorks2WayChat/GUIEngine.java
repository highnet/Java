import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class GUIEngine extends JPanel implements MouseListener, MouseMotionListener, ActionListener, KeyListener {


    private Timer timer = new Timer(150,this);
    private int mouseDragX;
    private int mouseDragY;
    private double nextTime;
    private Map<String, BufferedImage> bufferedImageMap;

    private String msg1 = "";
    private  String msg2 = "";

    Network network;


    public GUIEngine() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this); // Adds keyboard listener.

        setFocusable(true); // Setting required for keyboard listener.

        startUp();


    }


    private void startUp() {

         network = new Network();

        timer.start();
        

    }


    private void generateDecks() {


    }

    @Override
    public void paintComponent(Graphics g) {            // paints and controls what is currently painted on screen.

        super.paintComponent(g);
        g.setColor(Color.gray);
        g.fillRect(0,0,Main.WIDTH,Main.HEIGHT);

        g.setColor(Color.black);

        g.drawString("user1(X) : " +msg1,250,250);

        g.drawString("user2(Y) : " +msg2,250,400);



       
    }

    private void paintMulliganOption(Graphics g) {


    }



    @Override
    public void actionPerformed(ActionEvent e) {

        
        if (e.getSource() == timer && paint()) {
            this.repaint();

            msg1 = network.getString(0);
            msg2 = network.getString(1);
        }

            

     
    }


    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {


        switch (e.getKeyCode()) { // Keyboard switch -> e.getKeyCode() returns a virtual keyboard int value

            case KeyEvent.VK_ESCAPE:


                break;


            case KeyEvent.VK_X:
                network.inputString(0);
                break;
            case KeyEvent.VK_Y:
                network.inputString(1);
                break;



        }
    }



    @Override
    public void keyReleased(KeyEvent e) {


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        requestFocusInWindow();

        int x = e.getX();
        int y = e.getY();

        System.out.println("==CLICK==");
        System.out.println(x + ", " + y);
        System.out.println("=========");

        if (e.getButton() == MouseEvent.BUTTON1) {





        } else if (e.getButton() == MouseEvent.BUTTON3) {


        }
    }



    


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {


    }

    @Override
    public void mouseMoved(MouseEvent e) {

        mouseDragX = e.getX();
        mouseDragY = e.getY();

    }


    public static String getUserInput() {
        Scanner stringIn = new Scanner(System.in);

        System.out.println("please enters string:");

        while (stringIn.hasNext()) {
            if (stringIn.hasNextLine()) {
                return stringIn.nextLine();
            } else {
                System.out.println("invalid input");
                stringIn.next();
            }
        }
        return null;
    }

    public static Integer getUserInputInt() {
        Scanner stringIn = new Scanner(System.in);

        System.out.println("please enters string:");

        while (stringIn.hasNext()) {
            if (stringIn.hasNextInt()) {
                return stringIn.nextInt();
            } else {
                System.out.println("invalid input");
                stringIn.next();
            }
        }
        return null;
    }

    private boolean paint() {
        // convert the time to seconds
        double currTime = (double) System.nanoTime() / 1000000000.0;

        boolean paintScreen;
        if (currTime >= nextTime) {
            // assign the time for the next update
            double delta = 0.04;
            nextTime += delta;
            paintScreen = true;


        } else {
            int sleepTime = (int) (1000.0 * (nextTime - currTime));
            // sanity check
            paintScreen = false;

            if (sleepTime > 0) {
                // sleep until the next update
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    // do nothing
                }
            }

        }
        return paintScreen;

    }

    private void loadSprites() {



    }


    private void loadBufferedImage(String filePath, String syntaxName) {

        BufferedImage bi;

        try {
            bi = ImageIO.read((new File("Data/GFX/" + filePath)));
            bufferedImageMap.put(syntaxName, bi);

        } catch (IOException ignored) {
        }
    }

}

