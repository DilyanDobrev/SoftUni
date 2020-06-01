package DefiningClassesExercises.RawData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                System.in
                        )
                );

        int n = Integer.parseInt(reader.readLine());

        List<Car> cars = new ArrayList<>();

        while (n-- > 0) {
            String[] data = reader.readLine().split("\\s+");
            cars.add(new Car(data));
        }
        String command;
        if ("fragile".equals(command = reader.readLine())) {
            cars
                    .stream()
                    .filter(c -> c.getCargoType().equals(command))
                    .filter(Car::isPressureValid)
                    .forEach(System.out::println);
        } else {
            cars
                    .stream()
                    .filter(c -> c.getCargoType().equals(command))
                    .filter(Car::isEnginePowerValid)
                    .forEach(System.out::println);
        }
    }
}
