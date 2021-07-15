package numberstuff;

import java.util.*;
import java.math.BigInteger;

public class FibonacciNumbers {
    public static void main(String[] args) {
        System.out.println("How many fibonacci numbers do you want?");
        Scanner x = new Scanner(System.in);
        long n = x.nextLong();
        BigInteger a = new BigInteger("0");
        BigInteger b = new BigInteger("1");
        BigInteger c;
        System.out.print(a + " " + b + " ");
        long f = n - 3;
        for (long i = 0; i <= f; i++) {
            c = a.add(b);
            System.out.print(c + " ");
            a = b;
            b = c;
        }
        System.out.println(" ");
    }
    public static BigInteger nthFibonnaci(long n){
        if (n == 0)
            return BigInteger.ZERO;
        else if(n ==1||n==2)
            return BigInteger.ONE;
        else
            return nthFibonnaci(n-1).add(nthFibonnaci(n-2));
    }
}
