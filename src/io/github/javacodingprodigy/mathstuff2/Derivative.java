package io.github.javacodingprodigy.mathstuff2;

import java.math.BigDecimal;
import java.util.function.Function;

import static io.github.javacodingprodigy.mathstuff2.numberstuff.Approx.approx;

public class Derivative {
	public static void main(String[]args){
		System.out.println(d(Derivative::f , BigDecimal.valueOf(0)));
	}
	public static BigDecimal f(BigDecimal x){
		return x.abs();
	}
	public static BigDecimal d(Function<BigDecimal, BigDecimal> f,BigDecimal x){
		BigDecimal h = new BigDecimal("1E-323");
		return approx((f.apply(x.add(h)).subtract(f.apply(x))).divide(h));
	}
	public static Complex d(Function<Complex,Complex> f,Complex x){
		Complex h = new Complex(1E-200,1E-200);
		return approx((f.apply(x.add(h)).subtract(f.apply(x))).divide(h));
	}
}
