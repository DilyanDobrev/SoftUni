package MultidimensionalArraysExercises;

import java.util.Scanner;

public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        scanner.nextLine();

        int[][] matrix = new int[size][size];

        for (int row = 0; row < matrix.length; row++) {
            String[] line = scanner.nextLine().split("\\s+");
            for (int col = 0; col < line.length; col++) {
                matrix[row][col] = Integer.parseInt(line[col]);
            }
        }

        int primarySum = 0;
        int secondarySum = 0;
        for (int i = 0; i < matrix.length; i++) {
            primarySum += matrix[i][i];
            secondarySum += matrix[i][size - 1 - i];
        }
        System.out.println(Math.abs(primarySum - secondarySum));
    }
}
