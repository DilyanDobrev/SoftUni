package Exam26October2019;

import java.util.Scanner;

public class BookWorm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        int size = Integer.parseInt(scanner.nextLine());

        String[][] field = new String[size][size];

        int playerRow = -1;
        int playerCol = -1;

        for (int r = 0; r < size; r++) {
            String[] line = scanner.nextLine().split("");
            for (int i = 0; i < line.length; i++) {
                if (line[i].equals("P")) {
                    playerRow = r;
                    playerCol = i;
                }
            }
            field[r] = line;
        }

        String input;

        while (!"end".equals(input = scanner.nextLine())) {
            field[playerRow][playerCol] = "P";
            switch (input) {
                case "up":
                    if (isInRange(field, playerRow - 1, playerCol)) {
                        field[playerRow][playerCol] = "-";
                        if (isLetter(field, playerRow - 1, playerCol)) {
                            text += field[playerRow - 1][playerCol];
                        }
                        playerRow--;
                    } else {
                        text = punish(text);
                    }
                    break;
                case "down":
                    if (isInRange(field, playerRow + 1, playerCol)) {
                        field[playerRow][playerCol] = "-";
                        if (isLetter(field, playerRow + 1, playerCol)) {
                            text += field[playerRow + 1][playerCol];
                        }
                        playerRow++;
                    } else {
                        text = punish(text);
                    }
                    break;
                case "left":
                    if (isInRange(field, playerRow, playerCol - 1)) {
                        field[playerRow][playerCol] = "-";
                        if (isLetter(field, playerRow, playerCol - 1)) {
                            text += field[playerRow][playerCol - 1];
                        }
                        playerCol--;
                    } else {
                        text = punish(text);
                    }
                    break;
                case "right":
                    if (isInRange(field, playerRow, playerCol + 1)) {
                        field[playerRow][playerCol] = "-";
                        if (isLetter(field, playerRow, playerCol + 1)) {
                            text += field[playerRow][playerCol + 1];
                        }
                        playerCol++;
                    } else {
                        text = punish(text);
                    }
                    break;
            }
            field[playerRow][playerCol] = "P";

        }
        System.out.println(text);
        printField(field);
    }

    private static boolean isLetter(String[][] field, int r, int c) {

        char symbol = field[r][c].charAt(0);
        return Character.isAlphabetic(symbol);
    }

    private static String punish(String text) {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
        return text;
    }

    private static boolean isInRange(String[][] field, int row, int col) {
        return row >= 0 && row < field.length && col >= 0 && col < field[row].length;
    }

    private static void printField(String[][] field) {
        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[r].length; c++) {
                System.out.print(field[r][c]);
            }
            System.out.println();
        }
    }

}
