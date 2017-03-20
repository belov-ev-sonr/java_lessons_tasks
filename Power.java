public class Power{
	public static void main(String[] args){
		double reply=1;
		try{
			for(int i=0; i<Integer.parseInt(args[1]); i++){
				reply*=Integer.parseInt(args[0]);
			}
		}catch (NumberFormatException e){
			System.out.println("Arguments are not integer numbers");
			e.printStackTrace();
			return;
		}
		System.out.println("Reply: "+reply);
	}
	
}