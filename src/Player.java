/*
 * Player.java
 *
 * This class is for defining a player. It has teams (e.g. order or chaos for Order & Chaos game)
 * and pieces (e.g. ‘x’ or ‘o’ for tic tac toe game). Relevant for extendability if future games have more players
 * in a team or different symbols/pieces like in monopoly for example.
 *
 */
public class Player {
    private Piece piece;
    private Team team;

    public Player(Piece piece, Team team) {
        this.piece = piece;
        this.team = team;
    }

    public Piece getPiece() {
        return piece;
    }

    public Team getTeam() {
        return team;
    }

}
