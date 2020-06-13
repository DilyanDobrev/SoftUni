package encapsulation.classboxdatavalidation;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setHeight(double height) {
        dataValidation(height, "Height");
        this.height = height;
    }

    private void setWidth(double width) {
        dataValidation(width, "Width");
        this.width = width;
    }

    private void setLength(double length) {
        dataValidation(length, "Length");
        this.length = length;
    }

    private void dataValidation(double length, String side) {
        if (length < 1) {
            throw new IllegalArgumentException(side + " cannot be zero or negative.");
        }
    }

    public double calculateSurfaceArea () {
        double result = 2 * this.length * this.width +
                2 * this.length * this.height +
                2 * this.width * this.height;

        return result;
    }

    public double calculateLateralSurfaceArea () {
        double result = 2 * this.length * this.height +
                2 * this.width * this.height;

        return result;
    }

    public double calculateVolume () {
        double result = this.length * this.width * this.height;

        return result;
    }
}
