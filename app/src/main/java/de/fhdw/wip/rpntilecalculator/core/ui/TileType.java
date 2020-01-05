package de.fhdw.wip.rpntilecalculator.core.ui;

import de.fhdw.wip.rpntilecalculator.core.calculation.Action;
import de.fhdw.wip.rpntilecalculator.core.calculation.Minus;
import de.fhdw.wip.rpntilecalculator.core.calculation.Plus;
import de.fhdw.wip.rpntilecalculator.core.calculation.Slash;
import de.fhdw.wip.rpntilecalculator.core.calculation.Times;

public enum TileType {

    STACK("", null),
    OPERATOR("0", null),
    O_MINUS("-", Minus.getInstance()),
    O_PLUS("+", Plus.getInstance()),
    O_SLASH("/", Slash.getInstance()),
    O_TIMES("*", Times.getInstance());

    private String text;
    private Action action;

    TileType(String text, Action action) {
        this.text = text;
        this.action = action;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }


}