 /*
  * QBoard.java
  *
  * This class extends the board class and represents a Quoridor game board specifically. In addition to the existing features
  * of the Board class, this class adds in the concept of fences (displaying them on the board + logic related to fences like checking
  * them/getting them), and it overrides the printBoard method from Board to add the display necessary for Quoridor (mainly jsut adding
  * displaying fences)
  * note the fences are made to take inputted coordinate and include the square to the right of it (if horizontal) or under it (if vertical)
  * so in this implementation: the horizontal fences block access between a row to row+1, and
  * vertical fences block between column and column+1
  */

 import java.util.ArrayList;
 import java.util.List;

 public class QBoard extends Board {
     private List<Fence> fences;

     //initializing the quoridor board and list to hold fences
     public QBoard(int size) {
         super(size);
         fences = new ArrayList<>();
     }

     //return the position/square on the board a player is in currently
     public int[] getPlayerSquare(Piece playerPiece) {
         for (int row = 0; row < getSize(); row++) {
             for (int col = 0; col < getSize(); col++) {
                 if (getBlock(row, col).getPiece() != null && getBlock(row, col).getPiece().getSymbol() == playerPiece.getSymbol()) {
                     return new int[] { row, col };
                 }
             }
         }
         return null;
     }

     //adding fence to fence list
     public void placeFence(Fence fence) {
         //extending each fence input to the column next to it (if horizontal) or row below it (if vertical)
         if (fence.horizontal()) {
             fences.add(fence);
             fences.add(new Fence(fence.getRow(), fence.getCol() + 1, true));
         } else {
             fences.add(fence);
             fences.add(new Fence(fence.getRow() + 1, fence.getCol(), false));
         }
     }

     //checks if horizontal fence exists related to that square
     public boolean hasHorizontalFence(int row, int col) {
         for (Fence fence : fences) {
             if (fence.horizontal() && fence.getRow() == row && fence.getCol() == col) {
                 return true;
             }
         }
         return false;
     }
     //checks if vertical fence exists related to that square
     public boolean hasVerticalFence(int row, int col) {
         for (Fence fence : fences) {
             if (!fence.horizontal() && fence.getRow() == row && fence.getCol() == col) {
                 return true;
             }
         }
         return false;
     }

     //remove fence, used for checking if path valid with added fence
     public void removeFence(Fence fence) {
         //remove the inputted fence
         fences.remove(fence);
         //remove the rest of the fence too
         if (fence.horizontal()) {
             fences.remove(new Fence(fence.getRow(), fence.getCol() + 1, true));
         } else {
             fences.remove(new Fence(fence.getRow() + 1, fence.getCol(), false));
         }
     }

     //special printBoard for quoridor overriding Board one, just adds printing fences in this
     public void printBoard() {
         for (int row = 0; row < getSize(); row++) {
             //grid lines in the board before every row
             System.out.print("+");
             for (int col = 0; col < getSize(); col++) {
                 System.out.print("---+");
             }
             System.out.println();

             //printing rows and vertical fences
             System.out.print("|");
             for (int col = 0; col < getSize(); col++) {
                 Piece piece = this.getBlock(row, col).getPiece();
                 System.out.print(piece == null ? "   " : " " + piece.getSymbol() + " ");

                 //if vertical fence exists on the square, print || on the right, otherwise just |
                 if (col < getSize() - 1 && hasVerticalFence(row, col)) {
                     System.out.print("||");
                 } else {
                     System.out.print("|");
                 }
             }
             System.out.println();

             //if horizontal fence exists on the square, print bottom of square with === instead of ---
             if (row < getSize() - 1) {
                 System.out.print("+");
                 for (int col = 0; col < getSize(); col++) {
                     if (hasHorizontalFence(row, col)) {
                         //fence
                         System.out.print("===+");
                     } else {
                         //no fence
                         System.out.print("---+");
                     }
                 }
                 System.out.println();
             }
         }

         //ending grid line
         System.out.print("+");
         for (int col = 0; col < getSize(); col++) {
             System.out.print("---+");
         }
         System.out.println();
     }


 }

