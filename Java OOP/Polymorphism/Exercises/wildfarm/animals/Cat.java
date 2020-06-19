package polymorphism.wildfarm.animals;

public class Cat extends Felime {
    private String breed;

    public Cat(String name, String type, Double weight, String livingRegion, String breed) {
        super(name, type, weight, livingRegion);
        this.breed = breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public String toString() {
        String text = super.toString();
        int index = text.indexOf(",");

        StringBuilder builder = new StringBuilder(text);
        builder.insert(index + 2, this.breed + ", ");

        return builder.toString();
    }
}
