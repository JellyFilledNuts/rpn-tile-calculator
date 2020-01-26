package de.fhdw.wip.rpntilecalculator.view;

import android.graphics.Matrix;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.fraction.Fraction;

import java.util.Set;

import de.fhdw.wip.rpntilecalculator.model.calculation.Action;
import de.fhdw.wip.rpntilecalculator.model.calculation.Minus;
import de.fhdw.wip.rpntilecalculator.model.calculation.Plus;
import de.fhdw.wip.rpntilecalculator.model.calculation.Slash;
import de.fhdw.wip.rpntilecalculator.model.calculation.Times;
import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OFraction;
import de.fhdw.wip.rpntilecalculator.model.operands.OMatrix;
import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;
import de.fhdw.wip.rpntilecalculator.model.operands.OSet;
import de.fhdw.wip.rpntilecalculator.model.operands.OTuple;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.model.settings.AllClear;
import de.fhdw.wip.rpntilecalculator.model.settings.DeleteEntry;
import de.fhdw.wip.rpntilecalculator.model.settings.Enter;
import de.fhdw.wip.rpntilecalculator.model.settings.Setting;
import de.fhdw.wip.rpntilecalculator.model.settings.Swap;

public enum TileMapping {

    O_DOUBLE(TileType.OPERAND, ODouble.class),
    O_FRACTION(TileType.OPERAND, OFraction.class),
    O_MATRIX(TileType.OPERAND, OMatrix.class),
    O_POLYNOM(TileType.OPERAND, OPolynom.class),
    O_SET(TileType.OPERAND, OSet.class),
    O_TUPLE(TileType.OPERAND, OTuple.class),
    
    A_MINUS(TileType.ACTION, Minus.getInstance(), "-"),
    A_PLUS(TileType.ACTION, Plus.getInstance(), "+"),
    A_SLASH(TileType.ACTION, Slash.getInstance(), "/"),
    A_TIMES(TileType.ACTION, Times.getInstance(), "*"),

    S_AC(TileType.SETTING, AllClear.getInstance(), "AC"),
    S_DEL(TileType.SETTING, DeleteEntry.getInstance(), "Delete"),
    S_ENTER(TileType.SETTING, Enter.getInstance(), "Enter"),
    S_SWAP(TileType.SETTING, Swap.getInstance(), "Swap"),

    S_STACK(TileType.STACK, ""),

    X_ERROR(TileType.ERROR, "N/A");


    private TileType type;
    private Action actionType;
    private String actionText;
    private String settingText;
    private Setting settingType;
    private Class<? extends Operand> operandType;

    TileMapping(TileType type, String actionText) {
        this.type = type;
        this.actionText = actionText;
    }

    TileMapping(TileType type, Setting settingType, String settingText) {
        this.type = type;
        this.settingType = settingType;
        this.settingText = settingText;
    }

    TileMapping(TileType type, Action actionType, String actionText) {
        this.type = type;
        this.actionType = actionType;
        this.actionText = actionText;
    }

    TileMapping(TileType type, Class<? extends Operand>  operandType) {
        this.type = type;
        this.operandType = operandType;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public Class<? extends Operand> getOperandType() {
        return operandType;
    }

    public Action getActionType() {
        return actionType;
    }

    public String getActionText() {
        return actionText;
    }

    public String getSettingText() {
        return settingText;
    }

    public Setting getSettingType() {
        return settingType;
    }
}
