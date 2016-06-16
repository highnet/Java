import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class GameEngine extends JPanel implements MouseListener, MouseMotionListener, ActionListener, KeyListener {


    private Timer timer; // Game clock timer, controls: gamespeed / painting / resolving the duel phase when game is over

    private Timer drawTimer; // card drawing timer, controls: drawing cards during card draw phase 1 and phase 2

    private Timer revealTimer; // card revealing timer, controls: card reveals during card reveal phases

    private Timer endDuelTimer; // end duel timer, controls: determines how long the pause between the end of duel and a new duel.

    private Map<String, BufferedImage> bufferedImageMap; // Stores all bufferedimages(.png files) in an easy to use map.

    private int mouseDragX; // mouse x coordinate pointer, updates every time the mouse is "dragged", hence the name.
    private int mouseDragY; // mouse y coordinate pointer, updates every time the mouse is "dragged", hence the name.

    private double nextTime; // pointer variable used for locking the game at a determined frame per second(fps). leave untouched (or may cause performance issues)

    private Player p1_human; // Player 1 object (controlled by human)
    private Player p2_cpu; // Player 2 object (controlled by AI)

    private boolean trigger_startDuel; // Trigger switch(turns itself off after flipping it), begins the duel. (if this is triggered to true at any point, it will restart the game, not fully tested, may cause issues)
    private boolean trigger_endDuel; // Trigger switch(turns itself off after flipping it), ends the duel. (if this is triggered to true at any point, it will end the game, not fully tested, may cause issues)
    private boolean duelInProgress; // Boolean state, controls wether there is indeed a duel in progress.


    private Font font1_18; // Font pointer. uses: // TODO: 2016-06-16
    private Font font2_26; // Font pointer. uses:  // TODO: 2016-06-16
    private Font font3_12; // Font pointer. uses:  // TODO: 2016-06-16
    private Font font4_midscreen; // Font pointer, used solely for the mid screen text.
    private Font font5; // Font pointer. uses: // TODO: 2016-06-16


    /*
    DuelHandler Object.

    Isolated container of a duel.

    Includes all duel logic, scripts, states, counters, decklists,
     */
    private DuelHandler duelHandler;

    private int mousedOverState; // pointer which determines which custom-defined area of the screen the mouse is located. (used for tooltip display)

    private String helperTextPointer_MiddleScreen; // Middle screen helper text pointer String.
    private Color cyan; // custom defined cyan color. (does it really need to be a field)

    private Card mouseDragCardPointer; // pointer which determines what kind of card the player is dragging around (used for placement of cards)
    private int mouseDragCardIndexPointer; //  TODO: 2016-06-16 add comment description
    private boolean phaseDebugMode; // determines whether text is printed to the console with information of which game phases are currently active


    private boolean SHIFT_PRESSED;    // Virtual Key Boolean State Pointer

    private boolean ONE_PRESSED;    // Virtual Key Boolean State Pointer
    private boolean TWO_PRESSED;    // Virtual Key Boolean State Pointer
    private boolean THREE_PRESSED;    // Virtual Key Boolean State Pointer
    private boolean FOUR_PRESSED;    // Virtual Key Boolean State Pointer
    private boolean FIVE_PRESSED;    // Virtual Key Boolean State Pointer
    private boolean SIX_PRESSED;    // Virtual Key Boolean State Pointer
    private boolean SEVEN_PRESSED;    // Virtual Key Boolean State Pointer


    private int splash1_animationFrameCounter;// splash animation frame buffer counter pointer
    private int splash2_animationFrameCounter;// splash animation frame buffer counter pointer
    private int splash3_animationFrameCounter;// splash animation frame buffer counter pointer
    private int splash4_animationFrameCounter;// splash animation frame buffer counter pointer
    private int splash5_animationFrameCounter;// splash animation frame buffer counter pointer
    private int splash6_animationFrameCounter;// splash animation frame buffer counter pointer
    private int splash7_animationFrameCounter;// splash animation frame buffer counter pointer
    private int splash8_animationFrameCounter;// splash animation frame buffer counter pointer
    private int splash9_animationFrameCounter;// splash animation frame buffer counter pointer
    private int splash10_animationFrameCounter;// splash animation frame buffer counter pointer
    private int splash11_animationFrameCounter;// splash animation frame buffer counter pointer
    private int splash12_animationFrameCounter;// splash animation frame buffer counter pointer
    private int splash13_animationFrameCounter;// splash animation frame buffer counter pointer
    private int splash14_animationFrameCounter;// splash animation frame buffer counter pointer


    public GameEngine() { // GameEngine constructor.
        addMouseListener(this); // Adds mouse listener.
        addMouseMotionListener(this); // Adds mouse motion listener.
        addKeyListener(this); // Adds keyboard listener.
        setFocusable(true); // Setting required for keyboard listener.

        startUp(); // sets all startup configurations,
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
        font5 = new Font("Consola", Font.BOLD, 115);
        helperTextPointer_MiddleScreen = "Welcome to the duel";
        cyan = new Color(0, 186, 213);
        mouseDragCardPointer = null;
        mouseDragCardIndexPointer = -1;
        mousedOverState = -1;
        phaseDebugMode = false;
        resetAnimationCounters();
        ONE_PRESSED = false;
        TWO_PRESSED = false;
        THREE_PRESSED = false;
        FOUR_PRESSED = false;
        FIVE_PRESSED = false;
        SIX_PRESSED = false;
        SEVEN_PRESSED = false;
        SHIFT_PRESSED = false;
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


    private void generateDecks() { // Genereates a random , 20 card deck, from all "available cards"
        Deque<Card> deck1 = new LinkedList<>();
        for (int i = 0; i < 20; i++) {
            deck1.add(generateRandomCard());
        }
        p1_human.setDecklist(deck1);
    }

    private static Card generateRandomCard() { // Returns a random card from all "available cards"
        ArrayList<String> availableCards = new ArrayList<>(); // Empty List of all available cards
        availableCards.add("Lumberjack");
        availableCards.add("Tree");
        availableCards.add("Guard");
        availableCards.add("Randiq");
        availableCards.add("LumberjackAxe");
        availableCards.add("Toolmaker");
        availableCards.add("Shredder");
        availableCards.add("GreenDragon");

        int rand = (int) (Math.random() * availableCards.size());

        return new Card(availableCards.get(rand));
    }

    @Override
    public void paintComponent(Graphics g) {            // paints and controls what is currently painted on screen.

        super.paintComponent(g);

        if (duelInProgress) {
            paintVitalInformation(g);
            paintHand(g);
            executeRequestedAnimations(g);
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

    private void executeRequestedAnimations(Graphics g) {

        if (!SHIFT_PRESSED && ONE_PRESSED || duelHandler.splashAnimation1Request) {
            executeSplashAnimation(g, 1);
        }
        if (SHIFT_PRESSED && ONE_PRESSED || duelHandler.splashAnimation2Request) {
            executeSplashAnimation(g, 2);
        }
        if (!SHIFT_PRESSED && TWO_PRESSED || duelHandler.splashAnimation3Request) {
            executeSplashAnimation(g, 3);
        }
        if (SHIFT_PRESSED && TWO_PRESSED || duelHandler.splashAnimation4Request) {
            executeSplashAnimation(g, 4);
        }
        if (!SHIFT_PRESSED && THREE_PRESSED || duelHandler.splashAnimation5Request) {
            executeSplashAnimation(g, 5);
        }
        if (SHIFT_PRESSED && THREE_PRESSED || duelHandler.splashAnimation6Request) {
            executeSplashAnimation(g, 6);
        }
        if (!SHIFT_PRESSED && FOUR_PRESSED || duelHandler.splashAnimation7Request) {
            executeSplashAnimation(g, 7);
        }
        if (SHIFT_PRESSED && FOUR_PRESSED || duelHandler.splashAnimation8Request) {
            executeSplashAnimation(g, 8);
        }
        if (!SHIFT_PRESSED && FIVE_PRESSED || duelHandler.splashAnimation9Request) {
            executeSplashAnimation(g, 9);
        }
        if (SHIFT_PRESSED && FIVE_PRESSED || duelHandler.splashAnimation10Request) {
            executeSplashAnimation(g, 10);
        }
        if (!SHIFT_PRESSED && SIX_PRESSED || duelHandler.splashAnimation11Request) {
            executeSplashAnimation(g, 11);
        }
        if (SHIFT_PRESSED && SIX_PRESSED || duelHandler.splashAnimation12Request) {
            executeSplashAnimation(g, 12);
        }
        if (!SHIFT_PRESSED && SEVEN_PRESSED || duelHandler.splashAnimation13Request) {
            executeSplashAnimation(g, 13);
        }
        if (SHIFT_PRESSED && SEVEN_PRESSED || duelHandler.splashAnimation14Request) {
            executeSplashAnimation(g, 14);
        }
    }

    private void executeSplashAnimation(Graphics g, int requestedPosition) {

        if (requestedPosition == 1) {

            if (splash1_animationFrameCounter < 5) {
                g.drawImage(bufferedImageMap.get("splash_0"), 162, 407, this);

            } else if (splash1_animationFrameCounter < 10) {
                g.drawImage(bufferedImageMap.get("splash_1"), 162, 407, this);

            } else if (splash1_animationFrameCounter < 15) {
                g.drawImage(bufferedImageMap.get("splash_2"), 162, 407, this);

            } else if (splash1_animationFrameCounter < 20) {
                g.drawImage(bufferedImageMap.get("splash_3"), 162, 407, this);

            } else if (splash1_animationFrameCounter < 25) {
                g.drawImage(bufferedImageMap.get("splash_4"), 162, 407, this);

            } else if (splash1_animationFrameCounter < 30) {
                g.drawImage(bufferedImageMap.get("splash_5"), 162, 407, this);

            } else if (splash1_animationFrameCounter < 35) {
                g.drawImage(bufferedImageMap.get("splash_6"), 162, 407, this);

            } else if (splash1_animationFrameCounter < 40) {
                g.drawImage(bufferedImageMap.get("splash_7"), 162, 407, this);

            } else if (splash1_animationFrameCounter < 45) {
                g.drawImage(bufferedImageMap.get("splash_8"), 162, 407, this);
            } else if (splash1_animationFrameCounter < 50) {
                g.drawImage(bufferedImageMap.get("splash_9"), 162, 407, this);

            } else if (splash1_animationFrameCounter < 55) {
                g.drawImage(bufferedImageMap.get("splash_10"), 162, 407, this);

            } else if (splash1_animationFrameCounter < 60) {
                g.drawImage(bufferedImageMap.get("splash_11"), 162, 407, this);

            } else if (splash1_animationFrameCounter < 65) {
                g.drawImage(bufferedImageMap.get("splash_12"), 162, 407, this);

            } else if (splash1_animationFrameCounter < 70) {
                g.drawImage(bufferedImageMap.get("splash_13"), 162, 407, this);

            } else if (splash1_animationFrameCounter < 75) {
                g.drawImage(bufferedImageMap.get("splash_14"), 162, 407, this);

            }

            if (splash1_animationFrameCounter == 76)

            {
                splash1_animationFrameCounter = 0;
                duelHandler.splashAnimation1Request = false;

            }

            splash1_animationFrameCounter += 4;

        } else if (requestedPosition == 2) {

            if (splash2_animationFrameCounter < 5) {
                g.drawImage(bufferedImageMap.get("splash_0"), 162, 205, this);

            } else if (splash2_animationFrameCounter < 10) {
                g.drawImage(bufferedImageMap.get("splash_1"), 162, 205, this);

            } else if (splash2_animationFrameCounter < 15) {
                g.drawImage(bufferedImageMap.get("splash_2"), 162, 205, this);

            } else if (splash2_animationFrameCounter < 20) {
                g.drawImage(bufferedImageMap.get("splash_3"), 162, 205, this);

            } else if (splash2_animationFrameCounter < 25) {
                g.drawImage(bufferedImageMap.get("splash_4"), 162, 205, this);

            } else if (splash2_animationFrameCounter < 30) {
                g.drawImage(bufferedImageMap.get("splash_5"), 162, 205, this);

            } else if (splash2_animationFrameCounter < 35) {
                g.drawImage(bufferedImageMap.get("splash_6"), 162, 205, this);

            } else if (splash2_animationFrameCounter < 40) {
                g.drawImage(bufferedImageMap.get("splash_7"), 162, 205, this);

            } else if (splash2_animationFrameCounter < 45) {
                g.drawImage(bufferedImageMap.get("splash_8"), 162, 205, this);
            } else if (splash2_animationFrameCounter < 50) {
                g.drawImage(bufferedImageMap.get("splash_9"), 162, 205, this);

            } else if (splash2_animationFrameCounter < 55) {
                g.drawImage(bufferedImageMap.get("splash_10"), 162, 205, this);

            } else if (splash2_animationFrameCounter < 60) {
                g.drawImage(bufferedImageMap.get("splash_11"), 162, 205, this);

            } else if (splash2_animationFrameCounter < 65) {
                g.drawImage(bufferedImageMap.get("splash_12"), 162, 205, this);

            } else if (splash2_animationFrameCounter < 70) {
                g.drawImage(bufferedImageMap.get("splash_13"), 162, 205, this);

            } else if (splash2_animationFrameCounter < 75) {
                g.drawImage(bufferedImageMap.get("splash_14"), 162, 205, this);

            }

            if (splash2_animationFrameCounter == 76)

            {
                splash2_animationFrameCounter = 0;
                duelHandler.splashAnimation2Request = false;

            }

            splash2_animationFrameCounter += 4;

        } else if (requestedPosition == 3) {

            if (splash3_animationFrameCounter < 5) {
                g.drawImage(bufferedImageMap.get("splash_0"), 322, 407, this);

            } else if (splash3_animationFrameCounter < 10) {
                g.drawImage(bufferedImageMap.get("splash_1"), 322, 407, this);

            } else if (splash3_animationFrameCounter < 15) {
                g.drawImage(bufferedImageMap.get("splash_2"), 322, 407, this);

            } else if (splash3_animationFrameCounter < 20) {
                g.drawImage(bufferedImageMap.get("splash_3"), 322, 407, this);

            } else if (splash3_animationFrameCounter < 25) {
                g.drawImage(bufferedImageMap.get("splash_4"), 322, 407, this);

            } else if (splash3_animationFrameCounter < 30) {
                g.drawImage(bufferedImageMap.get("splash_5"), 322, 407, this);

            } else if (splash3_animationFrameCounter < 35) {
                g.drawImage(bufferedImageMap.get("splash_6"), 322, 407, this);

            } else if (splash3_animationFrameCounter < 40) {
                g.drawImage(bufferedImageMap.get("splash_7"), 322, 407, this);

            } else if (splash3_animationFrameCounter < 45) {
                g.drawImage(bufferedImageMap.get("splash_8"), 322, 407, this);
            } else if (splash3_animationFrameCounter < 50) {
                g.drawImage(bufferedImageMap.get("splash_9"), 322, 407, this);

            } else if (splash3_animationFrameCounter < 55) {
                g.drawImage(bufferedImageMap.get("splash_10"), 322, 407, this);

            } else if (splash3_animationFrameCounter < 60) {
                g.drawImage(bufferedImageMap.get("splash_11"), 322, 407, this);

            } else if (splash3_animationFrameCounter < 65) {
                g.drawImage(bufferedImageMap.get("splash_12"), 322, 407, this);

            } else if (splash3_animationFrameCounter < 70) {
                g.drawImage(bufferedImageMap.get("splash_13"), 322, 407, this);

            } else if (splash3_animationFrameCounter < 75) {
                g.drawImage(bufferedImageMap.get("splash_14"), 322, 407, this);

            }

            if (splash3_animationFrameCounter == 76)

            {
                splash3_animationFrameCounter = 0;
                duelHandler.splashAnimation3Request = false;

            }

            splash3_animationFrameCounter += 4;

        } else if (requestedPosition == 4) {

            if (splash4_animationFrameCounter < 5) {
                g.drawImage(bufferedImageMap.get("splash_0"), 322, 205, this);

            } else if (splash4_animationFrameCounter < 10) {
                g.drawImage(bufferedImageMap.get("splash_1"), 322, 205, this);

            } else if (splash4_animationFrameCounter < 15) {
                g.drawImage(bufferedImageMap.get("splash_2"), 322, 205, this);

            } else if (splash4_animationFrameCounter < 20) {
                g.drawImage(bufferedImageMap.get("splash_3"), 322, 205, this);

            } else if (splash4_animationFrameCounter < 25) {
                g.drawImage(bufferedImageMap.get("splash_4"), 322, 205, this);

            } else if (splash4_animationFrameCounter < 30) {
                g.drawImage(bufferedImageMap.get("splash_5"), 322, 205, this);

            } else if (splash4_animationFrameCounter < 35) {
                g.drawImage(bufferedImageMap.get("splash_6"), 322, 205, this);

            } else if (splash4_animationFrameCounter < 40) {
                g.drawImage(bufferedImageMap.get("splash_7"), 322, 205, this);

            } else if (splash4_animationFrameCounter < 45) {
                g.drawImage(bufferedImageMap.get("splash_8"), 322, 205, this);
            } else if (splash4_animationFrameCounter < 50) {
                g.drawImage(bufferedImageMap.get("splash_9"), 322, 205, this);

            } else if (splash4_animationFrameCounter < 55) {
                g.drawImage(bufferedImageMap.get("splash_10"), 322, 205, this);

            } else if (splash4_animationFrameCounter < 60) {
                g.drawImage(bufferedImageMap.get("splash_11"), 322, 205, this);

            } else if (splash4_animationFrameCounter < 65) {
                g.drawImage(bufferedImageMap.get("splash_12"), 322, 205, this);

            } else if (splash4_animationFrameCounter < 70) {
                g.drawImage(bufferedImageMap.get("splash_13"), 322, 205, this);

            } else if (splash4_animationFrameCounter < 75) {
                g.drawImage(bufferedImageMap.get("splash_14"), 322, 205, this);

            }

            if (splash4_animationFrameCounter == 76)

            {
                splash4_animationFrameCounter = 0;
                duelHandler.splashAnimation4Request = false;

            }

            splash4_animationFrameCounter += 4;

        } else if (requestedPosition == 5) {

            if (splash5_animationFrameCounter < 5) {
                g.drawImage(bufferedImageMap.get("splash_0"), 482, 407, this);

            } else if (splash5_animationFrameCounter < 10) {
                g.drawImage(bufferedImageMap.get("splash_1"), 482, 407, this);

            } else if (splash5_animationFrameCounter < 15) {
                g.drawImage(bufferedImageMap.get("splash_2"), 482, 407, this);

            } else if (splash5_animationFrameCounter < 20) {
                g.drawImage(bufferedImageMap.get("splash_3"), 482, 407, this);

            } else if (splash5_animationFrameCounter < 25) {
                g.drawImage(bufferedImageMap.get("splash_4"), 482, 407, this);

            } else if (splash5_animationFrameCounter < 30) {
                g.drawImage(bufferedImageMap.get("splash_5"), 482, 407, this);

            } else if (splash5_animationFrameCounter < 35) {
                g.drawImage(bufferedImageMap.get("splash_6"), 482, 407, this);

            } else if (splash5_animationFrameCounter < 40) {
                g.drawImage(bufferedImageMap.get("splash_7"), 482, 407, this);

            } else if (splash5_animationFrameCounter < 45) {
                g.drawImage(bufferedImageMap.get("splash_8"), 482, 407, this);
            } else if (splash5_animationFrameCounter < 50) {
                g.drawImage(bufferedImageMap.get("splash_9"), 482, 407, this);

            } else if (splash5_animationFrameCounter < 55) {
                g.drawImage(bufferedImageMap.get("splash_10"), 482, 407, this);

            } else if (splash5_animationFrameCounter < 60) {
                g.drawImage(bufferedImageMap.get("splash_11"), 482, 407, this);

            } else if (splash5_animationFrameCounter < 65) {
                g.drawImage(bufferedImageMap.get("splash_12"), 482, 407, this);

            } else if (splash5_animationFrameCounter < 70) {
                g.drawImage(bufferedImageMap.get("splash_13"), 482, 407, this);

            } else if (splash5_animationFrameCounter < 75) {
                g.drawImage(bufferedImageMap.get("splash_14"), 482, 407, this);

            }

            if (splash5_animationFrameCounter == 76)

            {
                splash5_animationFrameCounter = 0;
                duelHandler.splashAnimation5Request = false;

            }

            splash5_animationFrameCounter += 4;

        } else if (requestedPosition == 6) {

            if (splash6_animationFrameCounter < 5) {
                g.drawImage(bufferedImageMap.get("splash_0"), 482, 205, this);

            } else if (splash6_animationFrameCounter < 10) {
                g.drawImage(bufferedImageMap.get("splash_1"), 482, 205, this);

            } else if (splash6_animationFrameCounter < 15) {
                g.drawImage(bufferedImageMap.get("splash_2"), 482, 205, this);

            } else if (splash6_animationFrameCounter < 20) {
                g.drawImage(bufferedImageMap.get("splash_3"), 482, 205, this);

            } else if (splash6_animationFrameCounter < 25) {
                g.drawImage(bufferedImageMap.get("splash_4"), 482, 205, this);

            } else if (splash6_animationFrameCounter < 30) {
                g.drawImage(bufferedImageMap.get("splash_5"), 482, 205, this);

            } else if (splash6_animationFrameCounter < 35) {
                g.drawImage(bufferedImageMap.get("splash_6"), 482, 205, this);

            } else if (splash6_animationFrameCounter < 40) {
                g.drawImage(bufferedImageMap.get("splash_7"), 482, 205, this);

            } else if (splash6_animationFrameCounter < 45) {
                g.drawImage(bufferedImageMap.get("splash_8"), 482, 205, this);
            } else if (splash6_animationFrameCounter < 50) {
                g.drawImage(bufferedImageMap.get("splash_9"), 482, 205, this);

            } else if (splash6_animationFrameCounter < 55) {
                g.drawImage(bufferedImageMap.get("splash_10"), 482, 205, this);

            } else if (splash6_animationFrameCounter < 60) {
                g.drawImage(bufferedImageMap.get("splash_11"), 482, 205, this);

            } else if (splash6_animationFrameCounter < 65) {
                g.drawImage(bufferedImageMap.get("splash_12"), 482, 205, this);

            } else if (splash6_animationFrameCounter < 70) {
                g.drawImage(bufferedImageMap.get("splash_13"), 482, 205, this);

            } else if (splash6_animationFrameCounter < 75) {
                g.drawImage(bufferedImageMap.get("splash_14"), 482, 205, this);

            }

            if (splash6_animationFrameCounter == 76)

            {
                splash6_animationFrameCounter = 0;
                duelHandler.splashAnimation6Request = false;

            }

            splash6_animationFrameCounter += 4;

        } else if (requestedPosition == 7) {

            if (splash7_animationFrameCounter < 5) {
                g.drawImage(bufferedImageMap.get("splash_0"), 642, 407, this);

            } else if (splash7_animationFrameCounter < 10) {
                g.drawImage(bufferedImageMap.get("splash_1"), 642, 407, this);

            } else if (splash7_animationFrameCounter < 15) {
                g.drawImage(bufferedImageMap.get("splash_2"), 642, 407, this);

            } else if (splash7_animationFrameCounter < 20) {
                g.drawImage(bufferedImageMap.get("splash_3"), 642, 407, this);

            } else if (splash7_animationFrameCounter < 25) {
                g.drawImage(bufferedImageMap.get("splash_4"), 642, 407, this);

            } else if (splash7_animationFrameCounter < 30) {
                g.drawImage(bufferedImageMap.get("splash_5"), 642, 407, this);

            } else if (splash7_animationFrameCounter < 35) {
                g.drawImage(bufferedImageMap.get("splash_6"), 642, 407, this);

            } else if (splash7_animationFrameCounter < 40) {
                g.drawImage(bufferedImageMap.get("splash_7"), 642, 407, this);

            } else if (splash7_animationFrameCounter < 45) {
                g.drawImage(bufferedImageMap.get("splash_8"), 642, 407, this);
            } else if (splash7_animationFrameCounter < 50) {
                g.drawImage(bufferedImageMap.get("splash_9"), 642, 407, this);

            } else if (splash7_animationFrameCounter < 55) {
                g.drawImage(bufferedImageMap.get("splash_10"), 642, 407, this);

            } else if (splash7_animationFrameCounter < 60) {
                g.drawImage(bufferedImageMap.get("splash_11"), 642, 407, this);

            } else if (splash7_animationFrameCounter < 65) {
                g.drawImage(bufferedImageMap.get("splash_12"), 642, 407, this);

            } else if (splash7_animationFrameCounter < 70) {
                g.drawImage(bufferedImageMap.get("splash_13"), 642, 407, this);

            } else if (splash7_animationFrameCounter < 75) {
                g.drawImage(bufferedImageMap.get("splash_14"), 642, 407, this);

            }

            if (splash7_animationFrameCounter == 76)

            {
                splash7_animationFrameCounter = 0;
                duelHandler.splashAnimation7Request = false;

            }

            splash7_animationFrameCounter += 4;

        } else if (requestedPosition == 8) {
            if (splash8_animationFrameCounter < 5) {
                g.drawImage(bufferedImageMap.get("splash_0"), 642, 205, this);

            } else if (splash8_animationFrameCounter < 10) {
                g.drawImage(bufferedImageMap.get("splash_1"), 642, 205, this);

            } else if (splash8_animationFrameCounter < 15) {
                g.drawImage(bufferedImageMap.get("splash_2"), 642, 205, this);

            } else if (splash8_animationFrameCounter < 20) {
                g.drawImage(bufferedImageMap.get("splash_3"), 642, 205, this);

            } else if (splash8_animationFrameCounter < 25) {
                g.drawImage(bufferedImageMap.get("splash_4"), 642, 205, this);

            } else if (splash8_animationFrameCounter < 30) {
                g.drawImage(bufferedImageMap.get("splash_5"), 642, 205, this);

            } else if (splash8_animationFrameCounter < 35) {
                g.drawImage(bufferedImageMap.get("splash_6"), 642, 205, this);

            } else if (splash8_animationFrameCounter < 40) {
                g.drawImage(bufferedImageMap.get("splash_7"), 642, 205, this);

            } else if (splash8_animationFrameCounter < 45) {
                g.drawImage(bufferedImageMap.get("splash_8"), 642, 205, this);
            } else if (splash8_animationFrameCounter < 50) {
                g.drawImage(bufferedImageMap.get("splash_9"), 642, 205, this);

            } else if (splash8_animationFrameCounter < 55) {
                g.drawImage(bufferedImageMap.get("splash_10"), 642, 205, this);

            } else if (splash8_animationFrameCounter < 60) {
                g.drawImage(bufferedImageMap.get("splash_11"), 642, 205, this);

            } else if (splash8_animationFrameCounter < 65) {
                g.drawImage(bufferedImageMap.get("splash_12"), 642, 205, this);

            } else if (splash8_animationFrameCounter < 70) {
                g.drawImage(bufferedImageMap.get("splash_13"), 642, 205, this);

            } else if (splash8_animationFrameCounter < 75) {
                g.drawImage(bufferedImageMap.get("splash_14"), 642, 205, this);

            }

            if (splash8_animationFrameCounter == 76)

            {
                splash8_animationFrameCounter = 0;
                duelHandler.splashAnimation8Request = false;

            }

            splash8_animationFrameCounter += 4;

        } else if (requestedPosition == 9) {


            if (splash9_animationFrameCounter < 5) {
                g.drawImage(bufferedImageMap.get("splash_0"), 237, 569, this);

            } else if (splash9_animationFrameCounter < 10) {
                g.drawImage(bufferedImageMap.get("splash_1"), 237, 569, this);

            } else if (splash9_animationFrameCounter < 15) {
                g.drawImage(bufferedImageMap.get("splash_2"), 237, 569, this);

            } else if (splash9_animationFrameCounter < 20) {
                g.drawImage(bufferedImageMap.get("splash_3"), 237, 569, this);

            } else if (splash9_animationFrameCounter < 25) {
                g.drawImage(bufferedImageMap.get("splash_4"), 237, 569, this);

            } else if (splash9_animationFrameCounter < 30) {
                g.drawImage(bufferedImageMap.get("splash_5"), 237, 569, this);

            } else if (splash9_animationFrameCounter < 35) {
                g.drawImage(bufferedImageMap.get("splash_6"), 237, 569, this);

            } else if (splash9_animationFrameCounter < 40) {
                g.drawImage(bufferedImageMap.get("splash_7"), 237, 569, this);

            } else if (splash9_animationFrameCounter < 45) {
                g.drawImage(bufferedImageMap.get("splash_8"), 237, 569, this);
            } else if (splash9_animationFrameCounter < 50) {
                g.drawImage(bufferedImageMap.get("splash_9"), 237, 569, this);

            } else if (splash9_animationFrameCounter < 55) {
                g.drawImage(bufferedImageMap.get("splash_10"), 237, 569, this);

            } else if (splash9_animationFrameCounter < 60) {
                g.drawImage(bufferedImageMap.get("splash_11"), 237, 569, this);

            } else if (splash9_animationFrameCounter < 65) {
                g.drawImage(bufferedImageMap.get("splash_12"), 237, 569, this);

            } else if (splash9_animationFrameCounter < 70) {
                g.drawImage(bufferedImageMap.get("splash_13"), 237, 569, this);

            } else if (splash9_animationFrameCounter < 75) {
                g.drawImage(bufferedImageMap.get("splash_14"), 237, 569, this);

            }

            if (splash9_animationFrameCounter == 76)

            {
                splash9_animationFrameCounter = 0;
                duelHandler.splashAnimation9Request = false;

            }

            splash9_animationFrameCounter += 4;

        } else if (requestedPosition == 10) {
            if (splash10_animationFrameCounter < 5) {
                g.drawImage(bufferedImageMap.get("splash_0"), 237, 44, this);

            } else if (splash10_animationFrameCounter < 10) {
                g.drawImage(bufferedImageMap.get("splash_1"), 237, 44, this);

            } else if (splash10_animationFrameCounter < 15) {
                g.drawImage(bufferedImageMap.get("splash_2"), 237, 44, this);

            } else if (splash10_animationFrameCounter < 20) {
                g.drawImage(bufferedImageMap.get("splash_3"), 237, 44, this);

            } else if (splash10_animationFrameCounter < 25) {
                g.drawImage(bufferedImageMap.get("splash_4"), 237, 44, this);

            } else if (splash10_animationFrameCounter < 30) {
                g.drawImage(bufferedImageMap.get("splash_5"), 237, 44, this);

            } else if (splash10_animationFrameCounter < 35) {
                g.drawImage(bufferedImageMap.get("splash_6"), 237, 44, this);

            } else if (splash10_animationFrameCounter < 40) {
                g.drawImage(bufferedImageMap.get("splash_7"), 237, 44, this);

            } else if (splash10_animationFrameCounter < 45) {
                g.drawImage(bufferedImageMap.get("splash_8"), 237, 44, this);
            } else if (splash10_animationFrameCounter < 50) {
                g.drawImage(bufferedImageMap.get("splash_9"), 237, 44, this);

            } else if (splash10_animationFrameCounter < 55) {
                g.drawImage(bufferedImageMap.get("splash_10"), 237, 44, this);

            } else if (splash10_animationFrameCounter < 60) {
                g.drawImage(bufferedImageMap.get("splash_11"), 237, 44, this);

            } else if (splash10_animationFrameCounter < 65) {
                g.drawImage(bufferedImageMap.get("splash_12"), 237, 44, this);

            } else if (splash10_animationFrameCounter < 70) {
                g.drawImage(bufferedImageMap.get("splash_13"), 237, 44, this);

            } else if (splash10_animationFrameCounter < 75) {
                g.drawImage(bufferedImageMap.get("splash_14"), 237, 44, this);

            }

            if (splash10_animationFrameCounter == 76)

            {
                splash10_animationFrameCounter = 0;
                duelHandler.splashAnimation10Request = false;

            }

            splash10_animationFrameCounter += 4;
        } else if (requestedPosition == 11) {

            if (splash11_animationFrameCounter < 5) {
                g.drawImage(bufferedImageMap.get("splash_0"), 395, 569, this);

            } else if (splash11_animationFrameCounter < 10) {
                g.drawImage(bufferedImageMap.get("splash_1"), 395, 569, this);

            } else if (splash11_animationFrameCounter < 15) {
                g.drawImage(bufferedImageMap.get("splash_2"), 395, 569, this);

            } else if (splash11_animationFrameCounter < 20) {
                g.drawImage(bufferedImageMap.get("splash_3"), 395, 569, this);

            } else if (splash11_animationFrameCounter < 25) {
                g.drawImage(bufferedImageMap.get("splash_4"), 395, 569, this);

            } else if (splash11_animationFrameCounter < 30) {
                g.drawImage(bufferedImageMap.get("splash_5"), 395, 569, this);

            } else if (splash11_animationFrameCounter < 35) {
                g.drawImage(bufferedImageMap.get("splash_6"), 395, 569, this);

            } else if (splash11_animationFrameCounter < 40) {
                g.drawImage(bufferedImageMap.get("splash_7"), 395, 569, this);

            } else if (splash11_animationFrameCounter < 45) {
                g.drawImage(bufferedImageMap.get("splash_8"), 395, 569, this);
            } else if (splash11_animationFrameCounter < 50) {
                g.drawImage(bufferedImageMap.get("splash_9"), 395, 569, this);

            } else if (splash11_animationFrameCounter < 55) {
                g.drawImage(bufferedImageMap.get("splash_10"), 395, 569, this);

            } else if (splash11_animationFrameCounter < 60) {
                g.drawImage(bufferedImageMap.get("splash_11"), 395, 569, this);

            } else if (splash11_animationFrameCounter < 65) {
                g.drawImage(bufferedImageMap.get("splash_12"), 395, 569, this);

            } else if (splash11_animationFrameCounter < 70) {
                g.drawImage(bufferedImageMap.get("splash_13"), 395, 569, this);

            } else if (splash11_animationFrameCounter < 75) {
                g.drawImage(bufferedImageMap.get("splash_14"), 395, 569, this);

            }

            if (splash11_animationFrameCounter == 76)

            {
                splash11_animationFrameCounter = 0;
                duelHandler.splashAnimation11Request = false;

            }

            splash11_animationFrameCounter += 4;

        } else if (requestedPosition == 12) {

            if (splash12_animationFrameCounter < 5) {
                g.drawImage(bufferedImageMap.get("splash_0"), 395, 44, this);

            } else if (splash12_animationFrameCounter < 10) {
                g.drawImage(bufferedImageMap.get("splash_1"), 395, 44, this);

            } else if (splash12_animationFrameCounter < 15) {
                g.drawImage(bufferedImageMap.get("splash_2"), 395, 44, this);

            } else if (splash12_animationFrameCounter < 20) {
                g.drawImage(bufferedImageMap.get("splash_3"), 395, 44, this);

            } else if (splash12_animationFrameCounter < 25) {
                g.drawImage(bufferedImageMap.get("splash_4"), 395, 44, this);

            } else if (splash12_animationFrameCounter < 30) {
                g.drawImage(bufferedImageMap.get("splash_5"), 395, 44, this);

            } else if (splash12_animationFrameCounter < 35) {
                g.drawImage(bufferedImageMap.get("splash_6"), 395, 44, this);

            } else if (splash12_animationFrameCounter < 40) {
                g.drawImage(bufferedImageMap.get("splash_7"), 395, 44, this);

            } else if (splash12_animationFrameCounter < 45) {
                g.drawImage(bufferedImageMap.get("splash_8"), 395, 44, this);
            } else if (splash12_animationFrameCounter < 50) {
                g.drawImage(bufferedImageMap.get("splash_9"), 395, 44, this);

            } else if (splash12_animationFrameCounter < 55) {
                g.drawImage(bufferedImageMap.get("splash_10"), 395, 44, this);

            } else if (splash12_animationFrameCounter < 60) {
                g.drawImage(bufferedImageMap.get("splash_11"), 395, 44, this);

            } else if (splash12_animationFrameCounter < 65) {
                g.drawImage(bufferedImageMap.get("splash_12"), 395, 44, this);

            } else if (splash12_animationFrameCounter < 70) {
                g.drawImage(bufferedImageMap.get("splash_13"), 395, 44, this);

            } else if (splash12_animationFrameCounter < 75) {
                g.drawImage(bufferedImageMap.get("splash_14"), 395, 44, this);

            }

            if (splash12_animationFrameCounter == 76)

            {
                splash12_animationFrameCounter = 0;
                duelHandler.splashAnimation12Request = false;

            }

            splash12_animationFrameCounter += 4;
        } else if (requestedPosition == 13) {
            if (splash13_animationFrameCounter < 5) {
                g.drawImage(bufferedImageMap.get("splash_0"), 557, 569, this);

            } else if (splash13_animationFrameCounter < 10) {
                g.drawImage(bufferedImageMap.get("splash_1"), 557, 569, this);

            } else if (splash13_animationFrameCounter < 15) {
                g.drawImage(bufferedImageMap.get("splash_2"), 557, 569, this);

            } else if (splash13_animationFrameCounter < 20) {
                g.drawImage(bufferedImageMap.get("splash_3"), 557, 569, this);

            } else if (splash13_animationFrameCounter < 25) {
                g.drawImage(bufferedImageMap.get("splash_4"), 557, 569, this);

            } else if (splash13_animationFrameCounter < 30) {
                g.drawImage(bufferedImageMap.get("splash_5"), 557, 569, this);

            } else if (splash13_animationFrameCounter < 35) {
                g.drawImage(bufferedImageMap.get("splash_6"), 557, 569, this);

            } else if (splash13_animationFrameCounter < 40) {
                g.drawImage(bufferedImageMap.get("splash_7"), 557, 569, this);

            } else if (splash13_animationFrameCounter < 45) {
                g.drawImage(bufferedImageMap.get("splash_8"), 557, 569, this);
            } else if (splash13_animationFrameCounter < 50) {
                g.drawImage(bufferedImageMap.get("splash_9"), 557, 569, this);

            } else if (splash13_animationFrameCounter < 55) {
                g.drawImage(bufferedImageMap.get("splash_10"), 557, 569, this);

            } else if (splash13_animationFrameCounter < 60) {
                g.drawImage(bufferedImageMap.get("splash_11"), 557, 569, this);

            } else if (splash13_animationFrameCounter < 65) {
                g.drawImage(bufferedImageMap.get("splash_12"), 557, 569, this);

            } else if (splash13_animationFrameCounter < 70) {
                g.drawImage(bufferedImageMap.get("splash_13"), 557, 569, this);

            } else if (splash13_animationFrameCounter < 75) {
                g.drawImage(bufferedImageMap.get("splash_14"), 557, 569, this);

            }

            if (splash13_animationFrameCounter == 76)

            {
                splash13_animationFrameCounter = 0;
                duelHandler.splashAnimation13Request = false;

            }

            splash13_animationFrameCounter += 4;

        } else if (requestedPosition == 14) {
            if (splash14_animationFrameCounter < 5) {
                g.drawImage(bufferedImageMap.get("splash_0"), 557, 44, this);

            } else if (splash14_animationFrameCounter < 10) {
                g.drawImage(bufferedImageMap.get("splash_1"), 557, 44, this);

            } else if (splash14_animationFrameCounter < 15) {
                g.drawImage(bufferedImageMap.get("splash_2"), 557, 44, this);

            } else if (splash14_animationFrameCounter < 20) {
                g.drawImage(bufferedImageMap.get("splash_3"), 557, 44, this);

            } else if (splash14_animationFrameCounter < 25) {
                g.drawImage(bufferedImageMap.get("splash_4"), 557, 44, this);

            } else if (splash14_animationFrameCounter < 30) {
                g.drawImage(bufferedImageMap.get("splash_5"), 557, 44, this);

            } else if (splash14_animationFrameCounter < 35) {
                g.drawImage(bufferedImageMap.get("splash_6"), 557, 44, this);

            } else if (splash14_animationFrameCounter < 40) {
                g.drawImage(bufferedImageMap.get("splash_7"), 557, 44, this);

            } else if (splash14_animationFrameCounter < 45) {
                g.drawImage(bufferedImageMap.get("splash_8"), 557, 44, this);
            } else if (splash14_animationFrameCounter < 50) {
                g.drawImage(bufferedImageMap.get("splash_9"), 557, 44, this);

            } else if (splash14_animationFrameCounter < 55) {
                g.drawImage(bufferedImageMap.get("splash_10"), 557, 44, this);

            } else if (splash14_animationFrameCounter < 60) {
                g.drawImage(bufferedImageMap.get("splash_11"), 557, 44, this);

            } else if (splash14_animationFrameCounter < 65) {
                g.drawImage(bufferedImageMap.get("splash_12"), 557, 44, this);

            } else if (splash14_animationFrameCounter < 70) {
                g.drawImage(bufferedImageMap.get("splash_13"), 557, 44, this);

            } else if (splash14_animationFrameCounter < 75) {
                g.drawImage(bufferedImageMap.get("splash_14"), 557, 44, this);

            }

            if (splash14_animationFrameCounter == 76)

            {
                splash14_animationFrameCounter = 0;
                duelHandler.splashAnimation14Request = false;

            }

            splash14_animationFrameCounter += 4;
        }
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
            } // STOPSHIP TODO: NEEDS EXPANSION FOR ALL OTHER TOOLTIPS

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


    private void paintVitalInformation(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

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

        g2d.setColor(Color.black);


        if (duelHandler.getScore_p1_human() <= 99) {
            font5 = new Font("Consola", Font.BOLD, 115);
        } else if (duelHandler.getScore_p1_human() <= 999) {
            font5 = new Font("Consola", Font.BOLD, 75); // size undetermined
        }

        g2d.setFont(font5);
        g2d.drawString(String.valueOf(duelHandler.getScore_p1_human()), 10, 492);


        if (duelHandler.getScore_p2_cpu() <= 99) {
            font5 = new Font("Consola", Font.BOLD, 115);
        } else if (duelHandler.getScore_p2_cpu() <= 999) {
            font5 = new Font("Consola", Font.BOLD, 75); // size undetermined
        }
        g2d.setFont(font5);
        g2d.drawString(String.valueOf(duelHandler.getScore_p2_cpu()), 10, 371);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == timer && paint()) {
            this.repaint();

            if (trigger_startDuel) {
                trigger_startDuel = false;
                duelInProgress = true;

                shuffleDeck(p1_human.getDecklist());
                duelHandler = new DuelHandler(p1_human.getDecklist());
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

    private void shuffleDeck(Deque<Card> deckList) {

        List<Card> cardList = new LinkedList<>(deckList);

        Collections.shuffle(cardList);

        deckList = new LinkedList<>(cardList);

        p1_human.setDecklist(deckList);


    }

    private void drawCards2() {


        for (int i = 0; i < 6; i++) {
            if (duelHandler.getHand_p1_human()[i].getName().equals("null")) {
                duelHandler.getHand_p1_human()[i] = duelHandler.getDecklist_p1_human().pollFirst();
                break;
            }
        }
    }


    private void drawCards1(int index) {
        duelHandler.getHand_p1_human()[index] = duelHandler.getDecklist_p1_human().pollFirst();
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

            case KeyEvent.VK_1:

                ONE_PRESSED = true;

                break;

            case KeyEvent.VK_2:

                TWO_PRESSED = true;

                break;

            case KeyEvent.VK_3:

                THREE_PRESSED = true;

                break;

            case KeyEvent.VK_4:

                FOUR_PRESSED = true;

                break;

            case KeyEvent.VK_5:

                FIVE_PRESSED = true;

                break;

            case KeyEvent.VK_6:

                SIX_PRESSED = true;

                break;

            case KeyEvent.VK_7:

                SEVEN_PRESSED = true;

                break;

            case KeyEvent.VK_SHIFT:

                SHIFT_PRESSED = true;

                break;


        }
    }


    @Override
    public void keyReleased(KeyEvent e) {

        resetAnimationCounters(); // Assures that all animation frames are reset in case of a key release. TODO: 2016-06-16  #Hackfix.

        switch (e.getKeyCode()) {

            case KeyEvent.VK_1:

                ONE_PRESSED = false;


                break;

            case KeyEvent.VK_2:

                TWO_PRESSED = false;


                break;

            case KeyEvent.VK_3:

                THREE_PRESSED = false;

                break;

            case KeyEvent.VK_4:

                FOUR_PRESSED = false;

                break;

            case KeyEvent.VK_5:

                FIVE_PRESSED = false;

                break;

            case KeyEvent.VK_6:

                SIX_PRESSED = false;

                break;

            case KeyEvent.VK_7:

                SEVEN_PRESSED = false;

                break;

            case KeyEvent.VK_SHIFT:

                SHIFT_PRESSED = false;

                break;
        }

    }

    private void resetAnimationCounters() { // resets all animation frame counters, mostly used to stop a bug where if you are debugging the splash animations and leave the frame counter at a high level the animation seem to not play since they are only a few frames short.

        splash1_animationFrameCounter = 0;
        splash2_animationFrameCounter = 0;
        splash3_animationFrameCounter = 0;
        splash4_animationFrameCounter = 0;
        splash5_animationFrameCounter = 0;
        splash6_animationFrameCounter = 0;
        splash7_animationFrameCounter = 0;
        splash8_animationFrameCounter = 0;
        splash9_animationFrameCounter = 0;
        splash10_animationFrameCounter = 0;
        splash11_animationFrameCounter = 0;
        splash12_animationFrameCounter = 0;
        splash13_animationFrameCounter = 0;
        splash14_animationFrameCounter = 0;
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
        loadBufferedImage("dragon_icon.png", "dragon_icon");
        loadBufferedImage("UserIcon1.png", "UserIcon1");
        loadBufferedImage("UserIcon2.png", "UserIcon2");
        loadBufferedImage("UserIcon3.png", "UserIcon3");
        loadBufferedImage("UserIcon4.png", "UserIcon4");
        loadBufferedImage("UserIcon5.png", "UserIcon5");
        loadBufferedImage("GreenDragon.png", "GreenDragon");


        loadBufferedImage("splash0.png", "splash_0");
        loadBufferedImage("splash1.png", "splash_1");
        loadBufferedImage("splash2.png", "splash_2");
        loadBufferedImage("splash3.png", "splash_3");
        loadBufferedImage("splash4.png", "splash_4");
        loadBufferedImage("splash5.png", "splash_5");
        loadBufferedImage("splash6.png", "splash_6");
        loadBufferedImage("splash7.png", "splash_7");
        loadBufferedImage("splash8.png", "splash_8");
        loadBufferedImage("splash9.png", "splash_9");
        loadBufferedImage("splash10.png", "splash_10");
        loadBufferedImage("splash11.png", "splash_11");
        loadBufferedImage("splash12.png", "splash_12");
        loadBufferedImage("splash13.png", "splash_13");
        loadBufferedImage("splash14.png", "splash_14");


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

