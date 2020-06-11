package workingwithabstraction.cardsuit;

public class Main {
    public static void main(String[] args) {

        CardSuit[] cardSuit = CardSuit.values();

        System.out.println("Card Ranks:");
        for (CardSuit value : cardSuit) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", value.ordinal(), value);
        }
    }
}
