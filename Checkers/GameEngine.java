import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by bokense1 on 20/12/15.
 */
public class GameEngine extends JPanel implements MouseListener, ActionListener, MouseMotionListener, KeyListener {

    Tile currentlySelected = null;

    // row 1
    Tile tile0 = new Tile(0);
    Tile tile1 = new Tile(1);
    Tile tile2 = new Tile(0);
    Tile tile3 = new Tile(1);
    Tile tile4 = new Tile(0);
    Tile tile5 = new Tile(1);
    Tile tile6 = new Tile(0);
    Tile tile7 = new Tile(1);

    // row 2
    Tile tile8 = new Tile(1);
    Tile tile9 = new Tile(0);
    Tile tile10 = new Tile(1);
    Tile tile11 = new Tile(0);
    Tile tile12 = new Tile(1);
    Tile tile13 = new Tile(0);
    Tile tile14 = new Tile(1);
    Tile tile15 = new Tile(0);

    // row 3
    Tile tile16 = new Tile(0);
    Tile tile17 = new Tile(1);
    Tile tile18 = new Tile(0);
    Tile tile19 = new Tile(1);
    Tile tile20 = new Tile(0);
    Tile tile21 = new Tile(1);
    Tile tile22 = new Tile(0);
    Tile tile23 = new Tile(1);

    // row 4
    Tile tile24 = new Tile(0);
    Tile tile25 = new Tile(0);
    Tile tile26 = new Tile(0);
    Tile tile27 = new Tile(0);
    Tile tile28 = new Tile(0);
    Tile tile29 = new Tile(0);
    Tile tile30 = new Tile(0);
    Tile tile31 = new Tile(0);

    // row 5
    Tile tile32 = new Tile(0);
    Tile tile33 = new Tile(0);
    Tile tile34 = new Tile(0);
    Tile tile35 = new Tile(0);
    Tile tile36 = new Tile(0);
    Tile tile37 = new Tile(0);
    Tile tile38 = new Tile(0);
    Tile tile39 = new Tile(0);

    // row 6
    Tile tile40 = new Tile(2);
    Tile tile41 = new Tile(0);
    Tile tile42 = new Tile(2);
    Tile tile43 = new Tile(0);
    Tile tile44 = new Tile(2);
    Tile tile45 = new Tile(0);
    Tile tile46 = new Tile(2);
    Tile tile47 = new Tile(0);

    // row 7
    Tile tile48 = new Tile(0);
    Tile tile49 = new Tile(2);
    Tile tile50 = new Tile(0);
    Tile tile51 = new Tile(2);
    Tile tile52 = new Tile(0);
    Tile tile53 = new Tile(2);
    Tile tile54 = new Tile(0);
    Tile tile55 = new Tile(2);

    // row 8
    Tile tile56 = new Tile(2);
    Tile tile57 = new Tile(0);
    Tile tile58 = new Tile(2);
    Tile tile59 = new Tile(0);
    Tile tile60 = new Tile(2);
    Tile tile61 = new Tile(0);
    Tile tile62 = new Tile(2);
    Tile tile63 = new Tile(0);

    Timer timer = new Timer(16, this);

    public GameEngine() {


        timer.start();
        setFocusable(true); //allows keyboard input
        addKeyListener(this);
        addMouseListener(this);

    }

