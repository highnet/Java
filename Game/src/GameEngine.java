import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class GameEngine extends JPanel implements MouseListener, MouseMotionListener, ActionListener, KeyListener {


    private Timer timer; // GAME CLOCK TIMER

    private Timer drawTimer; //

    private Timer revealTimer;

    private Timer endDuelTimer;

    private Map<String, BufferedImage> bufferedImageMap;

    private int mouseDragX;
    private int mouseDragY;

    private double nextTime;

    private Player p1_human;
    private Player p2_cpu;

    private boolean trigger_startDuel;
    private boolean trigger_endDuel;
    private boolean duelInProgress;

    private Font font1_18;
    private Font font2_26;
    private Font font3_12;
    private Font font4_midscreen;
    private Font font5;

    private DuelHandler duelHandler;

    int mousedOverState;

    String helperTextPointer_MiddleScreen;


    private Color cyan;

    private Card mouseDragCardPointer;
    private int mouseDragCardIndexPointer;
    private boolean phaseDebugMode;

    public GameEngine() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this); // Adds keyboard listener.
        setFocusable(true); // Setting required for keyboard listener.

        startUp();


    }


    private void startUp() {
        System.out.println("STARTUP");
        drawTimer = new Timer(400, this);
        revealTimer = new Timer(2000, this);
        endDuelTimer = new Timer(10000, this);
        int gameSpeed = 1;
        timer = new Timer(gameSpeed, this);
        mouseDragX = 0;
        mouseDragY = 0;
        nextTime = (double) System.nanoTime() / 1000000000.0;
        font1_18 = new Font("Consola", Font.BOLD, 18);
        font2_26 = new Font("Consola", Font.BOLD, 26);
        font3_12 = new Font("Consola", Font.BOLD, 12);
        font4_midscreen = new Font("Consola", Font.BOLD, 22);
        font5 = new Font("Consola", Font.BOLD, 120);
        helperTextPointer_MiddleScreen = "Welcome to the duel";
        cyan = new Color(0, 186, 213);
        mouseDragCardPointer = null;
        mouseDragCardIndexPointer = -1;
        mousedOverState = -1;
        phaseDebugMode = false;


        //     Network network = new Network();
        bufferedImageMap = new HashMap<>();

        loadSprites();

        timer.start();
        drawTimer.start();

        p1_human = new Player("Highnet", new LinkedList<>(), (int) (Math.random() * (5) + 1));
        p2_cpu = new Player("Kirk43", new LinkedList<>(), (int) (Math.random() * (5) + 1));

        generateDecks();

        trigger_startDuel = true;


    }


    private void generateDecks() {
        Deque<Card> deck1 = new LinkedList<>();


        for (int i = 0; i < 20; i++) {

            deck1.add(generateRandomCard());


        }


        p1_human.setDecklist(deck1);


    }

    public static Card generateRandomCard() {

        ArrayList<String> availableCards = new ArrayList<>();

        availableCards.add("Lumberjack");
        availableCards.add("Tree");
        availableCards.add("Guard");
        availableCards.add("Randiq");
        availableCards.add("LumberjackAxe");
        availableCards.add("Toolmaker");
        availableCards.add("Shredder");

        int rand = (int) (Math.random() * availableCards.size());
        System.out.println(rand);

        return  new Card(availableCards.get(rand));

    }

    @Override
    public void paintComponent(Graphics g) {            // paints and controls what is currently painted on screen.

        super.paintComponent(g);


        if (duelInProgress) {
            paintBackground(g);
            paintHand(g);
            paintBoard(g);
            paintMousedOverCardTooltip(g);
            paintDebugInfo(g);


            paintHelperTextMiddleScreen(g);

            paintCardTooltip(g);

            if (duelHandler.playphase1 && duelHandler.playphase1_waitingOnPlay) {

                paintHelperNumbersPlayPhase1(g);

                if (mouseDragCardPointer != null) {
                    paintMouseDragCardPointer(g);
                }
                if (duelHandler.checkForConfirmPlayButtonPhase1()) {
                    paintConfirmButton(g);

                }
            }

            if (duelHandler.mulliganOptionPhase_waitingOnOption) {

                paintMulliganOption(g);


            }


            if (duelHandler.playPhase2 && duelHandler.playPhase2_waitingOnPlay) {
                if (mouseDragCardPointer != null) {
                    paintMouseDragCardPointer(g);
                }

                if (duelHandler.checkForConfirmPlayButtonPhase2()) {
                    paintConfirmButton(g);

                }
            }


        }

    }

    private void paintMulliganOption(Graphics g) {


    }

    private void paintConfirmButton(Graphics g) {

        g.setColor(Color.white);
        g.setFont(font2_26);
        g.drawString("YES", 615, 387);
        g.drawRect(614, 364, 53, 28);
    }

    private void paintBoard(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);


        if (duelHandler.drawPhase1) {

            if (phaseDebugMode) {
                System.out.println("duelHandler.drawPhase1");
            }
            // MY SLOTS
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 162, 409, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 322, 409, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 482, 409, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 642, 409, this);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 569, this);


            // NPC SLOTS
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 162, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 322, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 482, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 642, 204, this);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 44, this);
        }

        if (duelHandler.playphase1_waitingOnPlay) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playphase1_waitingOnPlay");
            }
            // MY SLOTS
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 162, 409, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 322, 409, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 482, 409, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 642, 409, this);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 569, this);

            // NPC SLOTS
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 162, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 322, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 482, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 642, 204, this);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 44, this);


            g2d.setFont(font1_18);
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.6f);

            if (duelHandler.playphase1 && duelHandler.playphase1_waitingOnPlay) {

                g2d.setComposite(alcom);
            }
            if (!duelHandler.getBoard_p1_human()[0].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
                if (duelHandler.getBoard_p1_human()[0].getColor().equals("black")) {
                    g2d.setColor(Color.white);
                }
                g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
                g2d.setColor(Color.black);
            }
            if (!duelHandler.getBoard_p1_human()[1].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
                if (duelHandler.getBoard_p1_human()[1].getColor().equals("black")) {
                    g2d.setColor(Color.white);
                }
                g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
                g2d.setColor(Color.black);

            }

            if (!duelHandler.getBoard_p1_human()[2].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
                if (duelHandler.getBoard_p1_human()[2].getColor().equals("black")) {
                    g2d.setColor(Color.white);
                }
                g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
                g2d.setColor(Color.black);

            }

            if (!duelHandler.getBoard_p1_human()[3].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
                if (duelHandler.getBoard_p1_human()[3].getColor().equals("black")) {
                    g2d.setColor(Color.white);
                }
                g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
                g2d.setColor(Color.black);

            }

            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);
            g2d.setComposite(alcom);
        }

        if (duelHandler.playphase1_revealCards_0) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playphase1_revealCards_0");
            }

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 44, this);

            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);


            g2d.setComposite(alcom);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 162, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 322, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 482, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 642, 204, this);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if (duelHandler.getBoard_p1_human()[0].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if (duelHandler.getBoard_p1_human()[1].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if (duelHandler.getBoard_p1_human()[2].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if (duelHandler.getBoard_p1_human()[3].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);


            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);
            g2d.setComposite(alcom);

        } else if (duelHandler.playphase1_revealCards_1) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playphase1_revealCards_1");
            }

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 44, this);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if (duelHandler.getBoard_p1_human()[0].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);

            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);


            g2d.setComposite(alcom);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 162, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 322, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 482, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 642, 204, this);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if (duelHandler.getBoard_p1_human()[1].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if (duelHandler.getBoard_p1_human()[2].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if (duelHandler.getBoard_p1_human()[3].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);

            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);
            g2d.setComposite(alcom);

        } else if (duelHandler.playphase1_revealCards_2) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playphase1_revealCards_2");
            }

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 44, this);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if (duelHandler.getBoard_p1_human()[0].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if (duelHandler.getBoard_p2_cpu()[0].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);


            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);


            g2d.setComposite(alcom);


            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 322, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 482, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 642, 204, this);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if (duelHandler.getBoard_p1_human()[1].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if (duelHandler.getBoard_p1_human()[2].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if (duelHandler.getBoard_p1_human()[3].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);


            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);
            g2d.setComposite(alcom);

        } else if (duelHandler.playphase1_revealCards_3) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playphase1_revealCards_3");
            }

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 44, this);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if (duelHandler.getBoard_p1_human()[0].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if (duelHandler.getBoard_p2_cpu()[0].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if (duelHandler.getBoard_p1_human()[1].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);

            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);


            g2d.setComposite(alcom);


            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 322, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 482, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 642, 204, this);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if (duelHandler.getBoard_p1_human()[2].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if (duelHandler.getBoard_p1_human()[3].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);

            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);
            g2d.setComposite(alcom);

        } else if (duelHandler.playphase1_revealCards_4) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playphase1_revealCards_4");
            }


            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 44, this);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if ((duelHandler.getBoard_p1_human()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if ((duelHandler.getBoard_p1_human()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[1].getName()), 322, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }

            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[1].getCurrentValue()), 355, 341);
            g2d.setColor(Color.black);

            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);


            g2d.setComposite(alcom);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 482, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 642, 204, this);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if ((duelHandler.getBoard_p1_human()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if ((duelHandler.getBoard_p1_human()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);
            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);


            g2d.setComposite(alcom);

        } else if (duelHandler.playphase1_revealCards_5) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playphase1_revealCards_5");
            }
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 44, this);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if ((duelHandler.getBoard_p1_human()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }

            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if ((duelHandler.getBoard_p1_human()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[1].getName()), 322, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[1].getCurrentValue()), 355, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if ((duelHandler.getBoard_p1_human()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);


            g2d.setComposite(alcom);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 482, 204, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 642, 204, this);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if ((duelHandler.getBoard_p1_human()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);
            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);


            g2d.setComposite(alcom);

        } else if (duelHandler.playphase1_revealCards_6) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playphase1_revealCards_6");
            }
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 44, this);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if ((duelHandler.getBoard_p1_human()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if ((duelHandler.getBoard_p1_human()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[1].getName()), 322, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[1].getCurrentValue()), 355, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if ((duelHandler.getBoard_p1_human()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[2].getName()), 482, 204, this);
            if (duelHandler.getBoard_p2_cpu()[2].getColor().equals("black")) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[2].getCurrentValue()), 515, 341);
            g2d.setColor(Color.black);
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);


            g2d.setComposite(alcom);


            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 642, 204, this);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if ((duelHandler.getBoard_p1_human()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);


            g2d.setComposite(alcom);

        } else if (duelHandler.playphase1_revealCards_7) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playphase1_revealCards_7");
            }
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 44, this);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if ((duelHandler.getBoard_p1_human()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if ((duelHandler.getBoard_p1_human()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[1].getName()), 322, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[1].getCurrentValue()), 355, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if ((duelHandler.getBoard_p1_human()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[2].getName()), 482, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[2].getCurrentValue()), 515, 341);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if ((duelHandler.getBoard_p1_human()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);


            g2d.setComposite(alcom);


            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 642, 204, this);
            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);


            g2d.setComposite(alcom);

        } else if (duelHandler.playphase1_revealCards_8 || duelHandler.drawPhase2 || duelHandler.mulliganOptionPhase) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playphase1_revealCards_8 or duelHandler.drawPhase2 or duelHandler.mulliganOptionPhase");
            }
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 237, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD_DARK"), 557, 44, this);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if ((duelHandler.getBoard_p1_human()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if ((duelHandler.getBoard_p1_human()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[1].getName()), 322, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[1].getCurrentValue()), 355, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if ((duelHandler.getBoard_p1_human()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[2].getName()), 482, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[2].getCurrentValue()), 515, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if ((duelHandler.getBoard_p1_human()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[3].getName()), 642, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[3].getCurrentValue()), 674, 341);
            g2d.setColor(Color.black);

            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);


            g2d.setComposite(alcom);


        } else if (duelHandler.playPhase2_waitingOnPlay) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playPhase2_waitingOnPlay");
            }
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if ((duelHandler.getBoard_p1_human()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if ((duelHandler.getBoard_p1_human()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[1].getName()), 322, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[1].getCurrentValue()), 355, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if ((duelHandler.getBoard_p1_human()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[2].getName()), 482, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[2].getCurrentValue()), 515, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if ((duelHandler.getBoard_p1_human()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[3].getName()), 642, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[3].getCurrentValue()), 674, 341);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 237, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 557, 44, this);


            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 237, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 557, 569, this);


            if (duelHandler.getBoard_p1_human()[4].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get("1"), 237, 569, this);
            }
            if (duelHandler.getBoard_p1_human()[5].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get("2"), 397, 569, this);
            }
            if (duelHandler.getBoard_p1_human()[6].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get("3"), 557, 569, this);
            }

            g2d.setComposite(alcom);
            if (!duelHandler.getBoard_p1_human()[4].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[4].getName()), 237, 569, this);
                if ((duelHandler.getBoard_p1_human()[4].getColor().equals("black"))) {
                    g2d.setColor(Color.white);
                }
                g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[4].getCurrentValue()), 270, 708);
                g2d.setColor(Color.black);
            }
            if (!duelHandler.getBoard_p1_human()[5].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[5].getName()), 397, 569, this);
                if ((duelHandler.getBoard_p1_human()[5].getColor().equals("black"))) {
                    g2d.setColor(Color.white);
                }
                g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[5].getCurrentValue()), 430, 708);
                g2d.setColor(Color.black);
            }

            if (!duelHandler.getBoard_p1_human()[6].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[6].getName()), 557, 569, this);
                if ((duelHandler.getBoard_p1_human()[6].getColor().equals("black"))) {
                    g2d.setColor(Color.white);
                }
                g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[6].getCurrentValue()), 593, 708);
                g2d.setColor(Color.black);
            }

            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);


            g2d.setComposite(alcom);
        } else if (duelHandler.playPhase2_revealCards_0) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playPhase2_revealCards_0");
            }
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if ((duelHandler.getBoard_p1_human()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if ((duelHandler.getBoard_p1_human()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[1].getName()), 322, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[1].getCurrentValue()), 355, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if ((duelHandler.getBoard_p1_human()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[2].getName()), 482, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[2].getCurrentValue()), 515, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if ((duelHandler.getBoard_p1_human()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[3].getName()), 642, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[3].getCurrentValue()), 674, 341);
            g2d.setColor(Color.black);
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);


            g2d.setComposite(alcom);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[4].getName()), 237, 569, this);
            if ((duelHandler.getBoard_p1_human()[4].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[4].getCurrentValue()), 266, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[5].getName()), 397, 569, this);
            if ((duelHandler.getBoard_p1_human()[5].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[5].getCurrentValue()), 429, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[6].getName()), 557, 569, this);
            if ((duelHandler.getBoard_p1_human()[6].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[6].getCurrentValue()), 587, 706);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 237, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 557, 569, this);


            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 237, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 557, 44, this);
            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);


            g2d.setComposite(alcom);

        } else if (duelHandler.playPhase2_revealCards_1) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playPhase2_revealCards_1");
            }
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if ((duelHandler.getBoard_p1_human()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if ((duelHandler.getBoard_p1_human()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[1].getName()), 322, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[1].getCurrentValue()), 355, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if ((duelHandler.getBoard_p1_human()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[2].getName()), 482, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[2].getCurrentValue()), 515, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if ((duelHandler.getBoard_p1_human()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[3].getName()), 642, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[3].getCurrentValue()), 674, 341);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[4].getName()), 237, 44, this);
            if ((duelHandler.getBoard_p2_cpu()[4].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[4].getCurrentValue()), 267, 180);
            g2d.setColor(Color.black);
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);


            g2d.setComposite(alcom);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[4].getName()), 237, 569, this);
            if ((duelHandler.getBoard_p2_cpu()[4].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[4].getCurrentValue()), 266, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[5].getName()), 397, 569, this);
            if ((duelHandler.getBoard_p2_cpu()[5].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[5].getCurrentValue()), 429, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[6].getName()), 557, 569, this);
            if ((duelHandler.getBoard_p2_cpu()[6].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[6].getCurrentValue()), 587, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 237, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 557, 569, this);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 557, 44, this);
            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);


            g2d.setComposite(alcom);

        } else if (duelHandler.playPhase2_revealCards_2) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playPhase2_revealCards_2");
            }
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if ((duelHandler.getBoard_p1_human()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if ((duelHandler.getBoard_p1_human()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[1].getName()), 322, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[1].getCurrentValue()), 355, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if ((duelHandler.getBoard_p1_human()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[2].getName()), 482, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[2].getCurrentValue()), 515, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if ((duelHandler.getBoard_p1_human()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[3].getName()), 642, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[3].getCurrentValue()), 674, 341);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[4].getName()), 237, 44, this);
            if ((duelHandler.getBoard_p2_cpu()[4].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[4].getCurrentValue()), 267, 180);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[4].getName()), 237, 569, this);
            if ((duelHandler.getBoard_p1_human()[4].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[4].getCurrentValue()), 266, 706);
            g2d.setColor(Color.black);
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);


            g2d.setComposite(alcom);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[5].getName()), 397, 569, this);
            if ((duelHandler.getBoard_p1_human()[5].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[5].getCurrentValue()), 429, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[6].getName()), 557, 569, this);
            if ((duelHandler.getBoard_p1_human()[6].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[6].getCurrentValue()), 587, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 557, 569, this);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 397, 44, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 557, 44, this);
            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);


            g2d.setComposite(alcom);

        } else if (duelHandler.playPhase2_revealCards_3) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playPhase2_revealCards_3");
            }
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if ((duelHandler.getBoard_p1_human()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if ((duelHandler.getBoard_p1_human()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[1].getName()), 322, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[1].getCurrentValue()), 355, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if ((duelHandler.getBoard_p1_human()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[2].getName()), 482, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[2].getCurrentValue()), 515, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if ((duelHandler.getBoard_p1_human()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[3].getName()), 642, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[3].getCurrentValue()), 674, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[4].getName()), 237, 44, this);
            if ((duelHandler.getBoard_p2_cpu()[4].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[4].getCurrentValue()), 267, 180);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[4].getName()), 237, 569, this);
            if ((duelHandler.getBoard_p1_human()[4].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[4].getCurrentValue()), 266, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[5].getName()), 397, 44, this);
            if ((duelHandler.getBoard_p2_cpu()[5].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[5].getCurrentValue()), 427, 180);
            g2d.setColor(Color.black);
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);


            g2d.setComposite(alcom);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[5].getName()), 397, 569, this);
            if ((duelHandler.getBoard_p1_human()[5].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[5].getCurrentValue()), 429, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[6].getName()), 557, 569, this);
            if ((duelHandler.getBoard_p2_cpu()[4].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[6].getCurrentValue()), 587, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 397, 569, this);
            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 557, 569, this);


            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 557, 44, this);

            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);


            g2d.setComposite(alcom);

        } else if (duelHandler.playPhase2_revealCards_4) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playPhase2_revealCards_4");
            }
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if ((duelHandler.getBoard_p1_human()[0].getName().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if ((duelHandler.getBoard_p1_human()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[1].getName()), 322, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[1].getCurrentValue()), 355, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if ((duelHandler.getBoard_p1_human()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[2].getName()), 482, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[2].getCurrentValue()), 515, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if ((duelHandler.getBoard_p1_human()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[3].getName()), 642, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[3].getCurrentValue()), 674, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[4].getName()), 237, 44, this);
            if ((duelHandler.getBoard_p2_cpu()[4].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[4].getCurrentValue()), 267, 180);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[4].getName()), 237, 569, this);
            if ((duelHandler.getBoard_p1_human()[4].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[4].getCurrentValue()), 266, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[5].getName()), 397, 44, this);
            if ((duelHandler.getBoard_p2_cpu()[5].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[5].getCurrentValue()), 427, 180);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[5].getName()), 397, 569, this);
            if ((duelHandler.getBoard_p1_human()[5].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[5].getCurrentValue()), 429, 706);
            g2d.setColor(Color.black);
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);


            g2d.setComposite(alcom);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[6].getName()), 557, 569, this);
            if ((duelHandler.getBoard_p1_human()[6].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[6].getCurrentValue()), 587, 706);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 557, 569, this);


            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 557, 44, this);

            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);


            g2d.setComposite(alcom);

        } else if (duelHandler.playPhase2_revealCards_5) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playPhase2_revealCards_5");
            }
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if ((duelHandler.getBoard_p1_human()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if ((duelHandler.getBoard_p1_human()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[1].getName()), 322, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[1].getCurrentValue()), 355, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if ((duelHandler.getBoard_p1_human()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[2].getName()), 482, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[2].getCurrentValue()), 515, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if ((duelHandler.getBoard_p1_human()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[3].getName()), 642, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[3].getCurrentValue()), 674, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[4].getName()), 237, 44, this);
            if ((duelHandler.getBoard_p2_cpu()[4].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[4].getCurrentValue()), 267, 180);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[4].getName()), 237, 569, this);
            if ((duelHandler.getBoard_p1_human()[4].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[4].getCurrentValue()), 266, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[5].getName()), 397, 44, this);
            if ((duelHandler.getBoard_p2_cpu()[5].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[5].getCurrentValue()), 427, 180);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[5].getName()), 397, 569, this);
            if ((duelHandler.getBoard_p1_human()[5].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[5].getCurrentValue()), 429, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[6].getName()), 557, 44, this);
            if ((duelHandler.getBoard_p2_cpu()[6].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[6].getCurrentValue()), 590, 180);
            g2d.setColor(Color.black);

            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 0.5f);


            g2d.setComposite(alcom);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[6].getName()), 557, 569, this);
            if ((duelHandler.getBoard_p1_human()[6].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[6].getCurrentValue()), 587, 706);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), 557, 569, this);

            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);


            g2d.setComposite(alcom);


        } else if (duelHandler.playPhase2_revealCards_6 || duelHandler.resolveGamePhase) {
            if (phaseDebugMode) {
                System.out.println("duelHandler.playPhase2_revealCards_6 or  duelHandler.resolveGamePhase");
            }
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[0].getName()), 162, 409, this);
            if ((duelHandler.getBoard_p1_human()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[0].getCurrentValue()), 196, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[0].getName()), 162, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[0].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[0].getCurrentValue()), 196, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[1].getName()), 322, 409, this);
            if ((duelHandler.getBoard_p1_human()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[1].getCurrentValue()), 359, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[1].getName()), 322, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[1].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[1].getCurrentValue()), 355, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[2].getName()), 482, 409, this);
            if ((duelHandler.getBoard_p1_human()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[2].getCurrentValue()), 516, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[2].getName()), 482, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[2].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[2].getCurrentValue()), 515, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[3].getName()), 642, 409, this);
            if ((duelHandler.getBoard_p1_human()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[3].getCurrentValue()), 676, 547);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[3].getName()), 642, 204, this);
            if ((duelHandler.getBoard_p2_cpu()[3].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[3].getCurrentValue()), 674, 341);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[4].getName()), 237, 44, this);
            if ((duelHandler.getBoard_p2_cpu()[4].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[4].getCurrentValue()), 267, 180);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[4].getName()), 237, 569, this);
            if ((duelHandler.getBoard_p1_human()[4].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[4].getCurrentValue()), 266, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[5].getName()), 397, 44, this);
            if ((duelHandler.getBoard_p2_cpu()[5].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[5].getCurrentValue()), 427, 180);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[5].getName()), 397, 569, this);
            if ((duelHandler.getBoard_p1_human()[5].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[5].getCurrentValue()), 429, 706);
            g2d.setColor(Color.black);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p2_cpu()[6].getName()), 557, 44, this);
            if ((duelHandler.getBoard_p2_cpu()[6].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p2_cpu()[6].getCurrentValue()), 590, 180);
            g2d.setColor(Color.black);

            g2d.drawImage(bufferedImageMap.get(duelHandler.getBoard_p1_human()[6].getName()), 557, 569, this);
            if ((duelHandler.getBoard_p1_human()[6].getColor().equals("black"))) {
                g2d.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getBoard_p1_human()[6].getCurrentValue()), 587, 706);
            g2d.setColor(Color.black);
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, 1f);


            g2d.setComposite(alcom);


        }


    }

    private void paintMouseDragCardPointer(Graphics g) {

        AlphaComposite alcom = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 0.5f);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(alcom);

        g2d.drawImage(bufferedImageMap.get(mouseDragCardPointer.getName()), mouseDragX - 50, mouseDragY - 50, 75, 75, this);
        g2d.setFont(font3_12);
        g2d.setColor(Color.black);
        g2d.drawString(String.valueOf(mouseDragCardPointer.getBaseValue()), mouseDragX - 34, mouseDragY + 18);
    }

    private void paintHelperNumbersPlayPhase1(Graphics g) {


        if (duelHandler.getBoard_p1_human()[0].getName().equals("null")) {
            g.drawImage(bufferedImageMap.get("1"), 162, 409, this);

        }

        if (duelHandler.getBoard_p1_human()[1].getName().equals("null")) {
            g.drawImage(bufferedImageMap.get("2"), 322, 409, this);

        }

        if (duelHandler.getBoard_p1_human()[2].getName().equals("null")) {
            g.drawImage(bufferedImageMap.get("3"), 482, 409, this);

        }

        if (duelHandler.getBoard_p1_human()[3].getName().equals("null")) {
            g.drawImage(bufferedImageMap.get("4"), 642, 409, this);

        }


    }


    private void paintHelperTextMiddleScreen(Graphics g) {
        g.setFont(font4_midscreen);
        g.setColor(Color.white);
        g.drawString(helperTextPointer_MiddleScreen, 380, 388);

    }


    private void paintDebugInfo(Graphics g) {

        g.setFont(font1_18);

        g.drawString(String.valueOf(mouseDragX) + ", " + String.valueOf(mouseDragY), 729, 713);
        g.drawString("Decksize=" + String.valueOf(p1_human.getDecklist().size()), 831, 709);
    }

    private void paintMousedOverCardTooltip(Graphics g) {


        mousedOverState = -1;

        if (mouseDragX > 819 && mouseDragY > 383) {

            if (mouseDragX > 828 && mouseDragX < 917 && mouseDragY > 399 && mouseDragY < 487) {
                mousedOverState = 0;
            } else if (mouseDragX > 929 && mouseDragX > 399 && mouseDragY > 398 && mouseDragY < 487) {
                mousedOverState = 1;
            } else if (mouseDragX > 828 && mouseDragX < 917 && mouseDragY > 500 && mouseDragY < 590) {
                mousedOverState = 2;
            } else if (mouseDragX > 929 && mouseDragX < 1017 && mouseDragY > 500 && mouseDragY < 590) {
                mousedOverState = 3;
            } else if (mouseDragX > 828 && mouseDragX < 917 && mouseDragY > 599 && mouseDragY < 688) {
                mousedOverState = 4;
            } else if (mouseDragX > 929 && mouseDragX < 1017 && mouseDragY > 599 && mouseDragY < 688) {
                mousedOverState = 5;
            }
        }

    }

    private void paintCardTooltip(Graphics g) {
        g.setFont(font1_18);

        Graphics2D g2d = (Graphics2D) g;


        if (mousedOverState <= -1) {
            g.drawImage(bufferedImageMap.get("EMPTY_CARD"), 848, 33, this);
        }


        if (mousedOverState > -1 && !duelHandler.getHand_p1_human()[mousedOverState].getName().equals("null")) {


            g.setColor(cyan);
            g2d.drawString(duelHandler.getHand_p1_human()[mousedOverState].getColor(), 943, 20);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[mousedOverState].getName()), 848, 33, this);


            g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[mousedOverState].getArchetype() + "_icon"), 891, 220, this);
            g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[mousedOverState].getSubtype() + "_icon"), 924, 220, this);


            g2d.setColor(cyan);
            g2d.drawString(duelHandler.getHand_p1_human()[mousedOverState].getName(), 900, 260);

            g2d.drawString(duelHandler.getHand_p1_human()[mousedOverState].getEffectText()[0], 831, 315);
            g2d.drawString(duelHandler.getHand_p1_human()[mousedOverState].getEffectText()[1], 831, 335);

            g2d.setColor(Color.black);
            g2d.drawString("- " + duelHandler.getHand_p1_human()[mousedOverState].getArchetype(), 845, 273);
            g2d.drawString("- " + duelHandler.getHand_p1_human()[mousedOverState].getSubtype(), 845, 289);


            if (duelHandler.getHand_p1_human()[mousedOverState].getColor().equals("black")) {
                g.setColor(Color.white);
            }
            g2d.drawString(String.valueOf(duelHandler.getHand_p1_human()[mousedOverState].getBaseValue()), 880, 171);
            g.setColor(Color.black);

        }

    }


    private void paintHand(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setFont(font1_18);


        int cardSize = 90;
        int row1 = 400;
        int row2 = 500;
        int row3 = 600;

        int col1 = 828;
        int col2 = 928;

        g.setColor(Color.black);

        if (duelHandler.drawPhase1 || duelHandler.playphase1) {


            if (duelHandler.cardsDrawn >= 1) {
                if (duelHandler.drawPhase1) {
                    helperTextPointer_MiddleScreen = "Drawing Cards 1/6";

                }

                if (duelHandler.getHand_p1_human()[0].getName().equals("null")) {
                    g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), col1, row1, cardSize, cardSize, this);

                } else {
                    if (duelHandler.getHand_p1_human()[0].getColor().equals("black")) {
                        g.setColor(Color.white);
                    }
                    g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[0].getName()), col1, row1, cardSize, cardSize, this);
                    g2d.drawString(String.valueOf(duelHandler.getHand_p1_human()[0].getBaseValue()), 845, 484);
                    g.setColor(Color.black);

                }


                if (duelHandler.cardsDrawn >= 2) {
                    if (duelHandler.drawPhase1) {
                        helperTextPointer_MiddleScreen = "Drawing Cards 2/6";

                    }
                    if (duelHandler.getHand_p1_human()[1].getName().equals("null")) {
                        g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), col2, row1, cardSize, cardSize, this);
                    } else {
                        if (duelHandler.getHand_p1_human()[1].getColor().equals("black")) {
                            g.setColor(Color.white);
                        }
                        g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[1].getName()), col2, row1, cardSize, cardSize, this);
                        g2d.drawString(String.valueOf(duelHandler.getHand_p1_human()[1].getBaseValue()), 945, 484);
                        g.setColor(Color.black);

                    }
                }

                if (duelHandler.cardsDrawn >= 3) {
                    if (duelHandler.drawPhase1) {
                        helperTextPointer_MiddleScreen = "Drawing Cards 3/6";

                    }
                    if (duelHandler.getHand_p1_human()[2].getName().equals("null")) {
                        g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), col1, row2, cardSize, cardSize, this);
                    } else {
                        if (duelHandler.getHand_p1_human()[2].getColor().equals("black")) {
                            g.setColor(Color.white);
                        }
                        g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[2].getName()), col1, row2, cardSize, cardSize, this);
                        g2d.drawString(String.valueOf(duelHandler.getHand_p1_human()[2].getBaseValue()), 845, 584);
                        g.setColor(Color.black);

                    }
                }
                if (duelHandler.cardsDrawn >= 4) {
                    if (duelHandler.drawPhase1) {
                        helperTextPointer_MiddleScreen = "Drawing Cards 4/6";

                    }
                    if (duelHandler.getHand_p1_human()[3].getName().equals("null")) {
                        g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), col2, row2, cardSize, cardSize, this);
                    } else {
                        if (duelHandler.getHand_p1_human()[3].getColor().equals("black")) {
                            g.setColor(Color.white);
                        }
                        g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[3].getName()), col2, row2, cardSize, cardSize, this);
                        g2d.drawString(String.valueOf(duelHandler.getHand_p1_human()[3].getBaseValue()), 945, 584);
                        g.setColor(Color.black);

                    }
                }
                if (duelHandler.cardsDrawn >= 5) {
                    if (duelHandler.drawPhase1) {
                        helperTextPointer_MiddleScreen = "Drawing Cards 5/6";

                    }
                    if (duelHandler.getHand_p1_human()[4].getName().equals("null")) {
                        g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), col1, row3, cardSize, cardSize, this);
                    } else {
                        if (duelHandler.getHand_p1_human()[4].getColor().equals("black")) {
                            g.setColor(Color.white);
                        }
                        g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[4].getName()), col1, row3, cardSize, cardSize, this);
                        g2d.drawString(String.valueOf(duelHandler.getHand_p1_human()[4].getBaseValue()), 845, 684);
                        g.setColor(Color.black);

                    }
                }

                if (duelHandler.cardsDrawn >= 6) {
                    if (duelHandler.drawPhase1) {
                        helperTextPointer_MiddleScreen = "Drawing Cards 6/6";

                    }
                    if (duelHandler.getHand_p1_human()[5].getName().equals("null")) {
                        g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), col2, row3, cardSize, cardSize, this);
                    } else {
                        if (duelHandler.getHand_p1_human()[5].getColor().equals("black")) {
                            g.setColor(Color.white);
                        }
                        g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[5].getName()), col2, row3, cardSize, cardSize, this);
                        g2d.drawString(String.valueOf(duelHandler.getHand_p1_human()[5].getBaseValue()), 945, 684);
                        g.setColor(Color.black);

                    }
                }
            }
        } else {

            if (duelHandler.getHand_p1_human()[0].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), col1, row1, cardSize, cardSize, this);

            } else {
                if (duelHandler.getHand_p1_human()[0].getColor().equals("black")) {
                    g.setColor(Color.white);
                }
                g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[0].getName()), col1, row1, cardSize, cardSize, this);
                g2d.drawString(String.valueOf(duelHandler.getHand_p1_human()[0].getBaseValue()), 845, 484);
                g.setColor(Color.black);

            }

            if (duelHandler.getHand_p1_human()[1].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), col2, row1, cardSize, cardSize, this);
            } else {
                if (duelHandler.getHand_p1_human()[1].getColor().equals("black")) {
                    g.setColor(Color.white);
                }
                g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[1].getName()), col2, row1, cardSize, cardSize, this);
                g2d.drawString(String.valueOf(duelHandler.getHand_p1_human()[1].getBaseValue()), 945, 484);
                g.setColor(Color.black);

            }
            if (duelHandler.getHand_p1_human()[2].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), col1, row2, cardSize, cardSize, this);
            } else {
                if (duelHandler.getHand_p1_human()[2].getColor().equals("black")) {
                    g.setColor(Color.white);
                }
                g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[2].getName()), col1, row2, cardSize, cardSize, this);
                g2d.drawString(String.valueOf(duelHandler.getHand_p1_human()[2].getBaseValue()), 845, 584);
                g.setColor(Color.black);

            }
            if (duelHandler.getHand_p1_human()[3].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), col2, row2, cardSize, cardSize, this);
            } else {
                if (duelHandler.getHand_p1_human()[3].getColor().equals("black")) {
                    g.setColor(Color.white);
                }
                g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[3].getName()), col2, row2, cardSize, cardSize, this);
                g2d.drawString(String.valueOf(duelHandler.getHand_p1_human()[3].getBaseValue()), 945, 584);
                g.setColor(Color.black);

            }
            if (duelHandler.getHand_p1_human()[4].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), col1, row3, cardSize, cardSize, this);
            } else {
                if (duelHandler.getHand_p1_human()[4].getColor().equals("black")) {
                    g.setColor(Color.white);

                }
                g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[4].getName()), col1, row3, cardSize, cardSize, this);
                g2d.drawString(String.valueOf(duelHandler.getHand_p1_human()[4].getBaseValue()), 845, 684);
                g.setColor(Color.black);

            }
            if (duelHandler.getHand_p1_human()[5].getName().equals("null")) {
                g2d.drawImage(bufferedImageMap.get("EMPTY_CARD"), col2, row3, cardSize, cardSize, this);
            } else {
                if (duelHandler.getHand_p1_human()[5].getColor().equals("black")) {
                    g.setColor(Color.white);
                }
                g2d.drawImage(bufferedImageMap.get(duelHandler.getHand_p1_human()[5].getName()), col2, row3, cardSize, cardSize, this);
                g2d.drawString(String.valueOf(duelHandler.getHand_p1_human()[5].getBaseValue()), 945, 684);
                g.setColor(Color.black);

            }

        }
    }


    private void paintBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //  g2d.drawImage(bufferedImageMap.get("background"),0,0,this);

        g2d.drawImage(bufferedImageMap.get("nobackground2"), 0, 0, this);

        g2d.setColor(Color.black);

        g2d.setFont(font1_18);
        g2d.drawString(p2_cpu.getName(), 61, 72);
        g2d.setColor(Color.black);
        g2d.setFont(font1_18);
        g2d.drawString(p1_human.getName(), 66, 660);

        g2d.setColor(Color.blue);
        g2d.drawString(String.valueOf(duelHandler.getBlueCount_p1_human()), 35, 608);
        g2d.setColor(Color.white);
        g2d.drawString(String.valueOf(duelHandler.getWhiteCount_p1_human()), 35, 588);
        g2d.setColor(Color.black);
        g2d.drawString(String.valueOf(duelHandler.getBlackCount_p1_human()), 35, 629);
        g2d.setColor(Color.red);
        g2d.drawString(String.valueOf(duelHandler.getRedCount_p1_human()), 96, 588);
        g2d.setColor(Color.green);
        g2d.drawString(String.valueOf(duelHandler.getGreenCount_p1_human()), 96, 606);
        g2d.setColor(Color.yellow);
        g2d.drawString(String.valueOf(duelHandler.getYellowCount_p1_human()), 96, 629);

        g2d.setColor(Color.blue);
        g2d.drawString(String.valueOf(duelHandler.getBlueCount_p2_cpu()), 29, 147);
        g2d.setColor(Color.white);
        g2d.drawString(String.valueOf(duelHandler.getWhiteCount_p2_cpu()), 29, 127);
        g2d.setColor(Color.black);
        g2d.drawString(String.valueOf(duelHandler.getBlackCount_p2_cpu()), 29, 167);
        g2d.setColor(Color.red);
        g2d.drawString(String.valueOf(duelHandler.getRedCount_p2_cpu()), 91, 127);
        g2d.setColor(Color.green);
        g2d.drawString(String.valueOf(duelHandler.getGreenCount_p2_cpu()), 91, 147);
        g2d.setColor(Color.yellow);
        g2d.drawString(String.valueOf(duelHandler.getYellowCount_p2_cpu()), 91, 167);


        g2d.drawImage(bufferedImageMap.get("UserIcon" + p1_human.playerIconID), 10, 639, 53, 53, this);

        g2d.drawImage(bufferedImageMap.get("UserIcon" + p2_cpu.playerIconID), 7, 48, 53, 53, this);


        g2d.setFont(font5);
        g2d.setColor(Color.black);
        g2d.drawString(String.valueOf(duelHandler.getScore_p1_human()), 10, 492);
        g2d.drawString(String.valueOf(duelHandler.getScore_p2_cpu()), 10, 371);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer && paint()) {
            this.repaint();

            if (trigger_startDuel) {
                trigger_startDuel = false;
                duelInProgress = true;
                duelHandler = new DuelHandler(p1_human.getDecklist(), p2_cpu.getDecklist());
            } else if (trigger_endDuel) {

                duelInProgress = false;
                trigger_endDuel = false;
                duelInProgress = false;
                startUp(); // RESTATS THE DUELHANDLER ((FRESH GAME))


            }

        }

        if (duelInProgress) {


            if (e.getSource() == drawTimer && duelHandler.drawPhase1) {


                if (duelHandler.cardsDrawn < 6) {
                    drawCards1(duelHandler.cardsDrawn++);
                } else {
                    helperTextPointer_MiddleScreen = "Choose 4 Cards";
                    duelHandler.drawPhase1 = false;
                    duelHandler.playphase1 = true;
                    duelHandler.playphase1_waitingOnPlay = true;
                    drawTimer.stop();

                }

            }

            if (e.getSource() == drawTimer && duelHandler.drawPhase2) {
                if (duelHandler.cardsDrawn2 < 4) {

                    drawCards2();
                    duelHandler.cardsDrawn2++;
                } else {
                    helperTextPointer_MiddleScreen = "Mulligan 1? [Y] - [N] ";
                    duelHandler.drawPhase2 = false;
                    duelHandler.mulliganOptionPhase = true;
                    duelHandler.mulliganOptionPhase_waitingOnOption = true;
                    drawTimer.stop();

                }
            }


            if (duelHandler.playphase1_waitingOnPlay) {

                if (duelHandler.checkForConfirmPlayButtonPhase1()) {
                    helperTextPointer_MiddleScreen = "Confirm play?";
                }
            }

            if (duelHandler.playPhase2_waitingOnPlay) {

                if (duelHandler.checkForConfirmPlayButtonPhase2()) {
                    helperTextPointer_MiddleScreen = "Confirm play?";
                }
            }


            if (e.getSource() == revealTimer &&
                    (duelHandler.playphase1_revealCards_0 ||
                            duelHandler.playphase1_revealCards_1 ||
                            duelHandler.playphase1_revealCards_2 ||
                            duelHandler.playphase1_revealCards_3 ||
                            duelHandler.playphase1_revealCards_4 ||
                            duelHandler.playphase1_revealCards_5 ||
                            duelHandler.playphase1_revealCards_6 ||
                            duelHandler.playphase1_revealCards_7 ||
                            duelHandler.playphase1_revealCards_8)) {

                if (duelHandler.playphase1_revealCards_0) {
                    duelHandler.playphase1_revealCards_0 = false;
                    duelHandler.playphase1_revealCards_1 = true;
                    duelHandler.assignPoints("p1_human", 0);
                } else if (duelHandler.playphase1_revealCards_1) {
                    duelHandler.playphase1_revealCards_1 = false;
                    duelHandler.playphase1_revealCards_2 = true;
                    duelHandler.assignPoints("p2_cpu", 1);
                } else if (duelHandler.playphase1_revealCards_2) {
                    duelHandler.playphase1_revealCards_2 = false;
                    duelHandler.playphase1_revealCards_3 = true;
                    duelHandler.assignPoints("p1_human", 2);
                } else if (duelHandler.playphase1_revealCards_3) {
                    duelHandler.playphase1_revealCards_3 = false;
                    duelHandler.playphase1_revealCards_4 = true;
                    duelHandler.assignPoints("p2_cpu", 3);
                } else if (duelHandler.playphase1_revealCards_4) {
                    duelHandler.playphase1_revealCards_4 = false;
                    duelHandler.playphase1_revealCards_5 = true;
                    duelHandler.assignPoints("p1_human", 4);
                } else if (duelHandler.playphase1_revealCards_5) {
                    duelHandler.playphase1_revealCards_5 = false;
                    duelHandler.playphase1_revealCards_6 = true;
                    duelHandler.assignPoints("p2_cpu", 5);
                } else if (duelHandler.playphase1_revealCards_6) {
                    duelHandler.playphase1_revealCards_6 = false;
                    duelHandler.playphase1_revealCards_7 = true;
                    duelHandler.assignPoints("p1_human", 6);
                } else if (duelHandler.playphase1_revealCards_7) {
                    duelHandler.playphase1_revealCards_7 = false;
                    duelHandler.playphase1_revealCards_8 = true;
                    duelHandler.assignPoints("p2_cpu", 7);
                } else if (duelHandler.playphase1_revealCards_8) {
                    duelHandler.playphase1_revealCards_8 = false;
                    duelHandler.playphase1 = false;
                    revealTimer.stop();
                    duelHandler.drawPhase2 = true;
                    helperTextPointer_MiddleScreen = "Drawing Cards";
                    drawTimer.start();

                }

            }

            if (e.getSource() == revealTimer &&
                    (
                            duelHandler.playPhase2_revealCards_0 ||
                                    duelHandler.playPhase2_revealCards_1 ||
                                    duelHandler.playPhase2_revealCards_2 ||
                                    duelHandler.playPhase2_revealCards_3 ||
                                    duelHandler.playPhase2_revealCards_4 ||
                                    duelHandler.playPhase2_revealCards_5 ||
                                    duelHandler.playPhase2_revealCards_6
                    )) {

                if (duelHandler.playPhase2_revealCards_0) {
                    duelHandler.playPhase2_revealCards_0 = false;
                    duelHandler.playPhase2_revealCards_1 = true;
                    duelHandler.assignPoints("p2_cpu", 8);
                } else if (duelHandler.playPhase2_revealCards_1) {
                    duelHandler.playPhase2_revealCards_1 = false;
                    duelHandler.playPhase2_revealCards_2 = true;
                    duelHandler.assignPoints("p1_human", 9);
                } else if (duelHandler.playPhase2_revealCards_2) {
                    duelHandler.playPhase2_revealCards_2 = false;
                    duelHandler.playPhase2_revealCards_3 = true;
                    duelHandler.assignPoints("p2_cpu", 10);
                } else if (duelHandler.playPhase2_revealCards_3) {
                    duelHandler.playPhase2_revealCards_3 = false;
                    duelHandler.playPhase2_revealCards_4 = true;
                    duelHandler.assignPoints("p1_human", 11);
                } else if (duelHandler.playPhase2_revealCards_4) {
                    duelHandler.playPhase2_revealCards_4 = false;
                    duelHandler.playPhase2_revealCards_5 = true;
                    duelHandler.assignPoints("p2_cpu", 12);
                } else if (duelHandler.playPhase2_revealCards_5) {
                    duelHandler.playPhase2_revealCards_5 = false;
                    duelHandler.playPhase2_revealCards_6 = true;
                    duelHandler.assignPoints("p1_human", 13);
                } else {
                    duelHandler.playPhase2_revealCards_6 = false;
                    duelHandler.playPhase2 = false;
                    revealTimer.stop();
                    helperTextPointer_MiddleScreen = "Comparing Scores";
                    duelHandler.resolveGamePhase = true;


                }

            }


            if (e.getSource() == timer && duelHandler.resolveGamePhase) {

                endDuelTimer.start();

                if (duelHandler.getScore_p1_human() > duelHandler.getScore_p2_cpu()) {
                    helperTextPointer_MiddleScreen = "YOU WIN";
                }

                if (duelHandler.getScore_p1_human() < duelHandler.getScore_p2_cpu()) {
                    helperTextPointer_MiddleScreen = "YOU LOSE";
                }

                if (duelHandler.getScore_p1_human() == duelHandler.getScore_p2_cpu()) {
                    helperTextPointer_MiddleScreen = "IT'S A DRAW";
                }
            }

            if (e.getSource() == endDuelTimer) {

                endDuelTimer.stop();
                trigger_endDuel = true;
            }

        }


    }

    private void drawCards2() {

        for (int i = 0; i < 6; i++) {
            if (duelHandler.getHand_p1_human()[i].getName().equals("null")) {
                duelHandler.getHand_p1_human()[i] = p1_human.getDecklist().pollFirst();
                break;
            }
        }
    }

    private void drawCards2(Integer index) {
        System.out.println(p1_human.getDecklist().peekFirst());
        duelHandler.getHand_p1_human()[index] = p1_human.getDecklist().pollFirst();

    }

    private void drawCards1(int index) {
        duelHandler.getHand_p1_human()[index] = p1_human.getDecklist().poll();
    }


    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {


        switch (e.getKeyCode()) { // Keyboard switch -> e.getKeyCode() returns a virtual keyboard int value

            case KeyEvent.VK_ESCAPE:

                mouseDragCardPointer = null;

                mouseDragCardIndexPointer = -1;
                break;


            case KeyEvent.VK_X:


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


            if (duelHandler != null && duelHandler.playphase1 && duelHandler.playphase1_waitingOnPlay) {
                onMouseClickSelectCardFromHand(x, y);

                if (mouseDragCardPointer != null) {
                    onMouseClickPlayCardFromHandphase1(x, y);
                }
                if (duelHandler.checkForConfirmPlayButtonPhase1()) {
                    onMouseClickConfirmPlayphase1(x, y);
                }

            }

            if (duelHandler != null && duelHandler.mulliganOptionPhase_waitingOnOption) {
                onMouseClickChooseMulliganOption(x, y);
            }

            if (duelHandler != null && duelHandler.playPhase2_waitingOnPlay) {
                onMouseClickSelectCardFromHand(x, y);

                if (mouseDragCardPointer != null) {
                    onMouseClickPlayCardFromHandphase2(x, y);
                }

                if (duelHandler.checkForConfirmPlayButtonPhase2()) {
                    onMouseClickConfirmPlayphase2(x, y);
                }
            }


        } else if (e.getButton() == MouseEvent.BUTTON3) {


        }
    }


    private void onMouseClickChooseMulliganOption(int x, int y) {

        if (x > 500 && x < 528 && y > 371 && y < 393) {  // YES
            duelHandler.mulliganOptionPhase_waitingOnOption = false;
            duelHandler.mulliganOptionPhase = false;
            duelHandler.playPhase2 = true;
            duelHandler.playPhase2_waitingOnPlay = true;
            helperTextPointer_MiddleScreen = "Choose 3 cards";
            duelHandler.getHand_p1_human()[(int) (Math.random() * 6)] = p1_human.getDecklist().poll();

        } else if (x > 549 && x < 577 && y > 373 && y < 393) { // NO
            duelHandler.mulliganOptionPhase_waitingOnOption = false;
            duelHandler.mulliganOptionPhase = false;
            duelHandler.playPhase2 = true;
            duelHandler.playPhase2_waitingOnPlay = true;
            helperTextPointer_MiddleScreen = "Choose 3 cards";
        }
    }

    private void onMouseClickConfirmPlayphase1(int x, int y) {
        //   g.drawRect(614, 364, 53, 28);
        if (x > 614 && x < 668 && y > 364 && y < 394) {
            duelHandler.playphase1_waitingOnPlay = false;
            duelHandler.playphase1_revealCards_0 = true;
            revealTimer.start();
            duelHandler.AIhandler.generatePhase1Play(duelHandler.getHand_p2_cpu(), duelHandler.getBoard_p2_cpu());
            helperTextPointer_MiddleScreen = "Revealing Cards";
        }
    }


    private void onMouseClickConfirmPlayphase2(int x, int y) {
        if (x > 614 && x < 668 && y > 364 && y < 394) {
            duelHandler.playPhase2_waitingOnPlay = false;
            duelHandler.playPhase2_revealCards_0 = true;
            revealTimer.start();
            duelHandler.AIhandler.generatePhase2Play(duelHandler.getHand_p2_cpu(), duelHandler.getBoard_p2_cpu());
            helperTextPointer_MiddleScreen = "Revealing Cards";
        }
    }

    private void onMouseClickPlayCardFromHandphase1(int x, int y) {


        if (x > 164 && x < 310 && y > 412 && y < 557) {
            duelHandler.getHand_p1_human()[mouseDragCardIndexPointer] = duelHandler.getBoard_p1_human()[0];
            duelHandler.getBoard_p1_human()[0] = mouseDragCardPointer;
            mouseDragCardPointer = null;

            mouseDragCardIndexPointer = -1;
        } else if (x > 323 && x < 470 && y > 412 && y < 557) {

            duelHandler.getHand_p1_human()[mouseDragCardIndexPointer] = duelHandler.getBoard_p1_human()[1];
            duelHandler.getBoard_p1_human()[1] = mouseDragCardPointer;
            mouseDragCardPointer = null;


            mouseDragCardIndexPointer = -1;
        } else if (x > 483 && x < 630 && y > 412 && y < 557) {

            duelHandler.getHand_p1_human()[mouseDragCardIndexPointer] = duelHandler.getBoard_p1_human()[2];
            duelHandler.getBoard_p1_human()[2] = mouseDragCardPointer;
            mouseDragCardPointer = null;


            mouseDragCardIndexPointer = -1;
        } else if (x > 644 && x < 790 && y > 412 && y < 557) {

            duelHandler.getHand_p1_human()[mouseDragCardIndexPointer] = duelHandler.getBoard_p1_human()[3];
            duelHandler.getBoard_p1_human()[3] = mouseDragCardPointer;
            mouseDragCardPointer = null;

            mouseDragCardIndexPointer = -1;
        }

    }

    private void onMouseClickPlayCardFromHandphase2(int x, int y) {
        if (x > 237 && x < 386 && y > 568 && y < 715) {
            duelHandler.getHand_p1_human()[mouseDragCardIndexPointer] = duelHandler.getBoard_p1_human()[4];
            duelHandler.getBoard_p1_human()[4] = mouseDragCardPointer;
            mouseDragCardPointer = null;

            mouseDragCardIndexPointer = -1;
        } else if (x > 397 && x < 545 && y > 568 && y < 715) {

            duelHandler.getHand_p1_human()[mouseDragCardIndexPointer] = duelHandler.getBoard_p1_human()[5];
            duelHandler.getBoard_p1_human()[5] = mouseDragCardPointer;
            mouseDragCardPointer = null;


            mouseDragCardIndexPointer = -1;
        } else if (x > 554 && x < 706 && y > 568 && y < 715) {

            duelHandler.getHand_p1_human()[mouseDragCardIndexPointer] = duelHandler.getBoard_p1_human()[6];
            duelHandler.getBoard_p1_human()[6] = mouseDragCardPointer;
            mouseDragCardPointer = null;


            mouseDragCardIndexPointer = -1;
        }
    }

    private void onMouseClickSelectCardFromHand(int x, int y) {
        if (x > 819 && y > 383) {

            if (x > 828 && x < 917 && y > 399 && y < 487) {

                if (!duelHandler.getHand_p1_human()[0].getName().equals("null")) {
                    mouseDragCardPointer = duelHandler.getHand_p1_human()[0];
                    mouseDragCardIndexPointer = 0;

                }
            } else if (x > 929 && x > 399 && y > 398 && y < 487) {
                if (!duelHandler.getHand_p1_human()[1].getName().equals("null")) {
                    mouseDragCardPointer = duelHandler.getHand_p1_human()[1];
                    mouseDragCardIndexPointer = 1;

                }
            } else if (x > 828 && x < 917 && y > 500 && y < 590) {
                if (!duelHandler.getHand_p1_human()[2].getName().equals("null")) {
                    mouseDragCardPointer = duelHandler.getHand_p1_human()[2];
                    mouseDragCardIndexPointer = 2;

                }
            } else if (x > 929 && x < 1017 && y > 500 && y < 590) {
                if (!duelHandler.getHand_p1_human()[3].getName().equals("null")) {
                    mouseDragCardPointer = duelHandler.getHand_p1_human()[3];
                    mouseDragCardIndexPointer = 3;

                }
            } else if (x > 828 && x < 917 && y > 599 && y < 688) {
                if (!duelHandler.getHand_p1_human()[4].getName().equals("null")) {
                    mouseDragCardPointer = duelHandler.getHand_p1_human()[4];
                    mouseDragCardIndexPointer = 4;

                }
            } else if (x > 929 && x < 1017 && y > 599 && y < 688) {
                if (!duelHandler.getHand_p1_human()[5].getName().equals("null")) {
                    mouseDragCardPointer = duelHandler.getHand_p1_human()[5];
                    mouseDragCardIndexPointer = 5;

                }

            }
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

        System.out.println("please enter string:");

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

        System.out.println("please enter string:");

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

        loadBufferedImage("nobackground2.png", "nobackground2");
        loadBufferedImage("1.png", "1");
        loadBufferedImage("2.png", "2");
        loadBufferedImage("3.png", "3");
        loadBufferedImage("4.png", "4");
        loadBufferedImage("EmptyCard.png", "EMPTY_CARD");
        loadBufferedImage("EmptyCardDark.png", "EMPTY_CARD_DARK");
        loadBufferedImage("Tree.png", "Tree");
        loadBufferedImage("Guard.png", "Guard");
        loadBufferedImage("Lumberjack.png", "Lumberjack");
        loadBufferedImage("Randiq.png", "Randiq");
        loadBufferedImage("LumberjackAxe.png", "LumberjackAxe");
        loadBufferedImage("Toolmaker.png", "Toolmaker");
        loadBufferedImage("Shredder.png", "Shredder");

        loadBufferedImage("forestry_icon.png", "forestry_icon");
        loadBufferedImage("plant_icon.png", "plant_icon");
        loadBufferedImage("human_icon.png", "human_icon");
        loadBufferedImage("imperial_icon.png", "imperial_icon");
        loadBufferedImage("tool_icon.png", "tool_icon");
        loadBufferedImage("mech_icon.png", "mech_icon");

        loadBufferedImage("UserIcon1.png", "UserIcon1");
        loadBufferedImage("UserIcon2.png", "UserIcon2");
        loadBufferedImage("UserIcon3.png", "UserIcon3");
        loadBufferedImage("UserIcon4.png", "UserIcon4");
        loadBufferedImage("UserIcon5.png", "UserIcon5");

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

