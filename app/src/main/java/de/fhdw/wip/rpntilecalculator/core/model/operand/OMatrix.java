package de.fhdw.wip.rpntilecalculator.core.model.operand;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class OMatrix extends Operand {

    @NotNull private RealMatrix matrix;

    public OMatrix(@NotNull RealMatrix matrix) {
        this.matrix = matrix;
    }

    public OMatrix(@NotNull double[][] doubleMatrix) {
        int longest = 0;

        for (double[] dim1 : doubleMatrix)
            if (dim1.length > longest) longest = dim1.length;

        double[][] modified = new double[doubleMatrix.length][longest];
        for (int i = 0; i < doubleMatrix.length; i++)
            System.arraycopy(
                    doubleMatrix[i], 0,
                    modified[i], 0, doubleMatrix[i].length
            );

        matrix = new Array2DRowRealMatrix(modified);
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

    @Override
    public boolean equalsValue(Operand operand) {
        if (operand == this) return true;
        if (!(operand instanceof OMatrix)) return false;

        return DoubleComparator.isEqual(
                matrix.getData(),
                ((OMatrix) operand).getMatrix().getData()
        );
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
