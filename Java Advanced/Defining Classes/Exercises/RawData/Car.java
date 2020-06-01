package DefiningClassesExercises.RawData;

import java.util.Arrays;

public class Car {
    private String model;
    private Engine engine;
    private Cargo cargo;
    private Tire[] tires;

    public Car(String[] data) {
        this.setModel(data[0]);
        this.setEngine(data);
        this.setCargo(data);
        this.setTires(data);
    }


    public void setModel(String model) {
        this.model = model;
    }

    public void setEngine(String[] data) {
        int speed = Integer.parseInt(data[1]);
        int power = Integer.parseInt(data[2]);
        this.engine = new Engine(speed, power);
    }

    public void setCargo(String[] data) {
        int weight = Integer.parseInt(data[3]);
        String type = data[4];
        this.cargo = new Cargo(weight, type);
    }

    public void setTires(String[] data) {
        this.tires = new Tire[4];
        for (int i = 5, j = 0; i < data.length; i += 2, j++) {
            double pressure = Double.parseDouble(data[i]);
            int age = Integer.parseInt(data[i + 1]);
            this.tires[j] = new Tire(age, pressure);
        }
    }

    public String getModel() {
        return this.model;
    }

    public String getCargoType() {
        return this.cargo.getType();
    }

    public boolean isPressureValid() {
        return Arrays.stream(this.tires).anyMatch(tire -> tire.getPressure() < 1);
    }

    public boolean isEnginePowerValid() {
        return this.engine.getPower() > 250;
    }

    @Override
    public String toString() {
        return this.getModel();
    }
}
