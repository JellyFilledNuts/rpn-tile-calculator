package de.fhdw.wip.rpntilecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import de.fhdw.wip.rpntilecalculator.controller.ClickHandlingException;
import de.fhdw.wip.rpntilecalculator.controller.Controller;
import de.fhdw.wip.rpntilecalculator.model.calculation.Plus;
import de.fhdw.wip.rpntilecalculator.model.stack.OperandStack;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayout;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutFactory;

public class MainActivity extends AppCompatActivity {

    public static MainActivity mainActivity = null;
    private static final OperandStack OPERAND_STACK = new OperandStack();
    private Controller controller = new Controller();
    private TileLayout tileLayout;

    private static final Plus PLUS = Plus.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_main);

        tileLayout = TileLayoutFactory.createLayout(this, "Morestack");
        controller.setDisplayEventListeners(tileLayout);

        setTileLayout(tileLayout);
    }

    public void setTileLayout(TileLayout tileLayout) {
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.removeAllViews();
        constraintLayout.setBackgroundColor(Color.WHITE);
        constraintLayout.addView(tileLayout.createView(this));
        setRequestedOrientation(tileLayout.getOrientation().getOrientation());
    }

    public TileLayout getTileLayout() {
        return tileLayout;
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
