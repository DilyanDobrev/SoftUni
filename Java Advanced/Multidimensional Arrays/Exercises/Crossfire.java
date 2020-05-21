package MultidimensionalArraysExercises;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Crossfire {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        ArrayList<ArrayList<Integer>> matrix = generateMatrix(rows, cols);

        String input;

        while (!"Nuke it from orbit".equals(input = scanner.nextLine())) {
            String[] data = input.split("\\s+");

            int rowIndex = Integer.parseInt(data[0]);
            int colIndex = Integer.parseInt(data[1]);
            int range = Integer.parseInt(data[2]);

            for (int r = rowIndex - range; r <= rowIndex + range; r++) {
                if (isInRange(matrix, r, colIndex)) {
                    matrix.get(r).set(colIndex, 0);
                }
            }
            for (int c = colIndex - range; c <= colIndex + range; c++) {
                if (isInRange(matrix, rowIndex, c)) {
                    matrix.get(rowIndex).set(c, 0);
                }
            }
            for (int r = 0; r < matrix.size(); r++) {
                matrix.set(r,matrix.get(r).stream().filter(e -> e != 0).collect(Collectors.toCollection(ArrayList::new)));

                if (matrix.get(r).size() == 0) {
                    matrix.remove(r);
                    r--;
                }
            }
        }
        printMatrix(matrix);
    }

    private static void printMatrix(ArrayList<ArrayList<Integer>> matrix) {
        for (int r = 0; r < matrix.size(); r++) {
            for (int c = 0; c < matrix.get(r).size(); c++) {
                System.out.print(matrix.get(r).get(c) + " ");
            }
            System.out.println();
        }
    }

    private static boolean isInRange(ArrayList<ArrayList<Integer>> matrix, int row, int col) {
        return row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(row).size();
    }

    private static ArrayList<ArrayList<Integer>> generateMatrix(int rows, int cols) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        int value = 1;
        for (int r = 0; r < rows; r++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int c = 0; c < cols; c++) {
                row.add(value);
                value++;
            }
            matrix.add(row);
        }
        return matrix;
    }
}
