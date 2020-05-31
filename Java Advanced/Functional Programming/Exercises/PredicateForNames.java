package FunctionalProgrammingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateForNames {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        int n = Integer.parseInt(reader.readLine());

        BiPredicate<String, Integer> nameFilter = (name, length) -> name.length() <= length;

        Arrays.stream(reader.readLine()
                .split("\\s+"))
                .filter(name -> nameFilter.test(name, n))
                .forEach(System.out::println);
    }
}
