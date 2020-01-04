package de.fhdw.wip.rpntilecalculator.core.model.operand;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class OSet extends Operand {

    @NotNull private Set<Double> set;

    public OSet(@NotNull Set<Double> set) {
        this.set = set;
    }

    @NotNull public Set<Double> getDoubleSet() {
        return set;
    }

    @NotNull @Override public OSet turnAroundSign() {
        Set<Double> newSet = new HashSet<>();
        for (double d : set)
            newSet.add(d * -1);
        return new OSet(newSet);
    }

    @NotNull @Override public OSet negateValue() {
        Set<Double> newSet = new HashSet<>();
        for (double d : set)
            newSet.add(Math.abs(d) * -1);
        return new OSet(newSet);
    }

    @Override public @NotNull OSet inverseValue() {
        Set<Double> newSet = new HashSet<>();
        for (double d : set)
            newSet.add(1 / d);
        return new OSet(newSet);
    }

    @NotNull @Override public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (double d : set) {
            builder.append(DoubleFormatter.format(d));
            builder.append(", ");
        }
        builder.delete(builder.length() - 2, builder.length());
        builder.append("]");
        return builder.toString();
    }

}
