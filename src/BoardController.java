 /*
  * BoardController.java
  * by Medha Srivastava
  *
  * This class is the overarching class for running all games. It takes main game components (board, players, rules)
  * and sets up generic shared methods e.g. handleMove, switchPlayer, checkWinner
  * and runGame as an abstract method that needs to be specifically defined for each different game.
  */

import java.util.List;

public abstract class BoardController {
    protected Board board;
    protected List<Player> players;
    protected Player currentPlayer;
    protected Rules rules;

    public BoardController(Board board, List<Player> players, Rules rules) {
        this.board = board;
        this.players = players;
        this.rules = rules;
        this.currentPlayer = players.get(0);
    }

    // abstract method that should be written for each game
    public abstract void runGame();

    public boolean checkWinner() {
        if (rules.checkWinCondition(board)) {
            if (currentPlayer.getTeam() != null) {
                // announce team winner for O&C-  if 5 in a row winner found it must be Order
                System.out.println("Team Order wins!");
            } else {
                // announce player winner for TTT & STTT
                System.out.println("Player " + currentPlayer.getPiece().getSymbol() + " wins!");
            }
            return true;
        }
        return false;
    }
    // more general methods
    public boolean handleMove(int row, int col) {
        if (rules.isValidMove(row, col, board)) {
            board.addPiece(row, col, currentPlayer.getPiece());

            if (checkWinner()) {
                return false;
            }

            switchPlayer();
            return true;
        } else {
            System.out.println("Invalid move! Try again.");
            return true; // Continue the game
        }
    }

    //overloading handleMove, for STTT
    public boolean handleMove(Board subBoard, int row, int col) {
        if (rules.isValidMove(row, col, subBoard)) {
            subBoard.addPiece(row, col, currentPlayer.getPiece());

            if (checkWinner()) {
                return false;
            }

            switchPlayer();
            return true;
        } else {
            System.out.println("Invalid move! Try again.");
            return true;
        }
    }

    // Special handle move for Order & Chaos because of either x/o choice
    public boolean handleMove(int row, int col, char piece) {
        if (rules.isValidMove(row, col, board)) {
            board.addPiece(row, col, new Piece(piece));

            if (checkWinner()) {
                return false;
            }

            switchPlayer();
            return true;
        } else {
            System.out.println("Invalid move! Try again.");
            return true;
        }
    }

    protected void switchPlayer() {
        int currentIndex = players.indexOf(currentPlayer);
        currentPlayer = players.get((currentIndex + 1) % players.size());
    }
}
