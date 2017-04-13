package objects;

public class ArifmeticalCalculator {

    public static double addUp(double arg1, double arg2){
        return (arg1+arg2);
    }

    public static double deduct(double arg1, double arg2){
        return (arg1-arg2);
    }

    public static double multiply(double arg1, double arg2){
        return (arg1*arg2);
    }

    public static double divide(double arg1, double arg2){
        return (arg1/arg2);

    }

    public static double changleSign(double arg){
        return -1*arg;
    }


    public static double getRadical(double arg){
        return Math.sqrt(arg);
    }

}
