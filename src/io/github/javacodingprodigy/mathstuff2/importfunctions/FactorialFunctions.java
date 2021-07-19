package io.github.javacodingprodigy.mathstuff2.importfunctions;

import java.math.BigInteger;
import java.util.Scanner;

public class FactorialFunctions {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		if (n >= 0) {
			System.out.println(n + "! = " + factorial(n));
			System.out.println("H(" + n + ") = " + hyperfactorial(n));
			System.out.println(n + "$ = " + superfactorial(n));
			System.out.println("!" + n + " = " + subfactorial(n));
			System.out.println(n + "!! = " + doublefactorial(n));
		} else {
			System.out.println(n + "! = ±∞");
			System.out.println("H(" + n + ") = ±∞");
			System.out.println(n + "$ = ±∞");
			System.out.println("!" + n + " = ±∞");
			System.out.println(n + "!! = ±∞");
		}
	}

	public static BigInteger factorial(long n) {
		if (n == 0 || n == 1) {
			return BigInteger.ONE;
		} else {
			return BigInteger.valueOf(n)
					.multiply(factorial(n - 1));
		}
	}

	public static BigInteger hyperfactorial(int n) {
		if (n == 0 || n == 1) {
			return BigInteger.ONE;
		} else {
			return (BigInteger.valueOf(n)
					.pow(n)).multiply(hyperfactorial(n - 1));
		}
	}

	public static BigInteger superfactorial(int n) {
		if (n == 0 || n == 1) {
			return BigInteger.ONE;
		} else {
			return factorial(n).multiply(superfactorial(n - 1));
		}
	}

	public static BigInteger subfactorial(int n) {
		if (n == 0 || n == 2) {
			return BigInteger.ONE;
		} else if (n == 1) {
			return BigInteger.ZERO;
		} else {
			return BigInteger.valueOf(n - 1)
					.multiply(subfactorial(n - 1).add(subfactorial(n - 2)));
		}
	}

	public static BigInteger doublefactorial(int n) {
		if (n == 1) {
			return BigInteger.ONE;
		} else if (n == 2) {
			return BigInteger.TWO;
		} else {
			return BigInteger.valueOf(n)
					.multiply(doublefactorial(n - 2));
		}
	}

	public static BigInteger aFactDivCFact(long num1, long num2) {
		if (num1 == num2) {
			return BigInteger.ONE;
		} else {
			return BigInteger.valueOf(num1)
					.multiply(aFactDivCFact(num1 - 1, num2));
		}
	}

	public static class FactorialFunctionsIterative {
		public static void main(String[] args) {
			System.out.println(subfactorial(5));
		}

		public static BigInteger factorial(int n) {
			BigInteger nfactorial = new BigInteger("1");
			if (n == 0) {
				nfactorial = BigInteger.ONE;
			} else {
				for (int i = 2; i <= n; i++) {
					nfactorial = nfactorial.multiply(BigInteger.valueOf(i));
				}
			}
			return nfactorial;
		}

		public static BigInteger hyperfactorial(int x) {
			BigInteger nhyperfactorial = new BigInteger("1");
			if (x == 0) {
				nhyperfactorial = BigInteger.ONE;
			} else {
				for (int i = 2; i <= x; i++) {
					nhyperfactorial = nhyperfactorial.multiply(BigInteger.valueOf(i)
							.pow(i));
				}
			}
			return nhyperfactorial;
		}

		public static BigInteger superfactorial(int x) {
			BigInteger nsuperfactorial = new BigInteger("1");
			if (x == 0) {
				nsuperfactorial = BigInteger.ONE;
			} else {
				for (int i = 2; i <= x; i++) {
					nsuperfactorial = nsuperfactorial.multiply(FactorialFunctionsIterative.factorial(i));
				}
			}
			return nsuperfactorial;
		}

		public static BigInteger subfactorial(int x) {
			BigInteger nsubfactorial = new BigInteger("1");
			BigInteger decnnsubfactorial = new BigInteger("0");
			BigInteger parallel;
			if (x == 0 || x == 2) {
				nsubfactorial = BigInteger.ONE;
			} else if (x == 1) {
				return BigInteger.ZERO;
			} else {
				for (int i = 3; i <= x; i++) {
					parallel = nsubfactorial;
					nsubfactorial = BigInteger.valueOf(i - 1)
							.multiply(decnnsubfactorial.add(nsubfactorial));
					decnnsubfactorial = parallel;
				}
			}
			return nsubfactorial;
		}
	}
}