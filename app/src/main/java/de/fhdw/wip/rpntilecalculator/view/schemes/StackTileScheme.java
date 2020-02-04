package de.fhdw.wip.rpntilecalculator.view.schemes;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.TileType;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutFactory;

/**
 * Summary: TileScheme for stack tiles (extends Operand-TileScheme)
 * Author:  Dennis Gentges
 * Date:    2020/01/08
 */
public class StackTileScheme extends OperandTileScheme {

    private int rank;

    /**
     * Crates an StackTileScheme with an operand in it
     * @param content rank of the stack field
     */
    public StackTileScheme(@NotNull TileMapping tileMapping, @NotNull String content, int rank) {
        super(tileMapping, content);
        this.rank = rank;
    }

    StackTileScheme(@NotNull Operand operand, int rank) {
        super(TileMapping.O_DOUBLE, operand);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public int getStyle() {
        return TileType.STACK.getStyle();
    }

    @NonNull
    @Override
    public String toString() {
        return "S_STACK"
                + TileLayoutFactory.VALUE_SEPERATOR + this.getRank()
                + TileLayoutFactory.VALUE_SEPERATOR + this.getTileType()
                + TileLayoutFactory.VALUE_SEPERATOR + this.getContent();
    }
}
