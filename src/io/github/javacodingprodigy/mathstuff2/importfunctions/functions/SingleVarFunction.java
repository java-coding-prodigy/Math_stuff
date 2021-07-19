package io.github.javacodingprodigy.mathstuff2.importfunctions.functions;

import java.util.Scanner;

import static io.github.javacodingprodigy.mathstuff2.numberstuff.Approx.approx;
import static java.lang.Math.*;

public class SingleVarFunction {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double x = sc.nextDouble();
		System.out.println("f(" + x + ")" + " = " + f(x));
	}

	public static double f(double x) {
		return approx((pow((1 + sqrt(5))/2, x) - pow((1 - sqrt(5))/2, x))/sqrt(5));
	}
}
