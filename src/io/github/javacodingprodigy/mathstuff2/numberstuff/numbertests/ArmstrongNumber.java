package io.github.javacodingprodigy.mathstuff2.numberstuff.numbertests;

public class ArmstrongNumber {
    public static boolean test(long n) {
        byte digits = (byte) (Math.log10(n) + 1);
        long expoSum = 0;
        for (byte i = digits; i > 0; i--) {
            byte digit = (byte) (n / Math.pow(10, i - 1) % 10);
            expoSum += Math.pow(digit, digits);
        }
        return expoSum == n;
    }

    public static void main(String[] args) {
        System.out.println(test(153));
    }
}