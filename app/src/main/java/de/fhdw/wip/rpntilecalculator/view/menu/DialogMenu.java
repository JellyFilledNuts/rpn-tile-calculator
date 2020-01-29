package de.fhdw.wip.rpntilecalculator.view.menu;

import android.app.Dialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.view.Tile;

/**
 * Summary: Super Class: Tile acts as button and forwards the connected type and click to the handler
 * Author:  Getuart Istogu
 **/

public abstract class DialogMenu {

    protected MainActivity context;
    protected Dialog dialog;
    @Nullable
    protected Dialog last;
    protected int contentView;
    final protected Tile tile;

    public DialogMenu(MainActivity context, int windowFeature, Tile tileOutside, Dialog last)
    {
        this.context = context;
        this.last = last;
        this.dialog = new Dialog(context);
        this.dialog.requestWindowFeature(windowFeature);

        this.setContentView();
        this.dialog.setContentView(contentView);


        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);

        this.tile = tileOutside;
    }

    protected abstract void setContentView();


    public void dismissAll()
    {
        if(last != null) last.dismiss();
        dialog.dismiss();
    }


}
