package de.fhdw.wip.rpntilecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import de.fhdw.wip.rpntilecalculator.presenter.Presenter;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayout;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutFactory;

/**
 * Summary: Starting activity that loads presenter and default layout
 * Author:  Tom Bockhorn
 * Date:    2019/09/04
 */
public class MainActivity extends AppCompatActivity {

    public static MainActivity mainActivity = null;
    private static Presenter presenter = new Presenter();
    private TileLayout tileLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_main);

        setTileLayout(TileLayoutFactory.createLayout(this, "Standardlayout"));
    }

    public void setTileLayout(TileLayout tileLayout) {
        this.tileLayout = tileLayout;
        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.removeAllViews();
        constraintLayout.setBackgroundColor(Color.WHITE);
        constraintLayout.addView(tileLayout.createView(this, presenter));
        presenter.setCurrentLayout(tileLayout);
        setRequestedOrientation(tileLayout.getOrientation().getOrientation());
    }

    public TileLayout getTileLayout() {
        return tileLayout;
    }

}
