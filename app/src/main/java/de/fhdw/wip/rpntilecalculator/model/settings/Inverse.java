package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.controller.Controller;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

public class Inverse extends Setting {

    @Contract(pure = true) @NotNull
    public static Inverse getInstance() { return new Inverse(); }

    /**
     * Changes + values to - and vice versa
     */
    @Override
    public boolean call() {
        if(Controller.OPERAND_STACK.size() == 0) return false;
        Operand operand = Controller.OPERAND_STACK.peek();
        Controller.OPERAND_STACK.pop();
        Operand result = operand.inverseValue();
        Controller.OPERAND_STACK.push(result);
        Controller.resetInputTerm(result);
        Controller.callStackUpdateEvent();
        return true;
    }
}
