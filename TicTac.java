import java.util.Random;
import java.util.Scanner;

public class TicTac {
    private Scanner sc = new Scanner(System.in);
    private String[][] board = new String[3][3];
    private boolean gameRunning = true;

    public TicTac() {
        initialize();
        while (gameRunning) {
            playerMove();
            if (gameRunning) {
                computerMove();
                trackingBoard(board);
            }
        }
    }

    private void playerMove() {
        int row, column;
        do {
            System.out.println("Enter the Row move (1-3): ");
            row = sc.nextInt() - 1;
            System.out.println("Enter the Column move (1-3): ");
            column = sc.nextInt() - 1;
        } while (!isValidMove(row, column));
        board[row][column] = "X";
        trackingBoard(board);
        checkWinner("X");
    }

    private void computerMove() {
        Random random = new Random();
        int row, column;
        do {
            row = random.nextInt(board.length);
            column = random.nextInt(board.length);
        } while (!isValidMove(row, column));
        board[row][column] = "O";
        System.out.println("Computer Played At Row:" + (row + 1) + " and Column:" + (column + 1));
        checkWinner("O");
    }

    private boolean isValidMove(int row, int column) {
        if (row < 0 || row >= board.length || column < 0 || column >= board[0].length) {
            System.out.println("Invalid move. Try again.");
            return false;
        }
        if (!board[row][column].equals("E")) {
            System.out.println("Cell already taken. Try again.");
            return false;
        }
        return true;
    }

    public void trackingBoard(String board[][]) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("| " + board[i][j] + " | ");
            }
            System.out.print("\n");
        }
    }

    public boolean checkMove(int row, int column) {
        if (board[row][column].equalsIgnoreCase("X") || board[row][column].equalsIgnoreCase("O")) {
            System.out.println("Try Another Place!!");
            return true;
        }
        return false;
    }

    public void initialize() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = "E";
            }
        }
    }
    
    
    public void checkWinner(String player) {
        // فحص القطر الرئيسي
        if (checkLine(board[0][0], board[1][1], board[2][2])) {
        	System.out.println("Winner is ("+board[0][0]+") User");
        	System.exit(0);
        }

        // فحص القطر الفرعي
        if (checkLine(board[0][2], board[1][1], board[2][0])) {
        	System.out.println("Winner is ("+board[0][2]+") User");
        	System.exit(0);
        }
        // فحص الصفوف والأعمدة
        for (int i = 0; i < 3; i++) {
            if (checkLine(board[i][0], board[i][1], board[i][2])) {
            	System.out.println("Winner is ("+board[i][0]+") User");
            	System.exit(0);
            }
            if (checkLine(board[0][i], board[1][i], board[2][i])) {
            	System.out.println("Winner is ("+board[0][i]+") User");
            	System.exit(0);
            }
        }
    }

    private boolean checkLine(String a, String b, String c) {
        return a.equalsIgnoreCase(b) && b.equalsIgnoreCase(c) && !a.isEmpty() &&!a.equals("E");
    }
}
