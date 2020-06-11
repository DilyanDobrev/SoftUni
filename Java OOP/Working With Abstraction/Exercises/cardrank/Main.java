package workingwithabstraction.cardrank;

public class Main {
    public static void main(String[] args) {

        CardRank[] enums = CardRank.values();

        System.out.println("Card Ranks:");
        for (CardRank value : enums) {
            System.out.printf("Ordinal value: %d; Name value: %s%n", value.ordinal(), value);
        }
    }
}
