
public class Circle implements Figure {
    private  double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculatePerimetr() {
        return 2*Math.PI*radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI*radius*radius;
    }
}
