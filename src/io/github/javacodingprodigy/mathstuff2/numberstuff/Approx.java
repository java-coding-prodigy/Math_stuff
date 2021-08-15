package io.github.javacodingprodigy.mathstuff2.numberstuff;

import io.github.javacodingprodigy.mathstuff2.DoubleComplex;

import java.math.*;

public class Approx {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("0.0000000000000000000000000000001");
        BigDecimal b = new BigDecimal("0.0000000000000000000000000000002");
        System.out.println(almostSameBig(a,b));
    }

    public static double approx(double a) {
        double epsilon = 0.0000001;
        if (Math.abs(a - Math.round(1000.0 * a) / 1000.0) <= epsilon)
            return Math.round(1000.0 * a) / 1000.0;
        else
            return a;
    }
    public static boolean almostSame(double a, double b){
        return approx((a - b)) == 0;
    }
    public static BigDecimal approx(BigDecimal a) {
        double epsilon = 0.00000001;
        if ((a.subtract(a.setScale(4, RoundingMode.HALF_UP))).abs().compareTo(BigDecimal.valueOf(epsilon)) <= epsilon)
            return a.setScale(4, RoundingMode.HALF_UP);
        else
            return a;

    }
    public static boolean almostSameBig(BigDecimal a, BigDecimal b){
        return approx(a.subtract(b)).compareTo(BigDecimal.ZERO) == 0;
    }

    public static DoubleComplex approx(DoubleComplex z){
        return new DoubleComplex(approx(z.getRealDouble()), approx(z.getImagDouble()));
    }
}