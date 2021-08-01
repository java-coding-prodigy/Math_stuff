package io.github.javacodingprodigy.mathstuff2;

import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {
Complex z = new Complex(Complex.POSITIVE_INFINITY);
	@org.junit.jupiter.api.Test
	void asin() {
		assertEquals(Complex.NEGATIVE_INFINITY_I,Complex.asin(z));
	}

	@org.junit.jupiter.api.Test
	void acos() {
		assertEquals(Complex.POSITIVE_INFINITY_I,Complex.acos(z));
	}

	@org.junit.jupiter.api.Test
	void atan() {
		assertEquals(Complex.atan(z), Complex.realValueOf(Math.PI / 2));
	}

	@org.junit.jupiter.api.Test
	void acot() {
		assertEquals(Complex.ZERO,Complex.acot(z));
	}

	@org.junit.jupiter.api.Test
	void asec() {
		assertEquals(Complex.realValueOf(Math.PI/2),Complex.asec(z));
	}

	@org.junit.jupiter.api.Test
	void acsc() {
		assertEquals(Complex.ZERO,Complex.acsc(z));
	}

	@org.junit.jupiter.api.Test
	void asinh() {
		assertEquals(Complex.POSITIVE_INFINITY,Complex.asinh(z));
	}

	@org.junit.jupiter.api.Test
	void acosh() {
		assertEquals(Complex.POSITIVE_INFINITY,Complex.acosh(z));
	}

	@org.junit.jupiter.api.Test
	void atanh() {
		assertEquals(Complex.imagValueOf(-Math.PI/2),Complex.atanh(z));
	}

	@org.junit.jupiter.api.Test
	void acsch() {
		assertEquals(Complex.ZERO,Complex.acsch(z));
	}

	@org.junit.jupiter.api.Test
	void asech() {
		assertEquals(Complex.realValueOf(Math.PI/2),Complex.asec(z));
	}

	@org.junit.jupiter.api.Test
	void acoth() {
		assertEquals(Complex.ZERO,Complex.acoth(z));
	}
	@org.junit.jupiter.api.Test
	void pow(){
		assertEquals(Complex.ONE , Complex.ONE.pow(Complex.I));
	}
}