package MultidimensionalArraysExercises;

import java.util.Scanner;

public class TheHeiganDance {

    private static int playerRow = 7;
    private static int playerCol = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double damage = Double.parseDouble(scanner.nextLine());

        int[][] field = new int[15][15];

        double playerHealth = 18500;
        double bossHealth = 3000000;

        String lastSpell = "";

        boolean shouldTakeDamage = false;
        while (playerHealth > 0 && bossHealth > 0) {

            bossHealth -= damage;

            if (shouldTakeDamage) {
                playerHealth -= 3500;
                shouldTakeDamage = false;
            }
            if (playerHealth <= 0 || bossHealth <= 0) {
                break;
            }

            String[] data = scanner.nextLine().split("\\s+");

            String spell = data[0];
            int targetRow = Integer.parseInt(data[1]);
            int targetCol = Integer.parseInt(data[2]);

            boolean isHit = checkCell(targetRow, targetCol);
            if (isHit) {
                boolean hasMoved = movePlayer(targetRow, targetCol);
                if (!hasMoved) {

                    if ("Cloud".equals(spell)) {
                        playerHealth -= 3500;
                        shouldTakeDamage = true;
                    } else {
                        playerHealth -= 6000;
                    }
                    lastSpell = spell;
                }
            }
        }
        lastSpell = lastSpell.equals("Cloud") ? "Plague Cloud" : lastSpell;

        if (bossHealth <= 0) {
            System.out.println("Heigan: Defeated!");
        } else {
            System.out.println(String.format("Heigan: %.2f", bossHealth));
        }
        if (playerHealth <= 0) {
            System.out.println("Player: Killed by " + lastSpell);
        } else {
            System.out.println(String.format("Player: %.0f", playerHealth));
        }

        System.out.println(String.format("Final position: %d, %d", playerRow, playerCol));
    }

    private static boolean movePlayer(int targetRow, int targetCol) {

        if (targetRow == playerRow && targetCol == playerCol) {
            return false;
        }

        boolean hasMoved = false;
        if (isInRange(playerRow - 1, playerCol) && canMove(targetRow, targetCol, playerRow - 1, playerCol)) {
            playerRow--;
            hasMoved = true;
        } else if (isInRange(playerRow, playerCol + 1) && canMove(targetRow, targetCol, playerRow, playerCol + 1)) {
            playerCol++;
            hasMoved = true;
        } else if (isInRange(playerRow + 1, playerCol) && canMove(targetRow, targetCol, playerRow + 1, playerCol)) {
            playerRow++;
            hasMoved = true;
        } else if (isInRange(playerRow, playerCol - 1) && canMove(targetRow, targetCol, playerRow, playerCol - 1)) {
            playerCol--;
            hasMoved = true;
        }
        return hasMoved;
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < 15 && c >= 0 && c <= 15;
    }

    private static boolean canMove(int targetRow, int targetCol, int newRow, int newCol) {
        return newRow < targetRow - 1 || newRow > targetRow + 1 ||
                newCol < targetCol - 1 || newCol > targetCol + 1;
    }

    private static boolean checkCell(int targetRow, int targetCol) {
        return playerRow >= targetRow - 1 && playerRow <= targetRow + 1 &&
                playerCol >= targetCol - 1 && playerCol <= targetCol + 1;
    }
}
