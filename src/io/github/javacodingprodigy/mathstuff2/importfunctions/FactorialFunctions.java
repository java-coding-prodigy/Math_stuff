package io.github.javacodingprodigy.mathstuff2.importfunctions;

import java.math.BigInteger;
import java.util.Scanner;

public class FactorialFunctions {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        if (n >= 0) {
            System.out.println(n + "! = " + factorial(n));
            System.out.println("H(" + n + ") = " + hyperfactorial(n));
            System.out.println(n + "$ = " + superfactorial(n));
            System.out.println("!" + n + " = " + subfactorial(n));
            System.out.println(n + "!! = " + doublefactorial(n));
        } else {
            System.out.println(n + "! = ±∞");
            System.out.println("H(" + n + ") = ±∞");
            System.out.println(n + "$ = ±∞");
            System.out.println("!" + n + " = ±∞");
            System.out.println(n + "!! = ±∞" );
        }
    }

    public static BigInteger factorial(long n) {
        if (n == 0 || n == 1)
            return BigInteger.ONE;
        else
            return BigInteger.valueOf(n).multiply(factorial(n - 1));
    }

    public static BigInteger hyperfactorial(int n) {
        if (n == 0 || n == 1)
            return BigInteger.ONE;
        else
            return (BigInteger.valueOf(n).pow(n)).multiply(hyperfactorial(n - 1));
    }

    public static BigInteger superfactorial(int n) {
        if (n == 0 || n == 1)
            return BigInteger.ONE;
        else
            return factorial(n).multiply(superfactorial(n - 1));
    }

    public static BigInteger subfactorial(int n) {
        if (n == 0 || n == 2)
            return BigInteger.ONE;
        else if (n == 1)
            return BigInteger.ZERO;
        else
            return BigInteger.valueOf(n - 1).multiply(subfactorial(n - 1).add(subfactorial(n - 2)));
    }

    public static BigInteger doublefactorial(int n) {
        if (n == 1)
            return BigInteger.ONE;
        else if (n == 2)
            return BigInteger.TWO;
        else
            return BigInteger.valueOf(n).multiply(doublefactorial(n - 2));
    }

    public static BigInteger aFactDivCFact(long num1, long num2) {
        if (num1 == num2)
            return BigInteger.ONE;
        else
            return BigInteger.valueOf(num1).multiply(aFactDivCFact(num1 - 1, num2));
    }
}
