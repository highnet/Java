public class Chess {

    public static void drawChessBoard(int userInput) {


        final char EMPTY = ' '; // Used to represent an empty slot. Initially the boardState array is filled with empty slots.
        final char OCCUPIED = 'T'; // Used to represent an occupied board slot .T stands for tower.

        final String fieldSeparator = "|***|***|***|***|***|***|***|***|"; // Used as a separator between slot line fields.


        char[] boardState = new char[65]; // Initialize the array which represents our chess board.

        int slotCounter = 0;    // Used to count which slot is being edited/read.

        for (int i = 0; i <= 64; i++) { // This loop populates our board with empty slots and with towers on the slots specified by the user.

            boardState[i] = EMPTY; // ' ' empty board state.

            if (slotCounter == userInput) { // Every [userInput] slots is "occupied" with a Tower(T).
                boardState[i] = OCCUPIED; // 'T' tower Board state.
                slotCounter = 0;  // reset the counter once it occupies a slot.
            }
            slotCounter++; // increment the counter.
        }


        for (int i = 1; i <= 64; i++) { // Debug loop.  Shows a list of the board state.
            System.out.println("[DEV_DEBUG]:  Field_Number = " + i + "  Board_State = " + boardState[i]);
        }

        slotCounter = 1; // Re-using the slotCounter variable to go through each array slot and print out its result.

        for (int i = 0; i < 8; i++) { // Repeat 8 times(one for each chess row field.
            System.out.println(fieldSeparator);  // Prints out a field separator.
            System.out.println("| " + boardState[slotCounter++] + " | " + boardState[slotCounter++] + " | " + boardState[slotCounter++] + " | " + boardState[slotCounter++] + " | " + boardState[slotCounter++] + " | " + boardState[slotCounter++] + " | " + boardState[slotCounter++] + " | " + boardState[slotCounter++] + " |");
                                    // print out the board.
        }
        System.out.println(fieldSeparator); // print out a final field separator.

    }

    public static void main(String[] args) {

        int n = 7; // user input. fills a chess board with towers(T) for every Nth slot.


        drawChessBoard(n); // run the chess board drawing method.


    }
}
