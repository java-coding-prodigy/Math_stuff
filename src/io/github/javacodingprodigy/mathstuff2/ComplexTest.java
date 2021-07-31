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
		assertEquals(true,Complex.atan(z).equals(Complex.realValueOf(Math.PI/2)));
	}

	@org.junit.jupiter.api.Test
	void acot() {
	}

	@org.junit.jupiter.api.Test
	void asec() {
	}

	@org.junit.jupiter.api.Test
	void acsc() {
	}

	@org.junit.jupiter.api.Test
	void asinh() {
	}

	@org.junit.jupiter.api.Test
	void acosh() {
	}

	@org.junit.jupiter.api.Test
	void atanh() {
	}

	@org.junit.jupiter.api.Test
	void acsch() {
	}

	@org.junit.jupiter.api.Test
	void asech() {
	}

	@org.junit.jupiter.api.Test
	void acoth() {
	}
}