package de.fhdw.wip.rpntilecalculator.core.ui;

import de.fhdw.wip.rpntilecalculator.core.calculation.Action;
import de.fhdw.wip.rpntilecalculator.core.calculation.Minus;
import de.fhdw.wip.rpntilecalculator.core.calculation.Plus;

public enum TileType {

    STACK("", null),
    O_MINUS("-", Minus.getInstance()),
    O_PLUS("+", Plus.getInstance());

    private String text;
    private Action action;

    TileType(String text, Action action) {
        this.text = text;
        this.action = action;
    }



}