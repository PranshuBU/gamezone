/*
 * Rules.java
 *
 * This class is for defining rules for the games, in a shared structure which specific game rules can extend.
 * This has abstract methods checking winning conditions and valid moves, and helper functions to
 * check wins in these board-games (like checking rows/columns/diagonals for n in a row)
 *
 */

public abstract class Rules {

    //checking if there's n pieces in a row
    protected boolean checkRow(Board board, int row, int n, char symbol) {
        int count = 0;
        for (int col = 0; col < board.getSize(); col++) {
            if (board.getBlock(row, col).getPiece() != null &&
                    board.getBlock(row, col).getPiece().getSymbol() == symbol) {
                count++;
                if (count == n) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    //checking if there's n pieces in a column
    protected boolean checkColumn(Board board, int col, int n, char symbol) {
        int count = 0;
        for (int row = 0; row < board.getSize(); row++) {
            if (board.getBlock(row, col).getPiece() != null &&
                    board.getBlock(row, col).getPiece().getSymbol() == symbol) {
                count++;
                if (count == n) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    // Check diagonals from top-left to bottom-right and top-right to bottom-left
    protected boolean checkDiagonal(Board board, int n, char symbol) {
        int size = board.getSize();

        // Check the top-left to bottom-right diagonal
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (board.getBlock(i, i).getPiece() != null &&
                    board.getBlock(i, i).getPiece().getSymbol() == symbol) {
                count++;
                if (count == n) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // Check the top-right to bottom-left diagonal
        count = 0;
        for (int i = 0; i < size; i++) {
            if (board.getBlock(i, size - 1 - i).getPiece() != null &&
                    board.getBlock(i, size - 1 - i).getPiece().getSymbol() == symbol) {
                count++;
                if (count == n) {
                    return true;
                }
            } else {
                count = 0;
            }
        }

        return false;
    }

    // abstract method for checking wins & moves, specific to each game
    public abstract boolean checkWinCondition(Board board);
    public abstract boolean isValidMove(int row, int col, Board board);
}
