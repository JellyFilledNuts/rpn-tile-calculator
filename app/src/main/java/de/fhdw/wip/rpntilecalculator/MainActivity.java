package de.fhdw.wip.rpntilecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import de.fhdw.wip.rpntilecalculator.core.calculation.CalculationException;
import de.fhdw.wip.rpntilecalculator.core.calculation.Minus;
import de.fhdw.wip.rpntilecalculator.core.calculation.Plus;
import de.fhdw.wip.rpntilecalculator.core.calculation.Slash;
import de.fhdw.wip.rpntilecalculator.core.calculation.Times;
import de.fhdw.wip.rpntilecalculator.core.model.operand.ODouble;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OFraction;
import de.fhdw.wip.rpntilecalculator.core.model.operand.OMatrix;
import de.fhdw.wip.rpntilecalculator.core.model.operand.Operand;
import de.fhdw.wip.rpntilecalculator.core.stack.OperandStack;
import de.fhdw.wip.rpntilecalculator.core.ui.Tile;
import de.fhdw.wip.rpntilecalculator.core.ui.TileLayout;
import de.fhdw.wip.rpntilecalculator.core.ui.TileScheme;
import de.fhdw.wip.rpntilecalculator.core.ui.TileType;

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
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        test();

        drawLayout(new TileLayout("TEST2"));
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

    public void drawLayout(TileLayout tileLayout) {
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

        //Create table by first creating one column as TableLayout
        TableLayout columns = new TableLayout(this);
        columns.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        columns.setBackgroundColor(Color.BLUE);

        //Creating the in tileLayout defined amount of rows
        for(int i = 0; i < tileLayout.getTileLayout().length; i++) {

            //Rows are of type TableRow, Layout is important!
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            row.setGravity(Gravity.CENTER);
            row.setBackgroundColor(Color.BLACK);

            //Creating buttons which amount defines the amount of columns
            for(int j = 0; j < tileLayout.getTileLayout()[i].length; j++) {

                //For the design of the Button TileScheme is used and for the button itself Tile
                TileScheme tileScheme = tileLayout.getTileLayout()[i][j];
                Button tile = new Tile(this, tileScheme.getType(), tileScheme.getContent());
                tile.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
                row.addView(tile, j);
            }
            columns.addView(row, i);
        }
        constraintLayout.addView(columns);
    }

    public void execute(String text, TileType type) {
        // TODO
        // Typ.Action.do(text)
    }
}
