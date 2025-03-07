 /*
  * Block.java
  * by Medha Srivastava
  *
  * This class is for defining an individual component of the board—a block/tile. It has getter method and setter method,
  * and method to check if the block is full/has a piece or not.
  */

public class Block {
    private Piece piece;

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean hasPiece() {
        return piece != null;
    }

}
