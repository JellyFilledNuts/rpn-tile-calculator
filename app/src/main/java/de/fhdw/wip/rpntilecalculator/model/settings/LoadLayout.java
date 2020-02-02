package de.fhdw.wip.rpntilecalculator.model.settings;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutFactory;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutLoader;

public class LoadLayout extends Setting {

    @Contract(pure = true) @NotNull
    public static LoadLayout getInstance() { return new LoadLayout(); }

    /**
     * Clears the entire Stack and current input
     */
    @Override
    public boolean call() {
        final MainActivity activity = MainActivity.mainActivity;

        final Dialog dialog = new Dialog(activity);

        LinearLayout l = new LinearLayout(activity.getBaseContext());
        for(final String s : TileLayoutLoader.getSavedLayouts(activity.getBaseContext())){
            Button b = new Button(activity.getBaseContext());
            b.setText(s);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                    activity.setTileLayout(TileLayoutFactory.createLayout(activity.getBaseContext(), s));
                }
            });
            l.addView(b);
        }
        dialog.addContentView(l,
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        dialog.setCancelable(true);
        dialog.show();

        return true;
    }

}