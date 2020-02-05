package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.presenter.Presenter;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

/**
 * Summary: Swaps the last two stack operands
 * Author:  Hendrik Falk
 * Date:    2020/01/30
 */
public class Swap extends Setting {

    @Contract(pure = true) @NotNull
    public static Swap getInstance() { return new Swap(); }

    /**
     * Swap the last entry with the one before
     */
    @Override
    public boolean call() {
        Presenter presenter = Presenter.getInstance();
        if(presenter.getOperandStack().size() < 2) return false;

        Operand one = presenter.getOperandStack().pop();
        Operand two = presenter.getOperandStack().pop();

        presenter.getOperandStack().push(one);
        presenter.getOperandStack().push(two);
        presenter.resetInputTerm(two);

        presenter.updateStack();
        return true;
    }
}
