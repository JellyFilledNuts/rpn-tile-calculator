package com.fhdw.wip.rpntilecalculator.core.model.operand;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OTuple extends Operand {

    @NotNull private List<@NotNull Double> tuple;

    public OTuple(@NotNull List<Double> tuple) {
        this.tuple = tuple;
    }

    public @NotNull List<Double> getTuple() {
        return tuple;
    }

    @NotNull @Override public OTuple turnAroundSign() {
        List<Double> newTuple = new ArrayList<>();
        for (double d : tuple)
            newTuple.add(d * -1);
        return new OTuple(newTuple);
    }

    @NotNull @Override public OTuple negateValue() {
        List<Double> newTuple = new ArrayList<>();
        for (double d : tuple)
            newTuple.add(Math.abs(d) * -1);
        return new OTuple(newTuple);
    }

    @Override
    public @NotNull OTuple inverseValue() {
        List<Double> newTuple = new ArrayList<>();
        for (double d : tuple)
            newTuple.add(1 / d);
        return new OTuple(newTuple);
    }

}
