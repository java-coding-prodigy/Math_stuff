package importfunctions;
import static importfunctions.LambertWFunction.W;
import static numberstuff.Approx.approx;
import static java.lang.Math.*;

public class SuperSquareRoot {
    public static void main(String[]args){
        System.out.println(approx(ssrt(256)));
    }
    public static double ssrt(double x){
        return pow(E, W(log(x)));
    }
}
