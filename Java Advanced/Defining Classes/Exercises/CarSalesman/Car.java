package DefiningClassesExercises.CarSalesman;

public class Car {
    private static final String DEFAULT_VALUE = "n/a";

    private String model;
    private Engine engine;
    private String weight;
    private String color;

    public Car(String model, Engine engine, String weight, String color) {
        this.model = model;
        this.engine = engine;
        this.weight = weight;
        this.color = color;
    }

    public Car(String model, Engine engine, String value) {
        this(model, engine, DEFAULT_VALUE, DEFAULT_VALUE);
        if (Character.isDigit(value.charAt(0))) {
            this.weight = value;
        } else {
            this.color = value;
        }
    }

    public Car(String model, Engine engine) {
        this(model, engine, DEFAULT_VALUE, DEFAULT_VALUE);
    }

    @Override
    public String toString() {
        return String.format("%s:%n" +
                        "%s%n" +
                        "Weight: %s%n" +
                        "Color: %s",
                this.model,
                this.engine.toString(),
                this.weight,
                this.color);
    }
}
