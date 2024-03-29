package MultidimensionalArraysExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FillTheMatrix {

    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

            String[] input = reader.readLine().split(",\\s+");
            
            int size = Integer.parseInt(input[0]);
            String pattern = input[1];

        int[][] matrix = new int[size][size];
            
            if ("A".equals(pattern)) {
                matrix = patternA(matrix, size);
            } else {
                matrix = patternB(matrix, size);
            }

            printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] patternB(int[][] matrix, int size) {
        int value = 1;

        for (int col = 0; col < size; col++) {
            if (col % 2 == 0) {
                for (int row = 0; row < size; row++) {
                    matrix[row][col] = value;
                    value++;
                }
            } else {
                for (int row = size - 1; row >= 0 ; row--) {
                    matrix[row][col] = value;
                    value++;
                }
            }
        }

        return matrix;
    }

    private static int[][] patternA(int[][] matrix, int size) {

        int value = 1;

        for (int col = 0; col < size; col++) {
            for (int row = 0; row < size; row++) {
                matrix[row][col] = value;
                value++;
            }
        }
        return matrix;
    }
}
