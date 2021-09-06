package io.github.javacodingprodigy.mathstuff2.polynomials;

import static io.github.javacodingprodigy.mathstuff2.numberstuff.Approx.*;
import static io.github.javacodingprodigy.mathstuff2.polynomials.Quadratic_equations.quadraticEquations;
import static java.lang.Math.*;

import java.util.Scanner;

public class CubicEquations {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double a = input.nextDouble();
		double b = input.nextDouble();
		double c = input.nextDouble();
		double d = input.nextDouble();
		System.out.print(
				"The roots of the cubic equation: " + a + "x³ " + String.format("%+f", b) + "x² " + String.format("%+f",
						c) + "x " + String.format("%+f", d) + " = 0 are: ");
		solveCubicEquations(a, b, c, d);
	}

	public static void solveCubicEquations(double a, double b, double c, double d) {
		if (a != 0) {
			double bDiv3a = -b / (3 * a);
			double bcDiv6asquare = (b * c) / (6 * a * a);
			double minusdDiv2a = -d / (2 * a);
			double cDiv3a = c / (3 * a);
			double partOfDiscriminant = pow(bDiv3a, 3) + bcDiv6asquare + minusdDiv2a;
			double otherPartOfDiscriminant = pow(cDiv3a - pow(bDiv3a, 2), 3);
			double discriminant = pow(partOfDiscriminant, 2) + otherPartOfDiscriminant;
			if (discriminant >= -0) {
				double firstPart = cbrt(partOfDiscriminant + sqrt(discriminant));
				double secondPart = cbrt(partOfDiscriminant - sqrt(discriminant));
				double x_1 = firstPart + secondPart + bDiv3a;
				double real_x_2and3 = firstPart / 2 + secondPart / 2 + bDiv3a;
				double imaginaryOmegaConstant = sin(2.09439510239320);
				double imaginary_x_2 = firstPart * (imaginaryOmegaConstant) + secondPart * imaginaryOmegaConstant;
				System.out.println(
						x_1 + ", " + real_x_2and3 + " " + String.format("%+f", imaginary_x_2) + "i and " + real_x_2and3
								+ " " + String.format("%+f", -imaginary_x_2) + "i");
			} else {

				discriminant = sqrt(abs(discriminant));
				double radius = sqrt(partOfDiscriminant * partOfDiscriminant + discriminant * discriminant);
				double angle1 = atan2(discriminant, partOfDiscriminant);
				double rootAngle1 = angle1 / 3;
				double x1 = approx(cbrt(radius) * (cos(rootAngle1) * 2) + bDiv3a);
				rootAngle1 += 2.09439510239320;
				double x2 = approx(cbrt(radius) * (cos(rootAngle1) * 2) + bDiv3a);
				rootAngle1 += 2.09439510239320;
				double x3 = approx(cbrt(radius) * (cos(rootAngle1) * 2) + bDiv3a);
				System.out.println(x1 + " , " + x2 + " and " + x3);
			}
		} else {
			quadraticEquations(b, c, d);
		}
	}
}
