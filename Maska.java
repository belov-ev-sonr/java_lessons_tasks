public class Maska {

    public static void main(String[] args) {
        String[] ip_1=args[0].split("\\.");
        String[] ip_2=args[2].split("\\.");
        String maska[]=args[1].split("\\.");
        int adress_link_1[]={0,0,0,0};
        int adress_link_2[]={0,0,0,0};
        for (int i = 0; i <4 ; i++) {
            adress_link_1[i]=Integer.parseInt(ip_1[i])&Integer.parseInt(maska[i]);
            adress_link_2[i]=Integer.parseInt(ip_2[i])&Integer.parseInt(maska[i]);
            if(adress_link_1[i]!=adress_link_2[i]){
                System.out.println("ip-addresses of different network");
                return;
            }
        }
        System.out.println("ip-addresses of the same network");
    }
}