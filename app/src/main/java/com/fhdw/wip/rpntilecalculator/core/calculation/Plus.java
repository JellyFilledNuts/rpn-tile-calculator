package com.fhdw.wip.rpntilecalculator.core.calculation;

import com.fhdw.wip.rpntilecalculator.core.calculation.exception.ConcreteCalculationException;
import com.fhdw.wip.rpntilecalculator.core.calculation.exception.GenericCalculationException;
import com.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import com.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;
import com.fhdw.wip.rpntilecalculator.core.model.operand.OMatrix;
import com.fhdw.wip.rpntilecalculator.core.model.operand.OPolynom;
import com.fhdw.wip.rpntilecalculator.core.model.operand.OSet;
import com.fhdw.wip.rpntilecalculator.core.model.operand.OTuple;
import com.fhdw.wip.rpntilecalculator.core.model.operand.Operand;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class Plus extends Action {

    @Nullable private static final Plus PLUS = new Plus();

    @Contract(pure = true)
    public static Plus getInstance() {
        return PLUS;
    }
    private Plus() { }

    @NotNull
    @Override
    public Operand with(@NotNull Operand... operands) throws GenericCalculationException, ConcreteCalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    //region Double
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull @Override
    protected Operand on(@NotNull ODouble oDouble1, @NotNull ODouble oDouble2) {
        return new ODouble(oDouble1.getDouble() + oDouble2.getDouble());
    }

    @Contract(pure = true) @NotNull @Override
    protected Operand on(@NotNull ODouble oDouble, @NotNull OFraction oFraction) {
        return new ODouble(oDouble.getDouble() + oFraction.getDouble());
    }

    @Contract(pure = true) @NotNull @Override
    protected Operand on(@NotNull ODouble oDouble, @NotNull OSet oSet) {
        Set<Double> newSet = new HashSet<>();
        for (double d : oSet.getDoubleSet())
            newSet.add(d + oDouble.getDouble());
        return new OSet(newSet);
    }

    @Contract(pure = true) @NotNull @Override
    protected Operand on(@NotNull ODouble oDouble, @NotNull OMatrix oMatrix) {
        return new OMatrix(oMatrix.getMatrix().scalarAdd(oDouble.getDouble()));
    }

    @Contract(pure = true) @NotNull @Override
    protected Operand on(@NotNull ODouble oDouble, @NotNull OPolynom oPolynom) {
        double[] d = oPolynom.getPolynom().getCoefficients();
        d[0] += oDouble.getDouble();
        return new OPolynom(new PolynomialFunction(d));
    }

    @Contract(pure = true) @NotNull @Override
    protected Operand on(@NotNull ODouble oDouble, @NotNull OTuple oTuple) {
        List<Double> tuple = new ArrayList<>();
        for (double d : oTuple.getTuple())
            tuple.add(oDouble.getDouble() + d);
        return new OTuple(tuple);
    }

    //------------------------------------------------------------------------------------
    //endregion

    //region Fraction
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull @Override
    protected Operand on(@NotNull OFraction oFraction1, @NotNull OFraction oFraction2) {
        return new OFraction(oFraction1.getFraction().add(oFraction2.getFraction()));
    }

    @Contract(pure = true) @NotNull @Override
    protected Operand on(@NotNull OFraction oFraction, @NotNull OSet oSet) {
        Set<Double> newSet = new HashSet<>();
        for (double d : oSet.getDoubleSet())
            newSet.add(d + oFraction.getDouble());
        return new OSet(newSet);
    }

    @Contract(pure = true) @NotNull @Override
    protected Operand on(@NotNull OFraction oFraction, @NotNull OMatrix oMatrix) {
        return new OMatrix(oMatrix.getMatrix().scalarAdd(oFraction.getDouble()));
    }

    @Contract(pure = true) @NotNull @Override
    protected Operand on(@NotNull OFraction oFraction, @NotNull OPolynom oPolynom) {
        double[] d = oPolynom.getPolynom().getCoefficients();
        d[0] += oFraction.getDouble();
        return new OPolynom(new PolynomialFunction(d));
    }

    @Contract(pure = true) @NotNull @Override
    protected Operand on(@NotNull OFraction oFraction, @NotNull OTuple oTuple) {
        List<Double> tuple = new ArrayList<>();
        for (double d : oTuple.getTuple())
            tuple.add(oFraction.getDouble() + d);
        return new OTuple(tuple);
    }

    //endregion

    //region Matrix
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull @Override
    protected Operand on(@NotNull OMatrix oMatrix1, @NotNull OMatrix oMatrix2) {
        return new OMatrix(oMatrix1.getMatrix().add(oMatrix2.getMatrix()));
    }

    //endregion

    //region Polynom
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull @Override
    protected Operand on(@NotNull OPolynom oPolynom1, @NotNull OPolynom oPolynom2) {
        return new OPolynom(oPolynom1.getPolynom().add(oPolynom2.getPolynom()));
    }

    //endregion

    //region Tuple
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull @Override
    protected Operand on(@NotNull OTuple oTuple1, @NotNull OTuple oTuple2) {
        List<Double> tuple1 = oTuple1.getTuple();
        List<Double> tuple2 = oTuple2.getTuple();
        List<Double> tupleSum = new ArrayList<>();

        if (tuple1.size() != tuple2.size())
            throw new IllegalArgumentException("Tuples must have matching size.");

        for (int i = 0; i < tuple1.size(); i++) {
            tupleSum.add(tuple1.get(i) + tuple2.get(i));
        }

        return new OTuple(tupleSum);
    }

    //endregion

}
