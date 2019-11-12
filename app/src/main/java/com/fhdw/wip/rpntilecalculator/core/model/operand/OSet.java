package com.fhdw.wip.rpntilecalculator.core.model.operand;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class OSet extends Operand {

    @NotNull private Set<@NotNull Double> set;

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

}
