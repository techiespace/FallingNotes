package com.techiespace.projects.fallingnotes.course.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.techiespace.projects.fallingnotes.R;

import androidx.appcompat.app.AppCompatActivity;

public class UniversityInstructionsActivity extends AppCompatActivity {


    String instructions;
    TextView tv_instructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_instructions);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
         tv_instructions = (TextView)findViewById(R.id.tv);

        if(intent.hasExtra("instructions_TEXT"))
        {


            instructions = intent.getStringExtra("instructions_TEXT");
            tv_instructions.setText(instructions);
            //  System.out.println("Practice Activity  HAs instructions"+instructions);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
