package io.github.javacodingprodigy.mathstuff2;

import io.github.javacodingprodigy.mathstuff2.numberstuff.Approx;

import java.util.function.BiFunction;

/**
 * This is a program to find the result to a function using euler's method
 */
public class EulerMethod {
	static double x = 1;
	static double y = 1;
	static double iterations = 10                                                    ;

	static double fPrime(double x, double y) {
		return x*2;
	}

	public static double answer(double x, double y, double iterations, double goal,
			BiFunction<Double, Double, Double> method) {
		double stepSize = (goal - x)/iterations;
		for (double count = x; !Approx.almostSame(count, goal); count += stepSize) {
			y = y + stepSize * method.apply(count, y);
			System.out.println(y);
		}
		return y;
	}
	public static Complex answer(Complex startVal1, Complex startVal2, Complex iterations, Complex goal,
			BiFunction<Complex, Complex, Complex> method) {
		Complex stepSize = (goal.subtract(startVal1)).divide(iterations);
		for (Complex count = startVal1; !count.equals(goal); count = count.add( stepSize)) {
			startVal2 = startVal2.add(stepSize.multiply(method.apply(count, startVal2)));
			System.out.println(startVal2);
		}
		return startVal2;
	}

	public static void main(String[] a) {
		System.out.println(answer(x, y, iterations, 2, EulerMethod::fPrime));
	}
}
