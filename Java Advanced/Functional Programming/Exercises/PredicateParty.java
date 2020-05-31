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

public class PredicateParty {
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

        while (!"Party!".equals(line = reader.readLine())) {
            String predicateName = line.substring(line.indexOf(" ") + 1);
            predicates.putIfAbsent(line, generateFilter(predicateName));
        }
        for (var kvp : predicates.entrySet()) {
            if (kvp.getKey().contains("Remove")) {
                names.removeIf(kvp.getValue());
            } else {
                List<String> doubleNames = names.stream().filter(kvp.getValue()).collect(Collectors.toList());
                names.addAll(doubleNames);
            }
        }
        if (names.size() != 0) {
            System.out.println(names.stream().sorted(String::compareTo).collect(Collectors.joining(", ")) + " are going to the party!");
        } else {
            System.out.println("Nobody is going to the party!");
        }
    }

    private static Predicate<String> generateFilter(String predicateName) {
        String[] data = predicateName.split("\\s+");

        String type = data[0];
        String param = data[1];

        Predicate<String> predicate = null;

        if (type.equals("StartsWith")) {
            predicate = str -> str.startsWith(param);
        } else if (type.equals("EndsWith")) {
            predicate = str -> str.endsWith(param);
        } else if (type.equals("Length")) {
            predicate = str -> str.length() == Integer.parseInt(param);
        }
        return predicate;
    }
}
