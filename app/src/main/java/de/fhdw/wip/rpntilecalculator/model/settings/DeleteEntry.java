package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.presenter.Presenter;

/**
 * Summary: Deletes the last input of the stack
 * Author:  Hendrik Falk
 * Date:    2020/01/26
 */
public class DeleteEntry extends Setting {

    @Contract(pure = true) @NotNull
    public static DeleteEntry getInstance() { return new DeleteEntry(); }

    /**
     * Delete the last entry and resetting the input term
     */
    @Override
    public boolean call() {
        Presenter presenter = Presenter.getInstance();
        presenter.getOperandStack().pop();
        presenter.resetInputTerm(presenter.getOperandStack().peek());
        presenter.updateStack();
        presenter.finalizeInput();
        return true;
    }
}
