import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите тип фигуры");
        Scanner scanner = new Scanner(System.in);
        String name_figure = scanner.next();
        Figure figure = null;
        try {
            switch (name_figure.toLowerCase()) {
                case "квадрат":
                    System.out.println("Length (a)");
                    figure = new Square(Double.parseDouble(scanner.next()));
                    break;
                case "прямоугольник":
                    System.out.println("Width, length (a b)");
                    figure = new Rectangle(Double.parseDouble(scanner.next()), Double.parseDouble(scanner.next()));
                    break;
                case "треугольник":
                    System.out.println("Sides of the triangle (a b c)");
                    figure = new Triangle(Double.parseDouble(scanner.next()), Double.parseDouble(scanner.next()),
                            Double.parseDouble(scanner.next()));
                    break;
                case "круг":
                    System.out.println("Radius (r)");
                    figure = new Circle(Double.parseDouble(scanner.next()));
                    break;
                default:
                    System.out.println("There is no such figure");
                    return;
            }
        }catch(NumberFormatException e){
            System.out.println("Invalid argument format");
            e.printStackTrace();
        }
        System.out.println("Area - "+figure.calculateArea());
        System.out.println("Perimetr - "+figure.calculatePerimetr());

    }

}
