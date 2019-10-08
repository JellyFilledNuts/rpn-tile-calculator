package com.fhdw.wip.tilecalculator.core.Operands;

import com.fhdw.wip.tilecalculator.core.Operand;

public class Number extends Operand {
    private double number;

    public Number(Double number){
        this.number = number;
    }

    @Override
    public Object getObject() {
        return number;
    }
}
