package de.fhdw.wip.rpntilecalculator.model.calculation;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.OMatrix;
import de.fhdw.wip.rpntilecalculator.model.operands.OSet;
import de.fhdw.wip.rpntilecalculator.model.operands.OTuple;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;

/*
 * Summary: Solving systems of linear equations with "LR decomposition with column pivot search"
 * Author:  Getuart Istogu
 * Date:    2020/01/27
 */

public class MatrixUtil extends Action {
    @NotNull
    private static final MatrixUtil MATRIX_UTIL  = new MatrixUtil();

    @Contract(pure = true) @NotNull public static MatrixUtil getInstance() { return MATRIX_UTIL; }
    private MatrixUtil() { requiredNumOfOperands = new int[] {2}; }

    @NotNull @Override
    public Operand with(@NotNull Operand... operands) throws CalculationException {
        scopedAction = this;
        return super.with(operands);
    }

    @Contract(pure = true) @NotNull OTuple on (@NotNull OMatrix A, @NotNull OTuple b) throws CalculationException {
        return solveLinearSystem(A, b);
    }
    /**
     * On the condition that A*x = b
     * @param A Matrix
     * @param b Solution vector
     * @return Returns the vector 'x'
     */
    public OTuple solveLinearSystem(@NotNull OMatrix A, OTuple b) throws CalculationException
    {
        if(A.getMatrix().isSquare()) {
            if (b.getTuple().length == A.getMatrix().getRowDimension()) {
                return new OTuple(solveLGSForX(A.getMatrix().getData(), b.getTuple()));
            }
            throw new CalculationException("A should have the same number of rows/columns as b values.");
        }
        throw new CalculationException("A isn't a square matrix");

    }

    /**
     * Determines pivot vector
     * @param A The linear system in double[][]
     * @return
     */
    private int[] pivot(@NotNull double A[][])
    {
        int n = A.length;
        int[] pivot = new int[n];
        for (int j = 0; j < n-1; j++)
        {
            double max = Math.abs(A[j][j]);
            int imax = j;
            for (int i = j+1; i < n; i++)
                if (Math.abs(A[i][j]) > max)
                {
                    max  = Math.abs(A[i][j]);
                    imax = i;
                }
            double[] h = A[j];
            A[j] = A[imax];
            A[imax] = h;
            pivot[j] = imax;
            for (int i = j+1; i < n; i++)
            {
                double f = -A[i][j]/A[j][j];
                for (int k = j+1; k < n; k++)
                    A[i][k] += f*A[j][k];
                A[i][j] = -f;
            }
        }
        return pivot;
    }

    /**
     * Löst das LGS A*x = b nach x auf
     * @param A The linear system in double[][]
     * @param b Solution vector
     * @return Returns the vector 'x'
     */
    private double[] solveLGSForX(double[][] A, double[] b)
    {
        double[][] B = A.clone();
        double[] x = b.clone();
        int[] pivot = pivot(B);
        int n = B.length;
        for (int i = 0; i < n-1; i++)
        {
            double h = b[pivot[i]];
            b[pivot[i]] = b[i];
            b[i] = h;
        }
        for (int j = 0; j < n; j++)
        {
            x[j] = b[j];
            for (int i = 0; i < j; i++)
                x[j] -= B[j][i]*x[i];
        }
        for (int j = n-1; j >= 0; j--)
        {
            for (int k = j+1; k < n; k++)
                x[j] -= B[j][k]*x[k];
            x[j] /= B[j][j];
        }
        return x;
    }
}