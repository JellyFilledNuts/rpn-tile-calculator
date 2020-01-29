package de.fhdw.wip.rpntilecalculator.view;

import android.content.Context;
import android.view.View;
import android.view.Window;

import androidx.appcompat.widget.AppCompatButton;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.presenter.Presenter;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayout;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.TileScheme;
import de.fhdw.wip.rpntilecalculator.view.menu.InputTileType;

/**
 * Summary: Tile acts as button and forwards the connected type and click to the handler
 * Author:  Tom Bockhorn
 **/

public class Tile extends AppCompatButton implements TypeQuestionable {

    private Tile tile;
    private MainActivity context;
    private TileScheme scheme;
    private TileLayout tileLayout;

    public TileLayout getTileLayout()
    {
        return tileLayout;
    }

    public Tile(@NotNull final Context context, @NotNull TileScheme scheme, @NotNull Presenter presenter, @NotNull TileLayout tileLayout) {
        super(context);
        this.tile = this;
        this.tileLayout = tileLayout;
        this.context = (MainActivity) context;
        update(scheme);

        setOnClickListener(presenter);

        //setOnLongClickListener(new InputTileType(...))

        setOnLongClickListener(new InputTileType((MainActivity) context, Window.FEATURE_NO_TITLE, this));
    }

    private void menu() {
        InputTileType inputTileType = new InputTileType(context, Window.FEATURE_NO_TITLE, this);
    }

    public void update(@Nullable TileScheme scheme) {
        if(scheme == null) {
            System.out.println("[TILE] Could not update Tile " + getText());
            return; //TODO: Throw exception
        }
        this.scheme = scheme;
        this.setText(scheme.toDisplayText());
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
}