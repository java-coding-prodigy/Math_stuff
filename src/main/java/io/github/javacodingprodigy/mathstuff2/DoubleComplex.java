package io.github.javacodingprodigy.mathstuff2;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.javacodingprodigy.mathstuff2.numberstuff.Approx.approx;

public class DoubleComplex implements Complex {

	private final double realPart;
	private final double imagPart;
	public static final DoubleComplex ONE = realValueOf(1);
	public static final DoubleComplex ZERO = realValueOf(0);
	public static final DoubleComplex NEGATIVE_ONE = realValueOf(-1);
	public static final DoubleComplex I = imagValueOf(1);
	public static final DoubleComplex NEGATIVE_I = imagValueOf(-1);
	public static final DoubleComplex POSITIVE_INFINITY = realValueOf(Double.POSITIVE_INFINITY);
	public static final DoubleComplex NEGATIVE_INFINITY = realValueOf(Double.NEGATIVE_INFINITY);
	public static final DoubleComplex POSITIVE_INFINITY_I = imagValueOf(Double.POSITIVE_INFINITY);
	public static final DoubleComplex NEGATIVE_INFINITY_I = imagValueOf(Double.NEGATIVE_INFINITY);
	public static final DoubleComplex NAN = realValueOf(Double.NaN);

	public DoubleComplex(double real, double imag) {
		realPart = real;
		imagPart = imag;
	}

	public DoubleComplex(DoubleComplex x) {
		realPart = x.getRealDouble();
		imagPart = x.getImagDouble();
	}

	@Override
	public double getImagDouble() {
		return this.imagPart;
	}

	@Override
	public double getRealDouble() {
		return this.realPart;
	}

	@Override
	public BigDecimal getRealBigDecimal() {
		return BigDecimal.valueOf(this.realPart);
	}

	@Override
	public BigDecimal getImagBigDecimal() {
		return BigDecimal.valueOf(this.imagPart);
	}

	public static void main(String[] args) {
	}

	public BigComplex toBigComplex() {
		return new BigComplex(this.getRealBigDecimal(), this.getImagBigDecimal());
	}

	@Override
	public String toString() {
		String realStr;
		String imagStr;
		if (this.realPart == 0 && this.imagPart == 0) {
			realStr = "0";
		} else if (this.realPart == 0) {
			realStr = "";
		} else if (this.realPart == Double.POSITIVE_INFINITY) {
			realStr = "∞";
		} else if (this.realPart == Double.NEGATIVE_INFINITY) {
			realStr = "-∞";
		} else {
			realStr = this.realPart == Math.floor(this.realPart) ? String.format("%.0f", this.realPart) :
					String.valueOf(this.realPart);
		}
		if (this.imagPart == 0) {
			imagStr = "";
		} else if (this.imagPart == Double.POSITIVE_INFINITY) {
			imagStr = "∞i";
		} else if (this.imagPart == Double.NEGATIVE_INFINITY) {
			imagStr = "-∞i";
		} else if (Math.abs(imagPart) == 1) {
			imagStr = imagPart == 1 ? "i" : "-i";
		} else {
			imagStr =
					(this.imagPart > 0 && this.realPart != 0 ? "+" : "") + (this.imagPart == Math.floor(this.imagPart) ?
							String.format("%.0f", this.imagPart) + "i" : (this.imagPart) + "i");
		}
		return realStr + imagStr;
	}

	private static final Pattern DoubleComplex_PATTERN =
			Pattern.compile("([+\\-]?\\d*(\\.\\d*)?)?\\s*(([+\\-])?\\s*?(\\d*(\\.\\d*)?)?(i))?");

	public static DoubleComplex parseLine(String line) {
		Matcher matcher = DoubleComplex_PATTERN.matcher(line);
		double real;
		double imaginary;
		char sign;
		if (!matcher.matches()) {
			throw new InputMismatchException();
		}
		try {
			real = Double.parseDouble(matcher.group(1));
		} catch (NumberFormatException e) {
			if (line.equals("-i")) {
				return NEGATIVE_I;
			}
			if (matcher.group(1)
					.contains(".") || matcher.group(1)
					.contains("-") || matcher.group(1)
					.contains("+")) {
				throw new InputMismatchException();
			}
			real = 0;
		}
		try {
			imaginary = Double.parseDouble(matcher.group(5));
		} catch (NumberFormatException | NullPointerException f) {
			if (matcher.group(4) == null && matcher.group(1) != null && matcher.group(5) != null && !matcher.group(7)
					.equals("i")) {
				throw new InputMismatchException();
			}
			imaginary = matcher.group(7) == null ? 0 : 1;
		}
		try {
			sign = matcher.group(4)
					.charAt(0);
		} catch (Exception g) {
			sign = ' ';
		}
		if (sign == '-') {
			imaginary *= -1;
		}
		return new DoubleComplex(real, imaginary);
	}

