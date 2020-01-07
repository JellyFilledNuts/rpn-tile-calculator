package de.fhdw.wip.rpntilecalculator.core.ui.layout;

import de.fhdw.wip.rpntilecalculator.core.calculation.Action;
import de.fhdw.wip.rpntilecalculator.core.calculation.Minus;
import de.fhdw.wip.rpntilecalculator.core.ui.TileType;

public enum TileMapping {

    O_DOUBLE(TileType.OPERAND, "ODouble"),
    A_MINUS(TileType.ACTION, Minus.getInstance(), "-");


    private TileType type;
    private Action actionType;
    private String actionText;
    private String operandType;

    TileMapping(TileType type, Action actionType, String actionText) {
        this.type = type;
        this.actionType = actionType;
        this.actionText = actionText;
    }

    TileMapping(TileType type, String operandType) {
        this.type = type;
        this.operandType = operandType;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public String getOperandType() {
        return operandType;
    }

    public Action getActionType() {
        return actionType;
    }

    public String getActionText() {
        return actionText;
    }
}
