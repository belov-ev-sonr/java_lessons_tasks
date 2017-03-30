
public class Square implements Figure {
    protected double a;

    public Square(double a) {
        this.a = a;
    }

    @Override
    public double calculatePerimetr() {
        return 4*a;
    }

    @Override
    public double calculateArea() {
        return a*a;
    }
}
