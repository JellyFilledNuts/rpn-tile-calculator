package de.fhdw.wip.rpntilecalculator.core.ui;

public enum TileType {

    STACK,
    OPERAND,
    ACTION,
    SETTING;

    public boolean isStack() {
        return this == STACK;
    }

    public boolean isOperand() {
        return this == OPERAND;
    }

    public boolean isAction() {
        return this == ACTION;
    }

    public boolean isSetting() {
        return this== SETTING;
    }
}