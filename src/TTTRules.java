/*
 * TTTRules.java
 *
 * This class inherits from rules, and its methods define specific rules for TTT such as validating player moves
 * and defining/checking win conditions.
 *
 */
public class TTTRules extends Rules {

    public boolean checkWinCondition(Board board) {
        int size = board.getSize();
        int n;
        if (size < 3) {
            n = size; // Win condition for <3 square board
        } else {
            n = 3; // Win condition for any square board >=3
        }

        // looping through each row & column to find any 3 same-pieces in a row
        for (int i = 0; i < size; i++) {
            if (checkRow(board, i, n, 'X') || checkRow(board, i, n, 'O') ||
                    checkColumn(board, i, n, 'X') || checkColumn(board, i, n, 'O')) {
                return true;
            }
        }

        // check both-direction diagonals for any 3 in a row pieces
        return checkDiagonal(board, n, 'X') || checkDiagonal(board, n, 'O');
    }

    //valid moves specific to TTT and O&C/similar-type games
    public boolean isValidMove(int row, int col, Board board) {
        return row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize() &&
                !board.getBlock(row, col).hasPiece();
    }
}
