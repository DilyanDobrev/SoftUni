package SetsAndMapsAdvancedExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class DragonArmy {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        int n = Integer.parseInt(reader.readLine());

        Map<String, Map<String, int[]>> dragons = new LinkedHashMap<>();

        while (n-- > 0) {
            String[] data = reader.readLine().split("\\s+");
            String type = data[0];
            String name = data[1];

            int damage = data[2].equals("null") ? 45 : Integer.parseInt(data[2]);
            int health = data[3].equals("null") ? 250 : Integer.parseInt(data[3]);
            int armor = data[4].equals("null") ? 10 : Integer.parseInt(data[4]);

            dragons.putIfAbsent(type, new TreeMap<>());
            dragons.get(type).put(name, new int[]{damage, health, armor});

        }
     dragons.entrySet().forEach(kvp -> {

         double damageAvg = 0;
         double healthAvg = 0;
         double armorAvg = 0;

         int[] dragonsParams = new int[3];

         kvp.getValue().entrySet().forEach(innerKvp -> {
            int damage = innerKvp.getValue()[0];
            int health = innerKvp.getValue()[1];
            int armor = innerKvp.getValue()[2];

             dragonsParams[0] += damage;
             dragonsParams[1] += health;
             dragonsParams[2] += armor;
         });
         int countDragons = kvp.getValue().size();

         damageAvg = dragonsParams[0] / (countDragons * 1.0);
         healthAvg = dragonsParams[1] / (countDragons * 1.0);
         armorAvg = dragonsParams[2] / (countDragons * 1.0);

         System.out.println(String.format("%s::(%.2f/%.2f/%.2f)",
                 kvp.getKey(),
                 damageAvg,
                 healthAvg,
                 armorAvg
         ));
         kvp.getValue().entrySet().forEach(dragon -> {
             System.out.printf("-%s -> damage: %d, health: %d, armor: %d%n",
                     dragon.getKey(),
                     dragon.getValue()[0],
                     dragon.getValue()[1],
                     dragon.getValue()[2]
                     );
         });
     });
    }
}
