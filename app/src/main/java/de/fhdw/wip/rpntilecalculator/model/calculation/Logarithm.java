package de.fhdw.wip.rpntilecalculator.model.calculation;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;

/*
 * Summary: A Class that can calculate the natural logarithm.
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/19
 */
public class Logarithm extends Action {

    @NotNull
    private static final Logarithm LOGARITHM = new Logarithm();

    /*
     * Singleton for Logarithm
     * @return singleton object
     */
    @Contract(pure = true) @NotNull public static Logarithm getInstance() { return LOGARITHM; }
    private Logarithm() {
        requiredNumOfOperands = new int[]{1, 2};
    }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    //Natural Logarithm
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble oDouble) {
        return new ODouble(Math.log(oDouble.getDouble()));
    }

    //Logarithm with specific base
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble base, ODouble logartihmOf) {
        return new ODouble(Math.log(logartihmOf.getDouble()) / Math.log(base.getDouble()));
    }
}