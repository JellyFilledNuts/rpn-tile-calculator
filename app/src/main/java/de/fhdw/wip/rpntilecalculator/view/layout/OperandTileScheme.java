package de.fhdw.wip.rpntilecalculator.view.layout;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OFraction;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;

public class OperandTileScheme extends TileScheme {

    private Operand operand = null;

    /**
     * Creates an OperandTileScheme with a valid operand
     * @param tileType exact type of the scheme
     * @param content operand value
     */
    OperandTileScheme(@NotNull TileMapping tileType, @NotNull String content) {
        super(tileType, content);

        Class<? extends Operand> operandClass = tileType.getOperandType();

        //Tried to do this generically but there were too many options - so we did it manually
        //it would have been really cool though
        if(operandClass == ODouble.class) {
            //1.0
            this.operand = new ODouble(Double.valueOf(content));
        } else if(operandClass == OFraction.class) {
            //(1.0/2.0)
            //TODO: Strange error with first of split

        }

        System.out.println("Created TileScheme: <Operand " + operandClass + ":" + content + ">");
    }

    public Operand getOperand() {
        return operand;
    }
}
