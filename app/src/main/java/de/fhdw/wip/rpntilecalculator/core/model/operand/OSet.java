package de.fhdw.wip.rpntilecalculator.core.model.operand;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OSet extends Operand {

    @NotNull private Set<Double> set;

    public OSet(@NotNull Set<Double> set) {
        this.set = set;
    }

    public OSet(@NotNull double... doubles) {
        ArrayList<Double> list = new ArrayList<>();
        for (double d : doubles) list.add(d);

        this.set = new HashSet<>();
        this.set.addAll(list);
    }

    @NotNull public Set<Double> getSet() {
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

    @Override public boolean equalsValue(Operand operand) {
        if (operand == this) return true;
        if (!(operand instanceof OSet)) return false;

        return DoubleComparator.isEqual(set, ((OSet) operand).getSet());
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
