package DefiningClassesExercises.CatLady;

public abstract class Cat {
    private String name;
    private double power;

    public Cat(String name, double power) {
        this.name = name;
        this.power = power;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f", this.name, this.power);
    }
}
