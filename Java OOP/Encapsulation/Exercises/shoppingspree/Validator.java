package encapsulation.shoppingspree;

public class Validator {

    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public static void validateCash(double cash) {
        if (cash < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }
}
