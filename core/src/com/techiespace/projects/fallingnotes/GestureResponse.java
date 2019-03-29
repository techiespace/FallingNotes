package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.TimeUtils;

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
    Stage stage;
    Label tempoTextLabel;

    public GestureResponse(FallingNotesGame app, Stage stage, FallingNotesScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.app = app;
        this.stage = stage;
        play = app.assets.get("piano/play.png", Texture.class);
        pause = app.assets.get("piano/pause.png", Texture.class);
        reset = app.assets.get("piano/reset.png", Texture.class);


        this.bbatch = gameScreen.bbatch;

        Texture texture = new Texture(Gdx.files.internal("font/courgette.png"), true); // true enables mipmaps
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image
        font = new BitmapFont(Gdx.files.internal("font/courgette.fnt"), new TextureRegion(texture), false);


        font.setColor(Color.WHITE);
        font.getData().setScale(1.4f);
//        font = new BitmapFont();
//        font.getData().setScale(2);
        showPlay = false;
        showPause = true;
        showTempo = false;
        showReset = false;

        initTempoText();
    }

    private void initTempoText() {
        Texture texture = new Texture(Gdx.files.internal("font/courgette.png"), true); // true enables mipmaps
        BitmapFont tempoFont;
        tempoFont = new BitmapFont(Gdx.files.internal("font/courgette.fnt"), new TextureRegion(texture), false);
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = tempoFont;
//        Pixmap labelColor = new Pixmap(100, 10, Pixmap.Format.RGB888);
//        labelColor.setColor(Color.valueOf("0E81D1"));
//        labelColor.fill();
//        style.background = new Image(new Texture(labelColor)).getDrawable();
        style.fontColor = Color.WHITE;
        tempoTextLabel = new Label("Speed\n" + (int) (gameScreen.getPrefs().getFloat("tempo_multiplier") * 100) + "%", style);
        tempoTextLabel.setBounds(Constants.WORLD_WIDTH * 0.45f, Constants.WORLD_HEIGHT / 2, Constants.WORLD_WIDTH / 4, Constants.WORLD_HEIGHT * 0.1f);
        tempoTextLabel.setFontScale(1.0f * Gdx.graphics.getDensity() / 2);
        stage.addActor(tempoTextLabel);
        tempoTextLabel.setVisible(false);
    }


    public void showPlayResponse() {
        startTime = TimeUtils.millis();
        endTime = startTime + 500;
        showPlay = true;

    }

    public void showPauseResponse() {
        showPause = true;
    }

    public void removePauseResponse(){
        showPause = false;
    }


    public void resetResponse() {
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
        showPauseResponse();
    }


    public void renderResponse() {

        bbatch.begin();

        if (!showTempo)
            tempoTextLabel.setVisible(false);

        if (endTime > TimeUtils.millis()) {
            if (showPlay == true) {

                bbatch.draw(pause, Constants.WORLD_WIDTH * 0.45f, Constants.WORLD_HEIGHT / 2, Constants.WORLD_WIDTH / 15, Constants.WORLD_WIDTH / 15);
            }  else if (showTempo == true) {
                tempoTextLabel.setText("Speed\n" + (int) (gameScreen.getPrefs().getFloat("tempo_multiplier") * 100) + "%");
                tempoTextLabel.setVisible(true);
//                font.draw(bbatch, "Speed", Constants.WORLD_WIDTH * 0.40f, Constants.WORLD_HEIGHT *0.67f);//Constants.NOTES_WIDTH*36/2,Constants.OFFSET/2+20);
//                font.draw(bbatch, (int) (gameScreen.getPrefs().getFloat("tempo_multiplier") * 100) + "%", Constants.WORLD_WIDTH * 0.405f, Constants.WORLD_HEIGHT *0.55f);//Constants.NOTES_WIDTH*36/2,Constants.OFFSET/2+20);
            }else if(showReset == true){

                bbatch.draw(reset, Constants.WORLD_WIDTH * 0.45f, Constants.WORLD_HEIGHT / 2, Constants.WORLD_WIDTH / 15, Constants.WORLD_WIDTH / 15);
            }
        }


        if (endTime < TimeUtils.millis())
            resetResponse();


        if (showPause == true && showReset == false && showTempo == false ){

            bbatch.draw(play, Constants.WORLD_WIDTH * 0.45f, Constants.WORLD_HEIGHT / 2, Constants.WORLD_WIDTH / 15, Constants.WORLD_WIDTH / 15);
        }

        bbatch.end();




    }


}
