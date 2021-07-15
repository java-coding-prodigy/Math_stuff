package io.github.javacodingprodigy.mathstuff2.numberstuff;

import java.util.Scanner;

public class MultiplicativePersistence {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long number = input.nextLong();
        byte count = 0;
        int digits = 2147483647;
        while (digits > 1) {
            long product = 1;
            if (number != 0) {
                count++;
                digits = (int) Math.floor(Math.log10(number)) + 1;
                while (digits > 0) {
                    long digit = (long) (number / Math.pow(10, (digits - 1)));
                    digit %= 10;
                    product *= digit;
                    digits--;
                }
                if (number != product) {
                    System.out.println(product);
                    digits = (int) Math.floor(Math.log10(number)) + 1;
                    number = product;
                }
            } else
                digits = 0;
        }
        System.out.println("Multiplicative Persistence = " + count);
    }
}
