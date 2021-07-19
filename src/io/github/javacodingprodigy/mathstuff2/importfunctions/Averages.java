package io.github.javacodingprodigy.mathstuff2.importfunctions;

import java.util.Scanner;

public class Averages {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int noOfEntries = input.nextInt();
		double[] numbers = new double[noOfEntries];
		for (int i = 0; i < noOfEntries; i++) {
			numbers[i] = input.nextDouble();
		}
		arithmeticMean(noOfEntries, numbers);
		geometricMean(noOfEntries, numbers);
		harmonicMean(noOfEntries, numbers);

	}

	public static void arithmeticMean(int noOfEntries, double[] AM) {
		double sum = 0;
		System.out.print("The Arithmetic Mean of ");
		for (int i = 0; i < noOfEntries; i++) {
			double inputtedEntry = AM[i];
			if (inputtedEntry == Math.floor(inputtedEntry)) {
				System.out.printf("%.0f", inputtedEntry);
			} else {
				System.out.print(inputtedEntry);
			}
			System.out.print(" , ");

			sum += inputtedEntry;
		}
		double answer = sum / noOfEntries;
		if (answer == Math.floor(answer)) {
			System.out.println("is " + String.format("%.0f", answer));
		} else {
			System.out.println(" is " + answer);
		}

	}

	public static void geometricMean(int noOfEntries, double[] GM) {
		double product = 1;
		System.out.print("The Geometric Mean of ");
		for (int i = 0; i < noOfEntries; i++) {
			double inputtedEntry = GM[i];
			if (inputtedEntry == Math.floor(inputtedEntry)) {
				System.out.printf("%.0f", inputtedEntry);
			} else {
				System.out.print(inputtedEntry);
			}

			System.out.print(" , ");

			product *= inputtedEntry;
		}
		double answer = Math.pow(product, 1.0 / noOfEntries);
		if (answer == Math.floor(answer)) {
			System.out.println("is " + String.format("%.0f", answer));
		} else {
			System.out.println(" is " + answer);
		}
	}

	public static void harmonicMean(int noOfEntries, double[] HM) {
		double reciprocalSum = 1;
		System.out.print("The Harmonic Mean of ");
		for (int i = 0; i < noOfEntries; i++) {
			double inputtedEntry = HM[i];
			if (inputtedEntry == Math.floor(inputtedEntry)) {
				System.out.printf("%.0f", inputtedEntry);
			} else {
				System.out.print(inputtedEntry);
			}

			System.out.print(" , ");

			reciprocalSum += 1 / inputtedEntry;
		}
		double answer = noOfEntries / reciprocalSum;
		if (answer == Math.floor(answer)) {
			System.out.println("is " + String.format("%.0f", answer));
		} else {
			System.out.println(" is " + answer);
		}
	}
}
