package de.fhdw.wip.rpntilecalculator.view.menu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import de.fhdw.wip.rpntilecalculator.view.MainActivity;
import de.fhdw.wip.rpntilecalculator.view.Tile;

/**
 * Summary: Super Class: Tile acts as button and forwards the connected type and click to the handler
 * Author:  Getuart Istogu
 * Date:    2020/01/29
 **/

public abstract class DialogMenu implements View.OnClickListener {

    protected MainActivity context;
    protected Dialog dialog;
    @Nullable
    protected DialogMenu last;
    protected int contentView;
    final protected Tile tile;

    public DialogMenu(MainActivity context, Tile displayTile, DialogMenu last)
    {
        this.context = context;
        this.last = last;
        this.dialog = new Dialog(context);
        this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.setContentView();
        this.dialog.setContentView(contentView);


        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);

        this.tile = displayTile;

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dismissAll();
            }
        });
    }

    protected abstract void setContentView();


    protected void dismissAll()
    {
        if(last != null) last.dismissAll();
        dialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        dialog.show();
    }
}
