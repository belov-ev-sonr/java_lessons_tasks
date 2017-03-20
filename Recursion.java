public class Recursion{
	
	private static int factorial(int number){
		int result=1;
		if(number==1)
			return 1;
		else
			result=number;
		return number*factorial(number-1);
	}
	
	public static void main(String[] args){
		try{
			System.out.println(factorial(Integer.parseInt(args[0])));
		}catch(NumberFormatException e){
			System.out.println("Arguments are not integer numbers");
			e.printStackTrace();
		}
	}
}