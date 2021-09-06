package io.github.javacodingprodigy.mathstuff2.importfunctions;

import io.github.javacodingprodigy.mathstuff2.DoubleComplex;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Averages {
	public static void main(String[] args) {
		System.out.println(
				"Enter the numbers which you want to find the means of, enter any non number to culminate the input");
		Scanner input = new Scanner(System.in);
		ArrayList<DoubleComplex> numbers = new ArrayList<>();
		while (true) {
			try {
				numbers.add(DoubleComplex.parseLine(input.nextLine()));
			} catch (InputMismatchException e) {
				break;
			}
		}
		arithmeticMean(numbers);
		geometricMean(numbers);
		harmonicMean(numbers);

	}

	public static void arithmeticMean(ArrayList<DoubleComplex> AM) {
		DoubleComplex sum = DoubleComplex.ZERO;
		System.out.print("The Arithmetic Mean of ");
		for (DoubleComplex inputtedEntry : AM) {
			System.out.print(inputtedEntry + ", ");
			sum = sum.add(inputtedEntry);

		}
		DoubleComplex answer = sum.divide(DoubleComplex.realValueOf(AM.size()));
		System.out.println(" is " + answer);
	}

	public static void geometricMean(ArrayList<DoubleComplex> GM) {
		DoubleComplex product = DoubleComplex.ONE;
		System.out.print("The Geometric Mean of ");
		for (DoubleComplex inputtedEntry : GM) {
			System.out.print(inputtedEntry + ", ");
			product = product.multiply(inputtedEntry);
		}
		DoubleComplex answer = product.root(GM.size());
		System.out.println(" is " + answer);
	}

	public static void harmonicMean(ArrayList<DoubleComplex> HM) {
		DoubleComplex reciprocalSum = DoubleComplex.ZERO;
		System.out.print("The Harmonic Mean of ");
		for (DoubleComplex inputtedEntry : HM) {
			System.out.print(inputtedEntry);
			reciprocalSum = reciprocalSum.add(inputtedEntry.reciprocate());
		}
		DoubleComplex answer = DoubleComplex.realValueOf(HM.size())
				.divide(reciprocalSum);
		System.out.println(" is " + answer);
	}
}
