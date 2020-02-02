package de.fhdw.wip.rpntilecalculator.model.calculation;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

    /*
     * Summary: Defines the Sinus action.
     * Author:  Jannis Luca Keienburg
     * Date:    2020/01/04
     */
    @SuppressWarnings({"unused", "WeakerAccess"})
    public class Tangens extends Action {

        @NotNull
        private static final Tangens TANGENS = new Tangens();

        /*
         * Singleton for SINUS
         * @return singleton object
         */
        @Contract(pure = true) @NotNull public static Tangens getInstance() { return TANGENS; }
        private Tangens() {
            requiredNumOfOperands = new int[]{1,2};
        }

        @NotNull @Override
        public Operand with(@NotNull Operand... operands) throws CalculationException {
            scopedAction = this;
            return super.with(operands);
        }

        // Calculates the tangens with a given angle.
        @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble angle) {
            return new ODouble(Math.tan(angle.getDouble()));
        }

        // Calculates the tangens with the reversed side and the adjacent side.
        @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble reversedSide, @NotNull ODouble adjacentSide) {
            return new ODouble(Math.atan(reversedSide.getDouble() / adjacentSide.getDouble()));
        }
    }


