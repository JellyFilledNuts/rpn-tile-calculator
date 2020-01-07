package de.fhdw.wip.rpntilecalculator.core.ui.layout;

/*
 * Summary: TileScheme contains the information about tiles and is used to create tiles and save them
 * Author:  Tom Bockhorn
 */


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fhdw.wip.rpntilecalculator.core.calculation.Action;
import de.fhdw.wip.rpntilecalculator.core.ui.TileType;

public class TileScheme {

    @NotNull private TileType type;
    @NotNull private String content;
    @Nullable private Action action;
    @Nullable private Operand operand;

    TileScheme(@NotNull TileType type, @NotNull String content, @NotNull Operand operand) {
        this(type, content, null, operand);
    }

    TileScheme(@NotNull TileType type, @NotNull String content, @NotNull Action action) {
        this(type, content, action, null);
    }

    TileScheme(@NotNull TileType type, @NotNull String content, @Nullable Action action, @Nullable Operand operand) {
        this.type = type;
        this.content = content;
        this.action = action;
        this.operand = operand;
    }

    @NotNull
    public TileType getType() {
        return type;
    }

    public void setType(@NotNull TileType type) {
        this.type = type;
    }

    @NotNull
    public String getContent() {
        return content;
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Operand getOperand() {
        return operand;
    }

    public void setOperand(Operand operand) {
        this.operand = operand;
    }
}
