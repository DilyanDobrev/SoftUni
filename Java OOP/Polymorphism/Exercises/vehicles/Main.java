package polymorphism.vehicles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Vehicle> vehicles = new LinkedHashMap<>();

        vehicles.put("polymorphism.vehiclesextension.Car", readVehicle(scanner.nextLine()));
        vehicles.put("polymorphism.vehiclesextension.Truck", readVehicle(scanner.nextLine()));

        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {
            String[] data = scanner.nextLine().split("\\s+");

            String type = data[1];

            if (data[0].equals("Drive")) {
                double distance = Double.parseDouble(data[2]);
                if (type.equals("polymorphism.vehiclesextension.Car")) {
                    vehicles.get(type).drive(distance);
                } else {
                    vehicles.get(type).drive(distance);
                }
            } else {
                double liters = Double.parseDouble(data[2]);
                if (type.equals("polymorphism.vehiclesextension.Car")) {
                    vehicles.get(type).refuel(liters);
                } else {
                    vehicles.get(type).refuel(liters);
                }
            }
        }
        vehicles.values().forEach(System.out::println);
    }

    private static Vehicle readVehicle(String nextLine) {
        String[] data = nextLine.split("\\s+");
        double quantity = Double.parseDouble(data[1]);
        double consumption = Double.parseDouble(data[2]);

        return data[0].equals("polymorphism.vehiclesextension.Car")
                ? new Car(quantity, consumption)
                : new Truck(quantity, consumption);
    }
}
