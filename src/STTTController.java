/*
 * STTTController.java
 *
 * This class inherits from BoardController, and manages the specific game flow for Super Tic Tac Toe. It defines
 * the STTT specific version of abstract BoardController methods such as runGame. It handles the actual player turns/inputs
 * in game, updating the board, and checking for a draw/end of the game. Also has methods specific to STTT
 * for printing the super board, and checking if the super board is full.
 *
 */

import java.util.List;

public class STTTController extends BoardController {
    private Board[][] superBoard; // 3x3 grid of ttt boards

    public STTTController(Board[][] superBoard, List<Player> players, STTTRules rules) {
        super(superBoard[0][0], players, rules); // initialize with the first sub-board in superboard
        this.superBoard = superBoard; // assigning superBoard
    }

    public void runGame() {
        while (!isSuperBoardFull()) {
            printSuperBoard(); // special print board method for STTT unlike TTT/OC

            System.out.println("Player " + currentPlayer.getPiece().getSymbol() + ", it's your turn.");
            char subBoardChoice = Input.getCharInput("Choose a sub-board (A-I): ");

            //valiidate subboardchoice
            if (subBoardChoice < 'A' || subBoardChoice > 'I') {
                System.out.println("Invalid input! Please choose a sub-board from A to I.");
                continue;
            }

            //convert board choice to number
            int boardNumber= subBoardChoice - 'A'; // 'A' = 0, ..., 'I' is 8
            Board subBoard = superBoard[boardNumber / 3][boardNumber % 3];
            int subBoardSize = superBoard[boardNumber/3][boardNumber % 3].getSize();
            int squareNumber = Input.getIntInput("Enter the square number (1-" + (subBoardSize * subBoardSize) + "): ");

            //validate square choice of the chosen subboard
            if (squareNumber < 1 || squareNumber > (subBoardSize * subBoardSize)) {
                System.out.println("Invalid square number. Please try again.");
                continue;
            }

            //convert square number to coordinates within the sub-board
            int row = (squareNumber - 1) / subBoardSize;
            int col = (squareNumber - 1) % subBoardSize;

            if (!handleMove(subBoard, row, col)) {
                return;
            }

        }
        //if it's a full board with no winner so far, it's a draw
        System.out.println("It's a draw!");
    }

    //special isfull method for superboard
    private boolean isSuperBoardFull() {
        for (int i = 0; i < superBoard.length; i++) {
            for (int j = 0; j < superBoard[i].length; j++) {
                if (!superBoard[i][j].isFull()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printSuperBoard() {
        int subBoardSize = superBoard[0][0].getSize();

        for (int superRow = 0; superRow < 3; superRow++) {
            //grid lines in the board before every row
            System.out.print("+");
            for (int col = 0; col < 3; col++) {
                //for each subboard, grid lines adaptable to size of board (# of columns)
                for (int d = 0; d < subBoardSize; d++) {
                    System.out.print("---");
                }
                //end of subboard
                System.out.print("+   ");
            }
            System.out.println();

            //printing each subBoard row
            for (int row = 0; row < subBoardSize; row++) {
                for (int col = 0; col < 3; col++) {
                    System.out.print(superBoard[superRow][col].getIndividualRow(row));
                    System.out.print("   ");
                }
                System.out.println();
            }
        }

        //ending grid line based on all subboards
        System.out.print("+");
        for (int col = 0; col < 3; col++) {
            for (int d = 0; d < subBoardSize; d++) {
                System.out.print("---");
            }
            System.out.print("+  "); //end of subboard
        }
        System.out.println();
    }
}
