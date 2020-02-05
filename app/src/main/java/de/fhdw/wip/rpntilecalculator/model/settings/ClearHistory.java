package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.presenter.Presenter;

/**
 * Summary: Empties the history stack of the presenter
 * Author:  Hendrik Falk
 * Date:    2020/01/26
 */
public class ClearHistory extends Setting {

    @Contract(pure = true) @NotNull
    public static ClearHistory getInstance() { return new ClearHistory(); }

    /**
     * Clears the entire History
     */
    @Override
    public boolean call() {
        Presenter presenter = Presenter.getInstance();
        presenter.getHistoryStack().clear();
        presenter.updateHistoryStack();
        return true;
    }
}
