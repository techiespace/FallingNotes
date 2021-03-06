package com.techiespace.projects.fallingnotes.pianoHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.techiespace.projects.fallingnotes.Constants;
import com.techiespace.projects.fallingnotes.FallingNotesGame;
import com.techiespace.projects.fallingnotes.FallingNotesScreen;

import static com.techiespace.projects.fallingnotes.pianoHelpers.HelperFunctions.getMidiNoteName;

public class LoadingScreenPlayMidi implements Screen {

    private final FallingNotesGame app;
    public String midiName;
    Table table;
    Stage stage;
    Skin skin;
    Skin pbSkin;
    boolean isLoading;
    RoundRectShapeRenderer roundRect;
    ProgressBar progressBar;
    BitmapFont font;
    SpriteBatch batch;
    Sprite backgroundSprite;
    Texture backgroundTexture;
    String instructions;
    private ShapeRenderer shapeRenderer;
    private float progress;


    public LoadingScreenPlayMidi(final FallingNotesGame app, String midiName) {
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
        this.progress = 0f;
        this.midiName = midiName;
        Gdx.app.log("Loading Screen Constructor", midiName);
        queueAssets();
        stage = new Stage();
        instructions = "";
        backgroundTexture = new Texture("background/scroll.png");


        backgroundSprite = new Sprite(backgroundTexture);

        table = new Table();
        roundRect = new RoundRectShapeRenderer();
        skin = new Skin(Gdx.files.internal("skin/tubular-ui.json"));
        pbSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        isLoading = true;

        progressBar = new ProgressBar(0, 100, 0.02f, false, pbSkin);


        addGameButton();

        Texture texture = new Texture(Gdx.files.internal("font/courgette.png"), true); // true enables mipmaps
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image
        font = new BitmapFont(Gdx.files.internal("font/courgette.fnt"), new TextureRegion(texture), false);

        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();


        initializeTextField();


        setInputProcessor();
    }
    //Constructor when instructions are passed

    public LoadingScreenPlayMidi(final FallingNotesGame app, String midiName, String instructions) {
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
        this.progress = 0f;
        this.midiName = midiName;
        this.instructions = instructions;
        // Gdx.app.log("Loading Screen Constructor", midiName);
        queueAssets();
        stage = new Stage();
        backgroundTexture = new Texture("background/scroll.png");


        backgroundSprite = new Sprite(backgroundTexture);

        table = new Table();
        roundRect = new RoundRectShapeRenderer();
        skin = new Skin(Gdx.files.internal("skin/tubular-ui.json"));
        pbSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        isLoading = true;

        progressBar = new ProgressBar(0, 100, 0.02f, false, pbSkin);


        addGameButton();

        Texture texture = new Texture(Gdx.files.internal("font/courgette.png"), true); // true enables mipmaps
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image
        font = new BitmapFont(Gdx.files.internal("font/courgette.fnt"), new TextureRegion(texture), false);

        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();


        initializeTextField();


        setInputProcessor();
    }

    public LoadingScreenPlayMidi(final FallingNotesGame app) {
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
        this.progress = 0f;

        queueAssets();
    }

    private void initializeTextField() {

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        style.fontColor = Color.BLACK;


        //Instructions Heading Lable
        Label instructionsHeader = new Label("Instructions", style);
        instructionsHeader.setBounds(Constants.WORLD_WIDTH /4f+Constants.WORLD_WIDTH/8, Constants.WORLD_HEIGHT * 0.75f, Constants.WORLD_WIDTH / 4, Constants.WORLD_HEIGHT * 0.1f);
        instructionsHeader.setWrap(true);
        instructionsHeader.setFontScale(1.1f * Gdx.graphics.getDensity() / 2);

        stage.addActor(instructionsHeader);


        //Instructions
        Label instructionsLabel = new Label(instructions, style);
        instructionsLabel.setBounds(Constants.WORLD_WIDTH / 4, Constants.WORLD_HEIGHT * 0.15f, Constants.WORLD_WIDTH / 2, Constants.WORLD_HEIGHT * 0.6f);
        instructionsLabel.setWrap(true);
        instructionsLabel.setFontScale(0.6f * Gdx.graphics.getDensity() / 2);


        stage.addActor(instructionsLabel);


    }

