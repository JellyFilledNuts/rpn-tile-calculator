package com.fhdw.wip.tilecalculator.core.Operands;

import com.fhdw.wip.tilecalculator.core.Operand;

import java.util.List;

public class Set extends Operand {
    private List<Operand> set;

    public Set(List<Operand> set){
        this.set = set;
    }

    @Override
    public Object getObject() {
        return null;
    }
}
