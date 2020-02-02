package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.presenter.Presenter;

public class DeleteEntry extends Setting {

    @Contract(pure = true) @NotNull
    public static DeleteEntry getInstance() { return new DeleteEntry(); }

    /**
     * Delete the last entry and resetting the input term
     */
    @Override
    public boolean call() {
        Presenter.OPERAND_STACK.pop();
        Presenter.resetInputTerm(Presenter.OPERAND_STACK.peek());
        Presenter.updateStack();
        return true;
    }
}
