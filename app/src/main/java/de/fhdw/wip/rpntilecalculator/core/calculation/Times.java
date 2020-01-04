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

@SuppressWarnings({"unused", "WeakerAccess"})
public class Times extends Action {

    @NotNull private static final Times TIMES = new Times();

    @Contract(pure = true) @NotNull public static Times getInstance() { return TIMES; }
    private Times() { }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        positionDoesNotMatter = true;
        scopedAction = this;
        return super.with(operands);
    }

    //region Double
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble oDouble1, @NotNull ODouble oDouble2) {
        return new ODouble(oDouble1.getDouble() * oDouble2.getDouble());
    }

    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble oDouble, @NotNull OFraction oFraction) {
        return new ODouble(oDouble.getDouble() * oFraction.getDouble());
    }

    @Contract(pure = true) @NotNull OSet on(@NotNull ODouble oDouble, @NotNull OSet oSet) {
        Set<Double> newSet = new HashSet<>();
        for (double d : oSet.getDoubleSet())
            newSet.add(d * oDouble.getDouble());
        return new OSet(newSet);
    }

    @Contract(pure = true) @NotNull OMatrix on(@NotNull ODouble oDouble, @NotNull OMatrix oMatrix) {
        return new OMatrix(oMatrix.getMatrix().scalarMultiply(oDouble.getDouble()));
    }

    @Contract(pure = true) @NotNull OPolynom on(@NotNull ODouble oDouble, @NotNull OPolynom oPolynom) {
        double[] d = oPolynom.getPolynom().getCoefficients();
        d[0] *= oDouble.getDouble();
        return new OPolynom(new PolynomialFunction(d));
    }

    @Contract(pure = true) @NotNull OTuple on(@NotNull ODouble oDouble, @NotNull OTuple oTuple) {
        double[] oldTuple = oTuple.getTuple();
        double[] newTuple = new double[oldTuple.length];
        for (int i = 0; i < newTuple.length; i++)
            newTuple[i] = oldTuple[i] * oDouble.getDouble();
        return new OTuple(newTuple);
    }

    //endregion

    //region Fraction
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OFraction on(@NotNull OFraction oFraction1, @NotNull OFraction oFraction2) {
        return new OFraction(oFraction1.getFraction().multiply(oFraction2.getFraction()));
    }

    @Contract(pure = true) @NotNull OSet on(@NotNull OFraction oFraction, @NotNull OSet oSet) {
        return on(new ODouble(oFraction.getDouble()), oSet);
    }

    @Contract(pure = true) @NotNull OMatrix on(@NotNull OFraction oFraction, @NotNull OMatrix oMatrix) {
        return new OMatrix(oMatrix.getMatrix().scalarAdd(oFraction.getDouble()));
    }

    @Contract(pure = true) @NotNull OPolynom on(@NotNull OFraction oFraction, @NotNull OPolynom oPolynom) {
        return on(new ODouble(oFraction.getDouble()), oPolynom);
    }

    @Contract(pure = true) @NotNull OTuple on(@NotNull OFraction oFraction, @NotNull OTuple oTuple) {
        return on(new ODouble(oFraction.getDouble()), oTuple);
    }

    //endregion

    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix oMatrix1, @NotNull OMatrix oMatrix2) {
        return new OMatrix(oMatrix1.getMatrix().multiply(oMatrix2.getMatrix()));
    }

    @Contract(pure = true) @NotNull OPolynom on(@NotNull OPolynom oPolynom1, @NotNull OPolynom oPolynom2) {
        return new OPolynom(oPolynom1.getPolynom().multiply(oPolynom2.getPolynom()));
    }

    @Contract(pure = true) @NotNull OTuple on(@NotNull OTuple oTuple1, @NotNull OTuple oTuple2) {
        double[] tuple1 = oTuple1.getTuple();
        double[] tuple2 = oTuple2.getTuple();
        double[] tupleSum = new double[tuple2.length];

        if (tuple1.length != tuple2.length)
            throw new IllegalArgumentException("Tuples must have matching size.");

        for (int i = 0; i < tuple1.length; i++)
            tupleSum[i] = tuple1[i] * tuple2[i];

        return new OTuple(tupleSum);
    }

}