    private void paintChessBoard(Graphics g) {

        g.setColor(Color.white);
        g.fillRect(0, 0, 600, 600);

        g.setColor(Color.black);
        int i = 75;
        g.fillRect(i, 0, 75, 75);
        g.fillRect(i * 3, 0, 75, 75);
        g.fillRect(i * 5, 0, 75, 75);
        g.fillRect(i * 7, 0, 75, 75);

        g.fillRect(i, 75 * 2, 75, 75);
        g.fillRect(i * 3, 75 * 2, 75, 75);
        g.fillRect(i * 5, 75 * 2, 75, 75);
        g.fillRect(i * 7, 75 * 2, 75, 75);

        g.fillRect(i, 75 * 4, 75, 75);
        g.fillRect(i * 3, 75 * 4, 75, 75);
        g.fillRect(i * 5, 75 * 4, 75, 75);
        g.fillRect(i * 7, 75 * 4, 75, 75);

        g.fillRect(i, 75 * 6, 75, 75);
        g.fillRect(i * 3, 75 * 6, 75, 75);
        g.fillRect(i * 5, 75 * 6, 75, 75);
        g.fillRect(i * 7, 75 * 6, 75, 75);

        g.fillRect(0, 75, 75, 75);
        g.fillRect(75 * 2, 75, 75, 75);
        g.fillRect(75 * 4, 75, 75, 75);
        g.fillRect(75 * 6, 75, 75, 75);

        g.fillRect(0, 75 * 3, 75, 75);
        g.fillRect(75 * 2, 75 * 3, 75, 75);
        g.fillRect(75 * 4, 75 * 3, 75, 75);
        g.fillRect(75 * 6, 75 * 3, 75, 75);

        g.fillRect(0, 75 * 5, 75, 75);
        g.fillRect(75 * 2, 75 * 5, 75, 75);
        g.fillRect(75 * 4, 75 * 5, 75, 75);
        g.fillRect(75 * 6, 75 * 5, 75, 75);

        g.fillRect(0, 75 * 5, 75, 75);
        g.fillRect(75 * 2, 75 * 5, 75, 75);
        g.fillRect(75 * 4, 75 * 5, 75, 75);
        g.fillRect(75 * 6, 75 * 5, 75, 75);

        g.fillRect(0, 75 * 5, 75, 75);
        g.fillRect(75 * 2, 75 * 5, 75, 75);
        g.fillRect(75 * 4, 75 * 5, 75, 75);
        g.fillRect(75 * 6, 75 * 5, 75, 75);

        g.fillRect(0, 75 * 7, 75, 75);
        g.fillRect(75 * 2, 75 * 7, 75, 75);
        g.fillRect(75 * 4, 75 * 7, 75, 75);
        g.fillRect(75 * 6, 75 * 7, 75, 75);


    }



