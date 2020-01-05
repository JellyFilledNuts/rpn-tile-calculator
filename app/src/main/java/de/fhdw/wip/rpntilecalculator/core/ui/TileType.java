package de.fhdw.wip.rpntilecalculator.core.ui;

import de.fhdw.wip.rpntilecalculator.core.calculation.Action;
import de.fhdw.wip.rpntilecalculator.core.calculation.Minus;
import de.fhdw.wip.rpntilecalculator.core.calculation.Plus;
import de.fhdw.wip.rpntilecalculator.core.calculation.Slash;
import de.fhdw.wip.rpntilecalculator.core.calculation.Times;

public enum TileType {

    STACK("", null),
    OPERAND("0", null),
    O_MINUS("-", Minus.getInstance()),
    O_PLUS("+", Plus.getInstance()),
    O_SLASH("/", Slash.getInstance()),
    O_TIMES("*", Times.getInstance());

    private String displayText;
    private Action action;

    TileType(String text, Action action) {
        this.displayText = text;
        this.action = action;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

}