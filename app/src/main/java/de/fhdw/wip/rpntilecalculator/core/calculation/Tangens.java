package de.fhdw.wip.rpntilecalculator.core.calculation;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OMatrix;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OPolynom;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OSet;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OTuple;
import de.fhdw.wip.rpntilecalculator.core.model.operand.Operand;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

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
        private Tangens() { }

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
            return new ODouble(Math.tan(angle.getDouble()));
        }

        /*
         * Multiplying ODouble and ODouble
         * @param oDouble1 first operand
         * @param oDouble2 second operand
         * @return product of params
         */
        @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble reversedSide, @NotNull ODouble adjacentSide) {
            return new ODouble(Math.atan(reversedSide.getDouble() / adjacentSide.getDouble()));
        }

        //endregion
    }



