package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class GestureResponse {

    FallingNotesGame app;

    FallingNotesScreen gameScreen;

    Texture play;
    Texture pause;

    SpriteBatch bbatch;

    long startTime;
    long endTime;


    boolean showPlay;
    boolean showPause;


    public GestureResponse(FallingNotesGame app, FallingNotesScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.app = app;
        play = app.assets.get("piano/play.png", Texture.class);
        pause = app.assets.get("piano/pause.png", Texture.class);

        this.bbatch = gameScreen.bbatch;

        showPlay = false;
        showPause = false;
    }

    public void showPlayResponse() {
        startTime = TimeUtils.millis();
        endTime = startTime + 500;
        showPlay = true;

    }

    public void showPauseResponse() {
        startTime = TimeUtils.millis();
        endTime = startTime + 500;
        showPause = true;
    }

    public void resetResponse() {
        showPause = false;
        showPlay = false;
    }


    public void renderResponse() {

        bbatch.begin();

        if (endTime>TimeUtils.millis()) {
            if (showPlay == true) {

                bbatch.draw(play, Constants.WORLD_WIDTH *0.45f, Constants.WORLD_HEIGHT / 2, Constants.WORLD_WIDTH / 10, Constants.WORLD_WIDTH / 10);
            } else if (showPause == true) {

                bbatch.draw(pause, Constants.WORLD_WIDTH *0.45f, Constants.WORLD_HEIGHT / 2, Constants.WORLD_WIDTH / 10, Constants.WORLD_WIDTH / 10);


            }


        }

        bbatch.end();

        if(endTime<TimeUtils.millis())
            resetResponse();



    }


}
