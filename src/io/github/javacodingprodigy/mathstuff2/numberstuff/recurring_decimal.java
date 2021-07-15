package io.github.javacodingprodigy.mathstuff2.numberstuff;

import java.util.Scanner;

public class recurring_decimal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        Fraction2decimal(a, b);
        DecimalToFraction(a, b);
    }

    public static void Fraction2decimal(long numerator, long denominator) {
        long part = numerator / denominator;
        System.out.print(numerator + "/" + denominator + " = " + part + ".");
        long num = numerator;
        for (long i = 0; i <= denominator && numerator % denominator != 0; i++) {
            numerator %= denominator;
            numerator *= 10;
            part = numerator / denominator;
            System.out.print(part);
        }
        if (numerator % denominator != 0) {
            numerator = num;
            for (long i = 0; i <= denominator && numerator % denominator != 0; i++) {
                numerator %= denominator;
                numerator *= 10;
                part = numerator / denominator;
                System.out.print(part);
            }
            System.out.print("...");
        }
        System.out.println();
    }

    public static void DecimalToFraction(long wholePart, long decimalPart) {
        long subtractWholePart = wholePart;
        long digTestOfDecimalPart = decimalPart;
        long digits;
        long divisor = 0;
        System.out.print(wholePart + "." + decimalPart + "" + decimalPart + "... = ");
        for (digits = 0; digTestOfDecimalPart > 0; digits++)
            digTestOfDecimalPart /= 10;
        wholePart *= Math.pow(10, digits);
        wholePart += decimalPart;
        wholePart -= subtractWholePart;
        long denominator = (long) Math.pow(10, digits) - 1;
        for (long i = wholePart; i >= 1; i--) {
            if (wholePart % i == 0 && denominator % i == 0) {
                divisor = i;
                break;
            }
        }
        if (divisor != 0) {
            wholePart /= divisor;
            denominator /= divisor;
        }
        System.out.println(wholePart + "/" + denominator);
    }
}