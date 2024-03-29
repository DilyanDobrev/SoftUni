package polymorphism.wildfarm.animals;

import polymorphism.wildfarm.foods.Food;

public abstract class Mammal extends Animal {
    private String livingRegion;

    protected Mammal(String name, String type, Double weight, String livingRegion) {
        super(name, type, weight);
        this.livingRegion = livingRegion;
    }

    @Override
    public void eat(Food food) {
        boolean isMeat = food.getClass().getSimpleName().equals("Meat");

        if (isMeat && !(this instanceof Felime)) {
            String text =
                    (this.getType() + "s are not eating that type of food!").replace("Mouses", "Mice");
            throw new IllegalArgumentException(text);
        } else if (!isMeat && this.getType().equals("Tiger")) {
            throw new IllegalArgumentException("Tigers are not eating that type of food!");
        }
        super.eat(food);
    }

    @Override
    public String toString() {
        return super.toString().replace("region", this.livingRegion);
    }
}
