package DefiningClassesExercises.CatLady;

public class Siamese extends Cat {

    public Siamese(String name, double power) {
        super(name, power);
    }

    @Override
    public String toString() {
        return "Siamese " + super.toString();
    }
}
