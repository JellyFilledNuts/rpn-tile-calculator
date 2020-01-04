package de.fhdw.wip.rpntilecalculator.core.model.operand;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public final class DoubleFormatter {

    private static final DecimalFormat DF = new DecimalFormat("#.##");

    @NotNull public static String format(double d) {
        return DF.format(d);
    }

}
