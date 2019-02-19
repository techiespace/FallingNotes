package com.techiespace.projects.fallingnotes.pianoHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
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
    boolean isLoading;
    RoundRectShapeRenderer roundRect;
    ProgressBar progressBar;
    BitmapFont font;
    SpriteBatch batch;
    TextArea textArea;


    public LoadingScreen(final FallingNotesGame app, String midiName) {
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
        this.progress = 0f;
        this.midiName = midiName;
        Gdx.app.log("Loading Screen Constructor", midiName);
        queueAssets();
        stage = new Stage();

        table = new Table();
        roundRect = new RoundRectShapeRenderer();
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        isLoading = true;

        progressBar = new ProgressBar(0, 100, 0.02f, false, skin);


        addGameButton();

        Texture texture = new Texture(Gdx.files.internal("font/courgette.png"), true); // true enables mipmaps
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image
        font = new BitmapFont(Gdx.files.internal("font/courgette.fnt"), new TextureRegion(texture), false);

        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();


        initializeTextField();


        stage.addActor(textArea);



        setInputProcessor();
    }

    private void initializeTextField() {
        textArea = new TextArea("Text Area\nEssentially,\n\n\n\n\n\n\n a text field\nwith\nmultiple\nlines.\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nIt can even handle very loooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong lines.",
                skin);
        textArea.setColor(1,1,1,0.5f);
        textArea.setPosition(Constants.WORLD_WIDTH / 3+10, Constants.WORLD_HEIGHT *0.15f+10);
        textArea.setSize(Constants.WORLD_WIDTH / 3-20, Constants.WORLD_HEIGHT*0.7f-20);
        textArea.setDisabled(true);
        TextArea.TextFieldStyle textFieldStyle = skin.get(TextArea.TextFieldStyle.class);
        textFieldStyle.font.getData().setScale(1.5f);


        textArea.setStyle(textFieldStyle);
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
                    app.setScreen(new FallingNotesScreen(app, midiName));
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

    public LoadingScreen(final FallingNotesGame app) {
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
        this.progress = 0f;

        queueAssets();
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
            app.assets.load("audio/" + getMidiNoteName(i + 21) + ".ogg", Sound.class);
        }
        app.assets.load("piano/black_down_yellow.png", Texture.class);
        app.assets.load("piano/white_down_blue_g.png", Texture.class);
        app.assets.load("piano/white_down_yellow.png", Texture.class);
        app.assets.load("piano/black_down_red_light.png", Texture.class);
        app.assets.load("piano/white_down_red.png", Texture.class);
        app.assets.load("piano/play.png", Texture.class);
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

        update(delta);




        //Draw the instruction screen

        roundRect.begin(ShapeRenderer.ShapeType.Filled);
        roundRect.roundedRect(roundRect, Constants.WORLD_WIDTH / 3, Constants.WORLD_HEIGHT *0.15f, Constants.WORLD_WIDTH / 3, Constants.WORLD_HEIGHT*0.7f, 20, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
        roundRect.end();


        stage.draw();

        batch.begin();

        font.setColor(Color.WHITE);
        font.getData().setScale(1f);

        // or for non final texts: layout.setText(font, text);

        if (!isLoading)
            font.draw(batch, "Tap To Play", Constants.WORLD_WIDTH * 0.45f, Constants.WORLD_HEIGHT / 10);//Constants.NOTES_WIDTH*36/2,Constants.OFFSET/2+20);

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
