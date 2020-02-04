package de.fhdw.wip.rpntilecalculator.view.schemes;

/*
  Summary: Contains information about tiles and is used to create tiles and save them
  Author:  Dennis Gentges
  Date: 2020/01/06
 */
import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fhdw.wip.rpntilecalculator.R;
import de.fhdw.wip.rpntilecalculator.model.operands.OEmpty;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutFactory;

public abstract class TileScheme {

    @NotNull protected TileMapping tileType;
    @NotNull private String content;

    TileScheme(@NotNull TileMapping tileType, @NotNull String content) {
        this.tileType = tileType;
        this.content = content;
    }

    /**
     * Factory method that creates a TileScheme depending on the type
     * @param tileType exact type of the scheme (determines the subclass)
     * @param content content
     * @return Type of TileScheme that inherits TileScheme.class
     */
    public static TileScheme createTileScheme(@NotNull TileMapping tileType, @Nullable String content) {
        if(tileType.getType().isAction())
            return new ActionTileScheme(tileType, content);
        else if(tileType.getType().isOperand())
            return new OperandTileScheme(tileType, content);
        else if(tileType.getType().isStack() || tileType.getType().isHistory()) {
            String[] a = content.split(TileLayoutFactory.VALUE_SEPERATOR);
            if(tileType.getType().isHistory())
                return new HistoryTileScheme(Enum.valueOf(TileMapping.class, a[1]), a[2], Integer.parseInt(a[0]));
            if(tileType.getType().isStack())
                return new StackTileScheme(Enum.valueOf(TileMapping.class, a[1]), a[2], Integer.parseInt(a[0]));
        }
        else if(tileType.getType().isSetting())
            return new SettingTileScheme(tileType, content);
        return new ErrorTileScheme(TileMapping.X_ERROR, "N/A");
    }

    public static TileScheme createTileScheme(@NotNull TileMapping tileType, @Nullable Operand operand, int rank) {
        if(tileType.getType().isStack()) {
            if(operand != null) return new StackTileScheme(operand, rank);
            else return new StackTileScheme(new OEmpty(), rank);
        }
        else if(tileType.getType().isOperand()) {
            assert operand != null;
            return new OperandTileScheme(tileType, operand);
        }
        else if(tileType.getType().isHistory()) {
            if(operand != null) return new HistoryTileScheme(operand, rank);
            else return new HistoryTileScheme(new OEmpty(), rank);
        }
        else {return null;}
    }

    // gives back a drawable resource
    public int getStyle() {
        return tileType == TileMapping.X_ERROR ? R.drawable.tile_error : tileType.getType().getStyle();
    }

    @NotNull
    public TileMapping getTileType() {
        return tileType;
    }

    public void setTileType(@NotNull TileMapping tileType) {
        this.tileType = tileType;
    }

    /**
     * For internal use only
     */
    @NotNull
    public String getContent() {
        return content;
    }

    /**
     * Override if display text varies from normal text
     */
    @NotNull
    public String toDisplayText() {
        return getContent();
    }

    public void setContent(@NotNull String content) {
        this.content = content;
    }

    @NonNull
    @Override
    public String toString() {
        //O_DOUBLE;20
        //A_MINUS;-
        return tileType.toString() + TileLayoutFactory.VALUE_SEPERATOR + this.getContent();
    }
}
