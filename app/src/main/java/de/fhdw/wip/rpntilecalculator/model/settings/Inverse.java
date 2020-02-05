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
        Presenter presenter = Presenter.getInstance();
        if(presenter.getOperandStack().size() == 0) return false;
        Operand operand = presenter.getOperandStack().peek();
        presenter.getOperandStack().pop();
        Operand result = operand.inverseValue();
        presenter.getOperandStack().push(result);
        presenter.resetInputTerm(result);
        presenter.updateStack();
        return true;
    }
}
