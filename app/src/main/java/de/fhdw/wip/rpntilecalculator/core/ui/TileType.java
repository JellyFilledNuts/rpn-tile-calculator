package de.fhdw.wip.rpntilecalculator.core.ui;

public enum TileType {

    STACK,
    OPERAND,
    ACTION,
    SETTING;

    public static boolean isStack(TileType type) {
        return type == STACK;
    }

    public static boolean isOperand(TileType type) {
        return type == OPERAND;
    }

    public static boolean isAction(TileType type) {
        return type == ACTION;
    }

    public static boolean isSetting(TileType type) {
        return type == SETTING;
    }
}