package de.fhdw.wip.rpntilecalculator.view.layout.schemes;

import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.TileType;

public class HistoryTileScheme extends StackTileScheme {

    HistoryTileScheme(@NotNull TileMapping tileMapping, @NotNull String content, @NotNull int rank) {
        super(tileMapping, content, rank);
    }

    HistoryTileScheme(@NotNull Operand operand, @NotNull int rank) {
        super(operand, rank);
    }


    public int getStyle() {
        return TileType.HISTORY.getStyle();
    }
}
