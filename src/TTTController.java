/*
 * TTTController.java
 *
 * This class inherits from BoardController, and manages the specific game flow for Tic Tac Toe. It defines
 * the TTT specific version of abstract BoardController methods such as runGame. It handles the actual player turns/inputs
 * in game, updating the board, and checking for a draw/end of the game.
 *
 */
import java.util.List;

public class TTTController extends BoardController {

    public TTTController(Board board, List<Player> players, Rules rules) {
        super(board, players, rules);
    }

    public void runGame() {
        board.printBoard();

        while (!board.isFull()) {
            System.out.println("Player " + currentPlayer.getPiece().getSymbol() + ", it's your turn.");

            int squareNumber = Input.getIntInput("Enter the square number (1-" + (board.getSize() * board.getSize()) + "): ");

            if (squareNumber < 1 || squareNumber > (board.getSize() * board.getSize())) {
                System.out.println("Invalid square number. Please try again.");
                continue;
            }
            // convert the square number into coordinates
            int row = (squareNumber - 1) / board.getSize();
            int col = (squareNumber - 1) % board.getSize();

            if (!handleMove(row, col)) {
                return;
            }

            board.printBoard();
        }
        //if it's a full board with no winner so far, it's a draw
        System.out.println("It's a draw!");
    }

}
