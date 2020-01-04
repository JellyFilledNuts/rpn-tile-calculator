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
    protected boolean positionDoesNotMatter;

    @Contract(pure = true)
    public @NotNull Operand with(@NotNull List<Operand> operands) throws CalculationException {
        Operand[] target = new Operand[operands.size()];
        for (int i = 0; i < target.length; i++) {
            target[i] = operands.get(i);
        }
        return with(target);
    }

    @Contract(pure = true) public @NotNull Operand with(@NotNull Operand... operands) throws CalculationException {
        List<Class<? extends Operand>> operandClasses = new ArrayList<>();
        for (Operand operand : operands)
            operandClasses.add(operand.getClass());
        System.out.println("test");
        Operand resultOperand = null;

        try {
            System.out.println(operands.length);
            if (operands.length == 2 && !positionDoesNotMatter) {
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
                System.out.println("op length passt");
            } else if (operands.length == 2 && positionDoesNotMatter) {
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
