package polymorphism.wildfarm.animals;

import polymorphism.wildfarm.foods.Food;

import java.text.DecimalFormat;

public abstract class Animal {
    private String name;
    private String type;
    private Double weight;
    private Integer foodEaten;

    protected Animal(String name, String type, Double weight) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.foodEaten = 0;
    }

    protected String getType() {
        return this.type;
    }

    public abstract void makeSound();

    public void eat(Food food) {
        this.foodEaten += food.getQuantity();
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("#.##");
        return String.format("%s[%s, %s, region, %d]",
                this.getType(), this.name, formatter.format(this.weight), this.foodEaten);
    }
}
