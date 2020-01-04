package de.fhdw.wip.rpntilecalculator.core.calculation;

import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OMatrix;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OTuple;
import de.fhdw.wip.rpntilecalculator.core.model.operand.Operand;

import java.lang.Math;
import java.lang.reflect.Array;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Contract;

public class Power extends Action{

    @NotNull private static final Power POWER = new Power();
    @NotNull private static final Times TIMES = Times.getInstance();

    @Contract(pure = true) @NotNull public static Power getInstance() { return POWER; }
    private Power() { }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    //region Double
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble base, @NotNull ODouble exponent) {
        return new ODouble(Math.pow(base.getDouble(), exponent.getDouble()));
    }

    @Contract(pure = true) @NotNull ODouble on(@NotNull ODouble base, @NotNull OFraction exponent) {
        return new ODouble(Math.pow(base.getDouble(), exponent.getDouble()));
    }

    //region Fraction
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OFraction on(@NotNull OFraction base, @NotNull ODouble exponent){
        return new OFraction(Math.pow(base.getDouble(), exponent.getDouble()));
    }

    @Contract(pure = true) @NotNull OFraction on(@NotNull OFraction base, @NotNull OFraction exponent){
        return new OFraction(Math.pow(base.getDouble(), exponent.getDouble()));
    }

    //region Matrix
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix base, @NotNull ODouble exponent) {
        if(base.getMatrix().isSquare()) {
            OMatrix resultMatrix = TIMES.on(base, base);

            if (exponent.getDouble() > 2) {
                for (int i = 2; i < exponent.getDouble(); i++)
                    resultMatrix = TIMES.on(resultMatrix, base);
            }
            return resultMatrix;
        }else
        {
            throw new IllegalArgumentException("You need a square matrix for power operation.");
        }
    }

    @Contract(pure = true) @NotNull OMatrix on(@NotNull OMatrix base, @NotNull OFraction exponent){
        return TIMES.on(TIMES.on(exponent, base), base);
    }

    //region Vector
    //------------------------------------------------------------------------------------

    @Contract(pure = true) @NotNull OTuple on(@NotNull OTuple base, @NotNull ODouble exponent) {
        double[] arrayTuple = base.getTuple();
        if(arrayTuple.length == 1)
        {
            arrayTuple[0] = Math.pow(Array.getDouble(arrayTuple,0), exponent.getDouble());
            return new OTuple(arrayTuple);
        }else
        {
            throw new IllegalArgumentException("You need a square matrix for power operation.");
        }
    }

    @Contract(pure = true) @NotNull OTuple on(@NotNull OTuple base, @NotNull OFraction exponent) {
        return on(base, new ODouble(exponent.getDouble()));
    }
}
