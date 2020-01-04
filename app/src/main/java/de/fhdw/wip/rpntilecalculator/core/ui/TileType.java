package de.fhdw.wip.rpntilecalculator.core.ui;

import de.fhdw.wip.rpntilecalculator.core.calculation.Action;
import de.fhdw.wip.rpntilecalculator.core.calculation.Minus;
import de.fhdw.wip.rpntilecalculator.core.calculation.Plus;
import de.fhdw.wip.rpntilecalculator.core.calculation.Slash;

public enum TileType {

    STACK("", null),
    O_MINUS("-", Minus.getInstance()),
    O_PLUS("+", Plus.getInstance()),
    O_SLASH("/", Slash.getInstance());

    private String text;
    private Action action;

    TileType(String text, Action action) {
        this.text = text;
        this.action = action;
    }



}