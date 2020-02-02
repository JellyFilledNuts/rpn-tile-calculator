package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.presenter.Presenter;

public class ClearHistory extends Setting {

    @Contract(pure = true) @NotNull
    public static ClearHistory getInstance() { return new ClearHistory(); }

    /**
     * Clears the entire History
     */
    @Override
    public boolean call() {
        Presenter.HISTORY_STACK.clear();
        Presenter.updateHistoryStack();
        return true;
    }
}
