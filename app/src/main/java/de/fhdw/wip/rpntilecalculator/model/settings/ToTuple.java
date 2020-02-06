package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OEmpty;
import de.fhdw.wip.rpntilecalculator.model.operands.OTuple;
import de.fhdw.wip.rpntilecalculator.model.stack.OperandStack;
import de.fhdw.wip.rpntilecalculator.presenter.Presenter;

/**
 * Summary: Loads as many values as possible as Tuple
 * Author:  Tim Jonas Meinerzhagen
 * Date:    2020/02/04
 */
public class ToTuple extends Setting {

    @Contract(pure = true) @NotNull
    public static ToTuple getInstance() { return new ToTuple(); }

    @Override
    public boolean call() {
        Presenter presenter = Presenter.getInstance();
        OperandStack operandStack = presenter.getOperandStack();
        List<Double> doubles = new ArrayList<>();
        while(operandStack.peek() instanceof ODouble) {
            double d = ((ODouble) operandStack.pop()).getDouble();
            doubles.add(d);
        }
        if(doubles.size() < 2) return false;
        double[] values = new double[doubles.size()];
        for(int i = 0; i < doubles.size(); i++) values[i] = doubles.get(i);
        OTuple tuple = new OTuple(values);
        presenter.getOperandStack().push(tuple);
        presenter.add2History(tuple);
        presenter.resetInputTerm(tuple);
        presenter.updateStack();
        presenter.finalizeInput();
        return true;
    }
}
