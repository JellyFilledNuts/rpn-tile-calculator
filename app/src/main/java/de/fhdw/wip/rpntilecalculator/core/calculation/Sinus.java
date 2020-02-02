package de.fhdw.wip.rpntilecalculator.core.calculation;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.Operand;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/*
 * Summary: Defines the Sinus action.
 * Author:  Jannis Luca Keienburg
 * Date:    2020/01/04
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class Sinus extends Action {

    @NotNull
    private static final Sinus SINUS = new Sinus();

    /*
     * Singleton for SINUS
     * @return singleton object
     */
    @Contract(pure = true) @NotNull public static Sinus getInstance() { return SINUS; }
    private Sinus() { }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    // Calculates the sinus with a given angle.
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble angle) {
        return new ODouble(Math.sin(angle.getDouble()));
    }

    // Calculates the sinus with the adjacent side and the reversed side.
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble adjacentSide, @NotNull ODouble reversedSide) {
        return new ODouble(Math.asin(adjacentSide.getDouble() / reversedSide.getDouble()));
    }
}
