package com.fhdw.wip.rpntilecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fhdw.wip.rpntilecalculator.core.calculation.CalculationException;
import com.fhdw.wip.rpntilecalculator.core.calculation.Minus;
import com.fhdw.wip.rpntilecalculator.core.calculation.Plus;
import com.fhdw.wip.rpntilecalculator.core.calculation.Slash;
import com.fhdw.wip.rpntilecalculator.core.calculation.Times;
import com.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import com.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;
import com.fhdw.wip.rpntilecalculator.core.model.operand.OMatrix;
import com.fhdw.wip.rpntilecalculator.core.model.operand.Operand;
import com.fhdw.wip.rpntilecalculator.core.stack.OperandStack;
import com.fhdw.wip.rpntilecalculator.ui.main.MainFragment;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final OperandStack OPERAND_STACK = new OperandStack();
    private static final Plus PLUS = Plus.getInstance();
    private static final Minus MINUS = Minus.getInstance();
    private static final Slash SLASH = Slash.getInstance();
    private static final Times TIMES = Times.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
        test();
    }

    public void test(){
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
