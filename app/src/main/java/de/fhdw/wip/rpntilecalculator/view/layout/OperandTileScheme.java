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

        try {
            System.out.println("Trying: " + operandClass + " with " + content);
            this.operand = operandClass.getConstructor(String.class).newInstance(content);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //System.out.println("Created TileScheme: <Operand " + operandClass + ":" + content + ">");
    }

    OperandTileScheme(@NotNull TileMapping tileType, @NotNull Operand operand) {
        super(tileType, operand.toString());
        this.operand = operand;
    }

    @Override
    public @NotNull String getContent() {
        return operand.toString();
    }

    public Operand getOperand() {
        return operand;
    }
}
