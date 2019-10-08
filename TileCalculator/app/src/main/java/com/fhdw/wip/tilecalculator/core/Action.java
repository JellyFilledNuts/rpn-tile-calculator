package com.fhdw.wip.tilecalculator.core;

import java.util.List;

public abstract class Action {
    private String name;

    public Action(String name){
        this.name = name;
    }

    public abstract void call(List<Operand> stack);
}
