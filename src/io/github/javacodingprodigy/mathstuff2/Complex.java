package io.github.javacodingprodigy.mathstuff2;



import java.lang.*;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.javacodingprodigy.mathstuff2.numberstuff.Approx.approx;

public class Complex {
	private final double realPart;
	private final double imagPart;
	public static final Complex ONE = realValueOf(1);
	public static final Complex ZERO = realValueOf(0);
	public static final Complex NEGATIVE_ONE = realValueOf(-1);
	public static final Complex I = imagValueOf(1);
	public static final Complex NEGATIVE_I = imagValueOf(-1);
	public static final Complex POSITIVE_INFINITY = ONE.divide(ZERO);
	public static final Complex NEGATIVE_INFINITY = NEGATIVE_ONE.divide(ZERO);
	public static final Complex POSITIVE_INFINITY_I = I.divide(ZERO);
	public static final Complex NEGATIVE_INFINITY_I = NEGATIVE_I.divide(ZERO);
	public static final Complex NAN = ZERO.divide(ZERO);

	public Complex(double real, double imag) {
		realPart = real;
		imagPart = imag;
	}

	Complex(Complex x) {
		realPart = x.getRealPart();
		imagPart = x.getImagPart();
	}

	public double getImagPart() {
		return imagPart;
	}

