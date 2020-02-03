package de.fhdw.wip.rpntilecalculator.view;

import de.fhdw.wip.rpntilecalculator.model.calculation.Action;
import de.fhdw.wip.rpntilecalculator.model.calculation.Cosinus;
import de.fhdw.wip.rpntilecalculator.model.calculation.Derivation;
import de.fhdw.wip.rpntilecalculator.model.calculation.HighAndLowPoints;
import de.fhdw.wip.rpntilecalculator.model.calculation.Logarithm;
import de.fhdw.wip.rpntilecalculator.model.calculation.Minus;
import de.fhdw.wip.rpntilecalculator.model.calculation.Modulo;
import de.fhdw.wip.rpntilecalculator.model.calculation.Plus;
import de.fhdw.wip.rpntilecalculator.model.calculation.Power;
import de.fhdw.wip.rpntilecalculator.model.calculation.Root;
import de.fhdw.wip.rpntilecalculator.model.calculation.Sinus;
import de.fhdw.wip.rpntilecalculator.model.calculation.Slash;
import de.fhdw.wip.rpntilecalculator.model.calculation.Tangens;
import de.fhdw.wip.rpntilecalculator.model.calculation.Times;
import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OFraction;
import de.fhdw.wip.rpntilecalculator.model.operands.OMatrix;
import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;
import de.fhdw.wip.rpntilecalculator.model.operands.OSet;
import de.fhdw.wip.rpntilecalculator.model.operands.OTuple;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.model.operands.OEmpty;
import de.fhdw.wip.rpntilecalculator.model.settings.AllClear;
import de.fhdw.wip.rpntilecalculator.model.settings.ClearHistory;
import de.fhdw.wip.rpntilecalculator.model.settings.DeleteEntry;
import de.fhdw.wip.rpntilecalculator.model.settings.Enter;
import de.fhdw.wip.rpntilecalculator.model.settings.Inverse;
import de.fhdw.wip.rpntilecalculator.model.settings.LoadLayout;
import de.fhdw.wip.rpntilecalculator.model.settings.SaveLayout;
import de.fhdw.wip.rpntilecalculator.model.settings.Split;
import de.fhdw.wip.rpntilecalculator.model.settings.TurnAroundSign;
import de.fhdw.wip.rpntilecalculator.model.settings.Setting;
import de.fhdw.wip.rpntilecalculator.model.settings.Swap;

public enum TileMapping {

    O_DOUBLE(TileType.OPERAND, ODouble.class, "Zahl"),
    O_FRACTION(TileType.OPERAND, OFraction.class, "Bruch"),
    O_MATRIX(TileType.OPERAND, OMatrix.class, "Matrix"),
    O_POLYNOM(TileType.OPERAND, OPolynom.class, "Funktion"),
    O_SET(TileType.OPERAND, OSet.class, "Menge"),
    O_TUPLE(TileType.OPERAND, OTuple.class, "Tuple"),
    O_Empty(TileType.OPERAND, OEmpty.class, "Empty"),
    
    A_MINUS(TileType.ACTION, Minus.getInstance(), "-"),
    A_PLUS(TileType.ACTION, Plus.getInstance(), "+"),
    A_SLASH(TileType.ACTION, Slash.getInstance(), "/"),
    A_TIMES(TileType.ACTION, Times.getInstance(), "*"),
    A_MODULO(TileType.ACTION, Modulo.getInstance(), "%"),
    A_LOG(TileType.ACTION, Logarithm.getInstance(), "log"),
    A_POWER(TileType.ACTION, Power.getInstance(), "bⁿ"),
    A_ROOT(TileType.ACTION, Root.getInstance(), "√"),
    A_DERIVE(TileType.ACTION, Derivation.getInstance(), "dy / dx"),
    A_HIGHLOW(TileType.ACTION, HighAndLowPoints.getInstance(), "Extrema"),
    A_SIN(TileType.ACTION, Sinus.getInstance(), "sin"),
    A_COS(TileType.ACTION, Cosinus.getInstance(), "cos"),
    A_TAN(TileType.ACTION, Tangens.getInstance(), "tan"),

    S_AC(TileType.SETTING, AllClear.getInstance(), "AC"),
    S_DEL(TileType.SETTING, DeleteEntry.getInstance(), "Delete"),
    S_ENTER(TileType.SETTING, Enter.getInstance(), "Enter"),
    S_SWAP(TileType.SETTING, Swap.getInstance(), "Swap"),
    S_TURNAROUNDSIGN(TileType.SETTING, TurnAroundSign.getInstance(), "+/-"),
    S_INVERSE(TileType.SETTING, Inverse.getInstance(), "1/x"),
    S_LOADLAYOUT(TileType.SETTING, LoadLayout.getInstance(), "Load Layout"),
    S_SAVELAYOUT(TileType.SETTING, SaveLayout.getInstance(), "Save Layout"),
    S_CLEARHISTORY(TileType.SETTING, ClearHistory.getInstance(), "Clear History"),
    S_SPLIT(TileType.SETTING, Split.getInstance(), "Split"),

    S_STACK(TileType.STACK, ""),

    H_HISTORY(TileType.HISTORY, ""),

    X_ERROR(TileType.ERROR, "N/A");


    private TileType type;
    private Action actionType;
    private String actionText;
    private String settingText;
    private Setting settingType;
    private String menuText;
    private Class<? extends Operand> operandType;

    // Stack & Error
    TileMapping(TileType type, String actionText) {
        this.type = type;
        this.actionText = actionText;
        this.menuText = actionText;
    }

    // Setting
    TileMapping(TileType type, Setting settingType, String settingText) {
        this.type = type;
        this.settingType = settingType;
        this.settingText = settingText;
        this.menuText = settingText;
    }

    // Action
    TileMapping(TileType type, Action actionType, String actionText) {
        this.type = type;
        this.actionType = actionType;
        this.actionText = actionText;
        this.menuText = actionText;
    }

    // Operand
    TileMapping(TileType type, Class<? extends Operand>  operandType, String menuText) {
        this.type = type;
        this.operandType = operandType;
        this.menuText = menuText;
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

    public String getMenuText() {
        return menuText;
    }
}
