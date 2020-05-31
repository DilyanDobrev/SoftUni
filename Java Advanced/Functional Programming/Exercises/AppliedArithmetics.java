package FunctionalProgrammingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class AppliedArithmetics {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        Function<String, Integer> parser = str -> Integer.parseInt(str);
        Supplier<ArrayList<Integer>> supplier = () -> new ArrayList<Integer>();

        ArrayList<Integer> numbers = Arrays.stream(reader.readLine()
                .split("\\s+"))
                .map(parser)
                .collect(Collectors.toCollection(supplier));

        String command;

        Consumer<ArrayList<Integer>> printer = list -> list.forEach(num -> System.out.print(num + " "));

        Function<ArrayList<Integer>, ArrayList<Integer>> increment =
                list -> list.stream().map(e -> ++e).collect(Collectors.toCollection(supplier));

        UnaryOperator<ArrayList<Integer>> multiplyByTwo =
                list -> list.stream().map(e -> e * 2).collect(Collectors.toCollection(supplier));

        UnaryOperator<ArrayList<Integer>> subtract =
                list -> list.stream().map(e -> e - 1).collect(Collectors.toCollection(supplier));

        while (!"end".equals(command = reader.readLine())) {
            switch (command) {
                case "add":
                   numbers = increment.apply(numbers);
                    break;
                case "multiply":
                    numbers = multiplyByTwo.apply(numbers);
                    break;
                case "subtract":
                    numbers = subtract.apply(numbers);
                    break;
                case "print":
                    printer.accept(numbers);
                    System.out.println();
                    break;
            }
        }
    }
}
