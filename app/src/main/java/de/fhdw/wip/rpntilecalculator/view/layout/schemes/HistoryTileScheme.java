package de.fhdw.wip.rpntilecalculator.view.layout.schemes;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.TileType;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutFactory;

public class HistoryTileScheme extends StackTileScheme {

    HistoryTileScheme(@NotNull TileMapping tileMapping, @NotNull String content, @NotNull int rank) {
        super(tileMapping, content, rank);
    }

    HistoryTileScheme(@NotNull Operand operand, @NotNull int rank) {
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
