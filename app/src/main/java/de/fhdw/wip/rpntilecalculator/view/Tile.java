package de.fhdw.wip.rpntilecalculator.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.fhdw.wip.rpntilecalculator.MainActivity;
import de.fhdw.wip.rpntilecalculator.R;
import de.fhdw.wip.rpntilecalculator.model.operands.OFraction;
import de.fhdw.wip.rpntilecalculator.presenter.Presenter;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayout;
import de.fhdw.wip.rpntilecalculator.view.layout.schemes.TileScheme;
import de.fhdw.wip.rpntilecalculator.view.menu.InputFraction;

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



    public Tile(@NotNull Context context, @NotNull TileScheme scheme, @NotNull Presenter presenter, @NotNull TileLayout tileLayout) {
        super(context);
        this.tile = this;
        this.tileLayout = tileLayout;
        this.context = (MainActivity) context;
        update(scheme);

        setOnClickListener(presenter);

        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                menu();
                return true;
            }
        });
    }

    private void menu() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.button_menu);

        Button stackButton = dialog.findViewById(R.id.menu_button2);
        stackButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                final Dialog operandDialog = new Dialog(context);
                operandDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                operandDialog.setContentView(R.layout.input_fraction);

                Window window = operandDialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.CENTER;
                window.setAttributes(wlp);
                operandDialog.show();
                Button enterButton = operandDialog.findViewById(R.id.enterButton);
                enterButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText numeratorText = operandDialog.findViewById(R.id.numerator);
                        EditText denumeratorText = operandDialog.findViewById(R.id.denumerator);
                        int nummerator = Integer.parseInt(numeratorText.getText().toString());
                        int denumerator = Integer.parseInt(denumeratorText.getText().toString());
                        OFraction oFraction = new OFraction(nummerator, denumerator);
                        TileScheme newTileScheme = TileScheme.createTileScheme(TileMapping.O_FRACTION, oFraction, 0);
                        update(newTileScheme);
                        setBackgroundResource(R.drawable.tile_operand_white);
                        tileLayout.removeFromHistoryStack(tile);
                        operandDialog.dismiss();
                        dialog.dismiss();
                    }
                });


            }
        });


        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.START | Gravity.TOP;
        int[] location = new int[2];
        this.getLocationOnScreen(location);
        wlp.x = location[0];
        wlp.y = location[1];
        window.setAttributes(wlp);

        dialog.show();
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