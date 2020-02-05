package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

/*
 * Summary: A Class that can calculate the logarith to the base of 10. 
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/19
 */
public class Logarithm10 extends Action {

    @NotNull
    private static final Logarithm10 LOGARITHM10 = new Logarithm10();

    /*
     * Singleton for Logarithm with the base of 10
     * @return singleton object
     */
    @Contract(pure = true) @NotNull public static Logarithm10 getInstance() { return LOGARITHM10; }
    private Logarithm10() {
        requiredNumOfOperands = new int[]{1};
    }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    //Logarithm to the base of 10
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble oDouble) {
        if(oDouble.getDouble() <= 0)
            throw new IllegalArgumentException("Value must be higher than Zero.");
        return new ODouble(Math.log10(oDouble.getDouble()));
    }
}
