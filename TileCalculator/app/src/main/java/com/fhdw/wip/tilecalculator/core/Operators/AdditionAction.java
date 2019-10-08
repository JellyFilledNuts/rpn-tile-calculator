package com.fhdw.wip.tilecalculator.core.Operators;

import com.fhdw.wip.tilecalculator.core.Action;
import com.fhdw.wip.tilecalculator.core.Operand;
import com.fhdw.wip.tilecalculator.core.Operands.Number;

import java.util.List;

public class AdditionAction extends Action {

    public AdditionAction(){
        super("Add");
    }

    @Override
    public void call(List<Operand> stack) {
        if(stack.get(0) instanceof Number){
            if(stack.get(1) instanceof Number){
                stack.add(0, new Number((double)stack.remove(0).getObject() + (double)stack.remove(0).getObject()));
            }
        }
    }
}