    int i = 0;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintChessBoard(g); //Paints the background chess board.
        paintCurrentlySelectedBorder(g);
        paintPieces(g);

    }

    private void paintPieces(Graphics g) {


        // row 1 - blue
        if (tile0.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(10, 10, 50, 50);
        }
        if (tile1.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(85, 10, 50, 50);
        }
        if (tile2.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(160, 10, 50, 50);
        }
        if (tile3.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(235, 10, 50, 50);
        }
        if (tile4.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(310, 10, 50, 50);
        }
        if (tile5.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(385, 10, 50, 50);
        }
        if (tile6.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(460, 10, 50, 50);
        }
        if (tile7.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(535, 10, 50, 50);
        }

        // row 2 - blue
        if (tile8.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(10, 90, 50, 50);
        }
        if (tile9.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(85, 90, 50, 50);
        }
        if (tile10.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(160, 90, 50, 50);
        }
        if (tile11.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(235, 90, 50, 50);
        }
        if (tile12.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(310, 90, 50, 50);
        }
        if (tile13.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(385, 90, 50, 50);
        }
        if (tile14.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(460, 90, 50, 50);
        }
        if (tile15.tileID == 1) {
            g.setColor(Color.blue);
            g.fillOval(535, 90, 50, 50);
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer) {

            //      System.out.println(currentlySelected);
            this.repaint();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_E) {
            System.out.println("pressed E");
        }



        if (code == KeyEvent.VK_ESCAPE) {
            System.out.println("pressed Escape");
            currentlySelected = null;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();
        System.out.println("Click:" + x + "," + y);


        if (currentlySelected == null) {
            currentlySelected = fieldAt(x, y);
        }

        if (currentlySelected != null && fieldAt(x, y).tileID == 0) {

            fieldAt(x, y).tileID = currentlySelected.tileID;
            currentlySelected.tileID = 0;
            currentlySelected = null;


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

    }


    private Tile fieldAt(int x, int y) {


        // ROW 1
        if (x > 0 && x < 75 && y > 0 && y < 75) {
            return tile0;
        }

        if (x > 75 && x < 150 && y > 0 && y < 75) {
            return tile1;
        }

        if (x > 150 && x < 225 && y > 0 && y < 75) {
            return tile2;
        }
        if (x > 225 && x < 300 && y > 0 && y < 75) {
            return tile3;
        }
        if (x > 300 && x < 375 && y > 0 && y < 75) {
            return tile4;
        }
        if (x > 375 && x < 450 && y > 0 && y < 75) {
            return tile5;
        }
        if (x > 450 && x < 525 && y > 0 && y < 75) {
            return tile6;
        }
        if (x > 525 && x < 600 && y > 0 && y < 75) {
            return tile7;
        }

        if (x > 0 && x < 75 && y > 75 && y < 150) {
            return tile8;
        }

        if (x > 75 && x < 150 && y > 75 && y < 150) {
            return tile9;
        }

        if (x > 150 && x < 225 && y > 75 && y < 150) {
            return tile10;
        }
        if (x > 225 && x < 300 && y > 75 && y < 150) {
            return tile11;
        }
        if (x > 300 && x < 375 && y > 75 && y < 150) {
            return tile12;
        }
        if (x > 375 && x < 450 && y > 75 && y < 150) {
            return tile13;
        }
        if (x > 450 && x < 525 && y > 75 && y < 150) {
            return tile14;
        }
        if (x > 525 && x < 600 && y > 75 && y < 150) {
            return tile15;
        }

        return null;
    }

    private void paintCurrentlySelectedBorder(Graphics g) {


        // ROW 1
        if (currentlySelected == tile0) {
            g.setColor(Color.orange);
            g.fillOval(8, 7, 55, 55);
        } else if (currentlySelected == tile1) {
            g.setColor(Color.orange);
            g.fillOval(83, 7, 55, 55);
        } else if (currentlySelected == tile2) {
            g.setColor(Color.orange);
            g.fillOval(157, 7, 55, 55);
        } else if (currentlySelected == tile3) {
            g.setColor(Color.orange);
            g.fillOval(233, 7, 55, 55);
        } else if (currentlySelected == tile4) {
            g.setColor(Color.orange);
            g.fillOval(317, 7, 55, 55);

        } else if (currentlySelected == tile5) {
            g.setColor(Color.orange);
            g.fillOval(382, 7, 55, 55);

        } else if (currentlySelected == tile6) {
            g.setColor(Color.orange);
            g.fillOval(457, 7, 55, 55);

        } else if (currentlySelected == tile7) {
            g.setColor(Color.orange);
            g.fillOval(532, 7, 55, 55);

        }

        // ROW 2

        if (currentlySelected == tile8) {
            g.setColor(Color.orange);
            g.drawOval(10, 90, 50, 50);
            g.drawOval(9, 90, 50, 50);
            g.drawOval(8, 90, 50, 50);

        } else if (currentlySelected == tile9) {
            g.setColor(Color.orange);
            g.drawOval(85, 90, 50, 50);
            g.drawOval(84, 90, 50, 50);
            g.drawOval(83, 90, 50, 50);


        } else if (currentlySelected == tile10) {
            g.setColor(Color.orange);
            g.drawOval(160, 90, 50, 50);
            g.drawOval(159, 90, 50, 50);
            g.drawOval(158, 90, 50, 50);
        } else if (currentlySelected == tile11) {
            g.setColor(Color.orange);
            g.drawOval(235, 90, 50, 50);
            g.drawOval(234, 90, 50, 50);
            g.drawOval(233, 90, 50, 50);
        } else if (currentlySelected == tile12) {
            g.setColor(Color.orange);
            g.drawOval(310, 90, 50, 50);
            g.drawOval(309, 90, 50, 50);
            g.drawOval(309, 90, 50, 50);
        } else if (currentlySelected == tile13) {
            g.setColor(Color.orange);
            g.drawOval(385, 90, 50, 50);
            g.drawOval(384, 90, 50, 50);
            g.drawOval(383, 90, 50, 50);
        } else if (currentlySelected == tile14) {
            g.setColor(Color.orange);
            g.drawOval(460, 90, 50, 50);
            g.drawOval(459, 90, 50, 50);
            g.drawOval(458, 90, 50, 50);
        } else if (currentlySelected == tile15) {
            g.setColor(Color.orange);
            g.drawOval(535, 90, 50, 50);
            g.drawOval(534, 90, 50, 50);
            g.drawOval(532, 90, 50, 50);
        }
    }
}

class Tile {
    int tileID;

    public Tile(int tileID) {
        this.tileID = tileID;
    }
}

