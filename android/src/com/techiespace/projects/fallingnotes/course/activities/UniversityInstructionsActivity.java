package com.techiespace.projects.fallingnotes.course.activities;

import android.os.Bundle;

import com.techiespace.projects.fallingnotes.R;

import androidx.appcompat.app.AppCompatActivity;

public class UniversityInstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_instructions);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
