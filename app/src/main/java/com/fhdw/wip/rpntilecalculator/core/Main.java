package com.fhdw.wip.rpntilecalculator.core;

import com.fhdw.wip.rpntilecalculator.core.calculation.Plus;
import com.fhdw.wip.rpntilecalculator.core.calculation.ConcreteCalculationException;
import com.fhdw.wip.rpntilecalculator.core.calculation.CalculationException;
import com.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import com.fhdw.wip.rpntilecalculator.core.model.operand.Operand;

public class Main {

    public static void main(String[] args) {
        Plus action = Plus.getInstance();
        try {
            Operand operand = action.with(new ODouble(2), new ODouble(2));
            System.out.println(((ODouble) operand).getDouble());
        } catch (CalculationException e) {
            System.out.println("111");
            e.printStackTrace();
        } catch (ConcreteCalculationException e) {
            System.out.println("Fail");
            e.printStackTrace();
        }
    }

}
