package com.techiespace.projects.fallingnotes;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.techiespace.projects.fallingnotes.pianoHelpers.Note;
import com.techiespace.projects.fallingnotes.pianoHelpers.PianoKey;
import com.techiespace.projects.fallingnotes.pianoHelpers.PianoView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PracticeActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private final static float SEEKBAR_OFFSET_SIZE = -12;
    public TextView noteText;
    public ExecutorService executor = Executors.newCachedThreadPool();
    ArrayList<PianoKey[]> whitePianoKeys;
    ArrayList<PianoKey[]> blackPianoKeys;
    Handler handler = new Handler();
    private SeekBar seekbar;
    private int scrollProgress = 0;
    private PianoView pianoView;
    private ArrayList<Note> noteList = new ArrayList<Note>();

    protected void init() {
        /*noteList.add(new Note(0, 1000, "C4"));
        noteList.add(new Note(1000, 2000, "D4"));
        noteList.add(new Note(2000, 3000, "E4"));
        noteList.add(new Note(3000, 4000, "F4"));
        noteList.add(new Note(4000, 5000, "G4"));
        noteList.add(new Note(5000, 6000, "A4"));
        noteList.add(new Note(6000, 7000, "B4"));*/
        MidiParser midiParser = new MidiParser(this);
        noteList = midiParser.parse("CScale.mid");
    }

    protected void Play() {
        if (noteList != null) {
//            Log.v("practice activity ", String.valueOf(new Date()));
            for (Note note : noteList) {
                RunnableNote rn = new RunnableNote(note, pianoView);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        executor.execute(rn);

                    }
                }, note.getStartTime());
//                    try {
//                        Thread.sleep(note.getStartTime());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                // }
            }
        }
    }


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
        // mediaPlayer = MediaPlayer(this,)
        //Calling the initializing function
        init();
        if (savedInstanceState != null)
            Play();
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
//    private PianoKey findPianoKey(String input) {
//        ArrayList<PianoKey[]> pianoKeys;
//        pianoKeys = pianoView.getWhiteStaticKeys();
//        if(pianoKeys==null)
//            Log.v("PracticeActivity  ","Static white Piano key is null");
////        for (PianoKey[] pianokey : pianoKeys) {
////            for (int i = 0; i < pianokey.length; i++) {
////                if (pianokey[i].getLetterName().equals(input)) {
////                    return pianokey[i];
////                }
////            }
////        }
//        return null;
//    }

}
