package DefiningClassesExercises.CatLady;

public class Cymric extends Cat {

    public Cymric(String name, double power) {
        super(name, power);
    }

    @Override
    public String toString() {
        return "Cymric " + super.toString();
    }
}
