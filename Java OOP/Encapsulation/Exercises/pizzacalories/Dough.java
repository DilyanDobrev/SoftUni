package encapsulation.pizzacalories;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String flourType) {
        Validator.flourType(flourType);
        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        Validator.bakingTechnique(bakingTechnique);
        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {
        Validator.doughWeight(weight);
        this.weight = weight;
    }

    public double calculateCalories() {
        double calories = this.weight * 2;

        if ("White".equalsIgnoreCase(this.flourType)) {
            calories *= 1.5;
        }

        if ("Crispy".equalsIgnoreCase(this.bakingTechnique)) {
            calories *= 0.9;
        } else if ("Chewy".equalsIgnoreCase(this.bakingTechnique)) {
            calories *= 1.1;
        }
        return calories;
    }
}
