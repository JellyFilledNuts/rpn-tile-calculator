package de.fhdw.wip.rpntilecalculator.model;

import de.fhdw.wip.rpntilecalculator.model.calculation.CalculationException;
import de.fhdw.wip.rpntilecalculator.model.calculation.Minus;
import de.fhdw.wip.rpntilecalculator.model.calculation.Plus;
import de.fhdw.wip.rpntilecalculator.model.calculation.Slash;
import de.fhdw.wip.rpntilecalculator.model.calculation.Times;
import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OFraction;
import de.fhdw.wip.rpntilecalculator.model.operands.OMatrix;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.model.stack.OperandStack;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import java.util.List;

/* KANN GELÖSCHT WERDEN
 * Summary: Wrapper for the Double Operand
 * Author:  Tim Schwenke
 * Date:    2020/01/04
 */
public class Main {

    private static final OperandStack OPERAND_STACK = new OperandStack();
    private static final Plus PLUS = Plus.getInstance();
    private static final Minus MINUS = Minus.getInstance();
    private static final Slash SLASH = Slash.getInstance();
    private static final Times TIMES = Times.getInstance();

    public static void main(String[] args) {
        ODouble oDouble1 = new ODouble(1);
        ODouble oDouble2 = new ODouble(2);
        ODouble oDouble3 = new ODouble(3);
        ODouble oDouble4 = new ODouble(4);
        OFraction oFraction1 = new OFraction(3, 4);
        OFraction oFraction2 = new OFraction(86, 3);
        OMatrix oMatrix = new OMatrix(new Array2DRowRealMatrix(new double[][]{
                new double[]{1, 2, 3},
                new double[]{4, 4, 4},
                new double[]{5, 5, 5},
        }));

        OPERAND_STACK.push(new Operand[]{
                oDouble1,
                oDouble2,
                oDouble3,
                oDouble4,
                oFraction1,
                oFraction2,
                oMatrix,
        });

        OPERAND_STACK.print();

        try {
            List<Operand> operands = OPERAND_STACK.peek(2);
            Operand result = PLUS.with(operands);
            OPERAND_STACK.pop(2);
            OPERAND_STACK.push(result);
        } catch (CalculationException e) {
            e.printStackTrace();
        }

        System.out.println();
        OPERAND_STACK.print();
    }

}
