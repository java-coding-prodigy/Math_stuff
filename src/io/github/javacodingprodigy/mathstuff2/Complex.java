package io.github.javacodingprodigy.mathstuff2;

import static java.lang.Math.*;

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

	public String toString() {
		return (this.realPart == 0 ? "" : this.realPart + " ") + (this.imagPart == 0 ? "" :
				abs(imagPart) != 1 ? (String.format("%+f", this.imagPart) + "i") :
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
		double realSol = (this.realPart * rs.realPart + this.imagPart * rs.imagPart) / (rs.realPart * rs.imagPart
				+ rs.imagPart * rs.imagPart);
		double imagSol = (this.imagPart * rs.realPart - this.realPart * rs.imagPart) / (rs.realPart * rs.imagPart
				+ rs.imagPart * rs.imagPart);
		return new Complex(realSol, imagSol);
	}
	public double radius(){
		return Math.sqrt(this.realPart*this.realPart + this.imagPart*this.imagPart);
	}
	public Complex sqrt(Complex ab){
		double angle = atan2(ab.imagPart, ab.realPart) /2;
		double radius
	}
}
