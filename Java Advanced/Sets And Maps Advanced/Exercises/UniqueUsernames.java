package SetsAndMapsAdvancedExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;

public class UniqueUsernames {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        int n = Integer.parseInt(reader.readLine());

        LinkedHashSet<String> names = new LinkedHashSet<>();

        while (n-- > 0) {
            String line = reader.readLine();
            names.add(line);
        }
        names.forEach(System.out::println);
    }
}
