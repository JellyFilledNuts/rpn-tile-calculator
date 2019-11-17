package com.fhdw.wip.rpntilecalculator.core.model.operand;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("unused")
public class OMatrix extends Operand {

    @NotNull private RealMatrix matrix;

    public OMatrix(@NotNull RealMatrix matrix) {
        this.matrix = matrix;
    }

    public OMatrix(@NotNull List<@NotNull List<@NotNull Double>> doubleMatrix) {
        int longestColumn = 0;

        // Get longest second dimension
        for (List<Double> column : doubleMatrix)
            if (column.size() > longestColumn) longestColumn = column.size();

        // Fill up all second dimensions to match length of longest dimension
        for (List<Double> column : doubleMatrix)
            if (column.size() < longestColumn)
                while (column.size() < longestColumn)
                    column.add(0d);

        matrix = new Array2DRowRealMatrix();
    }

    public @NotNull RealMatrix getMatrix() {
        return matrix;
    }

    @NotNull @Override public OMatrix turnAroundSign() {
        return new OMatrix(matrix.scalarMultiply(-1));
    }

    @NotNull @Override public OMatrix negateValue() {
        double[][] dim1 = matrix.getData();
        for (int i = 0; i < dim1.length; i++)
            for (int k = 0; k < dim1[i].length; k++)
                dim1[i][k] = Math.abs(dim1[i][k]) * -1;
        return new OMatrix(new Array2DRowRealMatrix(dim1));
    }

    @Override public @NotNull OMatrix inverseValue() {
        return new OMatrix(MatrixUtils.inverse(matrix));
    }

    @NotNull @Override public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (double[] doubles : matrix.getData()) {
            builder.append("[");
            for (double d : doubles) {
                builder.append(DoubleFormatter.format(d));
                builder.append(", ");
            }
            builder.delete(builder.length() - 2, builder.length());
            builder.append("], ");
        }
        builder.delete(builder.length() - 2, builder.length());
        builder.append("]");
        return builder.toString();
    }

}
