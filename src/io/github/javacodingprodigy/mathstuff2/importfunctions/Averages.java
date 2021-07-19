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
		arithmeticMean(numbers);
		geometricMean(numbers);
		harmonicMean(numbers);

	}

	public static void arithmeticMean(double[] AM) {
		double sum = 0;
		System.out.print("The Arithmetic Mean of ");
		for (double inputtedEntry : AM) {
			if (inputtedEntry == Math.floor(inputtedEntry)) {
				System.out.printf("%.0f", inputtedEntry);
			} else {
				System.out.print(inputtedEntry);
			}
			System.out.print(" , ");

			sum += inputtedEntry;
		}
		double answer = sum / AM.length;
		if (answer == Math.floor(answer)) {
			System.out.println("is " + String.format("%.0f", answer));
		} else {
			System.out.println(" is " + answer);
		}

	}

	public static void geometricMean(double[] GM) {
		double product = 1;
		System.out.print("The Geometric Mean of ");
		for (double inputtedEntry : GM) {
			if (inputtedEntry == Math.floor(inputtedEntry)) {
				System.out.printf("%.0f", inputtedEntry);
			} else {
				System.out.print(inputtedEntry);
			}

			System.out.print(" , ");

			product *= inputtedEntry;
		}
		double answer = Math.pow(product, 1.0 / GM.length);
		if (answer == Math.floor(answer)) {
			System.out.println("is " + String.format("%.0f", answer));
		} else {
			System.out.println(" is " + answer);
		}
	}

	public static void harmonicMean(double[] HM) {
		double reciprocalSum = 1;
		System.out.print("The Harmonic Mean of ");
		for (double inputtedEntry : HM) {
			if (inputtedEntry == Math.floor(inputtedEntry)) {
				System.out.printf("%.0f", inputtedEntry);
			} else {
				System.out.print(inputtedEntry);
			}

			System.out.print(" , ");

			reciprocalSum += 1 / inputtedEntry;
		}
		double answer = HM.length / reciprocalSum;
		if (answer == Math.floor(answer)) {
			System.out.println("is " + String.format("%.0f", answer));
		} else {
			System.out.println(" is " + answer);
		}
	}
}
