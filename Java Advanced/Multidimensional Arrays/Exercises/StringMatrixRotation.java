package MultidimensionalArraysExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class StringMatrixRotation {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );
        String text = reader.readLine();
        int degree = Integer.parseInt(text.substring(text.indexOf('(') + 1, text.indexOf(')'))) % 360;

        ArrayList<String> list = new ArrayList<>();
        int maxLength = 0;
        String input;
        while (!"END".equals(input = reader.readLine())) {
            list.add(input);
            if (input.length() > maxLength) {
                maxLength = input.length();
            }
        }

        char[][] matrix = new char[list.size()][maxLength];

        for (int r = 0; r < list.size(); r++) {
            for (int c = 0; c < maxLength; c++) {
                if (c > list.get(r).length() - 1) {
                    matrix[r][c] = ' ';
                } else {
                    matrix[r][c] = list.get(r).charAt(c);
                }
            }
        }
        if (degree == 90) {
            for (int c = 0; c < maxLength; c++) {
                for (int r = list.size() - 1; r >= 0; r--) {
                    System.out.print(matrix[r][c]);
                }
                System.out.print(System.lineSeparator());
            }
        } else if (degree == 180) {
            for (int r = list.size() - 1; r >= 0; r--) {
                for (int c = maxLength - 1; c >= 0; c--) {
                    System.out.print(matrix[r][c]);
                }
                System.out.print(System.lineSeparator());
            }
        } else if (degree == 270) {
            for (int c = maxLength - 1; c >= 0; c--) {
                for (int r = 0; r < list.size(); r++) {
                    System.out.print(matrix[r][c]);
                }
                System.out.print(System.lineSeparator());
            }
        } else {
            for (int r = 0; r < list.size(); r++) {
                for (int c = 0; c < maxLength; c++) {
                    System.out.print(matrix[r][c]);
                }
                System.out.print(System.lineSeparator());
            }
        }
    }
}
