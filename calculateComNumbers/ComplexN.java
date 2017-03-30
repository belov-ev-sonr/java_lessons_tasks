
public interface ComplexN {
    public double getX();
    public double getY();
    public ComplexNumbers adder(ComplexN compl);
    public ComplexNumbers substraction(ComplexN compl);
    public ComplexNumbers multiplication(ComplexN compl);
    public ComplexNumbers division(ComplexN compl);
    public double module();
    public String toString();
}
