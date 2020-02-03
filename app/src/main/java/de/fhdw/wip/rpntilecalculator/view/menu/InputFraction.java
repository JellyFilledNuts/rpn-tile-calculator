package de.fhdw.wip.rpntilecalculator.view.menu;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.R;
import de.fhdw.wip.rpntilecalculator.model.operands.OFraction;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.schemes.TileScheme;
import de.fhdw.wip.rpntilecalculator.view.menu.utils.DialogMenu;

/**
 * Summary: Class responsible for input of fractions
 * Author:  Getuart Istogu
 **/

public class InputFraction extends DialogMenu {

    private Button confirmButton = this.dialog.findViewById(R.id.enterButton);
    private EditText numeratorText = this.dialog.findViewById(R.id.numerator);
    private EditText denumeratorText = this.dialog.findViewById(R.id.denumerator);

   public InputFraction(MainActivity context, Tile displayTile, DialogMenu last)
   {
       super(context, displayTile, last);
       confirmButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int nummerator = Integer.parseInt(numeratorText.getText().toString());
               int denumerator = Integer.parseInt(denumeratorText.getText().toString());
               OFraction oFraction = new OFraction(nummerator, denumerator);
               TileScheme newTileScheme = TileScheme.createTileScheme(TileMapping.O_FRACTION, oFraction, 0);
               tile.update(newTileScheme);
               tile.getTileLayout().removeFromStacks(tile);
               dismissAll();
           }
       });
   }

    @Override
    protected void setContentView() {
        contentView = R.layout.input_fraction;
    }
}
