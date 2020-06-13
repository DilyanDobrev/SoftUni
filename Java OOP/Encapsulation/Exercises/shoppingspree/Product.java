package encapsulation.shoppingspree;

public class Product {
    private String name;
    private double cost;

    public Product(String name, double cost) {
        this.setName(name);
        this.setCost(cost);
    }

    private void setName(String name) {
        Validator.validateName(name);
        this.name = name;
    }

    private void setCost(double cost) {
        Validator.validateCash(cost);
        this.cost = cost;
    }

    public String getName() {
        return this.name;
    }

    public double getCost() {
        return this.cost;
    }
}
