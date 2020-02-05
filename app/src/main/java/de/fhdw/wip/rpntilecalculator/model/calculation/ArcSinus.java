package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

/*
 * Summary: Defines the arc Sinus action.
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/04
 */

public class ArcSinus extends Action {

    @NotNull
    private static final ArcSinus ARC_SINUS = new ArcSinus();

    /*
     * Singleton for ARC SINUS
     * @return singleton object
     */
    @Contract(pure = true) @NotNull public static ArcSinus getInstance() { return ARC_SINUS; }
    private ArcSinus() {
        requiredNumOfOperands = new int[]{1};
    }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    // Calculates the arc sinus with a given angle.
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble angle) {
        return new ODouble(Math.asin(Math.toRadians((angle.getDouble()))));
    }
}
