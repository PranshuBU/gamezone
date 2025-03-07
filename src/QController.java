/*
 * QController.java
 *
 * This class inherits from BoardController, and manages the specific game flow for Quoridor. It defines
 * the Quoridor specific version of abstract BoardController methods such as runGame. It handles the actual player turns/inputs
 * in game, updating the board, and handling the 2 specific types of moves: fence placement, or pawn moving.
 *
 */

import java.util.List;

public class QController extends BoardController {

    public QController(QBoard board, List<Player> players, QRules rules) {
        super(board, players, rules);
        //setting up starting positions for both players: bottom middle for player P/1, top middle for player X/2
        int middleCol = board.getSize() / 2;
        board.addPiece(board.getSize() - 1, middleCol, players.get(0).getPiece());
        board.addPiece(0, middleCol, players.get(1).getPiece());
    }

    public void runGame() {
        //making sure qBoard used
        QBoard qBoard = (QBoard) board;
        qBoard.printBoard();

        while (!rules.checkWinCondition(qBoard)) {
            System.out.println("Player " + currentPlayer.getPiece().getSymbol() + ", it's your turn.");
            int move = Input.getIntInput("Enter 1 to move your pawn or 2 to put a fence: ");
            if (move == 1) {
                handlePawnMove(qBoard);
            } else if (move == 2) {
                handleFencePlacement(qBoard);
            } else {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            //printing updated board after each turn
            qBoard.printBoard();
        }
    }

    //handling pawn move—user input, placing piece & removing old place, validating
    protected void handlePawnMove(QBoard board) {
        System.out.println("Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right");
        int move = Input.getIntInput("Enter your move: ");

        int[] currentPosition = board.getPlayerSquare(currentPlayer.getPiece());
        int currentRow = currentPosition[0];
        int currentCol = currentPosition[1];

        //remove the player's piece from old position
        board.getBlock(currentRow, currentCol).setPiece(null);

        //update the new position based on user input
        int newRow = currentRow, newCol = currentCol;
        //move up
        if (move == 1) {
            //if not the top row & no fence above
            if (currentRow > 0 && !board.hasHorizontalFence(currentRow - 1, currentCol)) {
                newRow--;
            } else {
                System.out.println("Invalid move up!");
                //if move's invalid have to put the piece back where it was
                board.addPiece(currentRow, currentCol, currentPlayer.getPiece());
                return;
            }
        } else if (move == 2) {  //down
            if (currentRow < board.getSize() - 1 && !board.hasHorizontalFence(currentRow, currentCol)) {
                newRow++;
            } else {
                System.out.println("Invalid move down!");
                board.addPiece(currentRow, currentCol, currentPlayer.getPiece());
                return;
            }
        } else if (move == 3) { //left
            if (currentCol > 0 && !board.hasVerticalFence(currentRow, currentCol - 1)) {
                newCol--;
            } else {
                System.out.println("Invalid move left!");
                board.addPiece(currentRow, currentCol, currentPlayer.getPiece());
                return;
            }
        } else if (move == 4) { //right
            if (currentCol < board.getSize() - 1 && !board.hasVerticalFence(currentRow, currentCol)) {
                newCol++;
            } else {
                System.out.println("Invalid move right!");
                board.addPiece(currentRow, currentCol, currentPlayer.getPiece());
                return;
            }
        } else {
            System.out.println("Invalid input. Try again.");
            board.addPiece(currentRow, currentCol, currentPlayer.getPiece());
            return;
        }

        //mostly just double-checking if new move is valid within the board
        if (rules.isValidMove(newRow, newCol, board)) {
            // Place the player's piece at the new position
            board.addPiece(newRow, newCol, currentPlayer.getPiece());
            switchPlayer();
        } else {
            System.out.println("Invalid move! Try again.");
            board.addPiece(currentRow, currentCol, currentPlayer.getPiece());
            //allow retrying the move when there was no valid move made
            handlePawnMove(board);
        }
    }


    //handling fence placement move: user input & validation, placing fence on board
    protected void handleFencePlacement(QBoard board) {
        //take user input for fence top/left most square
        int type = Input.getIntInput("1 = Horizontal, 2 = Vertical: ");
        while (type != 1 && type != 2) {
            System.out.println("Invalid input: Please enter 1 or 2");
            type = Input.getIntInput("1 = Horizontal, 2 = Vertical: ");
        }
        boolean horizontal = (type == 1);
        int row = Input.getIntInput("Enter row for the start square of the fence (1-9) (starting is left square if horizontal, top square if vertical): ") - 1;
        int col = Input.getIntInput("Enter column for the start square of the fence (1-9)  (starting is left square if horizontal, top square if vertical): ") - 1;

        //validating input to make sure fence can extend to 2 squares
        if (horizontal) {
            if (col >= board.getSize() - 1) {
                System.out.println("Invalid input: Fence must be 2 squares horizontally.");
                return;
            }
        } else {
            if (row >= board.getSize() - 1) {
                System.out.println("Invalid input: Fence must be 2 squares vertically.");
                return;
            }
        }

        Fence fence = new Fence(row, col, horizontal);

        //making sure qrules is used
        QRules qRules = (QRules) rules;
        //validating valid fence placement
        if (!qRules.isValidFencePlacement(fence, board, players.get(0), players.get(1))) {
            System.out.println("Invalid fence placement! Try again.");
            return;
        }

        board.placeFence(fence);
        switchPlayer();
    }

}


