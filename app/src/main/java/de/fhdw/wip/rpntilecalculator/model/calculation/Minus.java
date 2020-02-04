package de.fhdw.wip.rpntilecalculator.model.calculation;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OFraction;
import de.fhdw.wip.rpntilecalculator.model.operands.OMatrix;
import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;
import de.fhdw.wip.rpntilecalculator.model.operands.OSet;
import de.fhdw.wip.rpntilecalculator.model.operands.OTuple;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/*
 * Summary: Defines the Minus click. Lets the user subtract operands.
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
public class Minus extends Action {

    @NotNull private static final Plus PLUS = Plus.getInstance();

    @NotNull private static final Minus MINUS = new Minus();

    @Contract(pure = true) @NotNull public static Minus getInstance() { return MINUS; }
    private Minus() {
        requiredNumOfOperands = new int[]{2};
    }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    //region Double
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble oDouble1, @NotNull ODouble oDouble2) {
        return new ODouble(oDouble1.getDouble() - oDouble2.getDouble());
    }

    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble oDouble, @NotNull OFraction oFraction) {
        return new ODouble(oDouble.getDouble() - oFraction.getDouble());
    }

    //endregion

    //region Fraction
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OFraction on(@NotNull OFraction oFraction1, @NotNull OFraction oFraction2) {
        return new OFraction(oFraction1.getFraction().subtract(oFraction2.getFraction()));
    }

    @Contract(pure = true) @NotNull ODouble on(@NotNull OFraction oFraction, @NotNull ODouble oDouble) {
        return new ODouble(oFraction.getDouble() - oDouble.getDouble());
    }

    //endregion

    //region Set
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OSet on(@NotNull OSet oSet, @NotNull ODouble oDouble) {
        return PLUS.on(oDouble.turnAroundSign(), oSet);
    }

    @Contract(pure = true) @NotNull OSet on(@NotNull OSet oSet, @NotNull OFraction oFraction) {
        return on(oSet, new ODouble(oFraction.getDouble()));
    }

    //endregion

    //region Matrix
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix oMatrix1, @NotNull OMatrix oMatrix2) {
        return new OMatrix(oMatrix1.getMatrix().subtract(oMatrix2.getMatrix()));
    }

    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix oMatrix, @NotNull ODouble oDouble) {
        return new OMatrix(oMatrix.getMatrix().scalarAdd(oDouble.turnAroundSign().getDouble()));
    }

    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix oMatrix, @NotNull OFraction oFraction) {
        return new OMatrix(oMatrix.getMatrix().scalarAdd(oFraction.turnAroundSign().getDouble()));
    }

    //endregion

    //region Polynom
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OPolynom on(@NotNull OPolynom oPolynom1, @NotNull OPolynom oPolynom2) {
        return new OPolynom(oPolynom1.getPolynom().subtract(oPolynom2.getPolynom()));
    }

    @Contract(pure = true) @NotNull OPolynom on(@NotNull OPolynom oPolynom, @NotNull ODouble oDouble) {
        return PLUS.on(oDouble.turnAroundSign(), oPolynom);
    }

    @Contract(pure = true) @NotNull OPolynom on(@NotNull OPolynom oPolynom, @NotNull OFraction oFraction) {
        return on(oPolynom, new ODouble(oFraction.getDouble()));
    }

    //endregion

    //region Tuple
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OTuple on(@NotNull OTuple oTuple1, @NotNull OTuple oTuple2) {
        return PLUS.on(oTuple1, oTuple2.turnAroundSign());
    }

    @Contract(pure = true) @NotNull OTuple on(@NotNull OTuple oTuple, @NotNull ODouble oDouble) {
        return PLUS.on(oDouble.turnAroundSign(), oTuple);
    }

    @Contract(pure = true) @NotNull OTuple on(@NotNull OTuple oTuple, @NotNull OFraction oFraction) {
        return PLUS.on(oFraction.turnAroundSign(), oTuple);
    }

    //endregion

}
