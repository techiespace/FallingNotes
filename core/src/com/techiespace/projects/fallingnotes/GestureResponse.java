package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

import static com.techiespace.projects.fallingnotes.FallingNotesScreen.theme;

public class GestureResponse {

    FallingNotesGame app;

    FallingNotesScreen gameScreen;

    Texture play;
    Texture pause;
    Texture reset;

    SpriteBatch bbatch;

    long startTime;
    long endTime;


    boolean showPlay;
    boolean showPause;
    boolean showTempo;
    boolean showReset;

    BitmapFont font;


    public GestureResponse(FallingNotesGame app, FallingNotesScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.app = app;
        play = app.assets.get("piano/play.png", Texture.class);
        pause = app.assets.get("piano/pause.png", Texture.class);
        reset = app.assets.get("piano/reset.png", Texture.class);


        this.bbatch = gameScreen.bbatch;

        Texture texture = new Texture(Gdx.files.internal("font/courgette.png"), true); // true enables mipmaps
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image
        font = new BitmapFont(Gdx.files.internal("font/courgette.fnt"), new TextureRegion(texture), false);


        font.setColor(Color.WHITE);
         font.getData().setScale(1.2f);
//        font = new BitmapFont();
//        font.getData().setScale(2);
        showPlay = false;
        showPause = false;
        showTempo = false;
        showReset = false;
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
        showReset = false;
    }

    public void showTempoResponse() {
        startTime = TimeUtils.millis();
        endTime = startTime + 500;
        showTempo = true;
    }
    public void showResetResponse() {
        startTime = TimeUtils.millis();
        endTime = startTime + 500;
        showReset = true;
    }


    public void renderResponse() {

        bbatch.begin();

        if (endTime > TimeUtils.millis()) {
            if (showPlay == true) {

                bbatch.draw(pause, Constants.WORLD_WIDTH * 0.45f, Constants.WORLD_HEIGHT / 2, Constants.WORLD_WIDTH / 15, Constants.WORLD_WIDTH / 15);
            } else if (showPause == true) {

                bbatch.draw(play, Constants.WORLD_WIDTH * 0.45f, Constants.WORLD_HEIGHT / 2, Constants.WORLD_WIDTH / 15, Constants.WORLD_WIDTH / 15);
            } else if (showTempo == true) {
                font.draw(bbatch, (int) (gameScreen.getPrefs().getFloat("tempo_multiplier") * 100) + "", Constants.WORLD_WIDTH * 0.50f, Constants.WORLD_HEIGHT / 2);//Constants.NOTES_WIDTH*36/2,Constants.OFFSET/2+20);
            }else if(showReset == true){

                bbatch.draw(reset, Constants.WORLD_WIDTH * 0.45f, Constants.WORLD_HEIGHT / 2, Constants.WORLD_WIDTH / 15, Constants.WORLD_WIDTH / 15);


            }


        }

        bbatch.end();

        if (endTime < TimeUtils.millis())
            resetResponse();


    }


}
