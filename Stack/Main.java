

public class Main {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(35);
        stack.push("Stroka");
        stack.push(40);
        stack.push(50);
        stack.push(60.5);
        stack.push(70);
        stack.push(80);
        System.out.println(stack.getSize());
        stack.pop();
        System.out.println(stack.getSize());
        stack.pop();
        System.out.println(stack.getSize());


    }
}
