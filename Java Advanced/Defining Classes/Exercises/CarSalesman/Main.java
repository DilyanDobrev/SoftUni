package DefiningClassesExercises.CarSalesman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        int n = Integer .parseInt(reader.readLine());

        Map<String, Engine> engines = new LinkedHashMap<>();
        List<Car> cars = new ArrayList<>();

        while (n-- > 0) {
            String[] data = reader.readLine().split("\\s+");
            String model = data[0];
            String power = data[1];

            if (data.length == 2) {
                engines.putIfAbsent(model, new Engine(model, power));
            } else if (data.length == 3) {
                engines.putIfAbsent(model, new Engine(model, power, data[2]));
            } else if (data.length == 4) {
                engines.putIfAbsent(model, new Engine(model, power, data[2], data[3]));
            }
        }

        int m = Integer.parseInt(reader.readLine());

        while (m-- > 0) {
            String[] data = reader.readLine().split("\\s+");
            String model = data[0];
            Engine engine = engines.get(data[1]);

            if (data.length == 2) {
                cars.add(new Car(model, engine));
            } else if (data.length == 3) {
                cars.add(new Car(model, engine, data[2]));
            } else if (data.length == 4) {
                cars.add(new Car(model, engine, data[2], data[3]));
            }
        }
        cars.forEach(System.out::println);
    }
}
