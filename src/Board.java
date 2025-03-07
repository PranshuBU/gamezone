 /*
  * Board.java
  *
  * This class represents a game board for multiple board-games. It runs the state of the board (setting the board
  * based on user-inputted board size and blocks, checking if board is full, adding pieces) and handles the display/printing
  * for it.
  */

public class Board {
    private Block[][] board;
    private int size;

    //setup board with inputted size
    public Board(int size) {
        this.size = size;
        board = new Block[size][size];

        //initialize each of the individual blocks
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Block();
            }
        }
    }

    protected int getSize() {
        return size;
    }

    // get specific block by coordinates
    protected Block getBlock(int row, int col) {
        return board[row][col];
    }

    // check if board is full (case of a draw) - check if there is any block not occupied
    protected boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!board[i][j].hasPiece()) {
                    return false;
                }
            }
        }
        return true;
    }

    //helper function to get each individual row
    protected String getIndividualRow(int row) {
        String rowString = "|";
        //labelling squares for friendlier UI
        int squareNumber = row * size + 1;;

        //printing a row - getting number or symbol/piece for each block
        for (int col = 0; col < size; col++) {
            Piece piece = this.getBlock(row, col).getPiece();

            //showing square number if there's no piece on it yet
            rowString += (piece == null ? squareNumber : String.valueOf(piece.getSymbol()));
            rowString += " |";
            squareNumber++;
        }
        return rowString;
    }

    //to print a board,
    public void printBoard() {
        for (int row = 0; row < size; row++) {
            //grid lines in the board before every row
            System.out.print("+");
            for (int col = 0; col < size; col++) {
                System.out.print("---+");
            }
            System.out.println();
            System.out.println(getIndividualRow(row));
        }
            System.out.println();

        //ending grid line
        System.out.print("+");
        for (int col = 0; col < size; col++) {
            System.out.print("---+");
        }
        System.out.println();
    }

    // add a piece/symbol to a block on the board by coordinates
    protected void addPiece(int row, int col, Piece piece) {
        board[row][col].setPiece(piece);
    }


}

