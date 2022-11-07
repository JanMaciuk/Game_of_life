import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {
        int size = 10;
        boolean running = true;
        Scanner userIn = new Scanner(System.in);
        boolean[][] board = createBoard(size);

        while (running) {

            System.out.println("Wpisz cokolwiek lub wyjdź za pomocą 'exit'");
            if (userIn.nextLine().equalsIgnoreCase("exit")) {
                running = false;
            } else {
                board = nextVersion(board, size);
                Print(board, size);
            }

        }

    }

    public static boolean[][] createBoard(int size) {
        boolean[][] board = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ThreadLocalRandom.current().nextBoolean();
            }
        }
        return board;
    }

    public static int NeighboursNumber(boolean[][] board, int x, int y, int size) {
        int neighbours = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue; // komórka początkowa
                }
                if (x + i < 0 || x + i >= size || y + j < 0 || y + j >= size) {
                    continue; // komórka poza planszą.
                }
                if (board[x + i][y + j]) {
                    neighbours++;
                }
            }
        }
        return neighbours;
    }

    public static boolean[][] nextVersion(boolean[][] board, int size) {
        boolean[][] nextBoard = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int neighbours = NeighboursNumber(board, i, j, size);
                if (board[i][j]) {
                    if (neighbours < 2 || neighbours > 3) {
                        nextBoard[i][j] = false;
                    } else {
                        nextBoard[i][j] = true;
                    }
                } else {
                    if (neighbours == 3) {
                        nextBoard[i][j] = true;
                    } else {
                        nextBoard[i][j] = false;
                    }
                }
            }
        }
        return nextBoard;
    }


    public static void Print(boolean[][] board, int M) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j]) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
                }
            System.out.println();
            }
            System.out.println();
        }


}