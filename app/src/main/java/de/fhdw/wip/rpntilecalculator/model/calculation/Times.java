package de.fhdw.wip.rpntilecalculator.model.calculation;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OFraction;
import de.fhdw.wip.rpntilecalculator.model.operands.OMatrix;
import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;
import de.fhdw.wip.rpntilecalculator.model.operands.OSet;
import de.fhdw.wip.rpntilecalculator.model.operands.OTuple;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/*
 * Summary: Defines the Times click. Lets the user Multiplies operands.
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
@SuppressWarnings({"unused"})
public class Times extends Action {

    @NotNull private static final Times TIMES = new Times();

    /*
     * Singleton for TIMES
     * @return singleton object
     */
    @Contract(pure = true) @NotNull public static Times getInstance() { return TIMES; }
    private Times() {
        requiredNumOfOperands = new int[]{2};
    }

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
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble oDouble1, @NotNull ODouble oDouble2) {
        return new ODouble(oDouble1.getDouble() * oDouble2.getDouble());
    }

    /*
     * Multiplying ODouble and oFraction
     * @param oDouble first operand
     * @param oFraction second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble oDouble, @NotNull OFraction oFraction) {
        return new ODouble(oDouble.getDouble() * oFraction.getDouble());
    }

    @Contract(pure = true) @NotNull ODouble on(@NotNull OFraction oFraction, @NotNull ODouble oDouble) {
        return on(oDouble, oFraction);
    }

    // endregion

    //region OSet
    //------------------------------------------------------------------------------------

    /*
     * Multiplying ODouble and oSet
     * @param oDouble first operand
     * @param oSet second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OSet on(@NotNull ODouble oDouble, @NotNull OSet oSet) {
        Set<Double> newSet = new HashSet<>();
        for (double d : oSet.getSet())
            newSet.add(d * oDouble.getDouble());
        return new OSet(newSet);
    }

    @Contract(pure = true) @NotNull OSet on(@NotNull OSet oSet, @NotNull ODouble oDouble) {
        return on(oDouble, oSet);
    }

    /*
     * Multiplying ODouble and oMatrix
     * @param oDouble first operand
     * @param oMatrix second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OMatrix on(@NotNull ODouble oDouble, @NotNull OMatrix oMatrix) {
        return new OMatrix(oMatrix.getMatrix().scalarMultiply(oDouble.getDouble()));
    }

    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix oMatrix, @NotNull ODouble oDouble) {
        return on(oDouble, oMatrix);
    }

    /*
     * Multiplying ODouble and oPolynom
     * @param oDouble first operand
     * @param oPolynom second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OPolynom on(@NotNull ODouble oDouble, @NotNull OPolynom oPolynom) {
        double[] d = oPolynom.getPolynom().getCoefficients();
        for (int i = 0; i < d.length; i++)
            d[i] *= oDouble.getDouble();
        return new OPolynom(new PolynomialFunction(d));
    }

    @Contract(pure = true) @NotNull OPolynom on(@NotNull OPolynom oPolynom, @NotNull ODouble oDouble) {
        return on(oDouble, oPolynom);
    }

    /*
     * Multiplying ODouble and oTuple
     * @param oDouble first operand
     * @param oTuple second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OTuple on(@NotNull ODouble oDouble, @NotNull OTuple oTuple) {
        double[] oldTuple = oTuple.getTuple();
        double[] newTuple = new double[oldTuple.length];
        for (int i = 0; i < newTuple.length; i++)
            newTuple[i] = oldTuple[i] * oDouble.getDouble();
        return new OTuple(newTuple);
    }

    @Contract(pure = true) @NotNull OTuple on(@NotNull OTuple oTuple, @NotNull ODouble oDouble) {
        return on(oDouble, oTuple);
    }

    //endregion

    //region Fraction
    //------------------------------------------------------------------------------------

    /*
     * Multiplying OFraction and OFraction
     * @param oFraction1 first operand
     * @param oFraction2 second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OFraction on(@NotNull OFraction oFraction1, @NotNull OFraction oFraction2) {
        return new OFraction(oFraction1.getFraction().multiply(oFraction2.getFraction()));
    }

    /*
     * Multiplying OFraction and OSet
     * @param oFraction first operand
     * @param oSet second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OSet on(@NotNull OFraction oFraction, @NotNull OSet oSet) {
        return on(new ODouble(oFraction.getDouble()), oSet);
    }

    /*
     * Multiplying OFraction and OMatrix
     * @param oFraction first operand
     * @param oMatrix second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OMatrix on(@NotNull OFraction oFraction, @NotNull OMatrix oMatrix) {
        return new OMatrix(oMatrix.getMatrix().scalarMultiply(oFraction.getDouble()));
    }

    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix oMatrix, @NotNull OFraction oFraction) {
        return on(oFraction, oMatrix);
    }

    /*
     * Multiplying OFraction and oPolynom
     * @param oFraction first operand
     * @param oPolynom second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OPolynom on(@NotNull OFraction oFraction, @NotNull OPolynom oPolynom) {
        return on(new ODouble(oFraction.getDouble()), oPolynom);
    }

    /*
     * Multiplying OFraction and oTuple
     * @param oFraction first operand
     * @param oTuple second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OTuple on(@NotNull OFraction oFraction, @NotNull OTuple oTuple) {
        return on(new ODouble(oFraction.getDouble()), oTuple);
    }

    //endregion

    /*
     * Multiplying OMatrix and OMatrix
     * @param oMatrix1 first operand
     * @param oMatrix2 second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix oMatrix1, @NotNull OMatrix oMatrix2) {
        return new OMatrix(oMatrix1.getMatrix().multiply(oMatrix2.getMatrix()));
    }

    /*
     * Multiplying OPolynom and OPolynom
     * @param oPolynom1 first operand
     * @param oPolynom2 second operand
     * @return product of params
     */
    @Contract(pure = true) @NotNull OPolynom on(@NotNull OPolynom oPolynom1, @NotNull OPolynom oPolynom2) {
        return new OPolynom(oPolynom1.getPolynom().multiply(oPolynom2.getPolynom()));
    }

    /*
     * Multiplying OTuple and OTuple
     * @param oTuple1 first operand
     * @param oTuple2 second operand
     * @return product of params
     */
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
