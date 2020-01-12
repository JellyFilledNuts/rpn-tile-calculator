package de.fhdw.wip.rpntilecalculator.core.ui.layout;

import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import de.fhdw.wip.rpntilecalculator.core.model.operand.Operand;
import de.fhdw.wip.rpntilecalculator.core.ui.TileMapping;

public class OperandTileScheme extends TileScheme {

    private Operand operand = null;

    /**
     * Creates an OperandTileScheme with a valid operand
     * @param tileType exact type of the scheme
     * @param content operand value
     */
    OperandTileScheme(@NotNull TileMapping tileType, @Nullable String content) {
        super(tileType, content);

        Class<? extends Operand> operandClass = tileType.getOperandType();
        Class<?> operandValueClass = tileType.getOperandValueType();

        //TODO Change this
        if(content == null) content = "1";

        //Create operandValueClass type parameter of content
        Object parameter = null;
        try {
            parameter = operandValueClass.getConstructor(String.class).newInstance(content);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //Create operandClass and pass parameter
        try {
            //Change to finding the correct constructor (.getConstructor does not work on primitives!)
            this.operand = (Operand) operandClass.getConstructors()[0].newInstance(parameter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("Created TileScheme: <Operand " + operandClass + ":" + parameter + ">");
    }

    public Operand getOperand() {
        return operand;
    }
}
