/*
 * Team.java
 *
 * This class is for defining teams e.g. order & chaos. Mainly for extendability for future games when there
 *  might be multiple players in a team - so has team’s name and players in it, and
 * has accessor methods for both of those, and a method to add a new player to the team.
 *
 */
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name, List<Player> players) {
        this.name = name;
        this.players = players;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
