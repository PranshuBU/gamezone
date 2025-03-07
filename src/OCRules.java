/*
 * OCRules.java
 *
 * This class inherits from rules, and its methods define specific rules for O&C such as validating player moves
 * and defining/checking win conditions.
 *
 */
public class OCRules extends Rules {

    public boolean checkWinCondition(Board board) {
        int size = board.getSize();

        // looping through each row & column to find any 5 same-pieces in a row
        for (int i = 0; i < size; i++) {
            if (checkRow(board, i, 5, 'X') || checkRow(board, i, 5, 'O') ||
                    checkColumn(board, i, 5, 'X') || checkColumn(board, i, 5, 'O')) {
                return true;
            }
        }

        // check both-direction diagonals for any 5 in a row pieces
        return checkDiagonal(board, 5, 'X') || checkDiagonal(board, 5, 'O');
    }

    public boolean isValidMove(int row, int col, Board board) {
        return row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize() &&
                !board.getBlock(row, col).hasPiece();
    }
}
