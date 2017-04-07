
public class ArifmeticalCalculator {

    public static float addUp(float[] args){
        return (args[0]+args[1]);
    }

    public static float deduct(float[] args){
        return (args[0]-args[1]);
    }

    public static float multiply(float[] args){
        return (args[0]*args[1]);
    }

    public static float divide(float[] args){
        return (args[0]/args[1]);

    }

    public static float changleSign(float arg){
        return -1*arg;
    }


    public static float exponentiation(float[] args){
        return (float) Math.pow(args[0],args[1]);
    }

    public static float getRadical(float arg){
        return (float) Math.sqrt(arg);
    }

}
