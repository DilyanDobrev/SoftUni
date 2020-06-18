package interfacesandabstraction.foodshortage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<Buyer> buyers = new ArrayList<>();
        while (n-- > 0) {
            String[] data = scanner.nextLine().split("\\s+");

            Buyer buyer;
            if (data.length == 4) {
                buyer = new Citizen(data[0],Integer.parseInt(data[1]), data[2], data[3]);
            } else {
                buyer = new Rebel(data[0], Integer.parseInt(data[1]), data[2]);
            }

            buyers.add(buyer);
        }

        String input;
        while (!"End".equals(input = scanner.nextLine())) {
            for (Buyer buyer : buyers) {
                if (buyer.getName().equals(input)) {
                    buyer.buyFood();
                }
            }
        }
        System.out.println(buyers.stream().map(Buyer::getFood).mapToInt(Integer::intValue).sum());
    }
}
