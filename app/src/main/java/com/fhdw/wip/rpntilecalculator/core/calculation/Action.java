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

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Action {

    protected Action scopedAction;

    @Contract(pure = true) public @NotNull Operand with(@NotNull Operand... operands)
            throws GenericCalculationException, ConcreteCalculationException
    {
        List<Class<? extends Operand>> operandClasses = new ArrayList<>();
        for (Operand operand : operands)
            operandClasses.add(operand.getClass());

        Operand resultOperand = null;

        try {
            if (operands.length == 2) {
                resultOperand = (Operand) scopedAction.getClass()
                        .getDeclaredMethod(
                                "on",
                                operandClasses.get(0),
                                operandClasses.get(1)
                        ).invoke(
                                scopedAction,
                                operands[0],
                                operands[1]
                        );
            }
        } catch (RuntimeException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new ConcreteCalculationException(e.getMessage());
        }

        if (resultOperand != null) return resultOperand;
        else throw new GenericCalculationException();
    }

    @Contract(pure = true) protected @NotNull Operand on(@NotNull ODouble oDouble1, @NotNull ODouble oDouble2) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull ODouble oDouble, @NotNull OFraction oFraction) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull ODouble oDouble, @NotNull OSet oSet) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull ODouble oDouble, @NotNull OMatrix oMatrix) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull ODouble oDouble, @NotNull OPolynom oPolynom) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull ODouble oDouble, @NotNull OTuple oTuple) throws GenericCalculationException {
        throw new GenericCalculationException();
    }

    @Contract(pure = true) protected @NotNull Operand on(@NotNull OFraction oFraction1, @NotNull OFraction oFraction) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OFraction oFraction, @NotNull ODouble oDouble) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OFraction oFraction, @NotNull OSet oSet) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OFraction oFraction, @NotNull OMatrix oMatrix) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OFraction oFraction, @NotNull OPolynom oPolynom) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OFraction oFraction, @NotNull OTuple oTuple) throws GenericCalculationException {
        throw new GenericCalculationException();
    }

    @Contract(pure = true) protected @NotNull Operand on(@NotNull OSet oSet1, @NotNull OSet oSet2) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OSet oSet, @NotNull ODouble oDouble) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OSet oSet, @NotNull OFraction oFraction) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OSet oSet, @NotNull OMatrix oMatrix) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OSet oSet, @NotNull OPolynom oPolynom) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OSet oSet, @NotNull OTuple oTuple) throws GenericCalculationException {
        throw new GenericCalculationException();
    }

    @Contract(pure = true) protected @NotNull Operand on(@NotNull OMatrix oMatrix1, @NotNull OMatrix oMatrix2) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OMatrix oMatrix, @NotNull ODouble oDouble) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OMatrix oMatrix, @NotNull OFraction oFraction) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OMatrix oMatrix, @NotNull OSet oSet) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OMatrix oMatrix, @NotNull OPolynom oPolynom) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OMatrix oMatrix, @NotNull OTuple oTuple) throws GenericCalculationException {
        throw new GenericCalculationException();
    }

    @Contract(pure = true) protected @NotNull Operand on(@NotNull OPolynom oPolynom1, @NotNull OPolynom o2) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OPolynom oPolynom, @NotNull ODouble o2) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OPolynom oPolynom, @NotNull OFraction o2) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OPolynom oPolynom, @NotNull OSet o2) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OPolynom oPolynom, @NotNull OMatrix o2) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OPolynom oPolynom, @NotNull OTuple o2) throws GenericCalculationException {
        throw new GenericCalculationException();
    }

    @Contract(pure = true) protected @NotNull Operand on(@NotNull OTuple oTuple1, @NotNull OTuple oTuple2) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OTuple oTuple, @NotNull ODouble oDouble) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OTuple oTuple, @NotNull OFraction oFraction) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OTuple oTuple, @NotNull OSet oSet) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OTuple oTuple, @NotNull OMatrix oMatrix) throws GenericCalculationException {
        throw new GenericCalculationException();
    }
    @Contract(pure = true) protected @NotNull Operand on(@NotNull OTuple oTuple, @NotNull OPolynom oPolynom) throws GenericCalculationException {
        throw new GenericCalculationException();
    }

}