	@Override
	public DoubleComplex add(Complex cd) {
		return new DoubleComplex(this.getRealDouble() + cd.getRealDouble(), this.getImagDouble() + cd.getImagDouble());
	}

	@Override
	public DoubleComplex subtract(Complex cd) {
		DoubleComplex x = new DoubleComplex(-cd.getRealDouble(), -cd.getImagDouble());
		return this.add(x);
	}

	@Override
	public DoubleComplex multiply(Complex rs) {
		return new DoubleComplex(this.realPart * rs.getRealDouble() - this.imagPart * rs.getImagDouble(),
				this.realPart * rs.getImagDouble() + this.imagPart * rs.getRealDouble());
	}

	@Override
	public DoubleComplex divide(Complex rs) {
		if (this.getRealDouble() == 0 && rs.getRealDouble() == 0) {
			return imagValueOf(this.imagPart / rs.getImagDouble());
		} else if (this.imagPart == 0 && rs.getImagDouble() == 0) {
			return realValueOf(this.getRealDouble() / rs.getRealDouble());
		} else if (rs.getImagDouble() == 0) {
			return new DoubleComplex(this.getRealDouble() / rs.getRealDouble(), this.imagPart / rs.getRealDouble());
		} else if (rs.getRealDouble() == 0) {
			return new DoubleComplex(this.getRealDouble() / rs.getImagDouble(),
					this.imagPart / rs.getImagDouble()).multiply(NEGATIVE_I);
		} else if (rs == (ZERO)) {
			if (this == (ZERO)) {
				return NAN;
			} else if (this.getRealDouble() == 0) {
				return this.imagPart > 0 ? POSITIVE_INFINITY_I : NEGATIVE_INFINITY_I;
			} else if (this.imagPart == 0) {
				return this.getRealDouble() > 0 ? POSITIVE_INFINITY : NEGATIVE_INFINITY;
			} else {
				return NAN;
			}
		} else {
			double realSol = (this.getRealDouble() * rs.getRealDouble() + this.imagPart * rs.getImagDouble()) / (
					rs.getRealDouble() * rs.getRealDouble() + rs.getImagDouble() * rs.getImagDouble());

			double imagSol = (this.imagPart * rs.getRealDouble() - this.getRealDouble() * rs.getImagDouble()) / (
					rs.getRealDouble() * rs.getImagDouble() + rs.getImagDouble() * rs.getImagDouble());
			return new DoubleComplex(realSol, imagSol);
		}
	}

	@Override
	public DoubleComplex negate() {
		return NEGATIVE_ONE.multiply(this);
	}

	@Override
	public DoubleComplex reciprocate() {
		return ONE.divide(this);
	}

	@Override
	public DoubleComplex mod(Complex ab) {
		return this.add(ab.multiply(((this.divide(ab)).negate()))
				.ceil());
	}

	@Override
	public double getAbsDouble() {
		return Math.sqrt(this.realPart * this.realPart + this.imagPart * this.imagPart);
	}

	@Override
	public BigDecimal getAbsBigDecimal() {
		return BigDecimal.valueOf(this.getAbsDouble());
	}

	public static DoubleComplex realValueOf(double z) {
		return new DoubleComplex(z, 0);
	}

	public static DoubleComplex imagValueOf(double z) {
		return new DoubleComplex(z, 0);
	}

	public static DoubleComplex convertFromPolar(double radius, double theta) {
		return new DoubleComplex(radius * Math.cos(theta), radius * Math.sin(theta));
	}

