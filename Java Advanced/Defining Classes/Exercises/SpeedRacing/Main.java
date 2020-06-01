package DefiningClassesExercises.SpeedRacing;

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

        int n = Integer.parseInt(reader.readLine());

        Map<String, Car> cars = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] data = reader.readLine().split("\\s+");
            String model = data[0];
            double fuelAmount = Double.parseDouble(data[1]);
            double fuelCostPerKM = Double.parseDouble(data[2]);

            cars.putIfAbsent(model, new Car(model, fuelAmount, fuelCostPerKM));
        }

        String command;
        while (!"End".equals(command = reader.readLine())) {
            String[] data = command.split("\\s+");
            String model = data[1];
            int distance = Integer.parseInt(data[2]);

            if (!cars.get(model).drive(distance)) {
                System.out.println("Insufficient fuel for the drive");
            }
        }
        cars.entrySet().forEach(entry -> System.out.println(entry.getValue().toString()));
    }
}
