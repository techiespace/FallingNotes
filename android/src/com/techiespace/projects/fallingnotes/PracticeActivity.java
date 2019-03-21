package com.techiespace.projects.fallingnotes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


public class PracticeActivity extends AndroidApplication {

    String midiName;
    String instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            midiName = intent.getStringExtra(Intent.EXTRA_TEXT);
        }

        if(intent.hasExtra("instructions_TEXT"))
        {


            instructions = intent.getStringExtra("instructions_TEXT");
          //  System.out.println("Practice Activity  HAs instructions"+instructions);
        }
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

        //This is to keep the Game screen awake
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        if(instructions==null)
        initialize(new FallingNotesGame(midiName), cfg);
        else
        {
            //System.out.println("Practice Activity "+instructions);
            initialize(new FallingNotesGame(midiName,instructions), cfg);
        }
    }

    @Override
    protected void onResume() {

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }
}
