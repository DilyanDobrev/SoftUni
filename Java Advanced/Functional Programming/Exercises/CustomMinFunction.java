package FunctionalProgrammingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.IntStream;

public class CustomMinFunction {
    public static void main(String[] args) {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );
        try {
            int[] numbers = Arrays.stream(reader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Function<int[], Integer> minNumber = arr -> {
                int min = Arrays.stream(arr).min().orElse(0);
                return min;
            };
            System.out.println(minNumber.apply(numbers));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
