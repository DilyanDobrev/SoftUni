package polymorphism.vehiclesextension;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final String[] vehicleNames = {"Car", "Truck", "Bus"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Vehicle> vehicles = new LinkedHashMap<>();

        for (String vehicle : vehicleNames) {
            vehicles.put(vehicle, readVehicle(scanner.nextLine()));
        }

        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {
            String[] data = scanner.nextLine().split("\\s+");

            String command = data[0];
            String type = data[1];
            double value = Double.parseDouble(data[2]);

            try {
                if (command.contains("Drive") && type.equals("Bus")) {
                    vehicles.get(type).driveWithPassengers(value);
                } else if (command.equals("Refuel")) {
                    vehicles.get(type).refuel(value);
                } else {
                    vehicles.get(type).drive(value);
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

        }
        vehicles.values().forEach(System.out::println);
    }

    private static Vehicle readVehicle(String nextLine) {
        String[] data = nextLine.split("\\s+");
        double quantity = Double.parseDouble(data[1]);
        double consumption = Double.parseDouble(data[2]);
        double tankCapacity = Double.parseDouble(data[3]);

        return data[0].equals("Car")
                ? new Car(quantity, consumption, tankCapacity)
                : data[0].equals("Bus")
                ? new Bus(quantity, consumption, tankCapacity)
                : new Truck(quantity, consumption, tankCapacity);
    }
}
