package numberstuff;

import java.util.Scanner;

public class polarFormConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double x = sc.nextDouble();
        double y = sc.nextDouble();
        System.out.println(x + "" + String.format("%+f", y) + "i = " + radius(x, y) + "* e^i" + theta(x, y));
    }

    public static double radius(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    public static double theta(double x, double y) {
        return Math.atan2(x, y);
    }
}
