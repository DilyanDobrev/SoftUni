package DefiningClassesExercises.PokemonTrainer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Trainer> trainers = new LinkedHashMap<>();

        String input;

        while (!"Tournament".equals(input = scanner.nextLine())) {
            String[] data = input.split("\\s+");
            String trainerName = data[0];
            String pokemonName = data[1];
            String element = data[2];
            int health = Integer.parseInt(data[3]);

            Trainer trainer = new Trainer(trainerName);

            Pokemon pokemon = new Pokemon(pokemonName, element, health);

            trainers.putIfAbsent(trainerName, trainer);
            trainers.get(trainerName).addPokemon(pokemon);

        }

        String line;

        while (!"End".equals(line = scanner.nextLine())) {
            String element = line;
            for (Trainer trainer : trainers.values()) {
                if (trainer.hasElement(element)) {
                    trainer.increaseBadges(1);
                } else {
                    trainer.damagePokemons(10);
                }
            }
        }
        trainers
                .entrySet()
                .stream()
                .sorted((f, s) -> s.getValue().getBadges() - f.getValue().getBadges())
                .forEach(entry -> System.out.println(entry.getValue().toString()));
    }
}
