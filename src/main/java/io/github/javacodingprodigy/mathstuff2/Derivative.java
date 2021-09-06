package io.github.javacodingprodigy.mathstuff2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

import static io.github.javacodingprodigy.mathstuff2.numberstuff.Approx.approx;

public class Derivative {
	public static void main(String[] args) {
		Function<Complex,Complex> f = x -> x.pow(2);
		Function<BigDecimal,BigDecimal> g = x -> x.pow(2);
		System.out.println(d(f, DoubleComplex.ONE));
	}
	public static BigDecimal d(Function<BigDecimal, BigDecimal> f, BigDecimal x) {
		BigDecimal h = new BigDecimal("1E-100");
		return approx((f.apply(x.add(h))
				.subtract(f.apply(x))).divide(h, 500, RoundingMode.HALF_UP));
	}

	public static Complex d(Function<Complex, Complex> f, DoubleComplex x) {
		BigComplex h;
		if (x.getImagBigDecimal()
				.stripTrailingZeros()
				.equals(BigDecimal.ZERO)) {
			h = BigComplex.realValueOf(new BigDecimal("1E-100"));
		} else if (x.getRealBigDecimal()
				.stripTrailingZeros()
				.equals(BigDecimal.ZERO)) {
			h = BigComplex.imagValueOf(new BigDecimal("1E-100"));
		} else {
			h = new BigComplex(new BigDecimal("1E-100"), new BigDecimal("1E-100"));
		}
		System.out.println(h.add(x));
		System.out.println(f.apply(h.add(x)).getRealBigDecimal());
		return (f.apply((h).add(x))
				.subtract(f.apply(x))).divide(h);
	}
}
