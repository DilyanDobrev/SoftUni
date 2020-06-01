package DefiningClassesExercises.SpeedRacing;

public class Car {
    private String model;
    private double fuelAmount;
    private double fuelCostPerKM;
    private int distanceTraveled;

    public Car(String model, double fuelAmount,double fuelCostPerKM) {
        this.model = model;
        this.fuelAmount = fuelAmount;
        this.fuelCostPerKM = fuelCostPerKM;
        this.distanceTraveled = 0;
    }

    public boolean drive(int distance) {
        double fuelNeeded = this.fuelCostPerKM * distance;
        if (this.fuelAmount >= fuelNeeded) {
            this.fuelAmount -= fuelNeeded;
            this.distanceTraveled += distance;
            return true;
        }
        return false;
    }
    public String getModel() {
        return this.model;
    }

    public double getFuelAmount() {
        return this.fuelAmount;
    }

    public int getDistanceTraveled() {
        return this.distanceTraveled;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f %d", this.getModel(), this.getFuelAmount(), this.getDistanceTraveled());
    }
}
