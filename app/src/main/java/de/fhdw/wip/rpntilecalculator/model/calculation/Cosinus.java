package de.fhdw.wip.rpntilecalculator.model.calculation;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/*
 * Summary: Defines the Cosinus action.
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/04
 */
@SuppressWarnings({"unused"})
public class Cosinus extends Action {

    @NotNull
    private static final Cosinus COSINUS = new Cosinus();

    /*
     * Singleton for COSINUS
     * @return singleton object
     */
    @Contract(pure = true) @NotNull public static Cosinus getInstance() { return COSINUS; }
    private Cosinus() {
        requiredNumOfOperands = new int[]{1, 2};
    }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    // Calculates the cosinus with a given angle.
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble angle) {
        return new ODouble(Math.cos(Math.toRadians((angle.getDouble()))));
    }
}




