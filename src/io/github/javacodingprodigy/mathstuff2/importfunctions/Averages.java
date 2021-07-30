package io.github.javacodingprodigy.mathstuff2.importfunctions;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Averages {
	public static void main(String[] args) {
		System.out.println("Enter the numbers which you want to find the means of, enter any non number to culminate the input");
		Scanner input = new Scanner(System.in);
		ArrayList<Double> numbers = new ArrayList<>();
		while (true) {
			try {
				numbers.add(input.nextDouble());
			} catch (InputMismatchException e) {
				break;
			}
		}
		arithmeticMean(numbers);
		geometricMean(numbers);
		harmonicMean(numbers);

	}

	public static void arithmeticMean(ArrayList<Double> AM) {
		double sum = 0;
		System.out.print("The Arithmetic Mean of ");
		for (double inputtedEntry : AM) {
			System.out.print(
					inputtedEntry == Math.floor(inputtedEntry) ? String.format("%.0f", inputtedEntry) : inputtedEntry);
			if (inputtedEntry - 1 != AM.size()) {
				System.out.print(", ");
			}
		}
		double answer = sum / AM.size();
		System.out.println(" is " + (answer != Math.floor(answer) ? answer : String.format("%.0f", answer)));

	}

	public static void geometricMean(ArrayList<Double> GM) {
		double product = 1;
		System.out.print("The Geometric Mean of ");
		for (double inputtedEntry : GM) {
			System.out.print(
					inputtedEntry == Math.floor(inputtedEntry) ? String.format("%.0f", inputtedEntry) : inputtedEntry);
			if (inputtedEntry - 1 != GM.size()) {
				System.out.print(", ");
			}

			product *= inputtedEntry;
		}
		double answer = Math.pow(product, 1.0 / GM.size());
		System.out.println(" is " + (answer != Math.floor(answer) ? answer : String.format("%.0f", answer)));
	}

	public static void harmonicMean(ArrayList<Double> HM) {
		double reciprocalSum = 1;
		System.out.print("The Harmonic Mean of ");
		for (double inputtedEntry : HM) {
			System.out.print(
					inputtedEntry == Math.floor(inputtedEntry) ? String.format("%.0f", inputtedEntry) : inputtedEntry);

			if (inputtedEntry - 1 != HM.size()) {
				System.out.print(", ");
			}

			reciprocalSum += 1 / inputtedEntry;
		}
		double answer = HM.size() / reciprocalSum;
		System.out.println(" is " + (answer != Math.floor(answer) ? answer : String.format("%.0f", answer)));
	}
}
