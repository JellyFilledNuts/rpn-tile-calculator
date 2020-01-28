package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.presenter.Presenter;

public class AllClear extends Setting {

    @Contract(pure = true) @NotNull
    public static AllClear getInstance() { return new AllClear(); }

    /**
     * Clears the entire Stack and current input
     */
    @Override
    public boolean call() {
        Presenter.OPERAND_STACK.clear();
        Presenter.resetInputTerm(null);
        Presenter.updateStack();
        return true;
    }

}