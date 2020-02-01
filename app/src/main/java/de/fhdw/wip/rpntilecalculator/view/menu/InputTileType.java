package de.fhdw.wip.rpntilecalculator.view.menu;

import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.R;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileType;
import de.fhdw.wip.rpntilecalculator.view.menu.utils.DialogMenu;

/**
 * Summary: Tile acts as button and forwards the connected type and click to the handler
 * Author:  Getuart Istogu
 **/

public class InputTileType extends DialogMenu implements View.OnLongClickListener {

    private Button stackTypeButton = this.dialog.findViewById(R.id.stackTypeButton);
    private Button operandTypeButton = this.dialog.findViewById(R.id.operandTypeButton);
    private Button operatorTypeButton = this.dialog.findViewById(R.id.operatorTypeButton);
    private Button settingTypeButton = this.dialog.findViewById(R.id.settingTypeButton);
    private Button historyTypeButton = this.dialog.findViewById(R.id.historyTypeButton);

    public InputTileType(MainActivity context, Tile tileOutside)
    {
        super(context, tileOutside, null);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.START | Gravity.TOP;
        int[] location = new int[2];
        tile.getLocationOnScreen(location);
        wlp.x = location[0];
        wlp.y = location[1];
        window.setAttributes(wlp);

        stackTypeButton.setOnClickListener(
                new InputFraction(context, tile, dialog));

        operandTypeButton.setOnClickListener(
                new ChooseListMenu(context, TileType.OPERAND, tile, dialog));

        operatorTypeButton.setOnClickListener(
                new InputFraction(context, tile, dialog));

        settingTypeButton.setOnClickListener(
                new InputFraction(context, tile, dialog));

        //historyTypeButton.setOnClickListener();
    }

    @Override
    protected void setContentView() {
        contentView = R.layout.input_tile_type;
    }

    @Override
    public boolean onLongClick(View view) {
        dialog.show();
        return true;
    }
}
