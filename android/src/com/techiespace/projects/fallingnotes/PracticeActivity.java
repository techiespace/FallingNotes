package com.techiespace.projects.fallingnotes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


public class PracticeActivity extends AndroidApplication {

    String midiName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        if(intent.hasExtra(Intent.EXTRA_TEXT)) {
           midiName = intent.getStringExtra(Intent.EXTRA_TEXT);
        }
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

        initialize(new FallingNotesGame(midiName), cfg);
        }

    @Override
    protected void onResume() {

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }
}
