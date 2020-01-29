package de.fhdw.wip.rpntilecalculator.view.menu;

import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.R;
import de.fhdw.wip.rpntilecalculator.view.Tile;

/**
 * Summary: Tile acts as button and forwards the connected type and click to the handler
 * Author:  Getuart Istogu
 **/

public class InputTileType extends DialogMenu implements View.OnLongClickListener {

    private Button stackTypeButton = this.dialog.findViewById(R.id.stackTypeButton);
    private Button operandTypeButton = this.dialog.findViewById(R.id.operandTypeButton);
    private Button operatorTypeButton = this.dialog.findViewById(R.id.operatorTypeButton);
    private Button settingTypeButton = this.dialog.findViewById(R.id.settingTypeButton);

    //onLongClick(this.show())

    public InputTileType(MainActivity context, int windowFeature, Tile tileOutside)
    {
        super(context, windowFeature, tileOutside, null);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.START | Gravity.TOP;
        int[] location = new int[2];
        tile.getLocationOnScreen(location);
        wlp.x = location[0];
        wlp.y = location[1];
        window.setAttributes(wlp);

        stackTypeButton.setOnClickListener(new InputFraction(context, Window.FEATURE_NO_TITLE, tile, dialog));

        operandTypeButton.setOnClickListener(new InputFraction(context, Window.FEATURE_NO_TITLE, tile, dialog));

        operatorTypeButton.setOnClickListener(new InputFraction(context, Window.FEATURE_NO_TITLE, tile, dialog));

        settingTypeButton.setOnClickListener(new InputFraction(context, Window.FEATURE_NO_TITLE, tile, dialog));
    }

    @Override
    protected void setContentView() {
        contentView = R.layout.button_menu;
    }

    @Override
    public boolean onLongClick(View view) {
        dialog.show();
        return true;
    }
}
