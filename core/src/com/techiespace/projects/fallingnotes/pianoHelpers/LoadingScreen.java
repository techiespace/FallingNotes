package com.techiespace.projects.fallingnotes.pianoHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.techiespace.projects.fallingnotes.Constants;
import com.techiespace.projects.fallingnotes.FallingNotesGame;
import com.techiespace.projects.fallingnotes.FallingNotesScreen;

import static com.techiespace.projects.fallingnotes.pianoHelpers.HelperFunctions.getMidiNoteName;

public class LoadingScreen implements Screen {

    private final FallingNotesGame app;

    private ShapeRenderer shapeRenderer;
    private float progress;
    public String midiName;

    public LoadingScreen(final FallingNotesGame app,String midiName) {
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
        this.progress = 0f;
        this.midiName = midiName;
        Gdx.app.log("Loading Screen Constructor",midiName);
        queueAssets();
    }
    public LoadingScreen(final FallingNotesGame app) {
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
        this.progress = 0f;

        queueAssets();
    }

    private void queueAssets() {
        for (int i = 0; i < 88; i++) {
            app.assets.load("audio/" + getMidiNoteName(i + 21) + ".ogg", Sound.class);
        }
        app.assets.load("piano/black_down_yellow.png", Texture.class);
        app.assets.load("piano/white_down_blue_g.png", Texture.class);
        app.assets.load("piano/white_down_yellow.png", Texture.class);
        app.assets.load("piano/black_down_red_light.png", Texture.class);
        app.assets.load("piano/white_down_red.png", Texture.class);
        app.assets.load("piano/play.png",Texture.class);
        app.assets.load("piano/pause.png",Texture.class);
        app.assets.load("piano/reset.png",Texture.class);
    }

    @Override
    public void show() {

    }

    public void update(float delta) {
        progress = app.assets.getProgress();
        if (app.assets.update())
           app.setScreen(new FallingNotesScreen(app,midiName));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(Constants.WORLD_WIDTH / 2 - 100, Constants.WORLD_HEIGHT / 2 - 20, 100, 20);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(Constants.WORLD_WIDTH / 2 - 100, Constants.WORLD_HEIGHT / 2 - 20, progress * (100), 20);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
