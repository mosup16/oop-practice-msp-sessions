import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int X_HOLDER = -1;
        int O_HOLDER = 1;
        int[][] board = {
                {0, 0, 0}, // 1,1
                {0, 0, 0},
                {0, 0, 0}
        };
        boolean done = false;
        int X_PLAYER = 0;
        int O_Player = 1;
        int CURRENT_Player = X_PLAYER;
        displayBoard(board);
        while (!done) {
            int[] position = nextMove(scanner);
            int currentMove;
            if (checkIsValidPosition(board, position)) {
                currentMove = CURRENT_Player == X_PLAYER ? X_HOLDER : O_HOLDER;
                board[position[0]][position[1]] = currentMove;

                displayBoard(board);
                done = checkGameOver(board, position, currentMove);
            }
            CURRENT_Player = CURRENT_Player == X_PLAYER ? O_Player : X_PLAYER;
        }
        System.out.println("winner is " + CURRENT_Player);

    }

    private static boolean checkGameOver(int[][] board, int[] lastMovePos, int lastMoveValue) {
        // check row is filled with the same value
        int x = lastMovePos[0];
        boolean rowIsOver = true;
        for (int cell : board[x])
            if (cell != lastMoveValue) rowIsOver = false;

        // check column is filled with the same value
        boolean columnIsOver = true;
        for (int i = 0; i < board.length; i += 3)
            if (board[lastMovePos[0]][i] != lastMoveValue) columnIsOver = false;

        boolean rightDiagonalIsOver = true;
        for (int i = 0; i < board[0].length; i++) {
            int[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                if (i == j) {
                    if (board[i][j] != lastMoveValue) rightDiagonalIsOver = false;
                }
            }
        }
        return false;
    }

    private static boolean checkIsValidPosition(int[][] board, int[] position) {
        int x = position[0];
        int y = position[1];
        if (board[x][y] == 0)
            return true;
        else return false;
    }

    public static void displayBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row)
                System.out.print(cell + " ");
            System.out.println();
        }
    }

    public static int[] nextMove(Scanner scanner) {
        System.out.println("enter your next move row index");
        int x = scanner.nextInt();
        System.out.println("enter your next move cell index");
        int y = scanner.nextInt();
        int[] position = {x, y};
        return position;
    }
}