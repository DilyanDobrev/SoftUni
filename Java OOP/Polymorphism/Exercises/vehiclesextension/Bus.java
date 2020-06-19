package polymorphism.vehiclesextension;

public class Bus extends VehicleImpl {
    private static final double AIR_CONDITIONER = 1.4;
    private static final String CLAZZ_NAME = "Bus";

    public Bus(double fuelQuantity, double consumption, double tankCapacity) {
        super(fuelQuantity, consumption, tankCapacity);
    }

    @Override
    public void drive(double distance) {
        System.out.print(CLAZZ_NAME);
        super.drive(distance);
    }

    @Override
    public void driveWithPassengers(double distance) {
        System.out.print(CLAZZ_NAME);
        super.addConsumption(AIR_CONDITIONER);
        super.drive(distance);
        super.subtractConsumption(AIR_CONDITIONER);
    }

    @Override
    public String toString() {
        return CLAZZ_NAME + super.toString();
    }
}
