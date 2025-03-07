/*
 * Main.java
 *
 * This class is the entry point for the game, that initiates the execution of the program.
 * It initializes the GameSetup class, which handles the main setup and execution of the games.
 */
public class Main {

    public static void main(String[] args) {
        GameSetup gameSetup = new GameSetup();
        gameSetup.start();

    }
}