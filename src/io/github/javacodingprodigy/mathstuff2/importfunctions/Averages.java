package io.github.javacodingprodigy.mathstuff2.importfunctions;

import java.util.Scanner;

public class Averages {
    public static void main(String[] args) {
        System.out.println();
        Scanner input = new Scanner(System.in);
        int noOfEntries = input.nextInt();
        arithmeticMean(noOfEntries, input);
        geometricMean(noOfEntries, input);
        harmonicMean(noOfEntries, input);

    }

    public static void arithmeticMean(int noOfEntries, Scanner sc) {
        double sum = 0;
        System.out.print("The Arithmetic Mean of ");
        for (int i = 1; i <= noOfEntries; i++) {
            double inputtedEntry = sc.nextLong();
            System.out.print(inputtedEntry);
            if (inputtedEntry == Math.floor(inputtedEntry))
                inputtedEntry = (int) inputtedEntry;
            if (i != noOfEntries)
                System.out.print(" , ");
            sum += inputtedEntry;
        }
        double answer = sum / noOfEntries;
        if (answer == Math.floor(answer))
            answer = (int) answer;
        System.out.println(" is " + answer);

    }

    public static void geometricMean(int noOfEntries, Scanner sc) {
        double product = 1;
        System.out.print("The Geometric Mean of ");
        for (int i = 1; i <= noOfEntries; i++) {
            double inputtedEntry = sc.nextLong();
            System.out.print(inputtedEntry);
            if (inputtedEntry == Math.floor(inputtedEntry))
                inputtedEntry = (int) inputtedEntry;
            if (i != noOfEntries)
                System.out.print(" , ");
            product *= inputtedEntry;
        }
        double answer = Math.pow(product, 1.0 / noOfEntries);
        if (answer == Math.floor(answer))
            answer = (int) answer;
        System.out.println(" is " + answer);
    }

    public static void harmonicMean(int noOfEntries, Scanner sc) {
        double reciprocalSum = 1;
        System.out.print("The Harmonic Mean of ");
        for (int i = 1; i <= noOfEntries; i++) {
            double inputtedEntry = sc.nextLong();
            System.out.print(inputtedEntry);
            if (inputtedEntry == Math.floor(inputtedEntry))
                inputtedEntry = (int) inputtedEntry;
            if (i != noOfEntries)
                System.out.print(" , ");
            reciprocalSum += 1 / inputtedEntry;
        }
        double answer = noOfEntries / reciprocalSum;
        if (answer == Math.floor(answer))
            answer = (int) answer;
        System.out.println(" is " + answer);
    }
}
