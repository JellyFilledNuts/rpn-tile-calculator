package de.fhdw.wip.rpntilecalculator.view;

import android.content.Context;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.view.layout.TileScheme;

/*
 * Summary: Tile acts as button and forwards the connected type and click to the handler
 * Author:  Tom Bockhorn
 */

public class Tile extends AppCompatButton implements TypeQuestionable {

    private MainActivity context;
    private TileScheme scheme;

    public Tile(@NotNull Context context, @NotNull TileScheme scheme) {
        super(context);
        this.context = (MainActivity) context;
        update(scheme);

        setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                give();
            }
        });
    }

    private void give() {
        this.context.action(this);
    }

    public void update(@Nullable TileScheme scheme) {
        if(scheme == null) {
            System.out.println("[TILE] Could not update Tile " + this.scheme.getTileType() + " " + getText());
            return; //TODO: Throw exception
        }
        this.scheme = scheme;
        this.setText(scheme.getContent());
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
}