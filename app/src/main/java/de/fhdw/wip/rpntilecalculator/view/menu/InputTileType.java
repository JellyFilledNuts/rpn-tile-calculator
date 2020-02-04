package de.fhdw.wip.rpntilecalculator.view.menu;

import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import de.fhdw.wip.rpntilecalculator.view.MainActivity;
import de.fhdw.wip.rpntilecalculator.R;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.TileType;

/**
 * Summary: Menu for inputting the type of the tile
 * Author:  Getuart Istogu
 * Date:    2020/01/29
 **/
public class InputTileType extends DialogMenu implements View.OnLongClickListener {

    private Button stackTypeButton = this.dialog.findViewById(R.id.stackTypeButton);
    private Button operandTypeButton = this.dialog.findViewById(R.id.operandTypeButton);
    private Button operatorTypeButton = this.dialog.findViewById(R.id.operatorTypeButton);
    private Button settingTypeButton = this.dialog.findViewById(R.id.settingTypeButton);
    private Button historyTypeButton = this.dialog.findViewById(R.id.historyTypeButton);

    public InputTileType(MainActivity context, Tile displayTile)
    {
        super(context, displayTile, null);

        // Special design and location
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.START | Gravity.TOP;
        int[] location = new int[2];
        tile.getLocationInWindow(location);
        wlp.x = location[0];
        wlp.y = location[1];
        Point size = new Point();
        context.getWindowManager().getDefaultDisplay().getSize(size);
        wlp.height = size.y / 3;
        wlp.width = size.x / 3;
        window.setAttributes(wlp);


        stackTypeButton.setOnClickListener(
                new ChooseListMenu(context, TileMapping.S_STACK, tile, this));

        operandTypeButton.setOnClickListener(
                new ChooseListMenu(context, TileType.OPERAND, tile, this));

        operatorTypeButton.setOnClickListener(
                new ChooseListMenu(context, TileType.ACTION, tile, this));

        settingTypeButton.setOnClickListener(
                new ChooseListMenu(context, TileType.SETTING, tile, this));

        historyTypeButton.setOnClickListener(
                new ChooseListMenu(context, TileMapping.H_HISTORY, tile, this));
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
