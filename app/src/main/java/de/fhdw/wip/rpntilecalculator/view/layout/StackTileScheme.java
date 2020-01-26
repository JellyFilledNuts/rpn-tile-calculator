package de.fhdw.wip.rpntilecalculator.view.layout;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;

public class StackTileScheme extends TileScheme {

    private Operand operand = null;
    private int rank = 1;

    /**
     * Crates an StackTileScheme with an operand in it
     * @param content rank of the stack field
     */
    StackTileScheme(@NotNull String content) {
        super(TileMapping.S_STACK, "");

        rank = Integer.valueOf(content);
    }

    StackTileScheme(@NotNull Operand operand, @NotNull int rank) {
        super(TileMapping.S_STACK, operand.toString()); //TODO Update the toString method?
        this.operand = operand;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
