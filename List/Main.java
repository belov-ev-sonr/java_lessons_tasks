

public class Main {
    public static void main(String[] args) {
        List list = new List();
        list.push(35);
        list.push("Stroka");
        list.push(40);
        list.push(50);
        list.push(60.5);
        list.push(70);
        list.push(80);
        System.out.println(list.getSize());
        list.pop();
        System.out.println(list.getSize());
        list.pop();
        System.out.println(list.getSize());


    }
}
