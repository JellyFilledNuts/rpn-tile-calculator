package de.fhdw.wip.rpntilecalculator.core.model;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

/*
 * Summary: Util for formatting all Double Values
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
public final class DoubleFormatter {

    // Decimal Format
    private static final DecimalFormat DF = new DecimalFormat("#.##");

    /*
     * Format the given double in the format to a string
     * @param d the double that is to be formatted
     * @return the formatted string
     */
    @NotNull public static String format(double d) {
        return DF.format(d);
    }

}
