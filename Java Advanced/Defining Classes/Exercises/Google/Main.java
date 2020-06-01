package DefiningClassesExercises.Google;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        Map<String, Person> people = new LinkedHashMap<>();

        while (!"End".equals(line)) {
            String[] data = line.split("\\s+");

            String name = data[0];
            people.putIfAbsent(name, new Person(name));

            Person person = people.get(name);

            if (line.contains("company")) {
                person.setCompany(new Company(data[2], data[3], Double.parseDouble(data[4])));
            } else if (line.contains("pokemon")) {
                person.addPokemon(new Pokemon(data[2], data[3]));
            } else if (line.contains("parents")) {
                person.addParent(new Parent(data[2], data[3]));
            }else if (line.contains("children")) {
                person.addChild(new Child(data[2], data[3]));
            } else if (line.contains("car")) {
                person.setCar(new Car(data[2], data[3]));
            }

            line = scanner.nextLine();
        }
        String person = scanner.nextLine();
        if (people.containsKey(person)) {
            System.out.print(people.get(person));
        }
    }
}
