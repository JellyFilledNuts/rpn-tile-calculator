package de.fhdw.wip.rpntilecalculator.model.operands;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;

/*
 * Summary: Utility to compare Doubles.
 * Author:  Tim Schwenke
 * Date:    2020/01/08
 */
public class DoubleComparator {

    /**
     * Check if two doubles are equal (with a certain margin).
     * @param d1 First double
     * @param d2 Second double
     * @return Boolean
     */
    @Contract(pure = true) public static boolean isEqual(double d1, double d2) {
        return Math.abs(d1 - d2) < 0.000001;
    }

    /**
     * Compares two arrays of Double.
     * @param d1 First double array.
     * @param d2 Second double array
     * @return Boolean
     */
    @Contract(pure = true) public static boolean isEqual(
            @NotNull double[] d1,
            @NotNull double[] d2
    ) {
        if (d1.length != d2.length) return false;
        if (Arrays.equals(d1, d2)) return true;

        for (int i = 0; i < d1.length; i++)
            if (!isEqual(d1[i], d2[i])) return false;

        return true;
    }

    /**
     * Compares two matrices of doubles for equality.
     * @param d1 First matrix
     * @param d2 Second matrix
     * @return Boolean
     */
    @Contract(pure = true) public static boolean isEqual(
            @NotNull double[][] d1,
            @NotNull double[][] d2
    ) {
        if (d1.length != d2.length) return false;
        for (int i = 0; i < d1.length; i++)
            if (d1[i].length != d2[i].length) return false;

        for (int i = 0; i < d1.length; i++)
            if (!isEqual(d1[i], d2[i])) return false;

        return true;
    }

    /**
     * Compares two sets of Doubles for equality.
     * @param d1 First set
     * @param d2 Second set
     * @return Boolean
     */
    @Contract(pure = true) public static boolean isEqual(
            @NotNull Set<Double> d1,
            @NotNull Set<Double> d2
    ) {
        Double[] d1Array = new Double[d1.size()];
        d1.toArray(d1Array);

        Double[] d2Array = new Double[d2.size()];
        d2.toArray(d2Array);

        double[] d1ArrayPrim = new double[d1.size()];
        for (int i = 0; i < d1.size(); i++) d1ArrayPrim[i] = d1Array[i];

        double[] d2ArrayPrim = new double[d2.size()];
        for (int i = 0; i < d1.size(); i++) d2ArrayPrim[i] = d2Array[i];

        return isEqual(d1ArrayPrim, d2ArrayPrim);
    }

    /**
     * Check if double is about zero (margin).
     * @param d Double to check
     * @return Boolean
     */
    @Contract(pure = true) public static boolean isZero(double d) {
        double delta = 0.00001;
        return d < delta && d > -1d * delta;
    }

}
