package de.fhdw.wip.rpntilecalculator.model.settings;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.view.MainActivity;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutFactory;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutLoader;

/**
 * Summary: Creates a load layout menu to load a new layout design
 * Author:  Tim Jonas Meinerzhagen
 * Date:    2020/01/26
 */
public class LoadLayout extends Setting {

    @Contract(pure = true) @NotNull
    public static LoadLayout getInstance() { return new LoadLayout(); }

    /**
     * Clears the entire Stack and current input
     */
    @Override
    public boolean call() {
        final MainActivity activity = MainActivity.getInstance();

        final Dialog dialog = new Dialog(activity);

        LinearLayout l = new LinearLayout(activity.getBaseContext());
        for(final String s : TileLayoutLoader.getSavedLayouts(activity.getBaseContext(),
                                                              activity.getTileLayout().getOrientation())){
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