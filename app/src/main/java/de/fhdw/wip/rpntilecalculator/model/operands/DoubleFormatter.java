package de.fhdw.wip.rpntilecalculator.model.operands;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/*
 * Summary: Util for formatting all Double Values
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
public final class DoubleFormatter {

    // Decimal Format
    private static final DecimalFormat DF = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    /*
     * Format the given double in the format to a string
     * @param d the double that is to be formatted
     * @return the formatted string
     */
    @NotNull public static String format(double d) {
        return DF.format(d);
    }

}
