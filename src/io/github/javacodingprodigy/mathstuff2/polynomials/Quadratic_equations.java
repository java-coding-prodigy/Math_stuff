package io.github.javacodingprodigy.mathstuff2.polynomials;


import java.util.Scanner;

import static java.lang.Math.*;

public class Quadratic_equations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();
        System.out.print("The roots of the quadratic equation " + a + "x² + " + b + "x + " + c + " = 0 are ");
        quadraticEquations(a, b, c);
    }

    public static void quadraticEquations(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;
        if (a == 0 && b == 0 && c == 0)
            System.out.println("All possible values");
        else if (a == 0) {
            double x = -c / b;
            System.out.println(x);
        } else if (discriminant >= 0) {
            double x_1 = (-b + sqrt(discriminant)) / (2 * a);
            double x_2 = (-b - sqrt(discriminant)) / (2 * a);
            System.out.println(x_1 + " and " + x_2);
        } else if (discriminant < 0) {
            double real_x = -b / (2 * a);
            double imaginary_x = abs(((sqrt(abs(discriminant))) / (2 * a)));
            System.out.println(real_x + " + " + imaginary_x + "i and " + real_x + " - " + imaginary_x + "i");
        }
    }
}

