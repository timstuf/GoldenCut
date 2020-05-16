package com.nure;

import java.util.Arrays;

public class Solution {
    private final double t = 0.61803;
    private final double oneMinT = 0.38197;
    private final double e = 0.000001;
    private int i = 1;
    private double[] xMin = new double[31];

    private double func(double x) {
        return Math.pow((x-Math.PI),4);
    }

    private double x1(double a, double b) {
        return oneMinT * (b - a) + a;
    }

    private double x2(double a, double b) {
        return t * (b - a) + a;
    }

    private double[] iterate(double a, double b) {
        double x1 = x1(a, b);
        double x2 = x2(a, b);
        return new double[]{a, x1, x2, b, func(x1), func(x2)};
    }

    private double[] getNewBorders(double[] iter) {
        if (iter[4] < iter[5]) return new double[]{iter[0], iter[2]};
        else return new double[]{iter[1], iter[3]};
    }

    private boolean isEnd(double x1, double x2) {
        return Math.abs(x1 - x2) < e;
    }

    public void cycle(double a, double b) {
        double[] iteration = iterate(a, b);
        double min = Math.min(iteration[1], iteration[2]);
        xMin[i] = min;
        printIteration(iteration);
        if (isEnd(iteration[1], iteration[2])) {
            printFinalAnswer(iteration);
            return;
        }
        double[] newBorders = getNewBorders(iteration);
        cycle(newBorders[0], newBorders[1]);
    }

    private void printFinalAnswer(double[] iter) {
        double minf, minx;
        if (iter[4] < iter[5]) {
            minf = iter[4];
            minx = iter[1];
        } else {
            minf = iter[5];
            minx = iter[2];
        }
        System.out.printf("Конечный интервал: [%.9f, %.9f].\nНаименьшее значение функции равно" +
                " f = %.9f" +
                " и достигается оно в точке" +
                " x = %.9f.\n", iter[0], iter[3], minf, minx);
        System.out.println(Arrays.toString(xMin));
    }

    private void printIteration(double[] arr) {
        if (i == 1) {
            System.out.println("k   a            x1           x2           b            f1           f2");
        }
        System.out.print(String.format("%-2s  ", i++));
        for (double v : arr) {
            System.out.printf("%.9f  ", v);
        }
        System.out.println();
    }
}
