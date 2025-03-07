## Files
---------------------------------------------------------------------------
**Main.java:** This class runs the entry point to the games, basically the UI. Assigns teams/players/inputs.

**GameSetup.java:** This class is for initializing and managing the setup process and running for all the games.

**Input.java:** This class is a helper class for collecting user inputs in the games and in the UI. 

**BoardController.java:** This class is for running the games/starting and running the main game flow.

**TTTController.java:** This class inherits from BoardController and specifically is for running the tic tac toe game.

**OCController.java:** This class inherits from BoardController and specifically is for running the order and chaos game.

**STTTController.java:** This class inherits from BoardController and specifically is for running the Super tic tac toe game.

**QController.java:** This class inherits from BoardController and specifically is for running the quoridor game.

**Rules.java:** This class is for specifying general game rules and validating the processes/steps. 

**OCRules.java:** This class inherits from Rules, and is for rules/conditions specific to O&C.

**TTTRules.java:** This class inherits from Rules, and is for rules/conditions specific to Tic tac toe.

**STTTRules.java:** This class inherits from Rules, and is for rules/conditions specific to Super Tic tac toe.

**QRules.java:** This class inherits from Rules, and is for rules/conditions specific to Quoridor.

**Board.java:** This class is for constructing and using the board.

**QBoard.java:** This class inherits from the Board class and is for especially for constructing the Quoridor game board, 
with updated printBoard method (including fences logic), and methods specific to fences.

**Block.java:** This class is for constructing and running the individual components of the board.

**Fence.java:** This class is for constructing and storing the fence object.

**Team.java:** This class is for defining teams for any team-based games (so far just order and chaos)

**Player.java:** This class is for defining and constructing players for any game. 

**Piece.java:** This class is for constructing the individual pieces being played with (x's and o's or other pieces for future games).


## Notes
---------------------------------------------------------------------------
Please explain the cool features of your program. Anything that you feel like you did a good job at or were creative about, explain it in bullets here. Additionally, any design decisions should be made here.

How the design allowed for both scalability and extendibility to different turn based variants:
The existing structure of my classes for HW1 mainly allowed for extendibility to further turn based variants such as Super Tic Tac Toe, 
because of the inheritance structures for generic concepts in all turn-based games—specifically Teams/Players (Teams will be more useful for future games
but so far for order & chaos only), BoardController & Board, Rules. These classes allow for easy extendibility adding Super Tic Tac Toe
by adding a new controller class for it, a new rules class specific to it, and building it's turn-based components the same way as the other games using Players
and Pieces. The board class allowed easier building of the super board for STTT and can be extended to other board games too.

Building onto HW3 Quoridor involved using a lot of the existing structure again, such as Rules and Controller extendable subclasses for Quoridor specifically.
Also, the Board class was generic enough to use main components of that class while the subclass QBoard could specify game-specific board rules when needed.
The GameSetup file had an easy inclusion of a 4th game option with similar structure to the first 3. 

**Changes from HW2/notes on new features**
- To add Quoridor, the main changes building upon my existing structure was just adding a specific controller file and rules file for the new game,
adding Quoridor option to the overall GameSetup file to run it, and adding a "wall" component that was new to Quoridor
- Decided to add QuoridorBoard subclass because the board setup is different enough from the other games, and this would allow for most extendability 
in the future (by keeping Board class simple/only as needed)
- The teams functionality wasn't used in this implementation but could have been extended to Quoridor with teams as well.



General Classes/Structure Notes:

Main — GameSetup — Input (helper)

BoardController —> TTTController — OCController — STTTController

Rules —> TTTRules — OCCRules — STTTRules

Board —> QBoard --> Block — Piece —Teams — Player

Explanation of ideas/design:

**Main** = entry point/UI, initializes GameSetup

**GameSetup** = asks which game, makes teams and players assigned, gets user inputs (using Input class - might be helpful for future games with different types of inputs etc),

**Input** = helper class to get inputs - considers integer inputs for coordinates for the current games, and also string inputs for the UI/to quit

**BoardController** = overarching class for running all games, basically takes main game components (board, players, rules) and sets up generic shared methods e.g. making a move, alternating turns, checking win conditions. & starting/running a game that need to be defined specifically for each game but each game will have these concepts.

- **TTTController** = inherits from BoardController, defines more specific method for running Tic tac toe game
- **OCController** = inherits from BoardController, defines more specific method for running order & chaos game
- **STTTController** = inherits from BoardController, defines more specific method for running Super Tic tac toe game
- **QController** = inherits from BoardController, defines more specific method for running Quoridor


**Rules** = class to define rules specific to the games. has general methods checking winning conditions and valid moves, and helper functions to check wins in these board-games (like checking rows/columns/diagonals for n in a row)

