import java.util.Scanner;

public class Start {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ComplexN compl1 = null;
        ComplexN compl2 = null;
        String operation=null;
        System.out.println("Input two complex number without imaginary unit and operation(+,-,*,/,mod) (format: a b / c d) (format: a b mod)");
        protectedBlock:
        {
            try {
                compl1 = new ComplexNumbers(Double.parseDouble(scanner.next()), Double.parseDouble(scanner.next()));
                operation = new String(scanner.next());
                if (operation.equals("mod"))
                    //выход из блока protectedBlock!!!!!
                    break protectedBlock;
                compl2 = new ComplexNumbers(Double.parseDouble(scanner.next()), Double.parseDouble(scanner.next()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Wrong format");
            }
        }
        System.out.print("Result: ");
        switch(operation){
            case "+":
                System.out.println((compl1.adder(compl2)).toString());
                break;
            case "-":
                System.out.println((compl1.substraction(compl2)).toString());
                break;
            case "*":
                System.out.println((compl1.multiplication(compl2)).toString());
                break;
            case "/":
                System.out.println((compl1.division(compl2)).toString());
                break;
            case "mod":
                System.out.println(compl1.module());
                break;
            default:
                System.out.println("There is no such operation");
                return;
        }
    }
}
