package de.fhdw.wip.rpntilecalculator.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TableRow;

import androidx.appcompat.widget.AppCompatButton;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.R;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayout;
import de.fhdw.wip.rpntilecalculator.view.schemes.TileScheme;
import de.fhdw.wip.rpntilecalculator.view.menu.InputTileType;

/**
 * Summary: Tile acts as button and forwards the connected type and click to the handler
 *          Info of the type of the tile is stored in its scheme
 * Author:  Tom Bockhorn
 * Date:    2020/01/05
 **/
@SuppressLint("ViewConstructor")
public class Tile extends AppCompatButton implements TypeQuestionable {

    private static final int TILE_MARGIN = 3;
    public static Animation buttonClick;
    public static Animation buttonLoad;
    public static Animation buttonSave;

    private MainActivity context;
    private TileScheme scheme;
    @Nullable private TileLayout tileLayout;

    @Nullable
    public TileLayout getTileLayout()
    {
        return tileLayout;
    }

    /**
     * Creating a TileScheme in a TileLayout
     * @param scheme what type of scheme it is going to be
     * @param tileLayout the layout (if there is one)
     */
    public Tile(@NotNull Context context, @NotNull TileScheme scheme, @Nullable TileLayout tileLayout) {
        super(context);

        this.context = (MainActivity) context;
        this.tileLayout = tileLayout;

        buttonClick = AnimationUtils.loadAnimation(context, R.anim.button_bounce);
        buttonLoad = AnimationUtils.loadAnimation(context, R.anim.button_load);
        buttonSave = AnimationUtils.loadAnimation(context, R.anim.button_save);

        update(scheme);
    }

    /**
     * Updates the tile's display and text
     * @param scheme new scheme
     */
    public void update(@Nullable TileScheme scheme) {
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f);
        layoutParams.setMargins(TILE_MARGIN, TILE_MARGIN, TILE_MARGIN, TILE_MARGIN);
        setLayoutParams(layoutParams);

        if(scheme != null) {
            this.scheme = scheme;
            this.setBackgroundResource(getScheme().getStyle());
            this.setText(scheme.toDisplayText());
        } else {
            //Could not draw Tile
            update(TileScheme.createTileScheme(TileMapping.X_ERROR, scheme.getContent()));
        }

    }

    /**
     * Enables and sets the onLongClick function for the given tile
     */
    public void enableMenuListener() {
        setOnLongClickListener(new InputTileType(context, this));
    }

    public TileScheme getScheme() {
        return scheme;
    }

    @Override
    public boolean isStack() {
        return scheme.getTileType().getType().isStack();
    }

    @Override
    public boolean isOperand() {
        return scheme.getTileType().getType().isOperand();
    }

    @Override
    public boolean isAction() {
        return scheme.getTileType().getType().isAction();
    }

    @Override
    public boolean isSetting() {
        return scheme.getTileType().getType().isSetting();
    }

    @Override
    public boolean isHistory() {
        return scheme.getTileType().getType().isHistory();
    }

    /**
     * Show an animation when clicked
     */
    public void showAnimation(Animation animation) {
        startAnimation(animation);
    }
}