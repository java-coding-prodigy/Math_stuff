package numberstuff;

import java.math.BigInteger;
import java.lang.*;

public class Phi {
    public static double phi(){
        return 0.5 + Math.sqrt(5)/2;
    }

    public static void main(String[] args) {
        System.out.println(" ");
        long a = 5;
        long s = (long) (Math.sqrt(a));
        BigInteger b = BigInteger.valueOf(s);
        BigInteger y = BigInteger.valueOf(a).subtract(b.pow(2));
        BigInteger c = b;
        System.out.println("φ = ");
        System.out.print((BigInteger.ONE.add(b)).divide(BigInteger.valueOf(2)) + ".");
        BigInteger e = (BigInteger.ONE.add(b)).mod(BigInteger.valueOf(2));
        int odd = 0;
        int even = 0;
        for (long digits = 100000; digits >= 0; digits--) {
            b = b.add(c);
            b = b.multiply(BigInteger.TEN);
            y = y.multiply(BigInteger.valueOf(100));
            c = BigInteger.valueOf(-1);
            BigInteger d = BigInteger.valueOf(0);
            while (y.compareTo(d) >= d.compareTo(y)) {
                c = c.add(BigInteger.valueOf(1));
                d = (b.add(c)).multiply(c);
            }
            c = c.subtract(BigInteger.valueOf(1));
            b = b.add(c);
            d = b.multiply(c);
            y = y.subtract(d);
            System.out.print((c.add(e.multiply(BigInteger.valueOf(10))).divide(BigInteger.valueOf(2))));
        }
    }
}
