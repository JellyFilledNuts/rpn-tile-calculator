package de.fhdw.wip.rpntilecalculator.model.operands;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Summary: Every entry can only exist one time
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
public class OSet extends Operand {

    @NotNull private Set<Double> set;

    /**
     * Create Set from set.
     * @param set Set
     */
    public OSet(@NotNull Set<Double> set) {
        this.set = set;
    }

    /**
     * Create OSet from array of doubles
     * @param doubles Double array
     */
    public OSet(@NotNull double... doubles) {
        ArrayList<Double> list = new ArrayList<>();
        for (double d : doubles) list.add(d);

        this.set = new HashSet<>();
        this.set.addAll(list);
    }

    /**
     * Create OSet from String
     * @param set String representation of the Set
     */
    public OSet(@NotNull String set) {
        this.set = new HashSet<>();
        Pattern pat = Pattern.compile("[\\-0-9.]+");
        Matcher mat = pat.matcher(set);

        while(mat.find()) {
            this.set.add(Double.valueOf(set.substring(mat.start(), mat.end())));
        }
    }

    /**
     * Get underlying Set
     * @return Set
     */
    @NotNull public Set<Double> getSet() {
        return set;
    }

    /**
     * Turn around all signs
     * @return New OSet
     */
    @NotNull @Override public OSet turnAroundSign() {
        Set<Double> newSet = new HashSet<>();
        for (double d : set)
            newSet.add(d * -1);
        return new OSet(newSet);
    }

    /**
     * Negate all values
     * @return New OSet
     */
    @NotNull @Override public OSet negateValue() {
        Set<Double> newSet = new HashSet<>();
        for (double d : set)
            newSet.add(Math.abs(d) * -1);
        return new OSet(newSet);
    }

    /**
     * Inverse all values
     * @return new OSet
     */
    @Override public @NotNull OSet inverseValue() {
        Set<Double> newSet = new HashSet<>();
        for (double d : set)
            newSet.add(1 / d);
        return new OSet(newSet);
    }

    /**
     * Compare this instance with another Operand
     * @param operand Another operand
     * @return Boolean
     */
    @Override public boolean equalsValue(Operand operand) {
        if (operand == this) return true;
        if (!(operand instanceof OSet)) return false;

        return DoubleComparator.isEqual(set, ((OSet) operand).getSet());
    }

    /**
     * Turn this instance into an string.
     * @return String
     */
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
