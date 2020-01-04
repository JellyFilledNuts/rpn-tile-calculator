package de.fhdw.wip.rpntilecalculator.core.calculation;

import de.fhdw.wip.rpntilecalculator.core.model.operand.Operand;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/*
 * Summary: The framework for defining Actions. Actions are able to work with operands from the stack or executer functions.
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class Action {

    protected Action scopedAction;

    @Contract(pure = true)
    public @NotNull Operand with(@NotNull List<Operand> operands) throws CalculationException {
        Operand[] target = new Operand[operands.size()];
        for (int i = 0; i < target.length; i++) {
            target[i] = operands.get(i);
        }
        return with(target);
    }

    @Contract(pure = true) public @NotNull Operand with(@NotNull Operand... operands) throws CalculationException {
        Class[] operandClasses = new Class[operands.length];
        Operand resultOperand;

        for (int i = 0; i < operands.length; i++)
            operandClasses[i] = operands[i].getClass();

        try {
            resultOperand = (Operand) scopedAction.getClass()
                    .getDeclaredMethod(
                            "on",
                            operandClasses
                    ).invoke(
                            scopedAction,
                            (Object[]) operands
                    );
        } catch (RuntimeException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new CalculationException(e.getMessage());
        }

        if (resultOperand != null) return resultOperand;
        else throw new CalculationException();
    }

}
