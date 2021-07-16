package io.github.javacodingprodigy.mathstuff2.importfunctions;

import java.util.Scanner;

import static java.lang.Math.*;

public class function {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double x = sc.nextDouble();
		double y = sc.nextDouble();
		System.out.println("f(" + x + ", " + y + ") = " + f(x,y));
	}

	public static double f(double x, double y) {
		return sqrt(x * x + y * y);
	}
}

