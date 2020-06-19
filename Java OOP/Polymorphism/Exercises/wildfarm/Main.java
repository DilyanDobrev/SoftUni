package polymorphism.wildfarm;

import polymorphism.wildfarm.animals.*;
import polymorphism.wildfarm.foods.Food;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();

        String input;
        while (!"End".equals(input = scanner.nextLine())) {
            String[] animalData = input.split("\\s+");
            String[] foodData = scanner.nextLine().split("\\s+");

            Animal animal = generateAnimal(animalData);
            Food food = generateFood(foodData);

            animal.makeSound();
            try {
                animal.eat(food);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            animals.add(animal);
        }

        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }

    private static Animal generateAnimal(String[] animalData) {
        Animal animal = null;

        String type = animalData[0];
        String name = animalData[1];
        double weight = Double.parseDouble(animalData[2]);
        String region = animalData[3];
        switch (type) {
            case "Mouse":
                animal = new Mouse(name, type, weight, region);
                break;
            case "Zebra":
                animal = new Zebra(name, type, weight, region);
                break;
            case "Cat":
                animal = new Cat(name, type, weight, region, animalData[4]);
                break;
            case "Tiger":
                animal = new Tiger(name, type, weight, region);
                break;
        }

        return animal;
    }

    private static Food generateFood(String[] foodData) {
        String type = foodData[0];
        Food food = null;
        try {
            Class foodClass = Class.forName("polymorphism.wildfarm.foods." + type);
            Constructor constructor = foodClass.getConstructor(Integer.class);
            food = (Food) constructor.newInstance(Integer.parseInt(foodData[1]));
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return food;
    }
}
