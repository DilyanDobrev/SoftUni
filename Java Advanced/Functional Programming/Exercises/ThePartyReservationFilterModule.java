package FunctionalProgrammingExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ThePartyReservationFilterModule {
    public static void main(String[] args) throws IOException {

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        List<String> names = Arrays.stream(reader.readLine().split("\\s+"))
                .collect(Collectors.toList());

        Map<String, Predicate<String>> predicates = new HashMap<>();

        String line;

        while (!"Print".equals(line = reader.readLine())) {
            String predicateName = line.substring(line.indexOf(";") + 1);

            if (line.contains("Add")) {
                predicates.putIfAbsent(predicateName, generateFilter(predicateName));
            } else {
                predicates.remove(predicateName);
            }

        }
        Consumer<String> printer = str -> {
            boolean isValid = false;
            for (var kvp : predicates.entrySet()) {
                if (kvp.getValue().test(str)) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                System.out.print(str + " ");
            }
        };
        names.forEach(printer);
    }

    private static Predicate<String> generateFilter(String predicateName) {
        String[] data = predicateName.split(";");

        String type = data[0];
        String param = data[1];

        Predicate<String> predicate = null;

        if (type.equals("Starts with")) {
            predicate = str -> str.startsWith(param);
        } else if (type.equals("Ends with")) {
            predicate = str -> str.endsWith(param);
        } else if (type.equals("Length")) {
            predicate = str -> str.length() == Integer.parseInt(param);
        } else if (type.equals("Contains")) {
            predicate = str -> str.contains(param);
        }
        return predicate;
    }
}
