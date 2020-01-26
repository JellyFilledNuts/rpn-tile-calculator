package de.fhdw.wip.rpntilecalculator.model.calculation;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/*
     * Summary: Defines the Sinus click.
     * Author:  Jannis Luca Keienburg
     * Date:    2020/01/04
     */
    @SuppressWarnings({"unused"})
    public class Cosinus extends Action {

        @NotNull
        private static final Cosinus COSINUS = new Cosinus();

        /*
         * Singleton for SINUS
         * @return singleton object
         */
        @Contract(pure = true) @NotNull public static Cosinus getInstance() { return COSINUS; }
        private Cosinus() { }

        /*
         * Multiplying ODouble and ODouble
         * @param operands params
         * @return product of operands
         */
        @NotNull @Override
        public Operand with(@NotNull Operand... operands) throws CalculationException {
            scopedAction = this;
            return super.with(operands);
        }

        //region Double
        //------------------------------------------------------------------------------------

        /*
         * Multiplying ODouble and ODouble
         * @param oDouble1 first operand
         * @param oDouble2 second operand
         * @return product of params
         */
        @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble angle) {
            return new ODouble(Math.cos(angle.getDouble()));
        }

        /*
         * Multiplying ODouble and ODouble
         * @param oDouble1 first operand
         * @param oDouble2 second operand
         * @return product of params
         */
        @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble adjacentSide, @NotNull ODouble hypotenuse) {
            return new ODouble(Math.acos(adjacentSide.getDouble() / hypotenuse.getDouble()));
        }

        //endregion
    }




