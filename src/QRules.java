/*
 * QRules.java
 *
 * This class inherits from rules, and its methods define specific rules for Quoridor such as validating player moves
 * and defining/checking win conditions, and checking fence placement validation.
 *
 */
import java.util.ArrayList;
import java.util.List;

public class QRules extends Rules {

    public boolean checkWinCondition(Board board) {
        QBoard qBoard = (QBoard) board;
        int size = qBoard.getSize();

        //player 1 (P) wins if reached any square in top row (0)
        for (int col = 0; col < size; col++) {
            Piece piece = qBoard.getBlock(0, col).getPiece();
            if (piece != null && piece.getSymbol() == 'P') {
                System.out.println("Player P wins!");
                return true;
            }
        }
        //player 2 (X) wins if reached any square in bottom row (8/boardSize - 1)
        for (int col = 0; col < size; col++) {
            Piece piece = qBoard.getBlock(size - 1, col).getPiece();
            if (piece != null && piece.getSymbol() == 'X') {
                System.out.println("Player X wins!");
                return true;
            }
        }
        return false;
    }

    public boolean isValidMove(int row, int col, Board board) {
        QBoard qBoard = (QBoard) board;
        //very basic check for abstract class consistency, if position is within the board
        return row >= 0 && row < board.getSize() && col >= 0 && col < board.getSize();
    }

    //check to see if the fence is valid
    public boolean isValidFencePlacement(Fence fence, QBoard board, Player player1, Player player2) {
        //check if there is already a fence where new fence being attempted
        if ((fence.horizontal() && board.hasHorizontalFence(fence.getRow(), fence.getCol())) ||
                (!fence.horizontal() && board.hasVerticalFence(fence.getRow(), fence.getCol()))) {
            System.out.println("Already a fence there!");
            return false;
        }

        //check if there is an 'access to the goal line left open'
        //doing this using bfs with new fence
        board.placeFence(fence);

        //check if valid path/access still exists for both players after fence is added, using bfs
        boolean player1path = bfsPath(player1, board);
        boolean player2path = bfsPath(player2, board);

        //if path blocked for either, remove the fence
        if (!player1path || !player2path) {
            board.removeFence(fence);
            return false;
        }

        return true;
    }

    //BFS to check if there's a path from the player's current position to their goal row
    public boolean bfsPath(Player player, QBoard board) {
        int size = board.getSize();
        // to keep track of visited nodes
        boolean[][] visited = new boolean[size][size];
        //empty FIFO queue
        List<int[]> queue = new ArrayList<>();

        //start from player's current spot
        int[] start = board.getPlayerSquare(player.getPiece());
        queue.add(start);
        visited[start[0]][start[1]] = true;

        //while not all nodes are visited
        while (!queue.isEmpty()) {
            int[] pos = queue.remove(0);
            int row = pos[0];
            int col = pos[1];

            //check if the player won/ended yet
            if ((player.getPiece().getSymbol() == 'P' && row == 0) || (player.getPiece().getSymbol() == 'X' && row == size - 1)) {
                return true;
            }

            //explore all possible valid moves w no fences blocking
            //up
            if (row > 0 && !visited[row - 1][col] && !board.hasHorizontalFence(row - 1, col)) {
                queue.add(new int[] {row - 1, col});
                visited[row - 1][col] = true;
            }
            //down
            if (row < size - 1 && !visited[row + 1][col] && !board.hasHorizontalFence(row, col)) {
                queue.add(new int[] {row + 1, col});
                visited[row + 1][col] = true;
            }
            //left
            if (col > 0 && !visited[row][col - 1] && !board.hasVerticalFence(row, col - 1)) {
                queue.add(new int[] {row, col - 1});
                visited[row][col - 1] = true;
            }
            //right
            if (col < size - 1 && !visited[row][col + 1] && !board.hasVerticalFence(row, col)) {
                queue.add(new int[] {row, col + 1});
                visited[row][col + 1] = true;
            }
        }
        return false; 
    }
}
