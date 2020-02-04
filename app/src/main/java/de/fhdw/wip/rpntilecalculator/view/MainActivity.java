package de.fhdw.wip.rpntilecalculator.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.WindowManager;
import android.widget.Toast;

import de.fhdw.wip.rpntilecalculator.R;
import de.fhdw.wip.rpntilecalculator.presenter.Presenter;
import de.fhdw.wip.rpntilecalculator.view.layout.ScreenOrientation;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayout;
import de.fhdw.wip.rpntilecalculator.view.layout.TileLayoutFactory;

/**
 * Summary: Starting activity that loads presenter and default layout
 * Author:  Tom Bockhorn
 * Date:    2019/09/04
 */
public class MainActivity extends AppCompatActivity {

    private static MainActivity mainActivity = null;
    private static Presenter presenter = new Presenter();
    private TileLayout tileLayout;
    private OrientationEventListener orientationListener;
    private int currentOrientation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = this;

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_main);

        setTileLayout(TileLayoutFactory.createLayout(this, "v_Standardlayout"));

        orientationListener = new OrientationEventListener(getApplicationContext()) {
            @Override
            public void onOrientationChanged(int orientation) {
                System.out.println(orientation + " - " + currentOrientation);
                if(orientation != currentOrientation){
                    currentOrientation = orientation;
                    if (orientation == 0 || orientation == 180) {
                        if(tileLayout.getOrientation() == ScreenOrientation.LANDSCAPE)
                            setTileLayout(TileLayoutFactory.createLayout(mainActivity, "v_Standardlayout"));
                        setRequestedOrientation(ScreenOrientation.PORTRAIT.getOrientation());
                    } else if (orientation == 90 || orientation == 270) {
                        if(tileLayout.getOrientation() == ScreenOrientation.PORTRAIT)
                            setTileLayout(TileLayoutFactory.createLayout(mainActivity, "h_Standardlayout"));
                        setRequestedOrientation(ScreenOrientation.LANDSCAPE.getOrientation());
                    }
                }
                System.out.println("End");
            }
        };
        if (orientationListener.canDetectOrientation()) {
            orientationListener.enable();
        }
    }

    public static MainActivity getInstance(){
        return mainActivity;
    }

    public void setTileLayout(TileLayout tileLayout) {
        if(tileLayout == null){
            Toast.makeText(getApplicationContext(), "Could not load Layout", Toast.LENGTH_LONG).show();

        }else{
            this.tileLayout = tileLayout;
            ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
            constraintLayout.removeAllViews();
            constraintLayout.setBackgroundColor(Color.WHITE);
            constraintLayout.addView(tileLayout.createView(this, presenter));
            presenter.setCurrentLayout(tileLayout);
        }
    }

    public TileLayout getTileLayout() {
        return tileLayout;
    }

}
