
package workingwithabstraction.greedytimes;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {

    private static long capacityTaken = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long capacity = Long.parseLong(scanner.nextLine());
        String[] itemAndQuantity = scanner.nextLine().split("\\s+");

        var bag = new LinkedHashMap<String, LinkedHashMap<String, Long>>();

        long gold = 0;
        long gems = 0;
        long cash = 0;

        bag.put("Gold", new LinkedHashMap<>());
        bag.put("Gem", new LinkedHashMap<>());
        bag.put("Cash", new LinkedHashMap<>());

        for (int i = 0; i < itemAndQuantity.length; i += 2) {
            String type = itemAndQuantity[i];
            long quantity = Long.parseLong(itemAndQuantity[i + 1]);


            if (type.length() == 3) {
                type = "Cash";
            } else if (type.toLowerCase().endsWith("gem")) {
                type = "Gem";
            } else if (type.toLowerCase().equals("gold")) {
                type = "Gold";
            }

            String name = itemAndQuantity[i];
            if (capacity >= capacityTaken + quantity) {
                if (type.equals("Gem") && gold >= gems + quantity) {
                    gems += quantity;
                    addItem(bag, type, name, quantity);
                } else if (type.equals("Cash") && gems >= cash + quantity) {
                    cash += quantity;
                    addItem(bag, type, name, quantity);
                }
                else if (type.equals("Gold")){
                    gold += quantity;
                    addItem(bag, type, name, quantity);
                }
            }
        }

        for (var entry : bag.entrySet()) {
            Long sumValues = entry.getValue().values().stream().mapToLong(l -> l).sum();
            if (entry.getValue().size() != 0) {
                System.out.println(String.format("<%s> $%s", entry.getKey(), sumValues));

                entry.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));
            }
        }
    }

    private static void addItem(LinkedHashMap<String, LinkedHashMap<String, Long>> bag, String type, String name, long quantity) {

        if (!bag.get(type).containsKey(name)) {
            bag.get(type).put(name, quantity);
        } else {
            bag.get(type).put(name, bag.get(type).get(name) + quantity);
        }
        capacityTaken += quantity;
    }
}