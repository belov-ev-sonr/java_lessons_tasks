
public class ComplexNumbers implements ComplexN {
    private double x;
    private double y;

    public ComplexNumbers(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public ComplexNumbers adder(ComplexN compl) {
        return new ComplexNumbers(x+compl.getX(),y+compl.getY());
    }

    @Override
    public ComplexNumbers substraction(ComplexN compl) {
        return new ComplexNumbers(x-compl.getX(),y-compl.getY());
    }

    @Override
    public ComplexNumbers multiplication(ComplexN compl) {
        return new ComplexNumbers(x*compl.getX()-y*compl.getY(),y*compl.getX()+x*compl.getY());
    }

    @Override
    public ComplexNumbers division(ComplexN compl) {
        return new ComplexNumbers((x*compl.getX()+y*compl.getY())/(Math.pow(compl.getX(),2)+Math.pow(compl.getY(),2))
                ,(y*compl.getX()+x*compl.getY())/(Math.pow(compl.getX(),2)+Math.pow(compl.getY(),2)));
    }

    @Override
    public double module() {
        return Math.sqrt(x*x+y*y);
    }

    @Override
    public String toString() {
        if(y==0)
            return String.valueOf(x);
        if(y>0)
            return x+"+"+y+"*i";
        else
            return x+"-"+y+"*i";
    }
}
