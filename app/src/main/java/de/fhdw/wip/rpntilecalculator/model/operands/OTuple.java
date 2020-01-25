package de.fhdw.wip.rpntilecalculator.model.operands;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Summary: Wrapper for the Tuple Operand
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
public class OTuple extends Operand {

    @NotNull private double[] tuple;

    public OTuple(@NotNull double... doubles) {
        this.tuple = doubles;
    }

    private OTuple(@NotNull List<Double> tuple) {
        this.tuple = new double[tuple.size()];
        for (int i = 0; i < this.tuple.length; i++)
            this.tuple[i] = tuple.get(i);
    }

    public OTuple(@NotNull String tuple) {
        ArrayList<Double> listTuple = new ArrayList<>();
        Pattern pat = Pattern.compile("[\\-0-9.]+");
        Matcher mat = pat.matcher(tuple);

        while(mat.find()) {
            String value = tuple.substring(mat.start(), mat.end());
            listTuple.add(Double.valueOf(value));
        }
        this.tuple = new double[2];
        for(int i = 0; i < 2; i++) this.tuple[i] = listTuple.get(i);
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

        return DoubleComparator.isEqual(tuple, ((OTuple) operand).getTuple());
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
