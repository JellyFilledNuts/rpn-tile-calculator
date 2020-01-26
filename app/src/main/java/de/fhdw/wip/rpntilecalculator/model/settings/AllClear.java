package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.controller.Controller;

public class AllClear extends Setting {

    @Contract(pure = true) @NotNull
    public static AllClear getInstance() { return new AllClear(); }

    /**
     * Clears the entire Stack and current input
     */
    @Override
    public boolean call() {
        Controller.OPERAND_STACK.clear();
        Controller.resetInputTerm(null);
        Controller.callStackUpdateEvent();
        return true;
    }

}