- **TTTRules** = inherits from rules, defines specific valid moves and win conditions (3 in a row)
- **OCCRules** = inherits from rules, defines specific valid moves and win conditions (5 in a row and based on teams)
- **STTTRules** = inherits from rules, defines specific valid moves and win conditions (3 in a row in each subboard, and 3 in a row in overall winnerboard)
- **QRules** = inherits from rules, defines specific valid moves and win conditions (player reaching its goal row)

**Board** = class for drawing the board—uses board size (to allow for different ones) using blocks (which are a separate class representing an individual component of the board—to allow for extendability too), and general function methods like printing the board out, adding a piece to a specific block, or checking if the board is full.

**QBoard** = class for quoridor specific board, mostly incorporating fence-related methods and new printBoard

**Block** = individual component of the board. has getter and setter methods and method to check if the block is full/has a piece or not.

**Piece** = simple class to define a piece or symbol in these games e.g. ‘x’ and ‘o’ for these 2 games, but could be ‘red circle’ or ‘yellow circle’ for connect 4 or other games.

**Fence** = simple class to define the fence object for Quoridor

**Teams** = class for defining teams e.g. order & chaos. mainly for extendability for future games when there might be multiple players in a team - so has team’s name and players in it, and has accessor methods for both of those, and a method to add a new player to the team.

**Player** = class for defining a player. has teams (e.g. order or chaos for Order & Chaos game) and pieces (e.g. ‘x’ or ‘o’ for tic tac toe game). Relevant for extendability if future games have more players in a team or different symbols/pieces like in monopoly for example. Simple for now just has getter methods to get the team or piece assigned to player.


## How to compile and run
---------------------------------------------------------------------------
Your directions on how to run the code. Make sure to be as thorough as possible!
1. Navigate to the directory "CS611HW2" after unzipping the files
2. Navigate to "src" folder within CS611HW2
2. Run the following instructions:
   javac *.java
   java Main

## Input/Output Example
---------------------------------------------------------------------------
```
Enter 1 to move your pawn or 2 to put a fence: ^C(base) MacBook-Air-4:src medhasrivastava$ java Main.java
Hi! :)
Which game do you want to play? Enter a number for the following options:
1. Tic-Tac-Toe
2. Order & Chaos
3. Super Tic-Tac-Toe
4. Quoridor
4
Welcome to Quoridor!
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 2
1 = Horizontal, 2 = Vertical: 4
Invalid input: Please enter 1 or 2
1 = Horizontal, 2 = Vertical: 2
Enter row for the start square of the fence (1-9) (starting is left square if horizontal, top square if vertical): 4
Enter column for the start square of the fence (1-9)  (starting is left square if horizontal, top square if vertical): 4
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player X, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 2
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 2
Invalid move down!
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 2
Invalid move down!
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | X |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 2
1 = Horizontal, 2 = Vertical: 1
Enter row for the start square of the fence (1-9) (starting is left square if horizontal, top square if vertical): 2
Enter column for the start square of the fence (1-9)  (starting is left square if horizontal, top square if vertical): 5
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | X |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player X, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 2
Invalid move down!
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | X |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player X, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 3
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 1
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player X, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 2
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 1
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player X, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 2
1 = Horizontal, 2 = Vertical: 1
Enter row for the start square of the fence (1-9) (starting is left square if horizontal, top square if vertical): 3
Enter column for the start square of the fence (1-9)  (starting is left square if horizontal, top square if vertical): 3
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 1
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player X, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 2
Invalid move down!
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   | X |   |   |   |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player X, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 4
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | X |   |   |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 1
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | X |   |   |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   || P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player X, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 4
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   | X |   |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   || P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 1
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   | X |   |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   || P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player X, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 4
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   || P |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 1
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   | X |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player X, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 2
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   | X |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 1
Invalid move up!
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   | P |   |   |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   | X |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 3
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   | P |   |   |   |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   | X |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player X, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 2
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   | P |   |   |   |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   | X |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 1
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   | P |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   | X |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player X, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 2
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   | P |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P, it's your turn.
Enter 1 to move your pawn or 2 to put a fence: 1
Move: 1 = Up, 2 = Down, 3 = Left, 4 = Right
Enter your move: 1
+---+---+---+---+---+---+---+---+---+
|   |   |   | P |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+===+===+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+===+===+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   ||   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   | X |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
+---+---+---+---+---+---+---+---+---+
|   |   |   |   |   |   |   |   |   |
+---+---+---+---+---+---+---+---+---+
Player P wins!
Would you like to play again or choose a new game? (Enter 'yes' to continue or 'no' to quit): 
no
Ok bye!!



```
