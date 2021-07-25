package io.github.javacodingprodigy.mathstuff2;

import static io.github.javacodingprodigy.mathstuff2.numberstuff.Approx.approx;

public class Complex {
	private double realPart;
	private double imagPart;

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
		Complex z = new Complex(-1, 0);
		System.out.println(z.root(2)
				.toString());
	}

	public String toString() {
		return (this.realPart == 0 ? "" : this.realPart + " ") + (this.imagPart == 0 ? "" :
				Math.abs(imagPart) != 1 ? (String.format("%+f", this.imagPart) + "i") :
						(imagPart == -1 ? " - i" : " + i")) + (this.imagPart == 0 && this.realPart == 0 ? "0" : "");
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
		double realSol = (this.realPart * rs.realPart + this.imagPart * rs.imagPart) / (rs.realPart * rs.realPart
				+ rs.imagPart * rs.imagPart);
		double imagSol = (this.imagPart * rs.realPart - this.realPart * rs.imagPart) / (rs.realPart * rs.imagPart
				+ rs.imagPart * rs.imagPart);
		return new Complex(realSol, imagSol);
	}

	public double getAbs() {
		return Math.sqrt(this.realPart * this.realPart + this.imagPart * this.imagPart);
	}

	public double getAngle() {
		return Math.atan2(this.imagPart, this.realPart);
	}

	public Complex realValueOf(double z) {
		return new Complex(z, 0);
	}

	public Complex imagValueOf(double z) {
		return new Complex(0, z);
	}

	public Complex root(double r) {
		double rootAngle = this.getAngle() / r;
		double rootRad = Math.pow(this.getAbs(), 1.0 / r);
		return approx(new Complex(this.getAbs() * Math.cos(rootAngle), this.getAbs() * Math.sin(rootAngle)));
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

	public Complex sin(Complex ab) {
		return approx(
				new Complex(Math.sin(ab.realPart) * Math.cosh(ab.imagPart), Math.cos(realPart) * Math.sinh(imagPart)));
	}

	public Complex cos(Complex ab) {
		return approx(new Complex(Math.cos(ab.realPart) * Math.cosh(ab.imagPart),
				-Math.sin(ab.realPart) * Math.sinh(ab.imagPart)));
	}

	public Complex tan(Complex ab) {
		return approx(sin(ab).divide(cos(ab)));
	}

	public Complex sinh(Complex ab) {
		return approx(new Complex(Math.sinh(ab.realPart) * Math.cos(ab.imagPart),
				Math.cosh(ab.realPart) * Math.sin(ab.imagPart)));
	}

	public Complex cosh(Complex ab) {
		return approx(new Complex(Math.cosh(ab.realPart) * Math.cos(ab.imagPart),
				Math.sinh(ab.realPart) * Math.sin(ab.imagPart)));

	}

	public Complex tanh(Complex ab) {
		return approx(sinh(ab).divide(cosh(ab)));
	}

	public Complex floor(Complex ab) {
		return new Complex(Math.floor(ab.realPart), Math.floor(ab.imagPart));
	}

	public Complex ceil(Complex ab) {
		return new Complex(Math.ceil(ab.realPart), Math.ceil(ab.imagPart));
	}

	public Complex round(Complex ab) {
		return new Complex(Math.round(ab.realPart), Math.round(ab.imagPart));
	}
}
