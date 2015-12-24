import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joaquin Telleria
 */
public class GameEngine extends JPanel implements MouseListener, ActionListener {

    boolean myTurn;
    int numberOfClicks = 0;
    Timer timer = new Timer(500, this);
    JLabel turnLbl = new JLabel("Tic-Tac-Toe");
    Circle circle1 = new Circle(80, 80, 100, 100);
    Circle circle2 = new Circle(250, 80, 100, 100);
    Circle circle3 = new Circle(450, 80, 100, 100);

    Circle circle4 = new Circle(79, 259, 100, 100);
    Circle circle5 = new Circle(249, 275, 100, 100);
    Circle circle6 = new Circle(438, 275, 100, 100);


    Circle circle7 = new Circle(79, 469, 100, 100);
    Circle circle8 = new Circle(238, 436, 100, 100);
    Circle circle9 = new Circle(423, 431, 100, 100);

    GameData gameData = new GameData();


    public GameEngine(boolean whoStartsFirst) {


        this.myTurn = whoStartsFirst;

        this.add(turnLbl);

        addMouseListener(this);

        timer.start();

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //    System.out.println(gameData.myMatrix);
        //    System.out.println(gameData.enemyMatrix);

        g.drawLine(200, 50, 200, 550);
        g.drawLine(400, 50, 400, 550);
        g.drawLine(50, 400, 550, 400);
        g.drawLine(50, 200, 550, 200);

        g.setColor(Color.BLUE);


        if (circle1.visible) {
            g.fillOval(circle1.x, circle1.y, circle1.width, circle1.height);
        }
        if (circle2.visible) {
            g.fillOval(circle2.x, circle2.y, circle2.width, circle2.height);
        }
        if (circle3.visible) {
            g.fillOval(circle3.x, circle3.y, circle3.width, circle3.height);
        }
        if (circle4.visible) {
            g.fillOval(circle4.x, circle4.y, circle4.width, circle4.height);
        }
        if (circle5.visible) {
            g.fillOval(circle5.x, circle5.y, circle5.width, circle5.height);
        }
        if (circle6.visible) {
            g.fillOval(circle6.x, circle6.y, circle6.width, circle6.height);
        }
        if (circle7.visible) {
            g.fillOval(circle7.x, circle7.y, circle7.width, circle7.height);
        }
        if (circle8.visible) {
            g.fillOval(circle8.x, circle8.y, circle8.width, circle8.height);
        }
        if (circle9.visible) {
            g.fillOval(circle9.x, circle9.y, circle9.width, circle9.height);
        }


        g.setColor(Color.red);

        if (gameData.enemyMatrix.get(1)) {
            g.drawLine(80, 80, 190, 190);
            g.drawLine(190, 80, 80, 190);
        }
        if (gameData.enemyMatrix.get(2)) {
            g.drawLine(200, 53, 373, 183);
            g.drawLine(373, 53, 200, 183);
        }
        if (gameData.enemyMatrix.get(3)) {
            g.drawLine(411, 56, 538, 182);
            g.drawLine(535, 59, 428, 172);
        }
        if (gameData.enemyMatrix.get(4)) {
            g.drawLine(59, 224, 167, 374);
            g.drawLine(179, 234, 73, 370);
        }
        if (gameData.enemyMatrix.get(5)) {
            g.drawLine(219, 226, 356, 369);
            g.drawLine(367, 224, 241, 357);
        }
        if (gameData.enemyMatrix.get(6)) {
            g.drawLine(427, 235, 515, 364);
            g.drawLine(530, 235, 427, 364);
        }
        if (gameData.enemyMatrix.get(7)) {
            g.drawLine(69, 421, 170, 544);
            g.drawLine(178, 422, 71, 533);
        }
        if (gameData.enemyMatrix.get(8)) {
            g.drawLine(236, 429, 359, 533);
            g.drawLine(372, 418, 263, 518);
        }
        if (gameData.enemyMatrix.get(9)) {
            g.drawLine(424, 425, 513, 529);
            g.drawLine(525, 439, 453, 527);
        }


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer) {


            if (!myTurn && !hasWon(gameData.myMatrix)) {
                while (true) {
                    int rng = (int) (Math.random() * ((9 - 1) + 1) + 1);
                    if (!gameData.myMatrix.get(rng) && !gameData.enemyMatrix.get(rng)) {
                        gameData.enemyMatrix.put(rng, true);
                        myTurn = true;
                        break;
                    }

                }

                if (hasLost(gameData.enemyMatrix)) {
                    turnLbl.setFont(new Font(turnLbl.getFont().getFontName(), Font.BOLD, 36));
                    turnLbl.setText("YOU LOST :(");
                }

            }

            this.repaint();

        }


    }

    private boolean hasLost(Map<Integer, Boolean> enemyMatrix) {


        if (enemyMatrix.get(1) && enemyMatrix.get(2) & enemyMatrix.get(3)) return true;
        if (enemyMatrix.get(1) && enemyMatrix.get(4) & enemyMatrix.get(7)) return true;
        if (enemyMatrix.get(2) && enemyMatrix.get(5) & enemyMatrix.get(8)) return true;
        if (enemyMatrix.get(1) && enemyMatrix.get(5) & enemyMatrix.get(9)) return true;
        if (enemyMatrix.get(4) && enemyMatrix.get(5) & enemyMatrix.get(6)) return true;
        if (enemyMatrix.get(7) && enemyMatrix.get(8) & enemyMatrix.get(9)) return true;
        if (enemyMatrix.get(3) && enemyMatrix.get(6) & enemyMatrix.get(9)) return true;
        if (enemyMatrix.get(3) && enemyMatrix.get(5) & enemyMatrix.get(7)) return true;

        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        numberOfClicks++;

        if (numberOfClicks == 4 && !hasLost(gameData.enemyMatrix) && !hasWon(gameData.myMatrix)) {
            turnLbl.setFont(new Font(turnLbl.getFont().getFontName(), Font.BOLD, 36));
            turnLbl.setText("DRAW!!!");
        }

        int x = e.getX();
        int y = e.getY();

        System.out.println("Click:" + x + "," + y);

        if (x > 50 && x < 200 && y > 50 && y < 200) {
            gameData.myMatrix.put(1, true);
            circle1.visible = true;
        } else if (x > 200 && x < 400 && y > 50 && y < 200) {
            gameData.myMatrix.put(2, true);
            circle2.visible = true;
        } else if (x > 400 && x < 600 && y > 50 && y < 200) {
            gameData.myMatrix.put(3, true);
            circle3.visible = true;
        } else if (x > 55 && x < 188 && y > 212 && y < 383) {
            gameData.myMatrix.put(4, true);
            circle4.visible = true;
        } else if (x > 213 && x < 374 && y > 212 && y < 383) {
            gameData.myMatrix.put(5, true);
            circle5.visible = true;
        } else if (x > 404 && x < 540 && y > 212 && y < 383) {
            gameData.myMatrix.put(6, true);
            circle6.visible = true;
        } else if (x > 50 && x < 186 && y > 404 && y < 535) {
            gameData.myMatrix.put(7, true);
            circle7.visible = true;
        } else if (x > 219 && x < 420 && y > 404 && y < 535) {
            gameData.myMatrix.put(8, true);
            circle8.visible = true;
        } else if (x > 409 && x < 547 && y > 404 && y < 535) {
            gameData.myMatrix.put(9, true);
            circle9.visible = true;
        }

        if (hasWon(gameData.myMatrix)) {
            turnLbl.setFont(new Font(turnLbl.getFont().getFontName(), Font.BOLD, 36));
            turnLbl.setText("YOU WON!!!");
        }

        myTurn = false;

    }

    private boolean hasWon(Map<Integer, Boolean> myMatrix) {

        if (myMatrix.get(1) && myMatrix.get(2) & myMatrix.get(3)) return true;
        if (myMatrix.get(1) && myMatrix.get(4) & myMatrix.get(7)) return true;
        if (myMatrix.get(2) && myMatrix.get(5) & myMatrix.get(8)) return true;
        if (myMatrix.get(1) && myMatrix.get(5) & myMatrix.get(9)) return true;
        if (myMatrix.get(4) && myMatrix.get(5) & myMatrix.get(6)) return true;
        if (myMatrix.get(7) && myMatrix.get(8) & myMatrix.get(9)) return true;
        if (myMatrix.get(3) && myMatrix.get(6) & myMatrix.get(9)) return true;
        if (myMatrix.get(3) && myMatrix.get(5) & myMatrix.get(7)) return true;

        return false;

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

    private class Circle {
        int x;
        int y;
        int width;
        int height;

        boolean visible = false;

        public Circle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }

    private class GameData {

        Map<Integer, Boolean> myMatrix = new HashMap<>();
        Map<Integer, Boolean> enemyMatrix = new HashMap<>();


        public GameData() {

            myMatrix.put(1, false);
            myMatrix.put(2, false);
            myMatrix.put(3, false);
            myMatrix.put(4, false);
            myMatrix.put(5, false);
            myMatrix.put(6, false);
            myMatrix.put(7, false);
            myMatrix.put(8, false);
            myMatrix.put(9, false);

            enemyMatrix.put(1, false);
            enemyMatrix.put(2, false);
            enemyMatrix.put(3, false);
            enemyMatrix.put(4, false);
            enemyMatrix.put(5, false);
            enemyMatrix.put(6, false);
            enemyMatrix.put(7, false);
            enemyMatrix.put(8, false);
            enemyMatrix.put(9, false);

        }

    }
}
