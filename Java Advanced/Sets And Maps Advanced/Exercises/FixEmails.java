package SetsAndMapsAdvancedExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class FixEmails {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        Map<String, String> persons = new LinkedHashMap<>();

        String name;

        while (!"stop".equalsIgnoreCase(name = reader.readLine())) {
            String email = reader.readLine();
            if (isValid(email)) {
                persons.put(name, email);
            }
        }
        for (Map.Entry<String, String> entry : persons.entrySet()) {
            System.out.println(String.format("%s -> %s",
                    entry.getKey(),
                    entry.getValue()
            ));
        }
    }

    private static boolean isValid(String email) {
       String text = email.substring(email.lastIndexOf('.') + 1);
        return !"us".equalsIgnoreCase(text)
                && !"uk".equalsIgnoreCase(text)
                && !"com".equalsIgnoreCase(text);
    }
}
