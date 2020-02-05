package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.presenter.Presenter;

/**
 * Summary: Finishes the current input term so that a new input can be created
 * Author:  Hendrik Falk
 * Date:    2020/01/27
 */
public class Enter extends Setting {

    @Contract(pure = true) @NotNull
    public static Enter getInstance() { return new Enter(); }

    /**
     * finalizes an input string
     */
    @Override
    public boolean call() {
        Presenter presenter = Presenter.getInstance();
        presenter.finalizeInput();
        if(presenter.getOperandStack().size() != 0) {
            presenter.add2History(presenter.getOperandStack().peek());
            presenter.updateHistoryStack();
        }
        return true;
    }
}
