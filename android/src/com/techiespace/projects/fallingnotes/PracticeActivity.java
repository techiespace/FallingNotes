package com.techiespace.projects.fallingnotes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.techiespace.projects.fallingnotes.pianoHelpers.Note;


public class PracticeActivity extends AndroidApplication {

    String midiName;
    String instructions;
    boolean playMidi;
    public static final String PREF_USER_FIRST_TIME_GAME = "user_first_time";
    boolean isUserFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isUserFirstTime = Boolean.valueOf(Utils.readSharedSetting(this, PREF_USER_FIRST_TIME_GAME, "true"));

        Intent introIntent = new Intent(this, SliderActivity.class);
        introIntent.putExtra(PREF_USER_FIRST_TIME_GAME, isUserFirstTime);

        if (isUserFirstTime)
            startActivity(introIntent);



        Intent intent = getIntent();

        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            midiName = intent.getStringExtra(Intent.EXTRA_TEXT);
        }

        playMidi = intent.getBooleanExtra("playMidi", true);

        if(intent.hasExtra("instructions_TEXT"))
        {


            instructions = intent.getStringExtra("instructions_TEXT");
            //  System.out.println("Practice Activity  HAs instructions"+instructions);
        }
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

        //This is to keep the Game screen awake
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        System.out.println("Is valid Midi: " + isValidMidi());
        if (isValidMidi()) {

            if (instructions == null)
                initialize(new FallingNotesGame(midiName, playMidi), cfg);
            else {
                //System.out.println("Practice Activity "+instructions);
                initialize(new FallingNotesGame(midiName, instructions, playMidi), cfg);
            }
        } else {
//            Intent i = new Intent(this,MainActivity.class);
            Toast.makeText(this, "Invalid Midi", Toast.LENGTH_SHORT).show();
            finish();
//            this.startActivity(i);
        }
    }

    private boolean isValidMidi() {
        AndroidMidiParser midiParser = new AndroidMidiParser(this);
        Note[] notes = midiParser.parse(midiName);
        if (notes.length == 0)
            return true;
        if (notes.length == 1 && notes[0].getPressVelocity() == 0)  //inappmidi
            return true;
        for (Note note : notes) {
            if (note.getNoteName().equals("Error"))
                return false;
            if (note.getTrack() != 0 && note.getTrack() != 1 && note.getTrack() != 2)   //piano & voice
                return false;
        }
        return true;
    }

    @Override
    protected void onResume() {
        if (isValidMidi())
            if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        super.onResume();
    }
}
