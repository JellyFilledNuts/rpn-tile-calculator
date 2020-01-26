package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.fhdw.wip.rpntilecalculator.controller.Controller;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

public class Swap extends Setting {

    @Contract(pure = true) @NotNull
    public static Swap getInstance() { return new Swap(); }

    /**
     * Swap the last entry with the one before
     */
    @Override
    public boolean call() {
        if(Controller.OPERAND_STACK.size() < 2) return false;

        Operand one = Controller.OPERAND_STACK.pop();
        Operand two = Controller.OPERAND_STACK.pop();

        Controller.OPERAND_STACK.push(one);
        Controller.OPERAND_STACK.push(two);
        Controller.resetInputTerm(two);

        Controller.callStackUpdateEvent();
        return true;
    }
}
