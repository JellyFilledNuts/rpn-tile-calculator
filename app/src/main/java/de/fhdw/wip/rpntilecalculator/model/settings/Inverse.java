package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.presenter.Presenter;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

/**
 * Summary: Calculates the inverse of an operand
 * Author:  Hendrik Falk
 * Date:    2020/01/29
 */
public class Inverse extends Setting {

    @Contract(pure = true) @NotNull
    public static Inverse getInstance() { return new Inverse(); }

    /**
     * Changes + values to - and vice versa
     */
    @Override
    public boolean call() {
        if(Presenter.OPERAND_STACK.size() == 0) return false;
        Operand operand = Presenter.OPERAND_STACK.peek();
        Presenter.OPERAND_STACK.pop();
        Operand result = operand.inverseValue();
        Presenter.OPERAND_STACK.push(result);
        Presenter.resetInputTerm(result);
        Presenter.updateStack();
        return true;
    }
}
