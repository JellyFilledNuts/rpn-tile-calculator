package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.controller.Controller;

public class DeleteEntry extends Setting {

    @Contract(pure = true) @NotNull
    public static DeleteEntry getInstance() { return new DeleteEntry(); }

    /**
     * Delete the last entry and resetting the input term
     */
    @Override
    public boolean call() {
        Controller.OPERAND_STACK.pop();
        Controller.resetInputTerm(Controller.OPERAND_STACK.peek());
        Controller.callStackUpdateEvent();
        return true;
    }
}
