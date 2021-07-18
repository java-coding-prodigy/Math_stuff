package io.github.javacodingprodigy.mathstuff2.importfunctions;


import io.github.javacodingprodigy.mathstuff2.numberstuff.Approx;

import static java.lang.Math.*;

public class LambertWFunction {
    //W(x) is the inverse of f(x) = x*e^x
    //W(x*e^x) = x
    //W(x)*e^(W(x)) = x
    //code for finding the W of a number using Newton's method
    public static void main(String[]args) {
        System.out.println(W(PI* exp(PI)));
    }

    public static double normalFunction(double x) {
        //This is the regular function which Lambert W is the inverse of.
        //It is just f(x) = x*e^x
        return x * exp(x);
    }

    public static double derivativeOfFunction(double x) {
        //This is the derivative of the function in the previous method
        //f(x) = (x+1)*e^x
        return (x + 1) * exp(x);
    }

    public static double W(double x) {
        //This is the method which finds the W
        double b;
        if (x < 0)
            b = x * exp(1 - sqrt(2 * (E * x + 1)));
        else if (x < E)
            b = x / E;
        else
            b = log(x);
        //These are the first values of our Newton Iteration
        while (Approx.approx(normalFunction(b) - x) != 0) {
            //Checks whether we are getting an incorrect value and continues the loop if yes
            b -= (normalFunction(b) - x) / derivativeOfFunction(b);//System.out.println((b));
            //Newton iteration trying to get the closer value
        }
        return b;
    }
}
