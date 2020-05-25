package SetsAndMapsAdvancedExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PeriodicTable {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        int n = Integer.parseInt(reader.readLine());

        Set<String> elements = new TreeSet<>();

        while (n-- > 0) {
            ArrayList<String> line = Arrays.stream(reader.readLine().split("\\s+"))
                    .collect(Collectors.toCollection(ArrayList::new));
            elements.addAll(line);
        }
        elements.forEach(e -> System.out.print(e + " "));
    }
}
