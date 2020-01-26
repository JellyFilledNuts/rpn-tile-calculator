package de.fhdw.wip.rpntilecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
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

        TileLayout testLayout = TileLayoutFactory.createLayout(this, "Morestack");
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
            System.out.println("Clicked: " + tile.getText() + " from " + tile.getScheme().getTileType());
        } catch (ClickHandlingException ignored) {
            //TODO
        }
    }
}
