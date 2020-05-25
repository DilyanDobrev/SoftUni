package SetsAndMapsAdvancedExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetsOfElements {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        int[] input = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = input[0];
        int m = input[1];

        Set<String> first = new LinkedHashSet<>();
        Set<String> second = new LinkedHashSet<>();

        while (n-- > 0) {
            first.add(reader.readLine());
        }
        while (m-- > 0) {
            second.add(reader.readLine());
        }

        first.retainAll(second);

        first.forEach(e -> System.out.print(e + " "));
    }
}
