package IteratorsAndComparatorsExercises.ComparingObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Person> people = new ArrayList<>();

        String input;
        while (!"END".equals(input = scanner.nextLine())) {
            String[] data = input.split("\\s+");
            Person person = new Person(data[0], Integer.parseInt(data[1]), data[2]);
            people.add(person);
        }
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        Person person = people.get(index);

        int count = 0;
        for (Person p : people) {
            if (p.compareTo(person) == 0) {
                count++;
            }
        }
        if (count == 1) {
            System.out.println("No matches");
        } else {
            System.out.printf("%d %d %d", count, people.size() - count, people.size());
        }
    }
}
