package workingwithabstraction.cardswithpower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String rank = scanner.next();
        String suit = scanner.next();

        CardRank cardRank = CardRank.valueOf(rank);
        CardSuit cardSuit = CardSuit.valueOf(suit);

        int power = cardRank.getValue() + cardSuit.getValue();

        System.out.printf("Card name: %s of %s; Card power: %d%n", rank, suit, power);
    }
}
