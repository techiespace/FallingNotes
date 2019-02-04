package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import static com.techiespace.projects.fallingnotes.FallingNotesScreen.theme;

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
    boolean showTempo;

    BitmapFont font;



    public GestureResponse(FallingNotesGame app, FallingNotesScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.app = app;
        play = app.assets.get("piano/play.png", Texture.class);
        pause = app.assets.get("piano/pause.png", Texture.class);

        this.bbatch = gameScreen.bbatch;

       // font.setColor(theme.getGameNameColor());
       // font.getData().setScale(FallingNotesScreen.getTheme().getGameNameScale());
       font = new BitmapFont();
       font.getData().setScale(2);
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
        endTime = startTime + 100;
        showPause = true;
    }

    public void resetResponse() {
        showPause = false;
        showPlay = false;
        showTempo = false;
    }

    public void showTempoResponse()
    {
        startTime = TimeUtils.millis();
        endTime = startTime + 500;
        showTempo  = true;
    }


    public void renderResponse() {

        bbatch.begin();

        if (endTime>TimeUtils.millis()) {
            if (showPlay == true) {

                bbatch.draw(play, Constants.WORLD_WIDTH *0.55f, Constants.WORLD_HEIGHT / 2, Constants.WORLD_WIDTH / 10, Constants.WORLD_WIDTH / 10);
            } else if (showPause == true) {

                bbatch.draw(pause, Constants.WORLD_WIDTH *0.55f, Constants.WORLD_HEIGHT / 2, Constants.WORLD_WIDTH / 10, Constants.WORLD_WIDTH / 10);


            }
            else if(showTempo == true) {
                font.draw(bbatch, (int)(gameScreen.getPrefs().getFloat("tempo_multiplier")*100)+"",Constants.WORLD_WIDTH *0.45f,Constants.WORLD_HEIGHT / 2 );//Constants.NOTES_WIDTH*36/2,Constants.OFFSET/2+20);
            }


        }

        bbatch.end();

        if(endTime<TimeUtils.millis())
            resetResponse();



    }


}
