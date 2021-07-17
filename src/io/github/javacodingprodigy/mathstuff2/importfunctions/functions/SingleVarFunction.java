package io.github.javacodingprodigy.mathstuff2.importfunctions.functions;

import java.util.Scanner;

public class SingleVarFunction {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		double x = sc.nextDouble();
		System.out.println("f(" + x + ")" + " = " + f(x));
	}
	public static double f(double x){ return x*x ; }
}
