package DefiningClassesExercises.CatLady;

public class StreetExtraordinaire extends Cat{

    public StreetExtraordinaire(String name, double power) {
        super(name, power);
    }

    @Override
    public String toString() {
        return "StreetExtraordinaire " + super.toString();
    }
}
