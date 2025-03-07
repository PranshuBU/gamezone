 /*
  * GameSetup.java
  *
  * This class is for initializing and managing the setup process for all the games. It handles the main UI and user
  * inputs to select the game, play/quit, and decide the board & players/teams.
  * It initializes the board, players/teams, and rules for each game, and runs games.
  *
  */

import java.util.ArrayList;
import java.util.Scanner;

public class GameSetup {
    private Scanner scanner;

    public GameSetup() {
        this.scanner = new Scanner(System.in);
    }
    public void start() {
        boolean keepPlaying = true;

        while (keepPlaying) {
            System.out.println("Hi! Welcome to the GameZone:)");
            System.out.println("Which game do you want to play? Enter a number for the following options:");
            System.out.println("1. Tic-Tac-Toe");
            System.out.println("2. Order & Chaos");
            System.out.println("3. Super Tic-Tac-Toe");
            System.out.println("4. Quoridor");
            String gameChoice = scanner.next();
            if (gameChoice.equals("quit")) {
                System.out.println("Bye!");
                break;
            }

            ArrayList<Player> players = new ArrayList<>();

            // Tic-Tac-Toe
            if (gameChoice.equals("1"))  {
                System.out.println("Welcome to Tic-Tac-Toe!");
                int boardSize = Input.getIntInput("Enter the number of rows/columns for the board size (e.g., 3 for 3x3 board): ");
                Board board = new Board(boardSize);
                // choose player's pieces i.e. X's or O's
                System.out.println("Player 1, would you like to play as X's or O's? Enter 'X' or 'O': ");
                String p1Input = scanner.next().trim().toUpperCase();
                while (p1Input.isEmpty() || (p1Input.charAt(0) != 'X' && p1Input.charAt(0) != 'O')) {
                    System.out.println("Invalid input: Please enter 'X' or 'O': ");
                    p1Input = scanner.next().trim().toUpperCase();
                }
                char p1piece = p1Input.charAt(0);
                char p2piece= (p1piece == 'X') ? 'O' : 'X';
                System.out.println("Player 1 is " + p1piece + ", Player 2 is " + p2piece);

                //initialize the players with pieces
                Player player1 = new Player(new Piece(p1piece), null);
                Player player2 = new Player(new Piece(p2piece), null);
                players.add(player1);
                players.add(player2);

                // initialize the specific game controllers & start the game
                TTTRules rules = new TTTRules();
                TTTController tttController = new TTTController(board, players, rules);
                tttController.runGame();

            }
            // Order & Chaos
            else if (gameChoice.equals("2")) {
                System.out.println("Welcome to Order & Chaos!");
                int boardSize = 6;
                Board board = new Board(boardSize);

                // choose ~'teams'
                System.out.println("Player 1, would you like to play as Order or Chaos? Enter 'Order' or 'Chaos': ");
                String p1Team = scanner.next().trim().toLowerCase();

                while (!p1Team.equals("order") && !p1Team.equals("chaos")) {
                    System.out.println("Invalid input: Please enter 'Order' or 'Chaos': ");
                    p1Team = scanner.next().trim().toLowerCase();
                }
                String p2Team = (p1Team.equals("order")) ? "chaos" : "order";
                System.out.println("Player 1 is " + p1Team + ", Player 2 is " + p2Team);

                // initialize the teams & players
                Team orderTeam = new Team("Order", new ArrayList<>());
                Team chaosTeam = new Team("Chaos", new ArrayList<>() );
                Player player1 = new Player(null, p1Team.equals("order") ? orderTeam : chaosTeam);
                Player player2 = new Player(null, p2Team.equals("order") ? orderTeam : chaosTeam);
                players.add(player1);
                players.add(player2);
                orderTeam.addPlayer(p1Team.equals("order") ? player1 : player2);
                chaosTeam.addPlayer(p1Team.equals("order") ? player2 : player1);

                // initialize the specific game controllers & start the game
                OCRules rules = new OCRules();
                OCController ocController = new OCController(board, players, rules);
                ocController.runGame();

            }
            // Super Tic-Tac-Toe
            else if (gameChoice.equals("3"))  {
                System.out.println("Welcome to Super Tic-Tac-Toe!");
                int subBoardSize = Input.getIntInput("Enter the number of rows/columns for the sub-board size (e.g., 3 for 3x3 sub-board): ");
                Board[][] superBoard = new Board[3][3];

                //initialize the sub-boards
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        superBoard[i][j] = new Board(subBoardSize);
                    }
                }
                // choose player's pieces i.e. X's or O's
                System.out.println("Player 1, would you like to play as X's or O's? Enter 'X' or 'O': ");
                String p1Input = scanner.next().trim().toUpperCase();
                while (p1Input.isEmpty() || (p1Input.charAt(0) != 'X' && p1Input.charAt(0) != 'O')) {
                    System.out.println("Invalid input: Please enter 'X' or 'O': ");
                    p1Input = scanner.next().trim().toUpperCase();
                }
                char p1piece = p1Input.charAt(0);
                char p2piece= (p1piece == 'X') ? 'O' : 'X';
                System.out.println("Player 1 is " + p1piece + ", Player 2 is " + p2piece);

                //initialize the players with pieces
                Player player1 = new Player(new Piece(p1piece), null);
                Player player2 = new Player(new Piece(p2piece), null);
                players.add(player1);
                players.add(player2);

                // initialize the specific game controllers & start the game
                STTTRules rules = new STTTRules(superBoard);
                STTTController stttController = new STTTController(superBoard, players, rules);
                stttController.runGame();
            }
            // Quoridor
            else if (gameChoice.equals("4"))  {
                System.out.println("Welcome to Quoridor!");
                //fixed board size 9x9 for now
                int boardSize = 9;
                //initialize quoridor board specifically
                QBoard board = new QBoard(boardSize);

                //initialize the players with pieces
                Player player1 = new Player(new Piece('P'), null);
                Player player2 = new Player(new Piece('X'), null);
                players.add(player1);
                players.add(player2);

                // initialize the specific game controllers & start the game
                QRules rules = new QRules();
                QController qController = new QController(board, players, rules);
                qController.runGame();
            }
            else {
                System.out.println("That's not a game! Please try again.");
            }

            // allow new games/replaying
            System.out.println("Would you like to play again or choose a new game? (Enter 'yes' to continue or 'no' to quit): ");
            String playAgain = scanner.next().trim().toLowerCase();

            if (!playAgain.equals("yes") && !playAgain.equals("y")) {
                keepPlaying = false;
                System.out.println("Ok bye!!");
            }
        }
        scanner.close();
    }
}