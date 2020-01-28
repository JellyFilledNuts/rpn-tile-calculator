package de.fhdw.wip.rpntilecalculator.view.layout.schemes;

import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
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
        this(Integer.valueOf(content));
    }

    StackTileScheme(@NotNull int rank) {
        super(TileMapping.S_STACK, "");
        this.rank = rank;
        operand = new ODouble(0);
    }

    StackTileScheme(@NotNull Operand operand, @NotNull int rank) {
        super(TileMapping.S_STACK, operand.toString()); //TODO Update the toString method?
        this.operand = operand;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public Operand getOperand() {
        return operand;
    }

    @Override
    public @NotNull String getContent() {
        return String.valueOf(rank);
    }

    public boolean hasOperand() { return operand != null; }
}
