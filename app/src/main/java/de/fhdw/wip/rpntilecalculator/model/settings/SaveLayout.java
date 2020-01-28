package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.controller.Controller;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayout;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutFactory;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutLoader;

public class SaveLayout extends Setting {

    @Contract(pure = true) @NotNull
    public static SaveLayout getInstance() { return new SaveLayout(); }

    /**
     * Clears the entire Stack and current input
     */
    @Override
    public boolean call() {
        MainActivity activity = MainActivity.mainActivity;
        TileLayout t = activity.getTileLayout();
        t.setIndicator("Main");
        TileLayoutLoader.saveLayout(activity.getBaseContext(), activity.getTileLayout());
        return true;
    }

}