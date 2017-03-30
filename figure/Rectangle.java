
public class Rectangle extends Square implements Figure {
    protected double b;

    public Rectangle(double a,double b) {
        super(a);
        this.b=b;
    }

    @Override
    public double calculatePerimetr() {
        return 2*(a+b);
    }

    @Override
    public double calculateArea() {
        return a*b;
    }
}
