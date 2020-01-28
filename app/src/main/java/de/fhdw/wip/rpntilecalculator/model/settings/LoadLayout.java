package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutFactory;

public class LoadLayout extends Setting {

    @Contract(pure = true) @NotNull
    public static LoadLayout getInstance() { return new LoadLayout(); }

    /**
     * Clears the entire Stack and current input
     */
    @Override
    public boolean call() {
        MainActivity activity = MainActivity.mainActivity;
        activity.setTileLayout(TileLayoutFactory.createLayout(activity.getBaseContext(), "Main"));
        return true;
    }

}