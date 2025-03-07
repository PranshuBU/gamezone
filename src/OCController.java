/*
 * OCController.java
 *
 * This class inherits from BoardController, and manages the specific game flow for Order & Chaos. It defines
 * the O&C specific version of abstract BoardController methods such as runGame. It handles the actual player turns/inputs
 * in game, updating the board, and checking for a draw/end of the game.
 *
 */
import java.util.List;

public class OCController extends BoardController {

    public OCController(Board board, List<Player> players, Rules rules) {
        super(board, players, rules);
    }

    public void runGame() {
        board.printBoard();

        while (!board.isFull()) {
            System.out.println("Team " + currentPlayer.getTeam().getName() + ", it's your turn.");

            // allow player to pick a piece/symbol every turn
            char chosenPiece;
            do {
                String input = Input.getStringInput("Enter the piece to add (X or O): ");
                chosenPiece = input.toUpperCase().charAt(0);
            } while (chosenPiece != 'X' && chosenPiece != 'O');

            int squareNumber = Input.getIntInput("Enter the square number (1-" + (board.getSize() * board.getSize()) + "): ");

            //converting square number to coordinates
            int row = (squareNumber - 1) / board.getSize();
            int col = (squareNumber - 1) % board.getSize();

            // special handleMove function because has specific x/o choice
            if (handleMove(row, col, chosenPiece)) {
                board.printBoard();
            } else {
                return;
            }

            // checking if it's a draw
            if (board.isFull()) {
                System.out.println("Chaos wins!");
                return;
            }
        }
    }

}
