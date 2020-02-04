package de.fhdw.wip.rpntilecalculator.view.schemes;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.TileType;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutFactory;

/**
 * Summary: TileScheme for a History-Stack Tile (extends from normal Stack-TileScheme)
 * Author:  Dennis Gentges
 * Date:    2020/01/10
 */
public class HistoryTileScheme extends StackTileScheme {

    HistoryTileScheme(@NotNull TileMapping tileMapping, @NotNull String content, int rank) {
        super(tileMapping, content, rank);
    }

    HistoryTileScheme(@NotNull Operand operand, int rank) {
        super(operand, rank);
    }

    @NonNull
    @Override
    public String toString() {
        return "H_HISTORY"
                + TileLayoutFactory.VALUE_SEPERATOR + this.getRank()
                + TileLayoutFactory.VALUE_SEPERATOR + this.getTileType()
                + TileLayoutFactory.VALUE_SEPERATOR + this.getContent();
    }

    public int getStyle() {
        return TileType.HISTORY.getStyle();
    }
}