	@Override
	public int hashCode() {
		return Objects.hash(imagPart, realPart);
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof Complex ab)) {
			return false;
		}
		return (approx(this.getRealDouble() - ab.getRealDouble()) == 0 && approx(this.imagPart - ab.getImagDouble()) == 0) || (
				this.getRealDouble() == ab.getRealDouble() && this.imagPart == ab.getImagDouble());
	}

	@Override
	public DoubleComplex compareTo(final Complex ab) {
		return this.subtract(ab)
				.sgn();
	}

	@Override
	public double getAngleDouble() {
		return Math.atan2(this.imagPart, this.getRealDouble());
	}

	@Override
	public BigDecimal getAngleBigDecimal() {
		return BigDecimal.valueOf(this.getAngleDouble());
	}

	@Override
	public DoubleComplex root(double r) {
		double rootAngle = this.getAngleDouble() / r;
		double rootRad = Math.pow(this.getAbsDouble(), 1.0 / r);
		return (new DoubleComplex(rootRad * Math.cos(rootAngle), rootRad * Math.sin(rootAngle)));
	}

	@Override
	public DoubleComplex root(BigDecimal r) {
		return this.root(r.doubleValue());
	}

	@Override
	public DoubleComplex sqrt() {
		return this.root(2);
	}

	@Override
	public DoubleComplex cbrt() {
		return this.root(3);
	}

	@Override
	public DoubleComplex pow(double k) {
		if (k < 0) {
			if (this.getImagDouble() == 0) {
				return realValueOf(Math.pow(this.getRealDouble(), k));
			}
		}

		double rootAngle = this.getAngleDouble() * k;
		double rootRad = Math.pow(this.getAbsDouble(), k);
		return (new DoubleComplex(rootRad * Math.cos(rootAngle), rootRad * Math.sin(rootAngle)));
	}

	@Override
	public DoubleComplex pow(final BigDecimal k) {
		return this.pow(k.doubleValue());
	}

	public static DoubleComplex[] rootsOf1(int n) {
		DoubleComplex[] roots = new DoubleComplex[Math.abs(n)];
		switch ((int) Math.signum(n)) {
			case -1:
				n = Math.abs(n);
				for (int i = 0; i < roots.length; i++) {
					roots[i] = (new DoubleComplex(Math.cos(2 * i * Math.PI / n),
							Math.sin(2 * i * Math.PI / n))).reciprocate();
				}
				break;

			case 0:
				throw new ArithmeticException("/ by 0");
			case 1:
				for (int i = 0; i < roots.length; i++) {
					roots[i] = (new DoubleComplex(Math.cos(2 * i * Math.PI / n), Math.sin(2 * i * Math.PI / n)));
				}
				break;
			default:
				throw new ArithmeticException("Bad value from signum");
		}
		return roots;
	}

	@Override
	public DoubleComplex exp() {
		return new DoubleComplex(Math.exp(this.getRealDouble()) * Math.cos(this.getImagDouble()),
				Math.exp(this.getRealDouble()) * Math.sin(this.getImagDouble()));
	}

	@Override
	public DoubleComplex ln() {
		return new DoubleComplex(Math.log(this.getAbsDouble()), (this.getAngleDouble()));
	}

	@Override
	public Complex pow(Complex z) {
		DoubleComplex a = this.pow(z.getRealDouble());
		DoubleComplex b = this.pow(z.getImagDouble());
		DoubleComplex c = (b.ln()
				.cos()).add(b.ln()
				.sin());
		return a.multiply(c);
	}

	@Override
	public DoubleComplex W() {
		DoubleComplex b;
		if (this.getRealDouble() < 0) {
			b = this.multiply((ONE.subtract((realValueOf(2).multiply(realValueOf(Math.E).multiply(this)
					.add(ONE))).sqrt())).exp());
		} else if (this.getRealDouble() < Math.E) {
			b = this.divide(realValueOf(Math.E));
		} else {
			b = this.ln();
		}
		while (!b.multiply(b.exp())
				.equals(this)) {
			System.out.println(b);
			b = b.subtract((b.multiply(b.exp())
					.subtract(this)).divide((b.add(ONE)).multiply(this.exp())));

		}
		return b;
	}

	@Override
	public DoubleComplex sin() {
		return (new DoubleComplex(Math.sin(this.getRealDouble()) * Math.cosh(this.getImagDouble()),
				Math.cos(this.getRealDouble()) * Math.sinh(this.getImagDouble())));
	}

	@Override
	public DoubleComplex cos() {
		return (new DoubleComplex(Math.cos(this.getRealDouble()) * Math.cosh(this.getImagDouble()),
				-Math.sin(this.getRealDouble()) * Math.sinh(this.getImagDouble())));
	}

	@Override
	public DoubleComplex tan() {
		return this.sin()
				.divide(this.cos());
	}

	@Override
	public DoubleComplex csc() {
		return this.sin().reciprocate();
	}

	@Override
	public DoubleComplex sec() {
		return this.cos().reciprocate();
	}

	@Override
	public DoubleComplex cot() {
		return this.tan().reciprocate();
	}

	@Override
	public DoubleComplex sinh() {
		return (new DoubleComplex(Math.sinh(this.getRealDouble()) * Math.cos(this.getImagDouble()),
				Math.cosh(this.getRealDouble()) * Math.sin(this.getImagDouble())));
	}

	@Override
	public DoubleComplex cosh() {
		return (new DoubleComplex(Math.cosh(this.getRealDouble()) * Math.cos(this.getImagDouble()),
				Math.sinh(this.getRealDouble()) * Math.sin(this.getImagDouble())));

	}

	@Override
	public DoubleComplex tanh() {
		return this.sinh().divide(this.cosh());
	}

	@Override
	public DoubleComplex asin() {
		if (this.equals(POSITIVE_INFINITY) || this.equals(NEGATIVE_INFINITY_I)) {
			return NEGATIVE_INFINITY_I;
		} else if (this.equals(NEGATIVE_INFINITY) || this.equals(POSITIVE_INFINITY_I)) {
			return POSITIVE_INFINITY_I;
		} else {
			return NEGATIVE_I.multiply(((I.multiply(this)).add((ONE.subtract(this.pow(2))).sqrt())).ln());
		}
	}

	@Override
	public DoubleComplex acos() {
		if (this.equals(POSITIVE_INFINITY) || this.equals(NEGATIVE_INFINITY_I)) {
			return POSITIVE_INFINITY_I;
		} else if (this.equals(NEGATIVE_INFINITY) || this.equals(POSITIVE_INFINITY_I)) {
			return NEGATIVE_INFINITY_I;
		} else {
			return NEGATIVE_I.multiply((this.add((this.pow(2)
					.subtract(ONE)).sqrt())).ln());
		}
	}

	@Override
	public DoubleComplex atan() {
		if (this.equals(I.tan())) {
			return I;
		} else if (this.equals(NEGATIVE_I.tan())) {
			return NEGATIVE_I;
		} else if (this.equals(I)) {
			return POSITIVE_INFINITY_I;
		} else if (this.equals(NEGATIVE_I)) {
			return NEGATIVE_INFINITY_I;
		} else if (this.equals(ONE)) {
			return realValueOf(Math.PI / 4);
		} else if (this.equals(NEGATIVE_ONE)) {
			return realValueOf(-Math.PI / 4);
		} else if (this.equals(ZERO)) {
			return ZERO;
		} else if (this.equals(POSITIVE_INFINITY) || this.equals(POSITIVE_INFINITY_I)) {
			return realValueOf(Math.PI / 2);
		} else if (this.equals(NEGATIVE_INFINITY) || this.equals(NEGATIVE_INFINITY_I)) {
			return realValueOf(-Math.PI / 2);
		} else {
			return (I.divide(realValueOf(2))).multiply(((I.add(this)).divide(I.subtract(this))).ln());
		}
	}

	@Override
	public DoubleComplex acot() {
		return (this.reciprocate()).atan();
	}

	@Override
	public DoubleComplex asec() {
		return (this.reciprocate()).acos();
	}

	@Override
	public DoubleComplex acsc() {
		return (this.reciprocate()).asin();
	}

	@Override
	public DoubleComplex asinh() {
		if (this.equals(POSITIVE_INFINITY) || this.equals(POSITIVE_INFINITY_I)) {
			return POSITIVE_INFINITY;
		}
		if (this.equals(NEGATIVE_INFINITY) || this.equals(NEGATIVE_INFINITY_I)) {
			return NEGATIVE_INFINITY;
		} else {
			return (this.add((this.pow(2)
					.add(ONE)).sqrt())).ln();
		}
	}

	@Override
	public DoubleComplex acosh() {
		if (this.equals(POSITIVE_INFINITY) || this.equals(POSITIVE_INFINITY_I) || this.equals(NEGATIVE_INFINITY)
				|| this.equals(NEGATIVE_INFINITY_I)) {
			return POSITIVE_INFINITY;
		} else {
			return (this.add((this.pow(2)
					.subtract(ONE)).sqrt())).ln();
		}
	}

	@Override
	public DoubleComplex atanh() {
		if (this.equals(POSITIVE_INFINITY) || this.equals(NEGATIVE_INFINITY_I)) {
			return imagValueOf(-Math.PI / 2);
		} else if (this.equals(NEGATIVE_INFINITY) || this.equals(POSITIVE_INFINITY_I)) {
			return imagValueOf(Math.PI / 2);
		} else {
			return ((this.add(ONE)).divide(ONE.subtract(this))).ln()
					.divide(realValueOf(2));
		}
	}

	@Override
	public DoubleComplex acsch() {
		return (this.reciprocate()).asinh();
	}

	@Override
	public DoubleComplex asech() {
		return (this.reciprocate()).acosh();
	}

	@Override
	public DoubleComplex acoth() {
		return (this.reciprocate()).atanh();
	}

	@Override
	public DoubleComplex floor() {
		return new DoubleComplex(Math.floor(this.getRealDouble()), Math.floor(this.getImagDouble()));
	}

	@Override
	public DoubleComplex ceil() {
		return new DoubleComplex(Math.ceil(this.getRealDouble()), Math.ceil(this.getImagDouble()));
	}

	@Override
	public DoubleComplex round() {
		return new DoubleComplex(Math.round(this.getRealDouble()), Math.round(this.getImagDouble()));
	}

	@Override
	public DoubleComplex sgn() {
		return this.equals(ZERO) ? ZERO :
				new DoubleComplex(Math.cos(this.getAngleDouble()), Math.sin(this.getAngleDouble()));
	}
}
