package com.fhdw.wip.rpntilecalculator.core.calculation;

import com.fhdw.wip.rpntilecalculator.core.model.operand.Operand;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Action {

    protected Action scopedAction;
    protected boolean positionDoesNotMatter;

    @Contract(pure = true) public @NotNull Operand with(@NotNull Operand... operands) throws CalculationException {
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
            } else if (positionDoesNotMatter) {
                resultOperand = (Operand) scopedAction.getClass()
                        .getDeclaredMethod(
                                "on",
                                operandClasses.get(1),
                                operandClasses.get(0)
                        ).invoke(
                                scopedAction,
                                operands[1],
                                operands[0]
                        );
            }
        } catch (RuntimeException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new CalculationException(e.getMessage());
        }

        if (resultOperand != null) return resultOperand;
        else throw new CalculationException();
    }

}
