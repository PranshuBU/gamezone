/*
 * STTTRules.java
 *
 * This class inherits from rules, and its methods define specific rules for STTT such as validating player moves
 * and defining/checking win conditions for the super board and sub boards.
 *
 */

public class STTTRules extends Rules {
    private Board[][] superBoard;
    private Board winnersBoard;

    public STTTRules(Board[][] superBoard) {
        this.superBoard = superBoard;
        //tracking subboard winners by creating a board filled in with winners' symbol/piece
        winnersBoard = new Board(3);
    }

    public boolean checkWinCondition(Board board) {
        //check winners for each subboard
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Board subBoard = superBoard[i][j];
                char winner = checkSubBoardWin(subBoard);
                //fill winnersBoard with winners' symbol
                if (winner != ' ') {
                    winnersBoard.addPiece(i, j, new Piece(winner));
                    //print winners so far
                    System.out.println("Sub-board " + (char) ('A' + (i * 3 + j)) + " won by: " + winner);
                    System.out.println("Current Winners Board:");
                    winnersBoard.printBoard();
                }
            }
        }

        //check if winner in the overall winners board by making it input to subboard (individual board) checker
        if (checkSubBoardWin(winnersBoard) != ' ') {
            return true;
        }

        //check for a draw if the winner board is full but no 3 in a row found
        if (winnersBoard.isFull()) {
            System.out.println("It's a draw! There's no overall winner!");
            return false;
        }

        return false;
    }

    //check subboard winner - same logic as TTT winner but returns winners' symbol instead of boolean
    private char checkSubBoardWin(Board subBoard) {
        int size = subBoard.getSize();
        int n;
        if (size < 3) {
            n = size; // Win condition for <3 square board
        } else {
            n = 3; // Win condition for any square board >=3
        }

        // looping through each row & column to find any 3 same-pieces in a row
        for (int i = 0; i < size; i++) {
            if (checkRow(subBoard, i, n, 'X') || checkColumn(subBoard, i, n, 'X')) {
                return 'X';
            }
            if (checkRow(subBoard, i, n, 'O') || checkColumn(subBoard, i, n, 'O')) {
                return 'O';
            }
        }
        // check both-direction diagonals for any 3 in a row pieces
        if (checkDiagonal(subBoard, n, 'X')) return 'X';
        if (checkDiagonal(subBoard, n, 'O')) return 'O';

        return ' '; //return empty space string if no winner
    }

    public boolean isValidMove(int row, int col, Board subBoard) {
        return row >= 0 && row < subBoard.getSize() &&
                col >= 0 && col < subBoard.getSize() &&
                !subBoard.getBlock(row, col).hasPiece();
    }
}
