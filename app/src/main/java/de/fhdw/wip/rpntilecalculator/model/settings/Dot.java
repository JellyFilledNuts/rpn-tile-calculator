package de.fhdw.wip.rpntilecalculator.model.settings;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OEmpty;
import de.fhdw.wip.rpntilecalculator.presenter.Presenter;

/**
 * Summary: Places a '.' in the input term to create decimal values
 * Author:  Hendrik Falk
 * Date:    2020/02/03
 */
public class Dot extends Setting {

    @Contract(pure = true) @NotNull
    public static Dot getInstance() { return new Dot(); }

    @Override
    public boolean call() {
        Presenter presenter = Presenter.getInstance();
        if(presenter.getInputTerm().toString().equals(presenter.getInputFinalized()) ||
                presenter.getOperandStack().peek() instanceof OEmpty) {
            ODouble oDouble = new ODouble(0);
            presenter.resetInputTerm(oDouble);
            presenter.getOperandStack().push(oDouble);
        }
        if(!presenter.getInputTerm().toString().contains(".") &&
                !presenter.getInputTerm().toString().contains(",")) presenter.getInputTerm().append(".");
        presenter.updateStack();
        return true;
    }
}
