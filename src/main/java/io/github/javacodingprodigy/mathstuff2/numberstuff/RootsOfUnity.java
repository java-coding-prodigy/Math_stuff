package io.github.javacodingprodigy.mathstuff2.numberstuff;


import java.util.Scanner;


import static io.github.javacodingprodigy.mathstuff2.numberstuff.Approx.*;
import static java.lang.Math.*;

public class RootsOfUnity {
    public static void main(String[] args) {
        System.out.println("Input three numbers: the first will be the real part of the radicand,the second will be the imaginary part of it and the third will be the number you want to root it by ");
        Scanner sc = new Scanner(System.in);
        double realPart = Double.parseDouble(sc.nextLine());
        double imaginaryPart = Double.parseDouble(sc.nextLine());
        int rootPower = Integer.parseInt(sc.nextLine());
        double radius = sqrt(realPart * realPart + imaginaryPart * imaginaryPart);
        double angle = atan2(imaginaryPart, realPart);
        if (radius != 0) {
            double radiusAnswer = pow(radius, 1.0 / rootPower);
            if (rootPower > 0) {
                for (int n = 1; n <= rootPower; n++) {
                    double rootAngle = angle / rootPower;
                    double realPartRoot = radiusAnswer * (approx(cos(rootAngle)));
                    double imaginaryPartRoot = radiusAnswer * (approx(sin(rootAngle)));
                    if (realPartRoot != 0)
                        if (realPartRoot == floor(realPartRoot))
                            System.out.printf("%.0f", realPartRoot);
                        else
                            System.out.print(realPartRoot);
                    if (imaginaryPartRoot != 0.0) {
                        if (abs(imaginaryPartRoot) != 1.0)
                            System.out.printf("%+f", imaginaryPartRoot);
                        if (imaginaryPartRoot == -1)
                            System.out.print('-');
                        System.out.print('i');
                    }
                    System.out.println();
                    angle += 2 * PI;
                }
            } else if (rootPower < 0) {
                rootPower = abs(rootPower);
                for (int n = 1; n <= rootPower; n++) {
                    double rootAngle = angle / rootPower;
                    double realPartRoot = radiusAnswer * (approx(cos(rootAngle)));
                    double imaginaryPartRoot = radiusAnswer * (approx(sin(rootAngle)));
                    realPartRoot /= radiusAnswer;
                    imaginaryPartRoot /= radiusAnswer;
                    if (realPartRoot != 0)
                        if (realPartRoot == floor(realPartRoot))
                            System.out.printf("%.0f", realPartRoot);
                        else
                            System.out.print(realPartRoot);
                    if (imaginaryPartRoot != 0.0) {
                        if (abs(imaginaryPartRoot) != 1.0)
                            System.out.printf("%+f", imaginaryPartRoot);
                        if (imaginaryPartRoot == -1)
                            System.out.print('-');
                        System.out.print('i');
                    }
                    System.out.println();
                    angle += 2 * PI;
                }
            } else
                System.out.println("This value is not defined");
        } else if (rootPower > 0)
            System.out.println(0);
        else
            System.out.println("This value is not defined");
    }
}