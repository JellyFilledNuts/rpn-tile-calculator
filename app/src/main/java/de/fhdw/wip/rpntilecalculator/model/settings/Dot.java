package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.presenter.Presenter;

public class Dot extends Setting {

    @Contract(pure = true) @NotNull
    public static Dot getInstance() { return new Dot(); }

    @Override
    public boolean call() {
        if(!Presenter.INPUT_TERM.toString().contains(".")) Presenter.INPUT_TERM.append(".");
        Presenter.updateStack();
        return true;
    }
}
