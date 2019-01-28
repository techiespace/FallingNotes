package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.techiespace.projects.fallingnotes.Themes.HpTheme;
import com.techiespace.projects.fallingnotes.Themes.Theme;
import com.techiespace.projects.fallingnotes.pianoHelpers.RoundRectShapeRenderer;


public class FallingNotesScreen implements Screen {

    private final FallingNotesGame app;
    public String midiName;

    public FallingNotesScreen(FallingNotesGame app) {
        this.app = app;
    }

    Label lblMet;
    public static final String TAG = FallingNotesScreen.class.getName();

    private OrthographicCamera cam;

    Stage stage;
    BitmapFont font;
    Controls controls;


    RoundRectShapeRenderer renderer;
    ShapeRenderer lineRenderer;


    Notes notes;

    SpriteBatch batch;
    Sprite sprite;
    Piano piano;
    Texture backgroundTexture;
    Sprite backgroundSprite;
    public static Theme theme;
    SpriteBatch bbatch;
    private Preferences preferences;
    ShapeRenderer blinerenderer;

    Viewport viewport;

    private boolean isPlaying = false;

    float camViewportHalfX;
    float camViewportHalfY;

    public FallingNotesScreen(FallingNotesGame app, String midiName) {
        this.app = app;
        this.midiName = midiName;
    }

    protected Preferences getPrefs() {
        if (preferences == null)
            preferences = Gdx.app.getPreferences("play_prefrences");
        return preferences;
    }

    //Initialization Function

    @Override
    public void show() {


        //                                                              //
        //                                                              //
        //                                                              //
        // Changing Order of Initialization May Create Unwanted Trouble //
        //                                                              //
        //                                                              //
        //                                                              //

        stage = new Stage();
        controls = new Controls(this, stage, cam, getPrefs());

        //Initialize the theme
        initializeTheTheme();

        //Initialize the camera
        initializeTheCamera();

        //initialize the Game name and font
        initializeGameName();


        renderer = new RoundRectShapeRenderer();
        renderer.setAutoShapeType(true);
        lineRenderer = new ShapeRenderer();
        blinerenderer = new ShapeRenderer();

        //initialize Background
        initializeBackground();

        notes = new Notes(app, midiName, stage);
        batch = new SpriteBatch();
        bbatch = new SpriteBatch();
        sprite = new Sprite();
        piano = new Piano();

        camViewportHalfX = cam.viewportWidth * 0.5f;
        camViewportHalfY = cam.viewportHeight * 0.5f;

        controls.initSeekbar(notes, stage);
        //  Gdx.input.setInputProcessor(new GestureDetector(new GestureHandler()));
    }

    private void initializeBackground() {
        backgroundTexture = theme.getBackgroundTexture();
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion region = new TextureRegion(backgroundTexture, backgroundTexture.getWidth(), backgroundTexture.getHeight());
        backgroundSprite = new Sprite(region);
    }

    public void playPauseToggle() {
        isPlaying = !isPlaying;
    }

    private void initializeGameName() {
        Texture texture = new Texture(Gdx.files.internal(FallingNotesScreen.getTheme().getFntPngName()), true); // true enables mipmaps
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image
        font = new BitmapFont(Gdx.files.internal(FallingNotesScreen.getTheme().getFntFileName()), new TextureRegion(texture), false);
    }

    private void initializeTheTheme() {
        theme = new HpTheme(app);
    }

    private void initializeTheCamera() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera();
        viewport = new ExtendViewport(w / 2, h / 2, cam);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
    }

    @Override
    public void render(float delta) {
        stage.draw();

        if (isPlaying) {
            notes.update(delta);
        }

        //setting up camera
        settingUpCamera();

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Drawing Background //Using background batch
        bbatch.begin();
        bbatch.draw(backgroundSprite, 0, 0, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        renderGameName();
        bbatch.end();

        //Draw vertical guide line
        lineRenderer.begin(ShapeRenderer.ShapeType.Line);
        renderVerticalLines();
        lineRenderer.end();

        //Render Reference line
        blinerenderer.begin(ShapeRenderer.ShapeType.Line);
        blinerenderer.line(0, Constants.OFFSET * 2, Constants.WORLD_WIDTH, Constants.OFFSET * 2);
        blinerenderer.end();

        //Draw the Notes
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        notes.render(renderer);
        renderer.end();

        //Draw the piano
        batch.begin();
        piano.render(sprite, batch);
        batch.end();


        //render Seekbar
        controls.updateSeekbar(notes);

        stage.draw();

    }

    public void settingUpCamera() {
        //cam.zoom+=0.0005;
        handleInput();
        cam.update();
        viewport.apply();
        batch.setProjectionMatrix(cam.combined);
        renderer.setProjectionMatrix(cam.combined);
        lineRenderer.setProjectionMatrix(cam.combined);
    }

    private void renderVerticalLines() {
        lineRenderer.setColor(theme.getVerticalLineColor());   //color alpha not working
        for (int i = Constants.STARTING_OCTAVE; i < Constants.ENDING_OCTAVE; i++) {
            lineRenderer.line(Note.mapCoordinates("C" + i), Constants.OFFSET, Note.mapCoordinates("C" + i), Constants.WORLD_HEIGHT);
            lineRenderer.line(Note.mapCoordinates("F" + i), Constants.OFFSET, Note.mapCoordinates("F" + i), Constants.WORLD_HEIGHT);
        }
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void renderGameName() {
        font.setColor(theme.getGameNameColor());
        font.getData().setScale(FallingNotesScreen.getTheme().getGameNameScale());
        final GlyphLayout layout = new GlyphLayout(font, Constants.GAME_NAME);
        // or for non final texts: layout.setText(font, text);
        final float fontX = 0 + (Constants.WORLD_WIDTH - layout.width) / 2;
        font.draw(bbatch, Constants.GAME_NAME, fontX, Constants.OFFSET * 1.5f);//Constants.NOTES_WIDTH*36/2,Constants.OFFSET/2+20);
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.zoom += 0.005;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom -= 0.005;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-2, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(2, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -1, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 1, 0);
        }

        //This is to avoid translating the camera out of bounds
        cam.position.x = MathUtils.clamp(cam.position.x, camViewportHalfX, Gdx.graphics.getWidth() - camViewportHalfX);
        cam.position.y = MathUtils.clamp(cam.position.y, camViewportHalfY, Gdx.graphics.getHeight() - camViewportHalfY);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        renderer.dispose();
        lineRenderer.dispose();
        blinerenderer.dispose();
        bbatch.dispose();
    }

    @Override
    public void dispose() {
    }

    public void zoom(float ratio) {
        cam.zoom -= ratio * 0.001;
    }

    public void translate(float Xdelta) {
        cam.position.x += -Xdelta * 0.5;
        cam.position.x = MathUtils.clamp(cam.position.x, camViewportHalfX, Gdx.graphics.getWidth() - camViewportHalfX);
        cam.position.y = MathUtils.clamp(cam.position.y, camViewportHalfY, Gdx.graphics.getHeight() - camViewportHalfY);
    }

    public static Theme getTheme() {
        return theme;
    }
}
