package encapsulation.pizzacalories;

import java.util.Arrays;

public class Validator {

    public static void toppingWeight(String toppingType, double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(toppingType + " weight should be in the range [1..50].");
        }
    }

    public static void toppingType(String toppingType) {
        String[] table = {"Meat", "Veggies", "Cheese", "Sauce"};
        if (!Arrays.asList(table).contains(toppingType)) {
            throw new IllegalArgumentException("Cannot place " + toppingType + " on top of your pizza.");
        }
    }

    public static void pizzaName(String name) {
        if (name.trim().isEmpty() || name.length() > 15) {
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
    }

    public static void numberOfToppings(int numberOfToppings) {
        if (numberOfToppings < 0 || numberOfToppings > 10) {
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
    }

    public static void flourType(String type) {
        if (!"White".equalsIgnoreCase(type) && !"Wholegrain".equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    public static void bakingTechnique(String technique) {
        if (!"Crispy".equalsIgnoreCase(technique) && !"Chewy".equalsIgnoreCase(technique) && !"Homemade".equalsIgnoreCase(technique)) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
    }

    public static void doughWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
    }
}
