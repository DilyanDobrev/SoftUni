package encapsulation.footballteamgenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        Map<String, Team> teams = new LinkedHashMap<>();

        String input;

        while (!"END".equals(input = reader.readLine())) {

            String[] data = input.split(";");

            switch (data[0]) {
                case "Team":
                    try {
                        teams.put(data[1], new Team(data[1]));

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "Add":
                    int endurance = Integer.parseInt(data[3]);
                    int sprint = Integer.parseInt(data[4]);
                    int dribble = Integer.parseInt(data[5]);
                    int passing = Integer.parseInt(data[6]);
                    int shooting = Integer.parseInt(data[7]);

                    if (teams.containsKey(data[1])) {
                        try {
                            teams.get(data[1]).addPlayer(new Player(data[2], endurance, sprint, dribble, passing, shooting));
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println(String.format("Team %s does not exist.", data[1]));
                    }

                    break;
                case "Remove":
                    try {
                        teams.get(data[1]).removePlayer(data[2]);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "Rating":
                    if (teams.containsKey(data[1])) {
                        double rating = Math.round(teams.get(data[1]).getRating());
                        System.out.println(String.format("%s - %.0f", data[1], rating));
                    } else {
                        System.out.println(String.format("Team %s does not exist.", data[1]));
                    }

                    break;
            }

        }

    }
}
