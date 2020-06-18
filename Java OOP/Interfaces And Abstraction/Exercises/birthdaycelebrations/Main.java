package interfacesandabstraction.birthdaycelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        List<Birthable> peopleAndPets = new ArrayList<>();

        while (!"End".equals(input)) {
            String[] data = input.split("\\s+");
            Birthable birthable;
            switch (data[0]) {
                case "interfacesandabstraction.foodshortage.Citizen":
                    birthable = new Citizen(
                            data[1],
                            Integer.parseInt(data[2]),
                            data[3],
                            data[4]);
                    break;

                case "Pet":
                    birthable = new Pet(data[1], data[2]);
                    break;
                default:
                    input = sc.nextLine();
                    continue;
            }
            peopleAndPets.add(birthable);

            input = sc.nextLine();
        }

        String findBirthDate = sc.nextLine();

        for (Birthable peopleAndPet : peopleAndPets) {
            if (peopleAndPet.getBirthDate().endsWith(findBirthDate)) {
                System.out.println(peopleAndPet.getBirthDate());
            }
        }
    }
}
