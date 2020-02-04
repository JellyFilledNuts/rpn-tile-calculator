package de.fhdw.wip.rpntilecalculator.model.calculation;

import de.fhdw.wip.rpntilecalculator.model.operands.DoubleComparator;
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
 * Summary: Defines the Sinus action.
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
public class Slash extends Action {

    @NotNull private static final Times TIMES = Times.getInstance();
    @NotNull private static final Slash SLASH = new Slash();

    @Contract(pure = true) @NotNull public static Slash getInstance() { return SLASH; }
    private Slash() {
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
        if (DoubleComparator.isZero(oDouble2.getDouble()))
            throw new IllegalArgumentException("Division by Zero not allowed");
        return new ODouble(oDouble1.getDouble() / oDouble2.getDouble());
    }

    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble oDouble, @NotNull OFraction oFraction) {
        if (DoubleComparator.isZero(oFraction.getDouble()))
            throw new IllegalArgumentException("Division by Zero not allowed");
        return new ODouble(oDouble.getDouble() / oFraction.getDouble());
    }

    //endregion

    //region Fraction
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OFraction on(@NotNull OFraction oFraction1, @NotNull OFraction oFraction2) {
        if (DoubleComparator.isZero(oFraction2.getDouble()))
            throw new IllegalArgumentException("Division by Zero not allowed");
        return new OFraction(oFraction1.getFraction().divide(oFraction2.getFraction()));
    }

    @Contract(pure = true) @NotNull ODouble on(@NotNull OFraction oFraction, @NotNull ODouble oDouble) {
        if (DoubleComparator.isZero(oDouble.getDouble()))
            throw new IllegalArgumentException("Division by Zero not allowed");
        return new ODouble(oFraction.getDouble() / oDouble.getDouble());
    }

    //endregion

    //region Set
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OSet on(@NotNull OSet oSet, @NotNull ODouble oDouble) {
        if (DoubleComparator.isZero(oDouble.getDouble()))
            throw new IllegalArgumentException("Division by Zero not allowed");
        return TIMES.on(oDouble.inverseValue(), oSet);
    }

    @Contract(pure = true) @NotNull OSet on(@NotNull OSet oSet, @NotNull OFraction oFraction) {
        if (DoubleComparator.isZero(oFraction.getDouble()))
            throw new IllegalArgumentException("Division by Zero not allowed");
        return on(oSet, new ODouble(oFraction.getDouble()));
    }

    //endregion

    //region Matrix
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix oMatrix, @NotNull ODouble oDouble) {
        if (DoubleComparator.isZero(oDouble.getDouble()))
            throw new IllegalArgumentException("Division by Zero not allowed");
        return TIMES.on(oDouble.inverseValue(), oMatrix);
    }

    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix oMatrix, @NotNull OFraction oFraction) {
        if (DoubleComparator.isZero(oFraction.getDouble()))
            throw new IllegalArgumentException("Division by Zero not allowed");
        return on(oMatrix, new ODouble(oFraction.getDouble()));
    }

    //endregion

    //region Polynom
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OPolynom on(@NotNull OPolynom oPolynom1, @NotNull OPolynom oPolynom2) {
        for (double d : oPolynom2.getPolynom().getCoefficients())
            if (DoubleComparator.isZero(d))
                throw new IllegalArgumentException("Division by Zero not allowed");
        return new OPolynom(oPolynom1.getPolynom().multiply(oPolynom2.inverseValue().getPolynom()));
    }

    @Contract(pure = true) @NotNull OPolynom on(@NotNull OPolynom oPolynom, @NotNull ODouble oDouble) {
        if (DoubleComparator.isZero(oDouble.getDouble()))
            throw new IllegalArgumentException("Division by Zero not allowed");
        return TIMES.on(oDouble.inverseValue(), oPolynom);
    }

    @Contract(pure = true) @NotNull OPolynom on(@NotNull OPolynom oPolynom, @NotNull OFraction oFraction) {
        if (DoubleComparator.isZero(oFraction.getDouble()))
            throw new IllegalArgumentException("Division by Zero not allowed");
        return on(oPolynom, new ODouble(oFraction.getDouble()));
    }

    //endregion

    //region Tuple
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OTuple on(@NotNull OTuple oTuple1, @NotNull OTuple oTuple2) {
        for (double d : oTuple2.getTuple())
            if (DoubleComparator.isZero(d))
                throw new IllegalArgumentException("Division by Zero not allowed");
        return TIMES.on(oTuple1, oTuple2.inverseValue());
    }

    @Contract(pure = true) @NotNull OTuple on(@NotNull OTuple oTuple, @NotNull ODouble oDouble) {
        if (DoubleComparator.isZero(oDouble.getDouble()))
            throw new IllegalArgumentException("Division by Zero not allowed");
        return TIMES.on(oDouble.inverseValue(), oTuple);
    }

    @Contract(pure = true) @NotNull OTuple on(@NotNull OTuple oTuple, @NotNull OFraction oFraction) {
        if (DoubleComparator.isZero(oFraction.getDouble()))
            throw new IllegalArgumentException("Division by Zero not allowed");
        return TIMES.on(oFraction.inverseValue(), oTuple);
    }

    //endregion

}
