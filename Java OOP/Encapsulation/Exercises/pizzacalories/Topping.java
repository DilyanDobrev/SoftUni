package encapsulation.pizzacalories;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        Validator.toppingType(toppingType);
        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {
        Validator.toppingWeight(this.toppingType, weight);
        this.weight = weight;
    }

    public double calculateCalories() {
        double calories = this.weight * 2;

        switch (this.toppingType) {
            case "Meat":
                calories *= 1.2;
                break;
            case "Veggies":
                calories *= 0.8;
                break;
            case "Cheese":
                calories *= 1.1;
                break;
            case "Sauce":
                calories *= 0.9;
                break;
        }
        return calories;
    }
}
