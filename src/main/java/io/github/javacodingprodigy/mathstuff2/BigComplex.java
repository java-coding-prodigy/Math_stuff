package io.github.javacodingprodigy.mathstuff2;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

public class BigComplex implements Complex {

	private final BigDecimal realPart;
	private final BigDecimal imagPart;

	public static final BigComplex ZERO = realValueOf(BigDecimal.ZERO);
	public static final BigComplex ONE = realValueOf(BigDecimal.ONE);
	public static final BigComplex NEGATIVE_ONE = realValueOf(BigDecimal.ONE.negate());
	public static final BigComplex I = imagValueOf(BigDecimal.ONE);
	public static final BigComplex NEGATIVE_I = imagValueOf(BigDecimal.ONE.negate());

	public BigComplex(BigDecimal realPart, BigDecimal imagPart) {
		this.realPart = realPart;
		this.imagPart = imagPart;
	}

	public BigComplex(BigComplex x) {
		this.realPart = x.realPart;
		this.imagPart = x.imagPart;
	}

	public static void main(String[] args) {
		BigComplex h = new BigComplex(new BigDecimal("1E-100"), BigDecimal.ZERO);
		System.out.println(BigComplex.ONE.add(h)
				.pow(2));
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Complex)) {
			return false;
		}
		final BigComplex that = (BigComplex) o;
		return this.realPart.equals(that.imagPart) && this.imagPart.equals(that.imagPart);
	}

	@Override
	public String toString() {
		String realStr;
		String imagStr;
		if (this.realPart.stripTrailingZeros()
				.stripTrailingZeros()
				.equals(BigDecimal.ZERO) && this.imagPart.stripTrailingZeros()
				.equals(BigDecimal.ZERO)) {
			return "0";
		}
		if (this.realPart.equals(BigDecimal.ZERO)) {
			realStr = "";
		} else {
			realStr = this.realPart.equals(new BigDecimal(this.realPart.toBigInteger())) ?
					String.format("%.0f", this.realPart) : String.valueOf(this.realPart);
		}
		if (this.imagPart.stripTrailingZeros()
				.equals(BigDecimal.ZERO)) {
			imagStr = "";
		} else if (this.imagPart.abs()
				.stripTrailingZeros()
				.equals(BigDecimal.ONE)) {
			imagStr = this.realPart.stripTrailingZeros()
					.equals(BigDecimal.ZERO) ? "" : (this.realPart.signum() == 1 ? "+" : "-") + "i";
		} else {
			imagStr = (this.imagPart.signum() == 1 && !this.realPart.equals(BigDecimal.ZERO) ? "+" :
					"" + this.imagPart.stripTrailingZeros() + "i");
		}
		return realStr + imagStr;
	}

	@Override
	public int hashCode() {
		return Objects.hash(realPart, imagPart);
	}

	@Override
	public double getImagDouble() {
		return this.imagPart.doubleValue();
	}

	@Override
	public double getRealDouble() {
		return this.realPart.doubleValue();
	}

	@Override
	public BigDecimal getImagBigDecimal() {
		return this.imagPart;
	}

	@Override
	public BigDecimal getRealBigDecimal() {
		return this.realPart;
	}

	public static BigComplex realValueOf(BigDecimal z) {
		return new BigComplex(z, BigDecimal.ZERO);
	}

	public static BigComplex imagValueOf(BigDecimal z) {
		return new BigComplex(BigDecimal.ZERO, z);
	}

	public static BigComplex valueOf(DoubleComplex x) {
		return new BigComplex(x.getRealBigDecimal(), x.getImagBigDecimal());
	}

	@Override
	public BigComplex add(final Complex cd) {
		return new BigComplex(this.getRealBigDecimal()
				.add(cd.getRealBigDecimal()), this.getImagBigDecimal()
				.add(cd.getImagBigDecimal()));
	}

	@Override
	public BigComplex subtract(final Complex cd) {
		return new BigComplex(this.getRealBigDecimal()
				.subtract(cd.getRealBigDecimal()), this.getImagBigDecimal()
				.subtract(cd.getImagBigDecimal()));

	}

	@Override
	public BigComplex multiply(final Complex rs) {
		return new BigComplex((this.realPart.multiply(rs.getRealBigDecimal())).subtract(
				this.imagPart.multiply(rs.getImagBigDecimal())),
				(this.realPart.multiply(rs.getImagBigDecimal())).add(this.imagPart.multiply(rs.getRealBigDecimal())));

	}

	@Override
	public BigComplex divide(final Complex rs) {
		if (this.getRealBigDecimal()
				.equals(BigDecimal.ZERO) && rs.getRealBigDecimal()
				.equals(BigDecimal.ZERO)) {
			return imagValueOf(this.getImagBigDecimal()
					.divide(rs.getImagBigDecimal(), this.getImagBigDecimal()
							.scale() + rs.getImagBigDecimal()
							.scale(), RoundingMode.HALF_UP));
		} else if (this.getImagBigDecimal()
				.equals(BigDecimal.ZERO) && rs.getImagBigDecimal()
				.equals(BigDecimal.ZERO)) {
			return realValueOf(this.getRealBigDecimal()
					.divide(rs.getRealBigDecimal(), this.getRealBigDecimal()
							.scale() + this.getImagBigDecimal()
							.scale(), RoundingMode.HALF_UP));
		} else if (rs.getImagBigDecimal()
				.equals(BigDecimal.ZERO)) {
			return new BigComplex(this.getRealBigDecimal()
					.divide(rs.getRealBigDecimal(), this.getRealBigDecimal()
							.scale() + rs.getRealBigDecimal()
							.scale(), RoundingMode.HALF_UP), this.getImagBigDecimal()
					.divide(rs.getRealBigDecimal(), this.getImagBigDecimal()
							.scale() + rs.getRealBigDecimal()
							.scale(), RoundingMode.HALF_UP));
		} else if (rs.getRealBigDecimal()
				.equals(BigDecimal.ZERO)) {
			return new BigComplex((this.getRealBigDecimal()
					.divide(rs.getImagBigDecimal(), this.getRealBigDecimal()
							.scale() + rs.getImagBigDecimal()
							.scale(), RoundingMode.HALF_UP)), this.getImagBigDecimal()
					.divide(rs.getImagBigDecimal(), RoundingMode.HALF_UP)).multiply(NEGATIVE_I);
		} else if (rs == ZERO) {
			throw new ArithmeticException("/ by 0");
		} else {
			BigDecimal realSol = (this.getRealBigDecimal()
					.multiply(rs.getRealBigDecimal())
					.add(this.getRealBigDecimal()
							.multiply(rs.getImagBigDecimal()))).divide(rs.getRealBigDecimal()
					.multiply(rs.getRealBigDecimal())
					.add(rs.getImagBigDecimal()
							.multiply(rs.getImagBigDecimal())), (rs.getRealBigDecimal()
					.multiply(rs.getRealBigDecimal())
					.add(rs.getImagBigDecimal()
							.multiply(rs.getImagBigDecimal()))).scale() + (this.getRealBigDecimal()
					.multiply(rs.getRealBigDecimal())
					.add(this.getRealBigDecimal()
							.multiply(rs.getImagBigDecimal()))).scale(), RoundingMode.HALF_UP);

			BigDecimal imagSol = (this.getImagBigDecimal()
					.multiply(rs.getRealBigDecimal())
					.subtract(this.getRealBigDecimal()
							.multiply(rs.getImagBigDecimal()))).divide(rs.getRealBigDecimal()
					.multiply(rs.getImagBigDecimal())
					.add(rs.getImagBigDecimal()
							.multiply(rs.getImagBigDecimal())), RoundingMode.HALF_UP);
			return new BigComplex(realSol, imagSol);
		}
	}

	@Override
	public BigComplex negate() {
		return NEGATIVE_ONE.multiply(this);
	}

	@Override
	public BigComplex reciprocate() {
		return ONE.divide(this);
	}

	@Override
	public BigComplex mod(final Complex ab) {
		return this.add(ab.multiply(((this.divide(ab)).negate()))
				.ceil());
	}

	@Override
	public double getAbsDouble() {
		return this.getAbsBigDecimal()
				.doubleValue();
	}

	@Override
	public BigDecimal getAbsBigDecimal() {
		BigDecimal rSquare = this.getRealBigDecimal()
				.pow(2)
				.add(getImagBigDecimal().pow(2));
		return rSquare.sqrt(new MathContext(100));
	}

	@Override
	public double getAngleDouble() {
		return this.getAngleBigDecimal()
				.doubleValue();
	}

	@Override
	public BigDecimal getAngleBigDecimal() {
		return BigMath.atan2(this.imagPart, this.realPart, 100);
	}

	@Override
	public BigComplex compareTo(final Complex ab) {
		return this.subtract(ab)
				.sgn();
	}

	@Override
	public BigComplex root(final double r) {
		return this.root(new BigDecimal(String.valueOf(r)));
	}

	@Override
	public BigComplex root(final BigDecimal r) {
		BigDecimal rootAngle = this.getAngleBigDecimal()
				.divide(r, RoundingMode.HALF_UP);
		BigDecimal rootRad = BigMath.pow(this.getAbsBigDecimal(), BigDecimal.ONE.divide(r, RoundingMode.HALF_UP), 100);
		return new BigComplex(rootRad.multiply(BigMath.cos(rootAngle, 1000)),
				rootRad.multiply(BigMath.sin(rootAngle, 1000)));
	}

	@Override
	public BigComplex sqrt() {
		return this.root(BigDecimal.valueOf(2));
	}

	@Override
	public BigComplex cbrt() {
		return this.root(BigDecimal.valueOf(3));
	}

	@Override
	public BigComplex pow(final BigDecimal r) {
		if (this.imagPart.stripTrailingZeros()
				.equals(BigDecimal.ZERO)) {
			return new BigComplex(BigMath.pow(this.realPart, r, 50), BigDecimal.ZERO);
		}
		BigDecimal rootAngle = this.getAngleBigDecimal()
				.multiply(r);
		BigDecimal rootRad = BigMath.pow(this.getAbsBigDecimal(), BigDecimal.ONE.divide(r, RoundingMode.HALF_UP), 100);
		return new BigComplex(rootRad.multiply(BigMath.cos(rootAngle, 1000)),
				rootRad.multiply(BigMath.sin(rootAngle, 1000)));
	}

	@Override
	public BigComplex pow(final double k) {
		return this.pow(new BigDecimal(String.valueOf(k)));
	}

	@Override
	public BigComplex exp() {
		return new BigComplex(BigMath.exp(this.realPart, 100)
				.multiply(BigMath.cos(this.imagPart, 100)), BigMath.exp(this.realPart, 100)
				.multiply(BigMath.sin(this.imagPart, 100)));
	}

	@Override
	public BigComplex ln() {
		return realValueOf(BigMath.ln(this.getAbsBigDecimal(), 100)
				.add(this.getAngleBigDecimal()));
	}

	@Override
	public BigComplex pow(final Complex z) {
		BigComplex a = this.pow(z.getRealDouble());
		BigComplex b = this.pow(z.getImagDouble());
		BigComplex c = (b.ln()
				.cos()).add(b.ln()
				.sin());
		return a.multiply(c);
	}

	@Override
	public BigComplex W() {
		return null;
	}

	@Override
	public BigComplex sin() {
		return new BigComplex(BigMath.sin(this.realPart, 100)
				.multiply(BigMath.cosh(this.imagPart, 100)), BigMath.cos(this.realPart, 100)
				.multiply(BigMath.sinh(this.imagPart, 100)));
	}

	@Override
	public BigComplex cos() {
		return new BigComplex(BigMath.cos(this.realPart, 100)
				.multiply(BigMath.cosh(this.imagPart, 100)), BigMath.sin(this.realPart, 100)
				.multiply(BigMath.sinh(this.imagPart, 100)));
	}

	@Override
	public BigComplex tan() {
		return this.sin()
				.divide(this.cos());
	}

	@Override
	public BigComplex csc() {
		return this.sin()
				.reciprocate();
	}

	@Override
	public BigComplex sec() {
		return this.cos()
				.reciprocate();
	}

	@Override
	public BigComplex cot() {
		return this.cos()
				.divide(this.sin());
	}

	@Override
	public BigComplex sinh() {
		return new BigComplex(BigMath.sinh(this.realPart, 100)
				.multiply(BigMath.cos(this.imagPart, 100)), BigMath.sin(this.imagPart, 100)
				.multiply(BigMath.cosh(this.realPart, 100)));
	}

	@Override
	public BigComplex cosh() {
		return new BigComplex(BigMath.cosh(this.realPart, 100)
				.multiply(BigMath.cos(this.imagPart, 100)), BigMath.sin(this.imagPart, 100)
				.multiply(BigMath.sinh(this.realPart, 100)));
	}

	@Override
	public BigComplex tanh() {
		return this.sin()
				.divide(this.cosh());
	}

	@Override
	public BigComplex asin() {
		return (NEGATIVE_I.multiply(((I.multiply(this)).add((ONE.subtract(this.pow(2))).sqrt())).ln()));
	}

	@Override
	public BigComplex acos() {
		return (NEGATIVE_I.multiply((this.add((this.pow(2)
				.subtract(ONE)).sqrt())).ln()));
	}

	@Override
	public BigComplex atan() {
		if (this.equals(I.tan())) {
			return I;
		} else if (this.equals(NEGATIVE_I.tan())) {
			return NEGATIVE_I;
		} else if (this.equals(I) || this.equals(NEGATIVE_I)) {
			throw new ArithmeticException("Infinity or NAN");
		} else if (this.equals(ONE)) {
			return realValueOf(BigMath.PI.divide(BigDecimal.valueOf(4), RoundingMode.UNNECESSARY));
		} else if (this.equals(NEGATIVE_ONE)) {
			return realValueOf(BigMath.PI.divide(BigDecimal.valueOf(4), RoundingMode.UNNECESSARY)).negate();
		} else if (this.equals(ZERO)) {
			return ZERO;
		} else {
			return ((I.divide(realValueOf(BigDecimal.valueOf(2)))).multiply(
					((I.add(this)).divide(I.subtract(this))).ln()));
		}
	}

	@Override
	public BigComplex acot() {
		return (this.reciprocate()).atan();
	}

	@Override
	public BigComplex asec() {
		return (this.reciprocate()).acos();
	}

	@Override
	public BigComplex acsc() {
		return (this.reciprocate()).asin();
	}

	@Override
	public BigComplex asinh() {
		return (this.add((this.pow(2)
				.add(ONE)).sqrt())).ln();
	}

	@Override
	public BigComplex acosh() {
		return (this.add((this.pow(2)
				.subtract(ONE)).sqrt())).ln();
	}

	@Override
	public BigComplex atanh() {
		return ((this.add(ONE)).divide(ONE.subtract(this))).ln()
				.divide(realValueOf(BigDecimal.valueOf(2)));
	}

	@Override
	public BigComplex acsch() {
		return (this.reciprocate()).asinh();
	}

	@Override
	public BigComplex asech() {
		return (this.reciprocate()).acosh();
	}

	@Override
	public BigComplex acoth() {
		return (this.reciprocate()).atanh();
	}

	@Override
	public BigComplex floor() {
		return new BigComplex(this.getRealBigDecimal()
				.setScale(0, RoundingMode.FLOOR), this.getImagBigDecimal()
				.setScale(0, RoundingMode.FLOOR));
	}

	@Override
	public BigComplex ceil() {
		return new BigComplex(this.getRealBigDecimal()
				.setScale(0, RoundingMode.CEILING), this.getImagBigDecimal()
				.setScale(0, RoundingMode.CEILING));
	}

	@Override
	public BigComplex round() {
		return new BigComplex(this.getRealBigDecimal()
				.setScale(0, RoundingMode.HALF_UP), this.getImagBigDecimal()
				.setScale(0, RoundingMode.HALF_UP));
	}

	@Override
	public BigComplex sgn() {
		return new BigComplex(BigMath.cos(this.getAngleBigDecimal(), 100), BigMath.sin(this.getAngleBigDecimal(), 100));
	}
}
