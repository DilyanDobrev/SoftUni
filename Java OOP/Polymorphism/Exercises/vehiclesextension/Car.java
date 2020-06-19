package polymorphism.vehiclesextension;

public class Car extends VehicleImpl {
    private static final double AIR_CONDITIONER = 0.9;
    private static final String CLAZZ_NAME = "Car";

    public Car(double fuelQuantity, double consumption, double tankCapacity) {
        super(fuelQuantity, consumption + AIR_CONDITIONER, tankCapacity);
    }

    @Override
    public void drive(double distance) {
        System.out.print(CLAZZ_NAME);
        super.drive(distance);
    }

    @Override
    public String toString() {
        return CLAZZ_NAME + super.toString();
    }
}
