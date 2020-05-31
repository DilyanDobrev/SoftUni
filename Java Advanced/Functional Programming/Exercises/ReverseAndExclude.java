package FunctionalProgrammingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReverseAndExclude {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );
        ArrayList<Integer> numbers = Arrays.stream(reader.readLine()
                .split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        int n = Integer.parseInt(reader.readLine());

        BiFunction<ArrayList<Integer>, Integer, ArrayList<Integer>> revAndEX =
                (arr, number) -> {
                    Collections.reverse(numbers);
                    return numbers.stream()
                            .filter(num -> num % n != 0)
                            .collect(Collectors.toCollection(ArrayList::new));

                };
        revAndEX.apply(numbers, n).forEach(e -> System.out.print(e + " "));
    }
}
