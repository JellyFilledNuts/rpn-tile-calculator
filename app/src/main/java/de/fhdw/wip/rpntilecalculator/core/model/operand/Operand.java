package de.fhdw.wip.rpntilecalculator.core.model.operand;

import de.fhdw.wip.rpntilecalculator.core.model.Element;

import org.jetbrains.annotations.NotNull;

/*
 * Summary: Main class for all operands that can be used for calculating
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
public abstract class Operand extends Element {

    /**
     * Multiplies all values of the {@link Operand} with {@code -1}.
     */
    public abstract @NotNull Operand turnAroundSign();

    /**
     * Makes all values of the {@link Operand} negative.
     */
    public abstract @NotNull Operand negateValue();

    public abstract @NotNull Operand inverseValue();

}
