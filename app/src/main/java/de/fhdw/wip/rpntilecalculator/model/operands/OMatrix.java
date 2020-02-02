package de.fhdw.wip.rpntilecalculator.model.operands;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Summary: Wrapper for the Matrix Operand
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
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

    public OMatrix(@NotNull String matrix) {
        //[[1.23, 1.32], [0.23, 1.23]]
        ArrayList<double[]> listMatrix = new ArrayList<>();
        Pattern pat1 = Pattern.compile("\\[[^\\[\\]].*?\\]");
        Matcher mat1 = pat1.matcher(matrix);

        while(mat1.find()) {
            String row = matrix.substring(mat1.start(), mat1.end());

            ArrayList<Double> listArray = new ArrayList<>();
            pat1 = Pattern.compile("[\\-0-9.]+");
            Matcher mat2 = pat1.matcher(row);

            while(mat2.find()) {
                String value = row.substring(mat2.start(), mat2.end());
                listArray.add(Double.valueOf(value));
            }

            double[] doubleArray = new double[listArray.size()];
            for(int i = 0; i < listArray.size(); i++) doubleArray[i] = listArray.get(i);
            listMatrix.add(doubleArray);
        }
        double[][] doubleMatrix = new double[listMatrix.size()][];
        for(int i = 0; i < listMatrix.size(); i++) doubleMatrix[i] = listMatrix.get(i);
        this.matrix = new Array2DRowRealMatrix(doubleMatrix);
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
