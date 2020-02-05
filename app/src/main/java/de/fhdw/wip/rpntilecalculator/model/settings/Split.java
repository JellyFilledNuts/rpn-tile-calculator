package de.fhdw.wip.rpntilecalculator.model.settings;

import org.apache.commons.math3.linear.RealMatrix;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OMatrix;
import de.fhdw.wip.rpntilecalculator.model.operands.OSet;
import de.fhdw.wip.rpntilecalculator.model.operands.OTuple;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.presenter.Presenter;

/**
 * Summary: Splits the current loaded operand into several ODoubles
 * Author:  Hendrik Falk
 * Date:    2020/02/03
 */
public class Split extends Setting {

    @Contract(pure = true) @NotNull
    public static Split getInstance() { return new Split(); }

    /**
     * Splits a set / matrix / vektor / tuple into multiple ODouble values
     */
    @Override
    public boolean call() {
        Presenter presenter = Presenter.getInstance();
        if(presenter.getOperandStack().size() == 0) return false;

        Operand toSplit = presenter.getOperandStack().peek();
        List<ODouble> operandList = new ArrayList<>();

        if(toSplit instanceof OMatrix) {

            RealMatrix matrix = ((OMatrix) toSplit).getMatrix();
            int rowsCount = matrix.getRowDimension();
            for(int i = 0; i < rowsCount; i++) {
                double[] row = matrix.getRow(i);
                for(double value : row) operandList.add(new ODouble(value));
            }

        } else if(toSplit instanceof OSet) {

            @NotNull Set<Double> set = ((OSet) toSplit).getSet();
            for(double value : set) operandList.add(new ODouble(value));

        } else if(toSplit instanceof OTuple) {

            double[] tuple = ((OTuple) toSplit).getTuple();
            for(double value : tuple) operandList.add(new ODouble(value));

        }

        if(operandList.size() != 0) {
            presenter.getOperandStack().pop();

            Collections.reverse((operandList));
            for(ODouble oDouble : operandList) {
                presenter.add2History(oDouble);
                presenter.getOperandStack().push(oDouble);
            }

            presenter.resetInputTerm(operandList.get(operandList.size()-1));
            presenter.updateStack();
            presenter.updateHistoryStack();
            presenter.finalizeInput();
        }
        return true;
    }
}
