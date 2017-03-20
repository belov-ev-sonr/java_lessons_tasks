public class Adder{
	
	public static void main(String[] args){
		try {
			System.out.println(args[0]+"+"+args[1]+"="+(Double.parseDouble(args[0])+Double.parseDouble(args[1])));
        } catch (NumberFormatException e) {
            System.out.println("Arguments are not numbers");
            e.printStackTrace();
        }

    }
}