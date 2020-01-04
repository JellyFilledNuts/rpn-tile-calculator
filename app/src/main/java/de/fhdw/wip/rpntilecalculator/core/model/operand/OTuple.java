package de.fhdw.wip.rpntilecalculator.core.model.operand;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OTuple extends Operand {

    @NotNull private double[] tuple;

    public OTuple(@NotNull double[] tuple) {
        this.tuple = tuple;
    }

    private OTuple(@NotNull List<Double> tuple) {
        this.tuple = new double[tuple.size()];
        for (int i = 0; i < this.tuple.length; i++)
            this.tuple[i] = tuple.get(i);
    }

    public @NotNull double[] getTuple() {
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

    @Override
    public boolean equalsValue(Operand operand) {
        if (operand == this) return true;
        if (!(operand instanceof OTuple)) return false;

        return Arrays.equals(tuple, ((OTuple) operand).getTuple());
    }

    @NotNull @Override public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        for (double d : tuple) {
            builder.append(DoubleFormatter.format(d));
            builder.append(", ");
        }
        builder.delete(builder.length() - 2, builder.length());
        builder.append(")");
        return builder.toString();
    }

}
