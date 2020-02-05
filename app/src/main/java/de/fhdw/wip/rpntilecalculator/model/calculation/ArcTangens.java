package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

/*
 * Summary: Defines the arc Tangens action.
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/04
 */

public class ArcTangens  extends Action {

    @NotNull
    private static final ArcTangens ARC_TANGENS = new ArcTangens();

    /*
     * Singleton for ARC TANGENS
     * @return singleton object
     */
    @Contract(pure = true) @NotNull public static ArcTangens getInstance() { return ARC_TANGENS; }
    private ArcTangens() {
        requiredNumOfOperands = new int[]{1};
    }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    // Calculates the tangens with a given angle.
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble angle) {
        return new ODouble(Math.atan(Math.toRadians((angle.getDouble()))));
    }
}


