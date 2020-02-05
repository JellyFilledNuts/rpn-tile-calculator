package de.fhdw.wip.rpntilecalculator.model.operands;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Summary: Wrapper for the Tuple Operand
 * Author:  Tim Jonas Meinerzhagen
 * Date:    2019/11/13
 */
public class OTuple extends Operand {

    @NotNull private double[] tuple;

    /**
     * Create tuple from array of doubles.
     * @param doubles
     */
    public OTuple(@NotNull double... doubles) {
        this.tuple = doubles;
    }

    /**
     * Create tuple from list of doubles
     * @param tuple
     */
    private OTuple(@NotNull List<Double> tuple) {
        this.tuple = new double[tuple.size()];
        for (int i = 0; i < this.tuple.length; i++)
            this.tuple[i] = tuple.get(i);
    }

    /**
     * Create Tuple from String
     * @param tuple Tuple as String
     */
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

    /**
     * Get the underlying Tuple.
     * @return Tuple
     */
    public @NotNull double[] getTuple() {
        return tuple;
    }

    /**
     * Turn around all signs.
     * @return new Tuple.
     */
    @NotNull @Override public OTuple turnAroundSign() {
        List<Double> newTuple = new ArrayList<>();
        for (double d : tuple)
            newTuple.add(d * -1);
        return new OTuple(newTuple);
    }

    /**
     * Negate Value. Make all values negative.
     * @return new Tuple
     */
    @NotNull @Override public OTuple negateValue() {
        List<Double> newTuple = new ArrayList<>();
        for (double d : tuple)
            newTuple.add(Math.abs(d) * -1);
        return new OTuple(newTuple);
    }

    /**
     * Inverse the value of this instance
     * @return New Tuple
     */
    @Override
    public @NotNull OTuple inverseValue() {
        List<Double> newTuple = new ArrayList<>();
        for (double d : tuple)
            newTuple.add(1 / d);
        return new OTuple(newTuple);
    }

    /**
     * Compare this instance with another Operand
     * @param operand Another operand.
     * @return Boolean
     */
    @Override
    public boolean equalsValue(Operand operand) {
        if (operand == this) return true;
        if (!(operand instanceof OTuple)) return false;

        return DoubleComparator.isEqual(tuple, ((OTuple) operand).getTuple());
    }

    /**
     * Turn Operand into String representation
     * @return String
     */
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
