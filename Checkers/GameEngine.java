import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joaquin Telleria
 */

public class GameEngine extends JPanel implements MouseListener, ActionListener, MouseMotionListener, KeyListener {


    Timer timer = new Timer(500, this);
    GameData gameData = new GameData();
    GameData.Piece currentlySelected = null;


    public GameEngine() {


        setFocusable(true); // lets keyboard work
        addKeyListener(this);
        addMouseListener(this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        paintChessBoard(g); //Paints the background chess board.
        paintPieces(g);
        if (currentlySelected != null) {
            paintCurrentlySelectedBorder(g);
        }

    }

    private void paintCurrentlySelectedBorder(Graphics g) {

        if (currentlySelected == gameData.p1) {
            g.setColor(Color.orange);
            g.drawOval(10, 10, 50, 50);
            g.drawOval(9, 10, 50, 50);
            g.drawOval(8, 10, 50, 50);
        } else if (currentlySelected == gameData.p2) {
            g.setColor(Color.orange);
            g.drawOval(85, 10, 50, 50);
            g.drawOval(84, 10, 50, 50);
            g.drawOval(83, 10, 50, 50);
        } else if (currentlySelected == gameData.p3) {
            g.setColor(Color.orange);
            g.drawOval(160, 10, 50, 50);
            g.drawOval(159, 10, 50, 50);
            g.drawOval(158, 10, 50, 50);
        } else if (currentlySelected == gameData.p4) {
            g.setColor(Color.orange);
            g.drawOval(235, 10, 50, 50);
            g.drawOval(234, 10, 50, 50);
            g.drawOval(233, 10, 50, 50);
        } else if (currentlySelected == gameData.p5) {
            g.setColor(Color.orange);
            g.drawOval(310, 10, 50, 50);
            g.drawOval(309, 10, 50, 50);
            g.drawOval(309, 10, 50, 50);
        } else if (currentlySelected == gameData.p6) {
            g.setColor(Color.orange);
            g.drawOval(385, 10, 50, 50);
            g.drawOval(384, 10, 50, 50);
            g.drawOval(383, 10, 50, 50);
        } else if (currentlySelected == gameData.p7) {
            g.setColor(Color.orange);
            g.drawOval(460, 10, 50, 50);
            g.drawOval(459, 10, 50, 50);
            g.drawOval(458, 10, 50, 50);
        } else if (currentlySelected == gameData.p8) {
            g.setColor(Color.orange);
            g.drawOval(535, 10, 50, 50);
            g.drawOval(534, 10, 50, 50);
            g.drawOval(532, 10, 50, 50);
        }
    }

    private void paintPieces(Graphics g) {

        // ROW 1

        if (gameData.mapData.get(1).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(10, 10, 50, 50);
        }

        if (gameData.mapData.get(2).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(85, 10, 50, 50);
        }

        if (gameData.mapData.get(3).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(160, 10, 50, 50);
        }
        if (gameData.mapData.get(4).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(235, 10, 50, 50);
        }
        if (gameData.mapData.get(5).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(310, 10, 50, 50);
        }
        if (gameData.mapData.get(6).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(385, 10, 50, 50);
        }
        if (gameData.mapData.get(7).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(460, 10, 50, 50);
        }
        if (gameData.mapData.get(8).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(535, 10, 50, 50);
        }

        //ROW 2

        if (gameData.mapData.get(9).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(10, 90, 50, 50);
        }
        if (gameData.mapData.get(10).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(85, 90, 50, 50);
        }
        if (gameData.mapData.get(11).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(160, 90, 50, 50);
        }
        if (gameData.mapData.get(12).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(235, 90, 50, 50);
        }
        if (gameData.mapData.get(13).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(310, 90, 50, 50);
        }
        if (gameData.mapData.get(14).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(385, 90, 50, 50);
        }
        if (gameData.mapData.get(15).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(460, 90, 50, 50);
        }
        if (gameData.mapData.get(16).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(535, 90, 50, 50);
        }

        // ROW 3
        if (gameData.mapData.get(17).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(10, 170, 50, 50);
        }
        if (gameData.mapData.get(18).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(85, 170, 50, 50);
        }
        if (gameData.mapData.get(19).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(160, 170, 50, 50);
        }
        if (gameData.mapData.get(20).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(235, 170, 50, 50);
        }
        if (gameData.mapData.get(21).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(310, 170, 50, 50);
        }
        if (gameData.mapData.get(22).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(385, 170, 50, 50);
        }
        if (gameData.mapData.get(23).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(460, 170, 50, 50);
        }
        if (gameData.mapData.get(24).color.equals(Color.blue)) {
            g.setColor(Color.blue);
            g.fillOval(535, 170, 50, 50);
        }

        // ROW 8
        if (gameData.mapData.get(62).color.equals(Color.red)) {
            g.setColor(Color.red);
            g.fillOval(385, 535, 50, 50);
        }

        if (gameData.mapData.get(64).color.equals(Color.red)) {
            g.setColor(Color.red);
            g.fillOval(535, 535, 50, 50);
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer) {

            //      System.out.println(currentlySelected);
            this.repaint();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();
        System.out.println("Click:" + x + "," + y);


        if (currentlySelected != null) {
            gameData.executeMove(currentlySelected, pieceAt(fieldAt(x, y)));
        } else if (x > 0 && x < 75 && y > 0 && y < 75) {
            System.out.println("clicked square 1");

            if (gameData.mapData.get(1).color == Color.red || gameData.mapData.get(1).color == Color.blue  && gameData.mapData.get(1).color != Color.white) {
                currentlySelected = gameData.p1;
            }
        } else if (x > 75 && x < 150 && y > 0 && y < 75) {
            System.out.println("clicked square 2");

            if (gameData.mapData.get(2).color == Color.red || gameData.mapData.get(2).color == Color.blue && gameData.mapData.get(2).color != Color.white) {
                currentlySelected = gameData.p2;
            }
        } else if (x > 150 && x < 225 && y > 0 && y < 75) {
            System.out.println("clicked square 3");

            if (gameData.mapData.get(3).color == Color.red || gameData.mapData.get(3).color == Color.blue && gameData.mapData.get(3).color != Color.white) {
                currentlySelected = gameData.p3;
            }
        } else if (x > 225 && x < 300 && y > 0 && y < 75) {
            System.out.println("clicked square 4");

            if (gameData.mapData.get(4).color == Color.red || gameData.mapData.get(4).color == Color.blue && gameData.mapData.get(4).color != Color.white) {
                currentlySelected = gameData.p4;
            }
        }


       else if (x > 300 && x < 375 && y > 0 && y < 75) {
            System.out.println("clicked square 5");

            if (gameData.mapData.get(5).color == Color.red || gameData.mapData.get(5).color == Color.blue && gameData.mapData.get(5).color != Color.white) {
                currentlySelected = gameData.p5;
            }
        } else if (x > 375 && x < 450 && y > 0 && y < 75) {
            System.out.println("clicked square 6");

            if (gameData.mapData.get(6).color == Color.red || gameData.mapData.get(6).color == Color.blue && gameData.mapData.get(6).color != Color.white) {
                currentlySelected = gameData.p6;
            }
        } else if (x > 450 && x < 525 && y > 0 && y < 75) {
            System.out.println("clicked square 7");

            if (gameData.mapData.get(7).color == Color.red || gameData.mapData.get(7).color == Color.blue && gameData.mapData.get(7).color != Color.white) {
                currentlySelected = gameData.p7;
            }
        } else if (x > 525 && x < 600 && y > 0 && y < 75) {
            System.out.println("clicked square 8");

            if (gameData.mapData.get(8).color == Color.red || gameData.mapData.get(8).color == Color.blue && gameData.mapData.get(8).color != Color.white) {
                currentlySelected = gameData.p8;
            }
        }


    }

    private GameEngine.GameData.Piece pieceAt(int i) {

        return gameData.mapData.get(i);
    }

    private int fieldAt(int x, int y) {

        if (x > 0 && x < 75 && y > 0 && y < 75) {
            return 1;
        }

        if (x > 75 && x < 150 && y > 0 && y < 75) {
            return 2;
        }

        if (x > 150 && x < 225 && y > 0 && y < 75) {
            return 3;
        }
        if (x > 225 && x < 300 && y > 0 && y < 75) {
            return 4;
        }
        if (x > 300 && x < 375 && y > 0 && y < 75) {
            return 5;
        }
        if (x > 375 && x < 450 && y > 0 && y < 75) {
            return 6;
        }
        if (x > 450 && x < 525 && y > 0 && y < 75) {
            return 7;
        }
        if (x > 525 && x < 600 && y > 0 && y < 75) {
            return 8;
        }


        return -1;
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ESCAPE) {
            System.out.println("pressed escape");
            currentlySelected = null;
        }

        if (code == KeyEvent.VK_E) {
            System.out.println("pressed E");
            for (int i = 1; i <= 8; i++) {
                System.out.println("" + gameData.mapData.get(i).location + gameData.mapData.get(i).color);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    private class GameData {

        Map<Integer, Piece> mapData = new HashMap<>();

        Piece p1 = new Piece(Color.blue, 1);
        Piece p2 = new Piece(Color.white, 2);
        Piece p3 = new Piece(Color.blue, 3);
        Piece p4 = new Piece(Color.white, 4);
        Piece p5 = new Piece(Color.blue, 5);
        Piece p6 = new Piece(Color.white, 6);
        Piece p7 = new Piece(Color.blue, 7);
        Piece p8 = new Piece(Color.white, 8);
        Piece p9 = new Piece(Color.white, 9);
        Piece p10 = new Piece(Color.blue, 10);
        Piece p11 = new Piece(Color.white, 11);
        Piece p12 = new Piece(Color.blue, 12);
        Piece p13 = new Piece(Color.white, 13);
        Piece p14 = new Piece(Color.blue, 14);
        Piece p15 = new Piece(Color.white, 15);
        Piece p16 = new Piece(Color.blue, 16);
        Piece p17 = new Piece(Color.blue, 17);
        Piece p18 = new Piece(Color.white, 18);
        Piece p19 = new Piece(Color.blue, 19);
        Piece p20 = new Piece(Color.white, 20);
        Piece p21 = new Piece(Color.blue, 21);
        Piece p22 = new Piece(Color.white, 22);
        Piece p23 = new Piece(Color.blue, 23);
        Piece p24 = new Piece(Color.white, 24);
        Piece p25 = new Piece(Color.blue, 25);
        Piece p26 = new Piece(Color.white, 26);
        Piece p27 = new Piece(Color.blue, 27);
        Piece p28 = new Piece(Color.white, 28);
        Piece p29 = new Piece(Color.blue, 29);


        Piece p62 = new Piece(Color.red, 62);
        Piece p63 = new Piece(Color.white, 63);
        Piece p64 = new Piece(Color.red, 64);

        public GameData() {
            this.mapData.put(1, p1);
            this.mapData.put(2, p2);
            this.mapData.put(3, p3);
            this.mapData.put(4, p4);
            this.mapData.put(5, p5);
            this.mapData.put(6, p6);
            this.mapData.put(7, p7);
            this.mapData.put(8, p8);
            this.mapData.put(9, p9);
            this.mapData.put(10, p10);
            this.mapData.put(11, p11);
            this.mapData.put(12, p12);
            this.mapData.put(13, p13);
            this.mapData.put(14, p14);
            this.mapData.put(15, p15);
            this.mapData.put(16, p16);
            this.mapData.put(17, p17);
            this.mapData.put(18, p18);
            this.mapData.put(19, p19);
            this.mapData.put(20, p20);
            this.mapData.put(21, p21);
            this.mapData.put(22, p22);
            this.mapData.put(23, p23);
            this.mapData.put(24, p24);
            this.mapData.put(25, p25);
            this.mapData.put(26, p26);
            this.mapData.put(27, p27);
            this.mapData.put(28, p28);
            this.mapData.put(29, p29);


            this.mapData.put(62, p62);
            this.mapData.put(63, p63);
            this.mapData.put(64, p64);
        }

        public void executeMove(GameEngine.GameData.Piece currentlySelect, GameEngine.GameData.Piece swapMe) {

            System.out.println(currentlySelect.location + "loc");
            System.out.println(swapMe.location + "loc");

            Piece swapSelect = currentlySelect;
            Piece swapOther = swapMe;

            swapMe = swapSelect;
            currentlySelect = swapOther;

            mapData.put(currentlySelect.location, swapMe);
            mapData.put(swapMe.location, currentlySelect);

          /*  mapData.put(swapMe.location, currentlySelect);
            mapData.put(currentlySelect.location, swapMe);*/

        }

        private class Piece {

            Color color;
            int location;

            public Piece(Color color, int location) {
                this.color = color;
                this.location = location;

            }

        }


    }
}
