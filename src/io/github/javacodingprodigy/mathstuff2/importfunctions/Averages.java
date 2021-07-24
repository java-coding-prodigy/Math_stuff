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
			System.out.print(
					inputtedEntry == Math.floor(inputtedEntry) ? String.format("%.0f", inputtedEntry) : inputtedEntry);
			if (inputtedEntry - 1 != AM.length)
				System.out.print(", ");
		}
		double answer = sum / AM.length;
		System.out.println(" is " + (answer != Math.floor(answer) ? answer : String.format("%.0f", answer)));

	}

	public static void geometricMean(double[] GM) {
		double product = 1;
		System.out.print("The Geometric Mean of ");
		for (double inputtedEntry : GM) {
			System.out.print(
					inputtedEntry == Math.floor(inputtedEntry) ? String.format("%.0f", inputtedEntry) : inputtedEntry);
			if (inputtedEntry - 1 != GM.length)
				System.out.print(", ");

			product *= inputtedEntry;
		}
		double answer = Math.pow(product, 1.0 / GM.length);
		System.out.println(" is " + (answer != Math.floor(answer) ? answer : String.format("%.0f", answer)));
	}

	public static void harmonicMean(double[] HM) {
		double reciprocalSum = 1;
		System.out.print("The Harmonic Mean of ");
		for (double inputtedEntry : HM) {
			System.out.print(
					inputtedEntry == Math.floor(inputtedEntry) ? String.format("%.0f", inputtedEntry) : inputtedEntry);

			if (inputtedEntry - 1 != HM.length)
				System.out.print(", ");

			reciprocalSum += 1 / inputtedEntry;
		}
		double answer = HM.length / reciprocalSum;
		System.out.println(" is " + (answer != Math.floor(answer) ? answer : String.format("%.0f", answer)));
	}
}
