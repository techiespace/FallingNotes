package com.techiespace.projects.fallingnotes;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


public class PracticeActivity extends AndroidApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String midiName = intent.getStringExtra("midi");
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        initialize(new FallingNotesGame(), cfg);
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
