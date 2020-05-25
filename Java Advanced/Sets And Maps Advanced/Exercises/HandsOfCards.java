package SetsAndMapsAdvancedExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class HandsOfCards {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );
        Map<String, Set<String>> persons = new LinkedHashMap<>();

        String input;

        while (!"JOKER".equals(input = reader.readLine())) {
            String[] data = input.split(": ");
            String name = data[0];
            List<String> cards = Arrays.stream(data[1].split(", "))
                    .collect(Collectors.toList());

            if (!persons.containsKey(name)) {
                persons.put(name, new HashSet<>(){{
                    addAll(cards);
                }});
            } else {
                persons.get(name).addAll(cards);
            }
        }

        for (Map.Entry<String, Set<String>> entry : persons.entrySet()) {
            int power = getPower(entry.getValue());
            System.out.println(String.format("%s: %d", entry.getKey(), power));
        }
    }

    private static int getPower(Set<String> deck) {
        int totalPower = 0;
        for (String card : deck) {
            int power = 0;
            if (Character.isDigit(card.charAt(0)) && card.charAt(0) != '1') {
                power += card.charAt(0) - '0';
            } else {
                switch (card.charAt(0)) {
                    case '1':
                        power += 10;
                        break;
                    case 'J':
                        power += 11;
                        break;
                    case 'Q':
                        power += 12;
                        break;
                    case 'K':
                        power += 13;
                        break;
                    case 'A':
                        power += 14;
                        break;
                }
            }
            switch (card.charAt(card.length() - 1)) {
                case 'S':
                    power *= 4;
                    break;
                case 'H':
                    power *= 3;
                    break;
                case 'D':
                    power *= 2;
                    break;
            }
            totalPower += power;
        }
        return totalPower;
    }
}
