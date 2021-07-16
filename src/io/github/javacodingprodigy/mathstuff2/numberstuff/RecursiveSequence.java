package io.github.javacodingprodigy.mathstuff2.numberstuff;

import java.math.BigInteger;

public class RecursiveSequence {
    public static void main(String[]args){
        int n = 100;
        System.out.println(recursiveSequence(n));
        System.out.println(function(n));
    }
    public static BigInteger recursiveSequence(long n){
        if (n == 1)
            return BigInteger.ONE;
        else if (n == 2 )
            return BigInteger.valueOf(5);
        else
            return BigInteger.valueOf(5).multiply(recursiveSequence(n-1)).subtract(BigInteger.valueOf(6).multiply(recursiveSequence(n-2)));
    }
    public static BigInteger function(int n){
        return BigInteger.valueOf(3).pow(n).subtract(BigInteger.TWO.pow(n));
    }
}
