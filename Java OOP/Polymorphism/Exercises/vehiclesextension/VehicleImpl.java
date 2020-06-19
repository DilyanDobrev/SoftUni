package polymorphism.vehiclesextension;

import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {
    public static final DecimalFormat formatter = new DecimalFormat("#.##");

    private double fuelQuantity;
    private double consumption;
    private double tankCapacity;

    protected VehicleImpl(double fuelQuantity, double consumption, double tankCapacity) {
        this.setFuelQuantity(fuelQuantity);
        this.setConsumption(consumption);
        this.setTankCapacity(tankCapacity);
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.validate(fuelQuantity);
        this.fuelQuantity = fuelQuantity;
    }

    public void setConsumption(double consumption) {
        this.validate(consumption);
        this.consumption = consumption;
    }

    public void setTankCapacity(double tankCapacity) {
        this.validate(tankCapacity);
        this.tankCapacity = tankCapacity;
    }

    private void validate(double liters) {
        if (liters <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
    }

    protected void addConsumption(double additionalConsumption) {
        this.consumption += additionalConsumption;
    }

    protected void subtractConsumption(double additionalConsumption) {
        this.consumption -= additionalConsumption;
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
    public void driveWithPassengers(double distance) {
        this.drive(distance);
    }

    @Override
    public void refuel(double liters) {
        this.validate(liters);
        if (this.tankCapacity >= this.fuelQuantity + liters) {
            this.fuelQuantity += liters;
        } else {
            System.out.println("Cannot fit fuel in tank");
        }
    }

    private boolean canDrive(double distance) {
        return this.fuelQuantity >= this.consumption * distance;
    }

    @Override
    public String toString() {
        return String.format(": %.2f", this.fuelQuantity);
    }
}
