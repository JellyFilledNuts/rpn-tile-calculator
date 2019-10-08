package com.fhdw.wip.tilecalculator.core;

public abstract class Operator {
    private Action action;

    public void call(){
        action.call(TileCalculator.getInstance().getStack());
    }
}
