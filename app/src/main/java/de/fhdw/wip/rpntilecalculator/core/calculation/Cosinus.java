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
    @SuppressWarnings({"unused"})
    public class Cosinus extends Action {

        @NotNull
        private static final Cosinus COSINUS = new Cosinus();

        /*
         * Singleton for COSINUS
         * @return singleton object
         */
        @Contract(pure = true) @NotNull public static Cosinus getInstance() { return COSINUS; }
        private Cosinus() { }

        @NotNull @Override
        public Operand with(@NotNull Operand... operands) throws CalculationException {
            scopedAction = this;
            return super.with(operands);
        }

        // Calculates the cosinus with a given angle.
        @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble angle) {
            return new ODouble(Math.cos(angle.getDouble()));
        }

        // Calculates the cosinus with the adjacent side and the hypotenuse.
        @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble adjacentSide, @NotNull ODouble hypotenuse) {
            return new ODouble(Math.acos(adjacentSide.getDouble() / hypotenuse.getDouble()));
        }
    }




