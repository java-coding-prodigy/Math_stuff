package io.github.javacodingprodigy.mathstuff2.importfunctions;

import static io.github.javacodingprodigy.mathstuff2.importfunctions.LambertWFunction.W;
import static io.github.javacodingprodigy.mathstuff2.numberstuff.Approx.approx;
import static java.lang.Math.*;

public class SuperSquareRoot {
	public static void main(String[] args) {
		System.out.println(ssrt(0.44721359549995));
	}

	public static double ssrt(double x) {
		return approx(exp(W(log(x))));
	}
}