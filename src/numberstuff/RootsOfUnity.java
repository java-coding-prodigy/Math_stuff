package numberstuff;

import java.util.Scanner;
import java.lang.Math;

public class RootsOfUnity {
    public static void main(String[] args) {
        System.out.println("Input three numbers: the first will be the real part of the radicand,the second will be the imaginary part of it and the third will be the number you want to root it by ");
        Scanner sc = new Scanner(System.in);
        double realPart = sc.nextDouble();
        double imaginaryPart = sc.nextDouble();
        double rootPower = sc.nextInt();
        double radius = Math.sqrt(realPart * realPart + imaginaryPart * imaginaryPart);
        double angle = Math.atan2(imaginaryPart, realPart);
        if (radius != 0) {
            double radiusAnswer = Math.pow(radius, 1.0 / rootPower);
            if (rootPower > 0) {
                for (int n = 1; n <= rootPower; n++) {
                    double rootAngle = angle / rootPower;
                    double realPartRoot = radiusAnswer * (Approx.approx(Math.cos(rootAngle)));
                    double imaginaryPartRoot = radiusAnswer * (Approx.approx(Math.sin(rootAngle)));
                    if (realPartRoot != 0)
                        if (realPartRoot == Math.floor(realPartRoot))
                            System.out.printf("%.0f", realPartRoot);
                        else
                            System.out.print(realPartRoot);
                    if (imaginaryPartRoot != 0.0) {
                        if (Math.abs(imaginaryPartRoot) != 1.0)
                            System.out.printf("%+f", imaginaryPartRoot);
                        if (imaginaryPartRoot == -1)
                            System.out.print('-');
                        System.out.print('i');
                    }
                    System.out.println();
                    angle += 2 * Math.PI;
                }
            } else if (rootPower < 0) {
                rootPower = Math.abs(rootPower);
                for (int n = 1; n <= rootPower; n++) {
                    double rootAngle = angle / rootPower;
                    double realPartRoot = radiusAnswer * (Approx.approx(Math.cos(rootAngle)));
                    double imaginaryPartRoot = radiusAnswer * (Approx.approx(Math.sin(rootAngle)));
                    realPartRoot /= radiusAnswer;
                    imaginaryPartRoot /= radiusAnswer;
                    if (realPartRoot != 0)
                        if (realPartRoot == Math.floor(realPartRoot))
                            System.out.printf("%.0f", realPartRoot);
                        else
                            System.out.print(realPartRoot);
                    if (imaginaryPartRoot != 0.0) {
                        if (Math.abs(imaginaryPartRoot) != 1.0)
                            System.out.printf("%+f", imaginaryPartRoot);
                        if (imaginaryPartRoot == -1)
                            System.out.print('-');
                        System.out.print('i');
                    }
                    System.out.println();
                    angle += 2 * Math.PI;
                }
            } else if (rootPower == 0)
                System.out.println("This value is not defined");
        } else if (rootPower > 0)
            System.out.println(0);
        else if (rootPower <= 0)
            System.out.println("This value is not defined");
    }
}