package MultidimensionalArraysExercises;

import java.util.Arrays;
import java.util.Scanner;

public class MatrixShuffling {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        String[][] matrix = new String[rows][cols];

        for (int r = 0; r < rows; r++) {
            matrix[r] = scanner.nextLine().split("\\s+");
        }

        String input;

        while (!"END".equals(input = scanner.nextLine())) {
            String[] data = input.split("\\s+");

            String command = data[0];
            if ("swap".equals(command) && data.length == 5) {
                int firstRow = Integer.parseInt(data[1]);
                int firstCol = Integer.parseInt(data[2]);
                int secondRow = Integer.parseInt(data[3]);
                int secondCol = Integer.parseInt(data[4]);
                if (isInRange(matrix, firstRow, firstCol) && isInRange(matrix, secondRow, secondCol)) {
                    String temp = matrix[firstRow][firstCol];
                    matrix[firstRow][firstCol] = matrix[secondRow][secondCol];
                    matrix[secondRow][secondCol] = temp;

                    printMatrix(matrix);

                } else {
                    System.out.println("Invalid input!");
                }
            } else {
                System.out.println("Invalid input!");
            }
        }
    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isInRange(String[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }
}
