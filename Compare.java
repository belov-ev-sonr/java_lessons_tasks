public class Compare {
    private final static String simvols_lat="AaBCcHTEePpMXxKk";
    private final static String simvols_kir="АаВСсНТЕеРрМХхКк";

    public static void main(String[] args) {
        if(args[0].length()!=args[1].length()) {
            System.out.println("Strings not equals");
            return;
        }
        char[] stroka_1=args[0].toCharArray();
        char[] stroka_2=args[1].toCharArray();
        for (int i = 0; i <stroka_1.length ; i++) {
            if(stroka_1[i]!=stroka_2[i])
                if(((simvols_lat.indexOf(stroka_1[i])==simvols_kir.indexOf(stroka_2[i])) &&
                        (simvols_lat.indexOf(stroka_1[i])!=-1 || simvols_kir.indexOf(stroka_2[i])!=-1)) ||
                        ((simvols_kir.indexOf(stroka_1[i])==simvols_lat.indexOf(stroka_2[i])) &&
                         (simvols_lat.indexOf(stroka_2[i])!=-1 || simvols_kir.indexOf(stroka_1[i])!=-1))){
                    continue;
                }else{
                    System.out.println("Strings not equals");
                    return;
                }
        }
        System.out.println("Strings equals");
    }
}