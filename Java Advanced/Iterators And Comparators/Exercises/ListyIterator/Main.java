package IteratorsAndComparatorsExercises.ListyIterator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> list = Arrays.stream(scanner.nextLine().split("\\s+"))
                .skip(1)
                .collect(Collectors.toList());

        ListyIterator listyIterator = new ListyIterator(list);

        String input = "";
        while (!"END".equals(input = scanner.nextLine())) {
            String output = "";
            switch (input) {
                case "Move":
                    output = listyIterator.Move() ? "true" : "false";
                    break;
                case "HasNext":
                    output = listyIterator.HasNext() ? "true" : "false";
                    break;
                case "PrintAll":
                    output = listyIterator.PrintAll();
                    break;
                case "Print":
                    try {
                        output = listyIterator.print();
                    } catch (IllegalAccessException ex) {
                        output = ex.getMessage();
                    }
                    break;
            }
            if (!output.equals("")) {
                System.out.println(output);
            }
        }
    }
}
