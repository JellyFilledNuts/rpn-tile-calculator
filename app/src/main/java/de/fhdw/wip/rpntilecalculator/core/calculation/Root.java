package de.fhdw.wip.rpntilecalculator.core.calculation;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OMatrix;
import de.fhdw.wip.rpntilecalculator.core.model.operand.Operand;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Contract;

/*
 * Summary: Defines the Root action.
 * Author:  Getuart Istogu
 * Date:    2020/01/04
 */

@SuppressWarnings({"unused", "WeakerAccess"})
public class Root extends Action{
    @NotNull private static final Root ROOT = new Root();
    @NotNull private static final Power POWER = Power.getInstance();

    @Contract(pure = true) @NotNull public static Root getInstance() { return ROOT; }
    private Root() { }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        requiredNumOfOperands = 2;
        scopedAction = this;
        return super.with(operands);
    }

    //region Double
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble radicand, @NotNull ODouble exponent) {
        return POWER.on(radicand, new ODouble(1/exponent.getDouble()));
    }

    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble radicand, @NotNull OFraction exponent){
        return POWER.on(radicand, new OFraction(1/exponent.getDouble()));
    }

    //region Fraction
    //------------------------------------------------------------------------------------
    @Contract(pure = true) @NotNull OFraction on(@NotNull OFraction radicand, @NotNull ODouble exponent){
        return POWER.on(radicand, new OFraction(1/exponent.getDouble()));
    }

    @Contract(pure = true) @NotNull OFraction on(@NotNull OFraction radicand, @NotNull OFraction exponent){
        return POWER.on(radicand, new ODouble(1/exponent.getDouble()));
    }

    //region Matrix
    //------------------------------------------------------------------------------------
    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix radicand, @NotNull ODouble exponent) {
        return POWER.on(radicand, new ODouble(1/exponent.getDouble()));
    }

    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix radicand, @NotNull OFraction exponent) {
        return POWER.on(radicand, new ODouble(1/exponent.getDouble()));
    }


}
