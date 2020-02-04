package de.fhdw.wip.rpntilecalculator.model.settings;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import de.fhdw.wip.rpntilecalculator.view.MainActivity;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayout;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutLoader;

/**
 * Summary: Creates a save layout menu to save the current design
 * Author:  Tim Jonas Meinerzhagen
 * Date:    2020/01/26
 */
public class SaveLayout extends Setting {

    @Contract(pure = true) @NotNull
    public static SaveLayout getInstance() { return new SaveLayout(); }

    /**
     * Clears the entire Stack and current input
     */
    @Override
    public boolean call() {
        final MainActivity activity = MainActivity.getInstance();
        final Dialog dialog = new Dialog(activity);

        LinearLayout l = new LinearLayout(activity.getBaseContext());
        final EditText text = new EditText(activity.getBaseContext());
        text.setText("Main");
        Button b = new Button(activity.getBaseContext());
        b.setText("Save");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                TileLayout t = activity.getTileLayout();
                t.setIndicator(text.getText().toString());
                TileLayoutLoader.saveLayout(activity.getBaseContext(), t);
                t.showAnimation(Tile.buttonSave);
            }
        });
        l.addView(text);
        l.addView(b);
        dialog.addContentView(l,
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        dialog.setCancelable(true);
        dialog.show();

        return true;
    }

}