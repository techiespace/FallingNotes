package com.techiespace.projects.fallingnotes;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.techiespace.projects.fallingnotes.pianoHelpers.PianoView;


public class MidiPlayActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private final static float SEEKBAR_OFFSET_SIZE = -12;
    public TextView noteText;
    private SeekBar seekbar;
    private int scrollProgress = 0;
    private PianoView pianoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove the title bar
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);


        pianoView = findViewById(R.id.pv);
        noteText = findViewById(R.id.note_text);

        seekbar = findViewById(R.id.sb);
        seekbar.setThumbOffset((int) convertDpToPixel(SEEKBAR_OFFSET_SIZE));
        seekbar.setOnSeekBarChangeListener(this);


        AudioProcessor Ap = new AudioProcessor(noteText, pianoView);


    }

    @Override
    protected void onResume() {

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        Log.d("v", "onProgressChange" + i + " " + b);
        pianoView.scroll(i);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private float convertDpToPixel(float dp) {
        Resources resources = this.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

}
