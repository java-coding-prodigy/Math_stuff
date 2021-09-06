package io.github.javacodingprodigy.mathstuff2.numberstuff;

import java.math.BigInteger;

public class ParityTest {
    public static void main(String[]args) {
        System.out.println(parity(BigInteger.ONE) ? 1 : 0);
    }

    public static boolean parity(BigInteger n) {
        return n.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0;
    }
}
