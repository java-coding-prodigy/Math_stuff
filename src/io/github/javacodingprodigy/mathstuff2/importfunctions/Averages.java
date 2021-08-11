package io.github.javacodingprodigy.mathstuff2.importfunctions;

import io.github.javacodingprodigy.mathstuff2.Complex;

import java.util.*;

public class Averages {
	public static void main(String[] args) {
		System.out.println(
				"Enter the numbers which you want to find the means of, enter any non number to culminate the input");
		Scanner input = new Scanner(System.in);
		ArrayList<Complex> numbers = new ArrayList<>();
		while (true) {
			try {
				numbers.add(Complex.parseLine(input.nextLine()));
			} catch (InputMismatchException e) {
				break;
			}
		}
		arithmeticMean(numbers);
		geometricMean(numbers);
		harmonicMean(numbers);

	}

	public static void arithmeticMean(ArrayList<Complex> AM) {
		Complex sum = Complex.ZERO;
		System.out.print("The Arithmetic Mean of ");
		for (Complex inputtedEntry : AM) {
			System.out.print(inputtedEntry + ", ");
			sum = sum.add(inputtedEntry);

		}
		Complex answer = sum.divide(Complex.realValueOf(AM.size()));
		System.out.println(" is " + answer);
	}

	public static void geometricMean(ArrayList<Complex> GM) {
		Complex product = Complex.ONE;
		System.out.print("The Geometric Mean of ");
		for (Complex inputtedEntry : GM) {
			System.out.print(inputtedEntry + ", ");
			product = product.multiply(inputtedEntry);
		}
		Complex answer = product.root(GM.size());
		System.out.println(" is " + answer);
	}

	public static void harmonicMean(ArrayList<Complex> HM) {
		Complex reciprocalSum = Complex.ZERO;
		System.out.print("The Harmonic Mean of ");
		for (Complex inputtedEntry : HM) {
			System.out.print(inputtedEntry);
			reciprocalSum = reciprocalSum.add(Complex.reciprocate(inputtedEntry));
		}
		Complex answer = Complex.realValueOf(HM.size())
				.divide(reciprocalSum);
		System.out.println(" is " + answer);
	}
}
