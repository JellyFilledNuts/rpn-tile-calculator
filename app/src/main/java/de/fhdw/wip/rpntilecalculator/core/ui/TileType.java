package de.fhdw.wip.rpntilecalculator.core.ui;

import de.fhdw.wip.rpntilecalculator.R;

public enum TileType {

    STACK(R.drawable.tile_stack_green),
    OPERAND(R.drawable.tile_operand_white),
    ACTION(R.drawable.tile_operator_blue),
    SETTING(R.drawable.tile_settings_grey);

    private int style;

    TileType(int style) {
        this.style = style;
    }

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

    public int getStyle() {
        return style;
    }
}