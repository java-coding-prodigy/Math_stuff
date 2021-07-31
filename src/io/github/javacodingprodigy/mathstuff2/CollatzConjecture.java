package io.github.javacodingprodigy.mathstuff2;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Scanner;

public class CollatzConjecture {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BigInteger n = sc.nextBigInteger();
		BigInteger count = new BigInteger("0");
		System.out.print(n + " took ");
		while (true) {
			n = (n.mod(BigInteger.TWO))
					.compareTo(BigInteger.ONE) > 0 ? ((n.multiply(BigInteger.valueOf(3)))
					.add(BigInteger.ONE)) : n.divide(BigInteger.TWO);
			count = count.add(BigInteger.ONE);
			if (Objects.equals(n, BigInteger.ONE)) {
				System.out.println(count + " turns to converge");
				break;
			}

		}

	}
}
