package io.github.javacodingprodigy.mathstuff2.numberstuff;

import java.util.Scanner;


import static java.lang.Math.*;
import static io.github.javacodingprodigy.mathstuff2.numberstuff.Approx.*;

public class polarFormConverter {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double x = sc.nextDouble();
		double y = sc.nextDouble();
		System.out.println(x + "" + String.format("%+f", y) + "i = " + radius(x, y) + "* e^" + theta(x, y) + 'i');
		double radius = sc.nextDouble();
		double theta = toRadians(sc.nextDouble());
		System.out.println(radius + "*" + "e^" + theta + "i = " + x(radius, theta) + ' ' + String.format("%+f", y(radius, theta)) + 'i');
	}

	public static double radius(double x, double y) {
		return sqrt(x * x + y * y);
	}

	public static double theta(double x, double y) {
		return atan2(x, y);
	}

	public static double x(double radius, double theta) {
		return approx(radius * cos(theta));
	}

	public static double y(double radius, double theta) {
		return radius * sin(theta);
	}

}
