package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

/*
 * Summary: Defines the arc Cosinus action.
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/04
 */

public class ArcCosinus extends Action {

    @NotNull
    private static final ArcCosinus ARC_COSINUS = new ArcCosinus();

    /*
     * Singleton for ARc COSINUS
     * @return singleton object
     */
    @Contract(pure = true) @NotNull public static ArcCosinus getInstance() { return ARC_COSINUS; }
    private ArcCosinus() {
        requiredNumOfOperands = new int[]{1};
    }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    /**
     * Calculates the arc cosinus with a given angle.
     *
     * @param angle Representing the angle.
     * @return Result
     */
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble angle) {
        return new ODouble(Math.acos(Math.toRadians((angle.getDouble()))));
    }
}




