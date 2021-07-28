package io.github.javacodingprodigy.mathstuff2;

import java.lang.*;

import static io.github.javacodingprodigy.mathstuff2.numberstuff.Approx.approx;

public class Complex {
	private double realPart;
	private double imagPart;
	public static final Complex ONE = realValueOf(1);
	public static final Complex ZERO = realValueOf(0);
	public static final Complex NEGATIVE_ONE = realValueOf(-1);
	public static final Complex I = imagValueOf(1);
	public static final Complex NEGATIVE_I = imagValueOf(-1);

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

	public void setImagPart(final double imagPart) {
		this.imagPart = imagPart;
	}

	public double getRealPart() {
		return realPart;
	}

	public void setRealPart(final double realPart) {
		this.realPart = realPart;
	}

	public static void main(String[] args) {
		Complex z = ZERO;
		System.out.println(acos(z).toString());
	}

	public String toString() {
		return (this.realPart == 0 ? "" : this.realPart + " ") + (this.imagPart == 0 ? "" :
				Math.abs(this.imagPart) != 1 ? (String.format("%+f", this.imagPart) + "i") :
						(this.imagPart == -1 ? " - i" : " + i")) + (this.imagPart == 0 && this.realPart == 0 ? "0" :
				"");
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

	public boolean equals(Complex ab) {
		return approx(this.realPart - ab.realPart) == 0 && approx(this.imagPart - ab.imagPart) == 0;
	}

	public Complex root(double r) {
		double rootAngle = this.getAngle() / r;
		double rootRad = Math.pow(this.getAbs(), 1.0 / r);
		return approx(new Complex(rootRad * Math.cos(rootAngle), rootRad * Math.sin(rootAngle)));
	}

	public Complex sqrt(Complex ab) {
		return ab.root(2);
	}

	public Complex cbrt(Complex ab) {
		return ab.root(3);
	}

	public Complex pow(int k) {
		Complex ans = new Complex(this);
		for (int i = 1; i < k; i++) {
			ans = ans.multiply(this);
		}
		return ans;
	}

	public Complex[] rootsOf1(int n) {
		Complex[] roots = new Complex[n];
		for (int i = 0; i < roots.length; i++) {
			roots[i] = approx(new Complex(Math.cos(2 * i * Math.PI / n), Math.sin(2 * i * Math.PI / n)));
		}
		return roots;
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
		Complex b = new Complex(x);
		for (int i = 0; i < 100; i++) {
			b = b.subtract((sin(b).subtract(x)).multiply(sec(b)));
		}
		return b;
	}

	public static Complex acos(Complex x) {
		Complex b = new Complex(x);
		for (int i = 0; i < 100; i++) {
			b = b.subtract((cos(b).subtract(x)).divide(negate(sin(b))));

		}
		return b;
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