	public double getRealPart() {
		return realPart;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println(parseLine(sc.nextLine()));
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
		} else {
			imagStr = (this.imagPart > 0 && this.realPart != 0 ? "+" : "") + (
					this.imagPart == Math.floor(this.imagPart) ? String.format("%.0f", this.imagPart) + "i" :
							(this.imagPart) + "i");
		}
		return realStr + imagStr;
	}
	private static final Pattern[] COMPLEX_PATTERNs = {Pattern.compile("([0-9]{1,13}\\.[0-9]*)?\\s?([+\\-])\\s?([0-9]{1,13}\\.[0-9]*?)i"),
 Pattern.compile("([0-9]{1,13})?\\s?([+\\-])\\s?([0-9]{1,13}\\.[0-9]*?)i"),
 Pattern.compile("([0-9]{1,13}\\.[0-9]*)?\\s?([+\\-])\\s?([0-9]{1,13}?)i"),
 Pattern.compile("([0-9]{1,13})?\\s?([+\\-])\\s?([0-9]{1,13}?)i")};

	public static Complex parseLine(String line) {
		Matcher matcher;
		for (Pattern pattern : COMPLEX_PATTERNs){
			matcher = pattern.matcher(line);
			if (matcher.matches())
				break;
		}

		double real = Double.parseDouble(matcher.group(1));


		char sign = matcher.group(2).charAt(0);
		double imaginary = Double.parseDouble(matcher.group(3));

		if (sign == '-') {
			imaginary *= -1;
		}

		return new Complex(real, imaginary);
	}

	public Complex add(Complex cd) {
		return new Complex(this.getRealPart() + cd.getRealPart(), this.getImagPart() + cd.getImagPart());
	}

	public Complex subtract(Complex cd) {
		Complex x = new Complex(-cd.getRealPart(), -cd.getImagPart());
		return this.add(x);
	}

	public Complex multiply(Complex rs) {
		return new Complex(this.realPart * rs.realPart - this.imagPart * rs.imagPart,
				this.realPart * rs.imagPart + this.imagPart * rs.realPart);
	}

	public Complex divide(Complex rs) {
		if (this.realPart == 0 && rs.realPart == 0) {
			return imagValueOf(this.imagPart / rs.imagPart);
		} else if (this.imagPart == 0 && rs.imagPart == 0) {
			return realValueOf(this.realPart / rs.realPart);
		} else if (rs.imagPart == 0) {
			return new Complex(this.realPart / rs.realPart, this.imagPart / rs.realPart);
		} else if (rs.realPart == 0) {
			return new Complex(this.realPart / rs.imagPart, this.imagPart / rs.imagPart).multiply(NEGATIVE_I);
		} else if (rs.equals(ZERO)) {
			if (this.equals(ZERO)) {
				return NAN;
			} else if (this.realPart == 0) {
				return this.imagPart > 0 ? POSITIVE_INFINITY_I : NEGATIVE_INFINITY_I;
			} else {
				return this.realPart > 0 ? POSITIVE_INFINITY : NEGATIVE_INFINITY;
			}
		} else {
			double realSol = (this.realPart * rs.realPart + this.imagPart * rs.imagPart) / (rs.realPart * rs.realPart
					+ rs.imagPart * rs.imagPart);
			realSol = Double.isNaN(realSol) ? 0 : realSol;
			double imagSol = (this.imagPart * rs.realPart - this.realPart * rs.imagPart) / (rs.realPart * rs.imagPart
					+ rs.imagPart * rs.imagPart);
			imagSol = Double.isNaN(imagSol) ? 0 : imagSol;
			return new Complex(realSol, imagSol);
		}
	}

	public static Complex negate(Complex z) {
		return NEGATIVE_ONE.multiply(z);
	}

	public static Complex reciprocate(Complex z) {
		return ONE.divide(z);
	}

	public Complex mod(Complex ab) {
		return this.add(ab.multiply(ceil(negate(this.divide(ab)))));
	}

	public double getAbs() {
		return Math.sqrt(this.realPart * this.realPart + this.imagPart * this.imagPart);
	}

	public double getAngle() {
		return Math.atan2(this.imagPart, this.realPart);
	}

	public static Complex realValueOf(double z) {
		return new Complex(z, 0);
	}

	public static Complex imagValueOf(double z) {
		return new Complex(0, z);
	}

	public Complex convertFromPolar(double radius, double theta) {
		return new Complex(radius * Math.cos(theta), radius * Math.sin(theta));
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
		return (approx(this.realPart - ab.realPart) == 0 && approx(this.imagPart - ab.imagPart) == 0) || (
				this.realPart == ab.realPart && this.imagPart == ab.imagPart);
	}

	public Complex root(double r) {
		double rootAngle = this.getAngle() / r;
		double rootRad = Math.pow(this.getAbs(), 1.0 / r);
		return approx(new Complex(rootRad * Math.cos(rootAngle), rootRad * Math.sin(rootAngle)));
	}

	public static Complex sqrt(Complex ab) {
		return ab.root(2);
	}

	public static Complex cbrt(Complex ab) {
		return ab.root(3);
	}

	public Complex pow(double k) {
		double rootAngle = this.getAngle() * k;
		double rootRad = Math.pow(this.getAbs(), k);
		return approx(new Complex(rootRad * Math.cos(rootAngle), rootRad * Math.sin(rootAngle)));
	}

	public static Complex[] rootsOf1(int n) {
		Complex[] roots = new Complex[n];
		for (int i = 0; i < roots.length; i++) {
			roots[i] = approx(new Complex(Math.cos(2 * i * Math.PI / n), Math.sin(2 * i * Math.PI / n)));
		}
		return roots;
	}

	public static Complex exp(Complex z) {
		return new Complex(Math.exp(z.realPart)*Math.cos(z.imagPart), Math.exp(z.realPart)*Math.sin(z.imagPart));
	}

	public static Complex ln(Complex z) {
		return new Complex(Math.log(z.getAbs()), (z.getAngle()));
	}
	public Complex pow(Complex z){
		Complex a = this.pow(z.realPart);
		Complex b = this.pow(z.imagPart);
		Complex c = cos(ln(b)).add(sin(ln(b)));
		return a.multiply(c);
	}
	public static Complex W(Complex x){
		Complex b;
		if (x.realPart < 0)
			b = x.multiply(exp(ONE.subtract(sqrt(realValueOf(2).multiply(realValueOf(Math.E).multiply(x).add(ONE))))));
		else if (x.realPart < Math.E)
			b = x.divide(realValueOf(Math.E));
		else
			b = ln(x);
		while(!b.multiply(exp(b)).equals(x)){
			System.out.println(b);
			b = b.subtract((b.multiply(exp(b)).subtract(x)).divide((b.add(ONE)).multiply(exp(x))));

		}
		return b;
	}

	public static Complex sin(Complex ab) {
		return approx(new Complex(Math.sin(ab.realPart) * Math.cosh(ab.imagPart),
				Math.cos(ab.realPart) * Math.sinh(ab.imagPart)));
	}

	public static Complex cos(Complex ab) {
		return approx(new Complex(Math.cos(ab.realPart) * Math.cosh(ab.imagPart),
				-Math.sin(ab.realPart) * Math.sinh(ab.imagPart)));
	}

	public static Complex tan(Complex ab) {
		return approx(sin(ab).divide(cos(ab)));
	}

	public static Complex csc(Complex ab) {
		return approx(reciprocate(sin(ab)));
	}

	public static Complex sec(Complex ab) {
		return approx(reciprocate(cos(ab)));
	}

	public static Complex cot(Complex ab) {
		return approx(reciprocate(tan(ab)));
	}

	public static Complex sinh(Complex ab) {
		return approx(new Complex(Math.sinh(ab.realPart) * Math.cos(ab.imagPart),
				Math.cosh(ab.realPart) * Math.sin(ab.imagPart)));
	}

	public static Complex cosh(Complex ab) {
		return approx(new Complex(Math.cosh(ab.realPart) * Math.cos(ab.imagPart),
				Math.sinh(ab.realPart) * Math.sin(ab.imagPart)));

	}

	public static Complex tanh(Complex ab) {
		return approx(sinh(ab).divide(cosh(ab)));
	}

	public static Complex asin(Complex x) {
		if (x.equals(POSITIVE_INFINITY) || x.equals(NEGATIVE_INFINITY_I)) {
			return NEGATIVE_INFINITY_I;
		} else if (x.equals(NEGATIVE_INFINITY) || x.equals(POSITIVE_INFINITY_I)) {
			return POSITIVE_INFINITY_I;
		} else {
			return approx(NEGATIVE_I.multiply(ln((I.multiply(x)).add(sqrt(ONE.subtract(x.pow(2)))))));
		}
	}

	public static Complex acos(Complex x) {
		if (x.equals(POSITIVE_INFINITY) || x.equals(NEGATIVE_INFINITY_I)) {
			return POSITIVE_INFINITY_I;
		} else if (x.equals(NEGATIVE_INFINITY) || x.equals(POSITIVE_INFINITY_I)) {
			return NEGATIVE_INFINITY_I;
		} else {
			return approx(NEGATIVE_I.multiply(ln(x.add(sqrt(x.pow(2)
					.subtract(ONE))))));
		}
	}

	public static Complex atan(Complex z) {
		if (z.equals(tan(I))) {
			return I;
		} else if (z.equals(tan(NEGATIVE_I))) {
			return NEGATIVE_I;
		} else if (z.equals(I)) {
			return POSITIVE_INFINITY_I;
		} else if (z.equals(NEGATIVE_I)) {
			return NEGATIVE_INFINITY_I;
		} else if (z.equals(ONE)) {
			return realValueOf(Math.PI / 4);
		} else if (z.equals(NEGATIVE_ONE)) {
			return realValueOf(-Math.PI / 4);
		} else if (z.equals(ZERO)) {
			return ZERO;
		} else if (z.equals(POSITIVE_INFINITY) || z.equals(POSITIVE_INFINITY_I)) {
			return realValueOf(Math.PI / 2);
		} else if (z.equals(NEGATIVE_INFINITY) || z.equals(NEGATIVE_INFINITY_I)) {
			return realValueOf(-Math.PI / 2);
		} else {
			return approx((I.divide(realValueOf(2))).multiply(ln((I.add(z)).divide(I.subtract(z)))));
		}
	}

	public static Complex acot(Complex z) {
		return atan(reciprocate(z));
	}

	public static Complex asec(Complex z) {
		return acos(reciprocate(z));
	}

	public static Complex acsc(Complex z) {
		return asin(reciprocate(z));
	}

	public static Complex asinh(Complex z) {
		if(z.equals(POSITIVE_INFINITY) || z.equals(POSITIVE_INFINITY_I))
		return POSITIVE_INFINITY;
		if(z.equals(NEGATIVE_INFINITY) || z.equals(NEGATIVE_INFINITY_I))
		return NEGATIVE_INFINITY;
			else
			return ln(z.add(sqrt(z.pow(2)
				.add(ONE))));
	}

	public static Complex acosh(Complex x) {
		if(x.equals(POSITIVE_INFINITY) || x.equals(POSITIVE_INFINITY_I) || x.equals(NEGATIVE_INFINITY) || x.equals(NEGATIVE_INFINITY_I))
		return POSITIVE_INFINITY;
		else
			return ln(x.add(sqrt(x.pow(2)
				.subtract(ONE))));
	}

	public static Complex atanh(Complex z) {
		if(z.equals(POSITIVE_INFINITY) || z.equals(NEGATIVE_INFINITY_I))
			return imagValueOf(-Math.PI/2);
		else if(z.equals(NEGATIVE_INFINITY) || z.equals(POSITIVE_INFINITY_I))
			return imagValueOf(Math.PI/2);
		else
		return ln((z.add(ONE)).divide(ONE.subtract(z))).divide(realValueOf(2));
	}

	public static Complex acsch(Complex z) {
		return asinh(reciprocate(z));
	}

	public static Complex asech(Complex z) {
		return acosh(reciprocate(z));
	}

	public static Complex acoth(Complex z) {
		return atanh(reciprocate(z));
	}

	public static Complex floor(Complex ab) {
		return new Complex(Math.floor(ab.realPart), Math.floor(ab.imagPart));
	}

	public static Complex ceil(Complex ab) {
		return new Complex(Math.ceil(ab.realPart), Math.ceil(ab.imagPart));
	}

	public static Complex round(Complex ab) {
		return new Complex(Math.round(ab.realPart), Math.round(ab.imagPart));
	}

	public static Complex sgn(Complex ab) {
		return ab.equals(ZERO) ? ZERO : new Complex(Math.cos(ab.getAngle()), Math.sin(ab.getAngle()));
	}

}
