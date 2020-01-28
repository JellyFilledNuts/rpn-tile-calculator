package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.presenter.Presenter;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

public class Swap extends Setting {

    @Contract(pure = true) @NotNull
    public static Swap getInstance() { return new Swap(); }

    /**
     * Swap the last entry with the one before
     */
    @Override
    public boolean call() {
        if(Presenter.OPERAND_STACK.size() < 2) return false;

        Operand one = Presenter.OPERAND_STACK.pop();
        Operand two = Presenter.OPERAND_STACK.pop();

        Presenter.OPERAND_STACK.push(one);
        Presenter.OPERAND_STACK.push(two);
        Presenter.resetInputTerm(two);

        Presenter.updateStack();
        return true;
    }
}
