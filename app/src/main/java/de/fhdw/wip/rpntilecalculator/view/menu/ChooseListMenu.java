package de.fhdw.wip.rpntilecalculator.view.menu;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.R;
import de.fhdw.wip.rpntilecalculator.view.Tile;
import de.fhdw.wip.rpntilecalculator.view.TileMapping;
import de.fhdw.wip.rpntilecalculator.view.TileType;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.TileScheme;
import de.fhdw.wip.rpntilecalculator.view.menu.utils.DialogMenu;
import de.fhdw.wip.rpntilecalculator.view.menu.utils.InputMenuFactory;

public class ChooseListMenu extends DialogMenu {

    private ArrayList<TileMapping> tileOptions = new ArrayList<>();

    public ChooseListMenu(MainActivity context, TileType type, Tile displayTile, DialogMenu last) {
        super(context, displayTile, last);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        Point size = new Point();
        context.getWindowManager().getDefaultDisplay().getSize(size);
        wlp.height = (int) (size.y * 0.4);
        wlp.width = (int) (size.x * 0.8);
        window.setAttributes(wlp);

        for(TileMapping mapping : TileMapping.values()) {
            if(type == mapping.getType()) tileOptions.add(mapping);
        }
    }

    /**
     * Creates a 2d arraylist of buttons and sets their listener
     */
    public TableLayout createView() {

        //Create table by first creating one column as TableLayout
        TableLayout tableView = new TableLayout(context);
        tableView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tableView.setBackgroundResource(R.drawable.tile_operand_white);
        tableView.setPadding(20, 20, 20, 20);

        //Creating the in schemeLayout defined amount of rows
        int[] rowLengths = new int[]{
                tileOptions.size() / 2,
                tileOptions.size() - (tileOptions.size() / 2)};
        int currentOption = 0;
        for(int i = 0; i < 2; i++) {
            TableRow rowView = new TableRow(context);
            rowView.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            rowView.setGravity(Gravity.CENTER);
            rowView.setBackgroundColor(Color.WHITE);

            //Creating buttons which amount defines the amount of columns
            for(int j = 0; j < rowLengths[i]; j++) {

                TileMapping mapping = tileOptions.get(currentOption);
                TileScheme scheme = TileScheme.createTileScheme(mapping, mapping.getMenuText());

                Tile tile = new Tile(context, scheme, null);

                View.OnClickListener listener = InputMenuFactory.createListener(context, mapping, this.tile, this);
                tile.setOnClickListener(listener);
                tile.setText(mapping.getMenuText());

                rowView.addView(tile);

                currentOption++;
            }
            tableView.addView(rowView);
        }
        return tableView;
    }

    @Override
    public void onClick(View v) {
        ConstraintLayout constraintLayout = this.dialog.findViewById(R.id.constraintLayout);
        constraintLayout.addView(createView());
        dialog.show();
    }

    @Override
    protected void setContentView() {
        contentView = R.layout.activity_main;
    }
}
