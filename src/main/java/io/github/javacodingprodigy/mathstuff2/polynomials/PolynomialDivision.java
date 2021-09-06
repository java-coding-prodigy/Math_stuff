package io.github.javacodingprodigy.mathstuff2.polynomials;

import java.util.Scanner;

import static java.lang.Math.*;

public class PolynomialDivision {
	public static void main(String[] args) {
		Scanner degrees = new Scanner(System.in);
		Scanner Dividend = new Scanner(System.in);
	}

	public static void cubicNLinear(double a1, double b1, double c1, double d1, double a2, double b2) {
        double rootLinear = -b2/a2;
        double remainder = a1* pow(rootLinear,3) + b1* pow(rootLinear,2) + c1*rootLinear + d1;
        d1 -= remainder;

	}
}
