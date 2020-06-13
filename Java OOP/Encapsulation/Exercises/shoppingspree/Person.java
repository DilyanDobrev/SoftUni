package encapsulation.shoppingspree;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setName(String name) {
        Validator.validateName(name);
        this.name = name;
    }

    private void setMoney(double money) {
        Validator.validateCash(money);
        this.money = money;
    }

    public String getName() {
        return this.name;
    }

    public void buyProduct(Product product) {
        if (this.money < product.getCost()) {
            throw new IllegalArgumentException(this.name + " can't afford " + product.getName());
        }
        this.products.add(product);
    }
}
