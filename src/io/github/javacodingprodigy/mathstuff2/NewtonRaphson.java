package io.github.javacodingprodigy.mathstuff2;

import java.util.function.Function;

public class NewtonRaphson {

	public static void main(String[]args){
		System.out.println(inverse(Complex.ONE,NewtonRaphson::f,Complex.NEGATIVE_ONE));
	}
	private static Complex f(Complex x){
		return x.pow(2);
	}
	public static Complex inverse(Complex startValue, Function<Complex, Complex> f ,Complex goal){
		for(int i = 0; i < 100; i++){
			startValue = startValue.subtract((f(startValue).subtract(goal).divide(Derivative.d(f, startValue))));
		}
		return startValue;
	}
}