    private void setInputProcessor() {
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (!isLoading) {
                    app.setScreen(new FallingNotesScreen(app, midiName, false));
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });
    }

    protected void addGameButton() {


        table.add(progressBar).width(Constants.WORLD_WIDTH / 6).height(30).bottom();
        table.row();

        table.setSize(Constants.WORLD_WIDTH, 10);
        table.setPosition(0, Constants.WORLD_HEIGHT / 10);
        stage.addActor(table);


    }


    private void queueAssets() {
        for (int i = 0; i < 88; i++) {
            if (!app.assets.isLoaded("audio/" + getMidiNoteName(i + 21)))
                app.assets.load("audio/" + getMidiNoteName(i + 21) + ".ogg", Sound.class);
        }
        if (!app.assets.isLoaded("piano/black_down_yellow.png"))
            app.assets.load("piano/black_down_yellow.png", Texture.class);
        app.assets.load("piano/white_up.png", Texture.class);
        app.assets.load("piano/white_down_blue_g.png", Texture.class);
        app.assets.load("piano/white_down_yellow.png", Texture.class);
        app.assets.load("piano/black_down_red_light.png", Texture.class);
        app.assets.load("piano/white_down_red.png", Texture.class);
        app.assets.load("piano/black_down_green.png", Texture.class);
        app.assets.load("piano/black_down_red_light.png", Texture.class);
        app.assets.load("piano/white_down_green.png", Texture.class);
        app.assets.load("piano/white_down_red.png", Texture.class);

        app.assets.load("piano/play.png", Texture.class);
        app.assets.load("piano/play_button.png", Texture.class);
        app.assets.load("piano/pause.png", Texture.class);
        app.assets.load("piano/reset.png", Texture.class);
    }

    @Override
    public void show() {

    }

    public void update(float delta) {
        progress = app.assets.getProgress();
        if (app.assets.update()) {
            isLoading = false;
            progressBar.setVisible(false);

        }
        progressBar.setColor(Color.WHITE);
        progressBar.setValue(progress * 100);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(14f / 255, 129f / 255, 209f / 255, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        batch.begin();
//        batch.draw(backgroundSprite,Constants.WORLD_WIDTH / 3, Constants.WORLD_HEIGHT * 0.15f, Constants.WORLD_WIDTH / 3, Constants.WORLD_HEIGHT * 0.7f);
//        batch.end();

        update(delta);


        //Draw the instruction screen

        roundRect.begin(ShapeRenderer.ShapeType.Filled);
        roundRect.roundedRect(roundRect, Constants.WORLD_WIDTH / 4 - 10, Constants.WORLD_HEIGHT * 0.15f, Constants.WORLD_WIDTH / 2 + 20, Constants.WORLD_HEIGHT * 0.7f, 20, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
        roundRect.end();


        stage.draw();

        batch.begin();

        font.setColor(Color.WHITE);
        font.getData().setScale(1f);

        // or for non final texts: layout.setText(font, text);

        if (!isLoading) {
            Label.LabelStyle style2 = new Label.LabelStyle();
            style2.font = font;
            style2.fontColor = Color.WHITE;
//            font.draw(batch, "Tap To Play", Constants.WORLD_WIDTH * 0.4f, Constants.WORLD_HEIGHT / 10);//Constants.NOTES_WIDTH*36/2,Constants.OFFSET/2+20);
            Label playLable = new Label("Tap to Play", style2);
            playLable.setBounds(Constants.WORLD_WIDTH * 0.4f, Constants.WORLD_HEIGHT / 22, Constants.WORLD_WIDTH / 4, Constants.WORLD_HEIGHT * 0.1f);
            playLable.setWrap(true);
            playLable.setColor(Color.WHITE);
            playLable.setFontScale(1.2f * Gdx.graphics.getDensity() / 2);
            stage.addActor(playLable);
        }

        batch.end();
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
