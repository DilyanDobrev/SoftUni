package FunctionalProgrammingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListOfPredicates {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        Consumer<Integer> printer = num -> System.out.print(num + " ");

        int n = Integer.parseInt(reader.readLine());

        Set<Integer> divisors = Arrays.stream(reader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());

        Predicate<Integer> isDivisible = num -> {
            for (Integer divisor : divisors) {
                if (num % divisor != 0) {
                    return false;
                }
            }
            return true;
        };
        for (int i = 1; i <= n; i++) {
            if (isDivisible.test(i)) {
                printer.accept(i);
            }
        }
    }
}
