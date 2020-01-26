package de.fhdw.wip.rpntilecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import de.fhdw.wip.rpntilecalculator.controller.ClickHandlingException;
import de.fhdw.wip.rpntilecalculator.controller.Controller;
import de.fhdw.wip.rpntilecalculator.model.calculation.CalculationException;
import de.fhdw.wip.rpntilecalculator.model.calculation.Plus;
import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.model.operands.OFraction;
import de.fhdw.wip.rpntilecalculator.model.operands.OMatrix;
import de.fhdw.wip.rpntilecalculator.model.operands.Operand;
import de.fhdw.wip.rpntilecalculator.model.stack.OperandStack;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayout;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutFactory;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final OperandStack OPERAND_STACK = new OperandStack();
    private Controller controller = new Controller();

    private static final Plus PLUS = Plus.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_main);
        test();

        TileLayout testLayout = TileLayoutFactory.createLayout(this, "Standardlayout");
        controller.setDisplayEventListeners(testLayout);

        drawLayout(testLayout);
    }

    public void drawLayout(TileLayout tileLayout) {
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setBackgroundColor(Color.WHITE);
        constraintLayout.addView(tileLayout.createView(this));
        setRequestedOrientation(tileLayout.getOrientation().getOrientation());
    }

    public void action(Tile tile) {
        try {
            controller.click(tile);
        } catch (ClickHandlingException ignored) {
            //TODO
        }
        System.out.println("Clicked: " + tile.getText() + " from " + tile.getScheme().getTileType());
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
