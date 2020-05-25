package SetsAndMapsAdvancedExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Phonebook {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        Map<String, String> phoneBook = new HashMap<>();

        String input;

        while (!"search".equals(input = reader.readLine())) {
            String[] data = input.split("-");
            String name = data[0];
            String phoneNumber = data[1];

            phoneBook.putIfAbsent(name, phoneNumber);
        }
        String name;

        while (!"stop".equals(name = reader.readLine())) {
            if (!phoneBook.containsKey(name)) {
                System.out.println(String.format("Contact %s does not exist.",name));
            } else {
                System.out.println(String.format("%s -> %s",name, phoneBook.get(name)));
            }
        }
    }
}
