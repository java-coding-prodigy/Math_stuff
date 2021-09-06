package io.github.javacodingprodigy.mathstuff2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

public class NewtonRaphson {

	public static void main(String[]args){
		Function<BigDecimal,BigDecimal> f = bigDecimal -> bigDecimal.pow(2);
		System.out.println(inverse(f,new BigDecimal("2")));
		}
	public static BigDecimal inverse(Function<BigDecimal,BigDecimal> f, BigDecimal x){
		BigDecimal b = BigDecimal.ONE;
		for(int i = 0; i < 100; i++){
			BigDecimal num = f.apply(b).subtract(x);
			BigDecimal den = Derivative.d(f,b);
					b = b.subtract((num).divide(den,RoundingMode.HALF_UP));
		}
		return b;
	}
}
