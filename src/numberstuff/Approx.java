package numberstuff;

import java.math.*;

public class Approx {
    public static void main(String[] args) {
        System.out.println(approx(Math.cos(Math.sin(Math.PI))));
    }

    public static double approx(double a) {
        double epsilon = 0.00000001;
        if (Math.abs(a - Math.round(1000.0 * a) / 1000.0) <= epsilon)
            return Math.round(1000.0 * a) / 1000.0;
        else
            return a;
        //it just approximates to the nearest thousandths if and only if it is very close
        //for eg 0.9999999 gets approximated to 1 but 0.9 does not
        //I don't think it has anything to do with this class
    }
    public static BigDecimal approx(BigDecimal a) {
        double epsilon = 0.00000001;
        if ((a.subtract(a.setScale(4, RoundingMode.HALF_UP))).abs().compareTo(BigDecimal.valueOf(epsilon)) <= epsilon)
            return a.setScale(4, RoundingMode.HALF_UP);
        else
            return a;

    }
}