package de.fhdw.wip.rpntilecalculator.view.menu;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.R;
import de.fhdw.wip.rpntilecalculator.model.operands.OFraction;
import de.fhdw.wip.rpntilecalculator.model.operands.OPolynom;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.schemes.TileScheme;

public class InputPolynomial extends DialogMenu {

    private Button confirmButton = this.dialog.findViewById(R.id.enterButton2);
    private EditText coefficient_0_Txt = this.dialog.findViewById(R.id.coefficient_0);
    private EditText coefficient_1_Txt = this.dialog.findViewById(R.id.coefficient_1);
    private EditText coefficient_2_Txt = this.dialog.findViewById(R.id.coefficient_2);

    public InputPolynomial(MainActivity context, Tile displayTile, DialogMenu last)
    {
        super(context, displayTile, last);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double coefficient_0 = Integer.parseInt(coefficient_0_Txt.getText().toString());
                double coefficient_1 = Integer.parseInt(coefficient_1_Txt.getText().toString());
                double coefficient_2 = Integer.parseInt(coefficient_2_Txt.getText().toString());

                double[] coefficients = new double[] {coefficient_0, coefficient_1, coefficient_2};
                OPolynom oPolynom = new OPolynom(coefficients);
                TileScheme newTileScheme = TileScheme.createTileScheme(TileMapping.O_POLYNOM, oPolynom, 0);
                tile.update(newTileScheme);
                tile.getTileLayout().removeFromStacks(tile);
                dismissAll();
            }
        });
    }

    @Override
    protected void setContentView() {
        contentView = R.layout.input_polynomial;
    }
}
