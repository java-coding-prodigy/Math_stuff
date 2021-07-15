package io.github.javacodingprodigy.mathstuff2.importfunctions;

import java.util.Scanner;

public class function {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double x = sc.nextDouble();
        double y = sc.nextDouble();
        double z = Math.sqrt(x * x + y * y);
        System.out.println("f(" + x + "," + y + ") = " + z);
    }
}