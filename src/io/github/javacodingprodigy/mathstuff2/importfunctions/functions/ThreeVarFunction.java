package io.github.javacodingprodigy.mathstuff2.importfunctions.functions;

import java.util.Scanner;

public class ThreeVarFunction {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double x = input.nextDouble();
		double y = input.nextDouble();
		double z = input.nextDouble();
		System.out.print("f(" + x + ", " + y + ", " + z + ") = " + f(x, y, z));
	}

	public static double f(double x, double y, double z) {
		return (x + y) / z;
	}
}
