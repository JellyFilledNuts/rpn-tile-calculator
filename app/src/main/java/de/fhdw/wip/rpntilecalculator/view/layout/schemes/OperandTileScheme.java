package de.fhdw.wip.rpntilecalculator.view.layout.schemes;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.TileScheme;

public class OperandTileScheme extends TileScheme {

    private Operand operand = null;

    /**
     * Creates an OperandTileScheme with a valid operand
     * @param tileType exact type of the scheme
     * @param content operand value
     */
    OperandTileScheme(@NotNull TileMapping tileType, @NotNull String content) {
        super(tileType, content);

        // Ignore menu values
        if(content.equals(tileType.getMenuText())) return;

        Class<? extends Operand> operandClass = tileType.getOperandType();

        try {
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

    public boolean hasOperand(){
        return operand != null;
    }

    @Override
    public @NotNull String getContent() {
        return operand == null ? " " : operand.toString();
    }

    public Operand getOperand() {
        return operand;
    }
}
