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
        Presenter.INPUT_TERM = new StringBuilder().append(Presenter.INPUT_FINALIZED);
        if(Presenter.OPERAND_STACK.size() != 0) {
            Presenter.add2History(Presenter.OPERAND_STACK.peek());
            Presenter.updateHistoryStack();
        }
        return true;
    }
}
