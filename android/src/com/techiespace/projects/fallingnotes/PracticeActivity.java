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
        Log.d("Practice Activity ",midiName);
        initialize(new FallingNotesGame(midiName), cfg);
        //TODO: Imp issue: Probable crash in logs due to - android.util.AndroidRuntimeException: requestFeature() must be called before adding content
        //                                              at com.android.internal.policy.PhoneWindow.requestFeature(PhoneWindow.java:359)
    }

    @Override
    protected void onResume() {

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }
}
