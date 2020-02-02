package de.fhdw.wip.rpntilecalculator.model.operands;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
public class DoubleComparator {

    @Contract(pure = true) public static boolean isEqual(double d1, double d2) {
        return Math.abs(d1 - d2) < 0.000001;
    }

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

    @Contract(pure = true) public static boolean isZero(double d) {
        double delta = 0.00001;
        return d < delta && d > -1d * delta;
    }

}
