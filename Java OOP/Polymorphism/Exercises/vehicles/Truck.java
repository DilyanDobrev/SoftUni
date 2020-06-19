package polymorphism.vehicles;

public class Truck extends VehicleImpl {
    private static final double AIR_CONDITIONER = 1.6;
    private static final String CLAZZ_NAME = "polymorphism.vehiclesextension.Truck";

    public Truck(double fuelQuantity, double consumption) {
        super(fuelQuantity, consumption + AIR_CONDITIONER);
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
