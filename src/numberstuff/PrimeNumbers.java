package numberstuff;

import java.math.BigInteger;
import java.util.Scanner;

public class PrimeNumbers {
    public static void main(String[] args) {
        System.out.println("How many prime numbers do you want?");
        Scanner sc = new Scanner(System.in);
        long noOfOutputs = sc.nextLong();

        for (BigInteger no = new BigInteger("1"); noOfOutputs > 0; no = no.add(BigInteger.ONE)) {
            int temp = 0;
            for (BigInteger i = new BigInteger("2"); i.compareTo(no) <= no.subtract(BigInteger.ONE).compareTo(i); i = i.add(BigInteger.ONE)) {
                if ((no.mod(i)).compareTo(BigInteger.ZERO) == 0)
                    temp++;
            }
            if (temp == 0) {
                System.out.print(no + " ");
                noOfOutputs--;
            }
        }

    }
}
