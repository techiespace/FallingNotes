package com.techiespace.projects.fallingnotes.course.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.techiespace.projects.fallingnotes.R;

public class UniversityInstructionsActivity extends YouTubeBaseActivity {


    String instructions;
    TextView tv_instructions;
    YouTubePlayer mPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_instructions);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        tv_instructions = findViewById(R.id.tv);

        if(intent.hasExtra("instructions_TEXT"))
        {
            instructions = intent.getStringExtra("instructions_TEXT");
            tv_instructions.setText(instructions);
            //  System.out.println("Practice Activity  HAs instructions"+instructions);
        }

        final YouTubePlayerView youtubePlayerView = findViewById(R.id.youtubePlayerView);
        playVideo("BmTcXlfT1OE", youtubePlayerView);

    }

    public void playVideo(final String videoId, YouTubePlayerView youTubePlayerView) {
        youTubePlayerView.initialize("AIzaSyAk86K59BVMbKdAR-eB5kb0fc2mTkt7eMw",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(final YouTubePlayer.Provider provider,
                                                        final YouTubePlayer youTubePlayer, boolean b) {
                        if (youTubePlayer == null) return;
                        mPlayer = youTubePlayer;
                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                        //youTubePlayer.cueVideo(videoId, stopArr[0]);
                        youTubePlayer.loadVideo(videoId, 0);
                        //youTubePlayer.play();
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {
                    }
                });
    }

    /*@Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }*/
}
