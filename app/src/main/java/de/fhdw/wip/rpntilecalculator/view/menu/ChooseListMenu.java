package de.fhdw.wip.rpntilecalculator.view.menu;

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
import de.fhdw.wip.rpntilecalculator.view.schemes.TileScheme;
import de.fhdw.wip.rpntilecalculator.view.menu.utils.DialogMenu;
import de.fhdw.wip.rpntilecalculator.view.menu.utils.InputMenuFactory;

public class ChooseListMenu extends DialogMenu {

    private ArrayList<TileMapping> tileOptions = new ArrayList<>();
    private InputMenuFactory menuFactory;

    /**
     * Create a List Menu for putting in the type of a tile (action / setting / operand)
     * @param type super-type that contains the mappings
     * @param displayTile tile that should be edited
     * @param last last menu to close at once
     */
    public ChooseListMenu(MainActivity context, TileType type, Tile displayTile, DialogMenu last) {
        super(context, displayTile, last);
        menuFactory = new InputMenuFactory(displayTile, last, this);

        adjustWindow(0.4, 0.8);

        for(TileMapping mapping : TileMapping.values()) {
            if(type == mapping.getType()) tileOptions.add(mapping);
        }
    }

    /**
     * Create a list Menu for deciding on the stack number
     * @param type either stack or history-stack
     * @param displayTile tile that should be edited
     * @param last last menu to close at once
     */
    public ChooseListMenu(MainActivity context, TileMapping type, Tile displayTile, DialogMenu last) {
        super(context, displayTile, last);
        menuFactory = new InputMenuFactory(displayTile, last, this);

        adjustWindow(0.4, 0.8);

        tileOptions.add(type);
    }

    /**
     * Creates a 2d arraylist of buttons and sets their listener
     */
    public TableLayout createView(int rows) {

        //Create table by first creating one column as TableLayout
        TableLayout tableView = new TableLayout(context);
        tableView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tableView.setBackgroundResource(R.drawable.tile_operand_white);
        tableView.setPadding(20, 20, 20, 20);

        //Creating the defined amount of rows
        int[] rowLengths = new int[rows];
        for(int i = 0; i < rows; i++) {
            int optionsInRow = tileOptions.size() / rows;
            rowLengths[i] = i != rows-1 ? optionsInRow : tileOptions.size() - (optionsInRow * i);
        }
        int currentOption = 0;

        for(int i = 0; i < rows; i++) {
            TableRow rowView = new TableRow(context);
            rowView.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1.0f));
            rowView.setGravity(Gravity.CENTER);
            rowView.setBackgroundColor(Color.WHITE);

            //Creating buttons which amount defines the amount of columns
            for(int j = 0; j < rowLengths[i]; j++) {

                Tile option = createOption(currentOption);

                rowView.addView(option);
                currentOption++;
            }
            tableView.addView(rowView);
        }
        return tableView;
    }

    /**
     * Update the amount of stack tiles if it is to be changed
     */
    private void updateAmountIfStack() {
        TileMapping mapping = tileOptions.get(0);
        if(mapping.getType().isStack() || mapping.getType().isHistory()) {
            tileOptions.clear();
            tileOptions.add(mapping);
        }
        int amount = 0;
        if(mapping.getType().isStack()) amount = tile.getTileLayout().getStackSize();
        if(mapping.getType().isHistory()) amount = tile.getTileLayout().getHistoryStackSize();
        for(int i = 0; i < amount; i++) //Add one more than amount (0) to allow expansion
            tileOptions.add(tileOptions.get(0));
    }

    /**
     * Creates the tile based on the mapping
     */
    private Tile createOption(int currentOption) {
        TileMapping mapping = tileOptions.get(currentOption);
        if(mapping.getType().isStack() || mapping.getType().isHistory())
            return createStackOption(mapping, currentOption+1);
        else return createOptionByMapping(mapping);
    }

    /**
     * Creates option of certain non-Stack TileMappings and sets corresponding listener
     */
    private Tile createOptionByMapping(TileMapping mapping) {
        TileScheme scheme = TileScheme.createTileScheme(mapping, mapping.getMenuText());

        Tile tile = new Tile(context, scheme, null);

        View.OnClickListener listener = menuFactory.createListener(context, mapping);
        tile.setOnClickListener(listener);
        tile.setText(mapping.getMenuText());
        return tile;
    }

    /**
     * Creates option of Stack TileMappings and sets corresponding listener
     */
    private Tile createStackOption(TileMapping mapping, int rank) {
        TileScheme scheme = TileScheme.createTileScheme(mapping, null, rank);

        Tile tile = new Tile(context, scheme, null);

        View.OnClickListener listener = menuFactory.createListener(context, mapping, rank);
        tile.setOnClickListener(listener);
        tile.setText(String.valueOf(rank));
        return tile;
    }

    /**
     * Adjust the window size
     */
    private void adjustWindow(double heightInPercent, double widthInPercent) {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        Point size = new Point();
        context.getWindowManager().getDefaultDisplay().getSize(size);
        wlp.height = (int) (size.y * heightInPercent);
        wlp.width = (int) (size.x * widthInPercent);
        window.setAttributes(wlp);
    }

    @Override
    public void onClick(View v) {
        updateAmountIfStack();
        ConstraintLayout constraintLayout = this.dialog.findViewById(R.id.constraintLayout);
        int rows = (tileOptions.size() / 6) + 1; //max. 5 tiles in one row
        constraintLayout.addView(createView(rows));
        dialog.show();
    }

    @Override
    protected void setContentView() {
        contentView = R.layout.activity_main;
    }
}
