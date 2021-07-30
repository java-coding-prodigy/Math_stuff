package io.github.javacodingprodigy.mathstuff2.numberstuff;

import java.util.Scanner;
import java.math.BigInteger;
import java.lang.Math;

public class sqrt {
    public static void not_very_accurate() {
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        System.out.println("√" + a + " = " + Math.sqrt(a));
    }

    public static void main(String[] args) {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        long a = Long.parseLong(sc.nextLine());
        long s = (long) (Math.sqrt(a));
        BigInteger b = BigInteger.valueOf(s);
        BigInteger y = BigInteger.valueOf(a).subtract(b.pow(2));
        BigInteger c = b;
        System.out.println("√" + a + " = ");
        System.out.print(b + ".");
        for (long digits = 1000000; digits > 0; digits--) {
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
            System.out.print(c);
        }
    }
}

