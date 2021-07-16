package io.github.javacodingprodigy.mathstuff2.importfunctions;

import java.util.Scanner;
import java.math.BigInteger;



public class AChooseB {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long a = input.nextLong();
        long b = input.nextLong();
        long c = a - b;
        if (a < 0 || b < 0)
            System.out.println("ERROR");
        else if (b > a)
            System.out.println("ERROR");
        else {
            BigInteger answer = FactorialFunctions.aFactDivCFact(a, c).divide(FactorialFunctions.factorial(b));
            System.out.println(a + " choose " + b + " = " + answer);
        }
    }
}
