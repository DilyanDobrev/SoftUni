package DefiningClassesExercises.CatLady;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Cat> cats = new HashMap<>();

    public static void main(String[] args) {

        generateCats();
        printCat();
    }

    private static void printCat() {
        String catName = scanner.nextLine();
        System.out.print(cats.get(catName));
    }

    private static void generateCats() {
        String input;
        while (!"End".equals(input = scanner.nextLine())) {
            String[] data = input.split("\\s+");
            String type = data[0];
            String name = data[1];
            double power = Double.parseDouble(data[2]);

            switch (type) {
                case "Siamese":
                    cats.putIfAbsent(name, new Siamese(name, power));
                    break;
                case "Cymric":
                    cats.putIfAbsent(name, new Cymric(name, power));
                    break;
                case "StreetExtraordinaire":
                    cats.putIfAbsent(name, new StreetExtraordinaire(name, power));
                    break;
            }
        }
    }
}
