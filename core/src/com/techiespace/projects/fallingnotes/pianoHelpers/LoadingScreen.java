package com.techiespace.projects.fallingnotes.pianoHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.techiespace.projects.fallingnotes.Constants;
import com.techiespace.projects.fallingnotes.FallingNotesGame;
import com.techiespace.projects.fallingnotes.FallingNotesScreen;

import static com.techiespace.projects.fallingnotes.pianoHelpers.HelperFunctions.getMidiNoteName;

public class LoadingScreen implements Screen {

    private final FallingNotesGame app;



    private ShapeRenderer shapeRenderer;
    private float progress;
    public String midiName;
    Table table;
    Stage stage;
    Skin skin;
    TextureAtlas buttonAtlas;
    TextButton newGameButton;
    boolean isLoading;
    RoundRectShapeRenderer roundRect;


    public LoadingScreen(final FallingNotesGame app,String midiName) {
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
        this.progress = 0f;
        this.midiName = midiName;
        Gdx.app.log("Loading Screen Constructor",midiName);
        queueAssets();
        stage = new Stage();

        table = new Table();
        roundRect = new RoundRectShapeRenderer();
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        isLoading = true;


        addGameButton();

        Gdx.input.setInputProcessor(stage);
    }
    public LoadingScreen(final FallingNotesGame app) {
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
        this.progress = 0f;

        queueAssets();
    }

    protected  void addGameButton()
    {

        newGameButton = new TextButton("Start Game",skin);

        newGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(!isLoading)
                app.setScreen(new FallingNotesScreen(app,midiName));

            }
        });


        table.add(newGameButton).width(100).height(30).bottom();
        table.row();

        table.setSize(Constants.WORLD_WIDTH,10);
        table.setPosition(0, Constants.WORLD_HEIGHT/10);
        stage.addActor(table);



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
        if (app.assets.update()) {
            isLoading = false;
            //  app.setScreen(new FallingNotesScreen(app,midiName));
        }
       // Gdx.app.log("Loading Screen",isLoading+"");

        if(isLoading)
            newGameButton.setTouchable(Touchable.disabled);
        else
        {
            newGameButton.setTouchable(Touchable.enabled);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(14f/255, 129f/255, 209f/255, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();


        //Draw the instruction screen

       roundRect.begin(ShapeRenderer.ShapeType.Filled);
        roundRect.roundedRect(roundRect,Constants.WORLD_WIDTH /3, Constants.WORLD_HEIGHT / 3, Constants.WORLD_WIDTH/3,Constants.WORLD_HEIGHT/3,20,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE);
        roundRect.end();
//        shapeRenderer.setColor(Color.WHITE);
//        shapeRenderer.rect(Constants.WORLD_WIDTH /3, Constants.WORLD_HEIGHT / 3, Constants.WORLD_WIDTH/3, Constants.WORLD_HEIGHT/3);
//        shapeRenderer.end();

//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.BLACK);
//        shapeRenderer.rect(Constants.WORLD_WIDTH / 2 - 100, Constants.WORLD_HEIGHT / 2 - 20, 100, 20);
//        shapeRenderer.setColor(Color.RED);
//        shapeRenderer.rect(Constants.WORLD_WIDTH / 2 - 100, Constants.WORLD_HEIGHT / 2 - 20, progress * (100), 20);
//        shapeRenderer.end();
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
          stage.dispose();
          shapeRenderer.dispose();
    }

    @Override
    public void dispose() {

    }
}
