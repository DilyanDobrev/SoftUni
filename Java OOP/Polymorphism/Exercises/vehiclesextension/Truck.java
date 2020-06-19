package polymorphism.vehiclesextension;

public class Truck extends VehicleImpl {
    private static final double AIR_CONDITIONER = 1.6;
    private static final String CLAZZ_NAME = "Truck";

    public Truck(double fuelQuantity, double consumption, double tankCapacity) {
        super(fuelQuantity, consumption + AIR_CONDITIONER, tankCapacity);
    }

    @Override
    public void drive(double distance) {
        System.out.print(CLAZZ_NAME);
        super.drive(distance);
    }

    @Override
    public void refuel(double liters) {
        super.refuel(liters * 0.95);
    }

    @Override
    public String toString() {
        return CLAZZ_NAME + super.toString();
    }
}
