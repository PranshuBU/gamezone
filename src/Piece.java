/*
 * Piece.java
 *
 * This is a simple class to define a piece or symbol in these games e.g. ‘x’ and ‘o’ for TTT & OC & SuperTTT,
 * but could be ‘red circle’ or ‘yellow circle’ for connect 4 or other games.
 *
 */
public class Piece {
    private char symbol;

    public Piece(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
