package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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


    public static final String TAG = FallingNotesScreen.class.getName();

    private OrthographicCamera cam;

    Stage stage;
    BitmapFont font;
    Controls controls;


    RoundRectShapeRenderer renderer;
    ShapeRenderer lineRenderer;

    RoundRectShapeRenderer scoreRenderer;

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

    GestureResponse gestureResponse;

    private boolean isPlaying = false;


    boolean isOnce = false;
   static boolean recognitionMode = false;

    //score
    BitmapFont scoreFont;
    Label scoreTextLabel;
    Label gameNameTextLabel;


    public FallingNotesScreen(FallingNotesGame app, String midiName, boolean recognitionMode) {
        this.app = app;
        this.midiName = midiName;
        this.recognitionMode = recognitionMode;
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



        //Initialize the theme
        initializeTheTheme();

        //Initialize the camera
        initializeTheCamera();

        //initialize the Game name and font
        initializeGameName();






        renderer = new RoundRectShapeRenderer();
        renderer.setAutoShapeType(true);
        scoreRenderer = new RoundRectShapeRenderer();
        scoreRenderer.setAutoShapeType(true);
        lineRenderer = new ShapeRenderer();
        blinerenderer = new ShapeRenderer();

        //initialize Background
        initializeBackground();

        notes = new Notes(app, midiName, stage, recognitionMode);


        if (recognitionMode)
            TarsosFftYin.tarsos();
        else
            TarsosFftYin.dispose();


        batch = new SpriteBatch();
        bbatch = new SpriteBatch();
        sprite = new Sprite();
        piano = new Piano();


        controls = new Controls(this, stage, cam, getPrefs());


        controls.initSeekbar(notes, stage);
        //  Gdx.input.setInputProcessor(new GestureDetector(new GestureHandler()));

        //This is initializer of GestureResponse
        gestureResponse = new GestureResponse(app, stage, this);


        if (recognitionMode)
            initScoreVars();


    }

    private void initializeBackground() {
        backgroundTexture = theme.getBackgroundTexture();
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion region = new TextureRegion(backgroundTexture, backgroundTexture.getWidth(), backgroundTexture.getHeight());
        backgroundSprite = new Sprite(region);
    }

    public void playPauseToggle() {
        isPlaying = !isPlaying;
        if(isPlaying)
            gestureResponse.removePauseResponse();
        else
            gestureResponse.showPauseResponse();
        }

    private void initializeGameName() {
        Texture texture = new Texture(Gdx.files.internal("font/courgette.png"), true); // true enables mipmaps
        scoreFont = new BitmapFont(Gdx.files.internal("font/courgette.fnt"), new TextureRegion(texture), false);
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = scoreFont;
//        Pixmap labelColor = new Pixmap(100, 10, Pixmap.Format.RGB888);
//        labelColor.setColor(Color.valueOf("0E81D1"));
//        labelColor.fill();
//        style.background = new Image(new Texture(labelColor)).getDrawable();
        style.fontColor = Color.WHITE;
        gameNameTextLabel = new Label(Constants.GAME_NAME, style);
        gameNameTextLabel.setBounds(Constants.WORLD_WIDTH / 2 - Constants.WORLD_WIDTH / 8, Constants.WORLD_HEIGHT / 160f, Constants.WORLD_WIDTH / 4, Constants.WORLD_HEIGHT * 0.1f);
//        Group group = new Group();
//        group.setPosition(Constants.WORLD_WIDTH / 2 - Constants.WORLD_WIDTH / 8, Constants.WORLD_HEIGHT / 160f);
//        group.addActor(gameNameTextLabel);
//        group.setScale(1.4f * Gdx.graphics.getDensity() / 3,1.4f * Gdx.graphics.getDensity() / 3);
        gameNameTextLabel.setFontScale(1.4f * Gdx.graphics.getDensity() / 3);
        stage.addActor(gameNameTextLabel);
//        Texture texture = new Texture(Gdx.files.internal(FallingNotesScreen.getTheme().getFntPngName()), true); // true enables mipmaps
//        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image
//        font = new BitmapFont(Gdx.files.internal(FallingNotesScreen.getTheme().getFntFileName()), new TextureRegion(texture), false);
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
//
//            Gdx.app.log(TAG,notes.initialTime+"");
//            Gdx.app.log(TAG+"midiEndTIme",notes.midiEndTime+"");

            //Reset Condition
            if(notes.initialTime*1000>notes.animationEndTime) {

                reset();
            }
        }

        //setting up camera
        settingUpCamera();

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Drawing Background //Using background batch
        bbatch.begin();
        bbatch.draw(backgroundSprite, 0, 0, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
//        renderGameName();
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

        scoreRenderer.begin(ShapeRenderer.ShapeType.Filled);
        scoreRenderer.roundedRect(scoreRenderer,
                Constants.WORLD_WIDTH - Constants.WORLD_WIDTH / 4.0f, Constants.WORLD_HEIGHT - Constants.WORLD_HEIGHT / 6.5f,
                Constants.WORLD_WIDTH / 4.5f,
                Constants.WORLD_HEIGHT / 9.5f, 56 * Gdx.graphics.getDensity() / 3,
                new Color(Color.valueOf("6dd5ed")),
                new Color(Color.valueOf("0E81D1")),
                new Color(Color.valueOf("0E81D1")),
                new Color(Color.valueOf("6dd5ed"))
//                FallingNotesScreen.getTheme().getRH_lightBlackKeyColor(),
//                FallingNotesScreen.getTheme().getRH_darkBlackKeyColor(),
//                FallingNotesScreen.getTheme().getRH_darkBlackKeyColor(),
//                FallingNotesScreen.getTheme().getRH_lightBlackKeyColor()
        );
        scoreRenderer.end();

        if (recognitionMode)
            setScore();

        stage.draw();

        //render GestureResponse
        gestureResponse.renderResponse();

    }

    private void setScore() {
        scoreTextLabel.setText("Score: " + notes.getCorrectNoteCount() + "/" + notes.getTotalNotesCount());
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
        font.draw(bbatch, Constants.GAME_NAME, fontX, Constants.OFFSET * 1f);//Constants.NOTES_WIDTH*36/2,Constants.OFFSET/2+20);
    }

    private void handleInput() {

        if(isOnce==false)
        {
            cam.translate(Constants.WORLD_WIDTH/3,0,0);
            isOnce = true;
        }
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
        cam.position.x = MathUtils.clamp(cam.position.x, cam.viewportWidth*0.5f*cam.zoom, Gdx.graphics.getWidth() - cam.viewportWidth*0.5f*cam.zoom);
        cam.position.y = MathUtils.clamp(cam.position.y, 0.8552987f*cam.viewportHeight*0.5f*cam.zoom+39.29953f, 0.8552987f*cam.viewportHeight*0.5f*cam.zoom+39.29953f);

        //This is to limit the zoom level
        //minimum zoom level should be to fit the whhole piano
        //Maximum zoom level should to fit 3 octaves


//        Gdx.app.log(TAG,"min clamp "+cam.viewportHeight*0.5);
//        Gdx.app.log(TAG,"cam position y "+cam.position.y);
//        Gdx.app.log(TAG,"zoom "+cam.zoom);



        cam.zoom = MathUtils.clamp(cam.zoom,0.855f,1.98f);


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
    }

    //This function is called when the song is playd 100 percent
    public void reset()
    {
        notes.setCorrectNoteCount(0);
        Gdx.app.log(TAG,"reset");
        gestureResponse.showResetResponse();
        isPlaying = false;
        notes.reset();
        controls.reset();
        piano.reset();
    }


    @Override
    public void pause() {

//         reset();
        piano.reset();
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
        stage.dispose();
        batch.dispose();
        piano.disposePiano();
    }

    @Override
    public void dispose() {
    }

    public void zoom(float ratio) {
        cam.zoom -= ratio * 0.004;
        cam.zoom = MathUtils.clamp(cam.zoom,0.855f,1.98f);
    }

    public void translate(float Xdelta) {
        cam.position.x += -Xdelta * 0.5;

        //This is to avoid translating the camera out of bounds
        //This is calculated by hand
        cam.position.x = MathUtils.clamp(cam.position.x, cam.viewportWidth*0.5f*cam.zoom, Gdx.graphics.getWidth() - cam.viewportWidth*0.5f*cam.zoom);
        cam.position.y = MathUtils.clamp(cam.position.y, 0.8552987f*cam.viewportHeight*0.5f*cam.zoom+39.29953f, 0.8552987f*cam.viewportHeight*0.5f*cam.zoom+39.29953f);

    }

    private void initScoreVars() {

        Texture texture = new Texture(Gdx.files.internal("font/courgette.png"), true); // true enables mipmaps
        scoreFont = new BitmapFont(Gdx.files.internal("font/courgette.fnt"), new TextureRegion(texture), false);
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = scoreFont;
//        Pixmap labelColor = new Pixmap(100, 10, Pixmap.Format.RGB888);
//        labelColor.setColor(Color.valueOf("0E81D1"));
//        labelColor.fill();
//        style.background = new Image(new Texture(labelColor)).getDrawable();
        style.fontColor = Color.WHITE;
        scoreTextLabel = new Label("score", style);
        scoreTextLabel.setBounds(Constants.WORLD_WIDTH - Constants.WORLD_WIDTH / 4.5f, Constants.WORLD_HEIGHT - Constants.WORLD_HEIGHT / 6.5f, Constants.WORLD_WIDTH / 4, Constants.WORLD_HEIGHT * 0.1f);
        scoreTextLabel.setFontScale(1.1f * Gdx.graphics.getDensity() / 3);
        stage.addActor(scoreTextLabel);
    }

    public static Theme getTheme() {
        return theme;
    }

    public Notes getNotes()
    {
        return notes;
    }

    public static boolean isRecognitionMode() {
        return recognitionMode;
    }
}
