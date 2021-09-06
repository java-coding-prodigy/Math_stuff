package io.github.javacodingprodigy.mathstuff2.numberstuff.numbertests;

public class NeonNumber {
    public static boolean test(long n){
        long nSquare = n*n;
        byte digits = (byte)(Math.log10(nSquare)+1);
        long squareDigSum = 0;
        for (byte i = digits; i > 0; i--) {
            byte digit = (byte) (nSquare / Math.pow(10, i-1) % 10);
            squareDigSum += digit;
        }
        return squareDigSum == n;
    }
    public static void main(String[]args){
        System.out.println(test(9));
    }
}