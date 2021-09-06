package io.github.javacodingprodigy.mathstuff2.polynomials;


import io.github.javacodingprodigy.mathstuff2.DoubleComplex;

import java.util.ArrayList;
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
        ArrayList<DoubleComplex> solutions = new ArrayList<>();
        double discriminant = b * b - 4 * a * c;
        if (a == 0 && b == 0 && c == 0)
            System.out.println("All possible values");
        else if (a == 0) {
            solutions.add(0,new DoubleComplex(-c / b , 0));
        } else if (discriminant >= 0) {
            solutions.add(0,new DoubleComplex((-b + sqrt(discriminant) )/ (2 * a),0));
            solutions.add(1,new DoubleComplex((-b - sqrt(discriminant) )/ (2 * a),0));
        } else if (discriminant < 0) {
            solutions.add(0, new DoubleComplex( (-b / (2*a) ), sqrt(discriminant) / (2 * a)));
        }
        for (DoubleComplex i : solutions)
        {
            System.out.print(i.toString());
            if (i != solutions.get(1))
                System.out.print(", ");
        }
    }
}

