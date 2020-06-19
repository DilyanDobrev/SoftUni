package polymorphism.vehicles;

import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {
    public static final DecimalFormat formatter = new DecimalFormat("#.##");

    private double fuelQuantity;
    private double consumption;

    protected VehicleImpl(double fuelQuantity, double consumption) {
        this.fuelQuantity = fuelQuantity;
        this.consumption = consumption;
    }

    @Override
    public void drive(double distance) {
        if (canDrive(distance)) {
            this.fuelQuantity -= this.consumption * distance;
            System.out.println(String.format(" travelled %s km", formatter.format(distance)));
        } else {
            System.out.println(" needs refueling");
        }
    }

    @Override
    public void refuel(double liters) {
        this.fuelQuantity += liters;
    }

    private boolean canDrive(double distance) {
        return this.fuelQuantity >= this.consumption * distance;
    }

    @Override
    public String toString() {
        return String.format(": %.2f", this.fuelQuantity);
    }
}
