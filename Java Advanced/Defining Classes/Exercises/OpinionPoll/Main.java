package DefiningClassesExercises.OpinionPoll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        int n = Integer.parseInt(reader.readLine());

        List<Person> persons = new ArrayList<>();

        while (n-- > 0) {
            String[] data = reader.readLine().split("\\s+");

            String name = data[0];
            int age = Integer.parseInt(data[1]);

            Person person = new Person(name, age);

            persons.add(person);
        }
        persons.stream()
                .filter(p -> p.getAge() > 30)
                .sorted(Comparator.comparing(Person::getName))
                .forEach(p -> System.out.println(p.toString()));
    }
}
