/*
 * Fence.java
 *
 * This class is for defining a fence. It just defines the fence itself and has getter functions for the row and column of the
 * fence (defined by the left most square of a horizontal fence/top most square of a vertical fence, since they each span 2 squares)
 * Also has a method tracking if it is horizontal or vertical (non-horizontal) fence.
 *
 */

public class Fence {
    private int row;
    private int col;
    private boolean horizontal;

    public Fence(int row, int col, boolean horizontal) {
        this.row = row;
        this.col = col;
        this.horizontal = horizontal;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    //tracking horizontal fence or non-horizontal (vertical)
    public boolean horizontal() {
        return horizontal;
    }
}
