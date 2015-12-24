import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by bokense1 on 21/12/15.
 */

import java.util.Deque;
import java.util.LinkedList;

public class GameEngine extends JPanel implements KeyListener, ActionListener {


    final int dimension;
    String direction;
    int xPos;
    int yPos;
    int trailSize;
    boolean hasCollided;
    int xPosFood;
    int yPosFood;
    boolean ateFood;


    Timer timer;

    Deque<Integer> xCoords; // TAIL TRAIL COORDINATES
    Deque<Integer> yCoords; // TAIL TRAIL COORDINATES

    int k = 0;
    int tick = 0;

    int gameSpeed = 10;


    public GameEngine() { // Game Engine CONSTRUCTOR.


        setFocusable(true); // Setting requires for keyboard listener.
        addKeyListener(this); // Adds keyboard listener.

        this.direction = "right"; // Start direction
        this.dimension = 30;
        this.xPos = 0;
        this.yPos = 0;

        this.timer = new Timer(gameSpeed, this);
        this.timer.start();
        trailSize = 0;
        hasCollided = false;
        ateFood = true;
        xCoords = new LinkedList<>();
        yCoords = new LinkedList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer && !hasCollided) {
            //        i++;
            k++;
            tick++;

            calculateDirection();
            if (ateFood) {
                int rngX = ((int) (Math.random() * (10)));
                int rngY = ((int) (Math.random() * (10)));

                xPosFood = (rngX) * 30;
                yPosFood = (rngY) * 30;
                ateFood = false;

            }



/*            int rng = ((int) (Math.random() * (5 - 1))) + 1;


            if (i == 5) {
                switch (rng) {
                    case 1:
                        direction = "up";
                        break;
                    case 2:
                        direction = "down";
                        break;
                    case 3:
                        direction = "right";
                        break;
                    case 4:
                        direction = "left";
                        break;
                }
                i = 0;
            }*/


            this.repaint();
        }

    }

    private void calculateDirection() {

        // START OF COLLISION DETECTION

        Deque copyOfX = new LinkedList<Integer>(xCoords);
        Deque copyOfY = new LinkedList<Integer>(yCoords);
        for (int i = 0; i < xCoords.size() - 1; i++) {


            int copyX = (int) copyOfX.poll();
            int copyY = (int) copyOfY.poll();

            if (xPos == copyX && yPos == copyY) {
                System.out.println("supposed collission between you at " + xPos + "," + yPos + " AND obstruction at " + copyX + ", " + copyY);

                hasCollided = true;

            }
        }

        if (xPos == xPosFood && yPos == yPosFood) {
            ateFood = true;
            trailSize++;
            if (gameSpeed != 2) { // Limits how low the delays goes (if it reaches -1 it will crash)
                timer.setDelay(gameSpeed--);
            }
        }

        // END OF  COLLISION DETECTION


        // GRID JUMP MOVEMENT
        if (tick == 50) {
            switch (direction) {
                case "up":
                    yPos -= 30;
                    break;
                case "down":
                    yPos += 30;
                    break;
                case "right":
                    xPos += 30;
                    break;
                case "left":
                    xPos -= 30;
                    break;

            }
            tick = 0;

        }

    }

    //Render object.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

         /*
            - Displays the Game Over Screen once a collision is detected.
            - This action occurs only once since the game stop rendering as soon as the condition is met

              */
        if (hasCollided) {
            g.setColor(Color.red);
            g.fillRect(0, 0, 300, 300);
            g.setColor(Color.white);
            g.drawString("GAME OVER", 120, 100);

        }


        if (!hasCollided) {

            // Paints the background (white rectangle)
            paintBackground(g);


            if (tick == 49) {

                // This code was added to fix a bug.
                // Not working as intended, some unknown bug is present.


                if (xCoords.size() > trailSize - 1) {
                    xCoords.removeFirst();
                    yCoords.removeFirst();
                }

                // offer your current coords to the snake tail trail.
                xCoords.offer(xPos);
                yCoords.offer(yPos);

            }

            // Create dummy queues to traverse through our trail Deques.
            Deque copyOfX = new LinkedList<Integer>(xCoords);
            Deque copyOfY = new LinkedList<Integer>(yCoords);


            System.out.println(copyOfX + "   " + copyOfY);


            // fades off objects from the tail trail deques.
            if (k == 50) { // k and j determine fade rate
                for (int j = 0; j < xCoords.size() - trailSize; j++) {
                    xCoords.pollFirst();
                    yCoords.pollFirst();
                }
                k = 0;
            }

            // Draws the entire tail trail deque.
            for (int i = 0; i < xCoords.size(); i++) {
                g.setColor(Color.BLUE);
                g.fillRect((int) copyOfX.poll(), (int) copyOfY.poll(), dimension, dimension);
            }


            // Draws the snake head (Player)
            g.setColor(Color.green);
            g.fillRect(xPos, yPos, dimension, dimension); // PLAYER

            // Draws the food object.
            g.setColor(Color.magenta);
            g.fillRect(xPosFood, yPosFood, dimension, dimension);


            //Draws the grid lines
            g.setColor(Color.darkGray);
            for (int i = 0; i <= 10; i++) {
                g.drawLine(0, i * 30, 300, i * 30);
            }
            for (int i = 0; i <= 10; i++) {
                g.drawLine(i * 30, 0, i * 30, 300);
            }
        }


    }

    // Paints background
    private void paintBackground(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 300, 300);
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }


    // KEYBOARD ACTION
    @Override
    public void keyPressed(KeyEvent e) {
        int keyPressed = e.getKeyCode();

        switch (keyPressed) {
            case KeyEvent.VK_W:
                System.out.println("W");
                direction = "up";
                break;
            case KeyEvent.VK_S:
                System.out.println("S");
                direction = "down";
                break;
            case KeyEvent.VK_D:
                System.out.println("D");
                direction = "right";
                break;
            case KeyEvent.VK_A:
                System.out.println("A");
                direction = "left";
                break;


        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

/* Deleted code but useful code.

/*            case "upright":
                xPos++;
                yPos--;
                break;
            case "upleft":
                xPos--;
                yPos--;
                break;
            case "downright":
                xPos++;
                yPos++;
                break;
            case "downleft":
                xPos--;
                yPos++;
                break;


        case KeyEvent.VK_E:
        System.out.println("E");
        direction = "upright";
        break;

        case KeyEvent.VK_Q:
        System.out.println("Q");
        direction = "upleft";
        break;

        case KeyEvent.VK_Y:
        System.out.println("Y");
        direction = "downleft";
        break;

        case KeyEvent.VK_C:
        System.out.println("C");
        direction = "downright";
        break;

        case KeyEvent.VK_SPACE:
        System.out.println("Space");
        direction = "";
        break;



 */
