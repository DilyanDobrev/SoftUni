package IteratorsAndComparatorsExercises.StrategyPattern;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<Person> peopleByName = new TreeSet<>(new PersonCompareByName());
        Set<Person> peopleByAge = new TreeSet<>(new PersonCompareByAge());

        int count = Integer.parseInt(scanner.nextLine());
        while (count-- > 0) {
            String[] data = scanner.nextLine().split("\\s+");
            Person person = new Person(data[0], Integer.parseInt(data[1]));
            peopleByName.add(person);
            peopleByAge.add(person);
        }

        for (Person person : peopleByName) {
            System.out.println(person);
        }

        for (Person person : peopleByAge) {
            System.out.println(person);
        }
    }
}
