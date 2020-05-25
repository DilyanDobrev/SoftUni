package SetsAndMapsAdvancedExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class AMinerTask {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );


        String input;

        Map<String, Integer> materials = new LinkedHashMap<>();

        while (!"stop".equals(input = reader.readLine())) {
            int value = Integer.parseInt(reader.readLine());
            if (!materials.containsKey(input)) {
                materials.put(input, value);
            } else {
                materials.put(input, materials.get(input) + value);
            }
        }
        materials.forEach((key, value) -> System.out.printf("%s -> %s%n", key, value));
    }
}
