package de.fhdw.wip.rpntilecalculator.view.menu;

import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.view.Tile;

public abstract class DialogMenu {

    protected Dialog dialog;
    protected int contentView;
    final protected Tile tile;

    public DialogMenu(MainActivity context, int windowFeature, Tile tileOutside)
    {
        this.dialog = new Dialog(context);
        this.setContentView();
        this.dialog.setContentView(contentView);
        this.dialog.requestWindowFeature(windowFeature);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);

        this.tile = tileOutside;
    }

    protected abstract void setContentView();

    public void show()
    {
        dialog.show();
    }
}
