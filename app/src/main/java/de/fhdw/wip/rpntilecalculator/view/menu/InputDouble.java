package de.fhdw.wip.rpntilecalculator.view.menu;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.fhdw.wip.rpntilecalculator.view.MainActivity;
import de.fhdw.wip.rpntilecalculator.R;
import de.fhdw.wip.rpntilecalculator.model.operands.ODouble;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.schemes.TileScheme;

public class InputDouble extends DialogMenu {

    private Button confirmButton = this.dialog.findViewById(R.id.enterButton3);
    private EditText numberText = this.dialog.findViewById(R.id.oDouble_input);

    public InputDouble(final MainActivity context, Tile displayTile, DialogMenu last)
    {
        super(context, displayTile, last);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double number = Double.parseDouble(numberText.getText().toString());
                    ODouble oDouble = new ODouble(number);
                    TileScheme newTileScheme = TileScheme.createTileScheme(TileMapping.O_DOUBLE, oDouble, 0);
                    tile.update(newTileScheme);
                    tile.getTileLayout().removeFromStacks(tile);
                    dismissAll();
                }catch (Exception e)
                {
                    Toast.makeText(context, "Please check your input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void setContentView() {
        contentView = R.layout.input_double;
    }
}
