package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
        //vGdx.app.log("FallingNotesScreen Constructor",midiName);

    }

    public FallingNotesScreen(FallingNotesGame app,String midiName) {
        this.app = app;
        this.midiName  = midiName;
        Gdx.app.log("FallingNotesScreen Constructor",midiName);

    }
    public static final String TAG = FallingNotesScreen.class.getName();

    private OrthographicCamera cam;

    Stage stage;
    Button playPauseButton;
    Button leftHandButton;
    Button rightHandButton;
    Button.ButtonStyle playPausebuttonStyle;
    Button.ButtonStyle leftHandbuttonStyle;
    Button.ButtonStyle rightHandbuttonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    BitmapFont bitmapFont;


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

        //set preferences
        setPreferences();


        //initialize the buttons
        initializeTheButtons();


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

        notes = new Notes(app,midiName);
        batch = new SpriteBatch();
        bbatch = new SpriteBatch();
        sprite = new Sprite();
        piano = new Piano();

        //initialize Background
        initializeBackground();
        //Gdx.input.setInputProcessor(this);

        //initialize button listeners
        initializeButtonListeners();

        //https://github.com/EsotericSoftware/tablelayout
        initializeControlTable();
    }

    private void initializeBackground() {
        backgroundTexture = theme.getBackgroundTexture();
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion region = new TextureRegion(backgroundTexture,backgroundTexture.getWidth(),backgroundTexture.getHeight());
        backgroundSprite = new Sprite(region);
    }

    private void initializeControlTable() {
        Table controlsTable = new Table();
        //bg
        Pixmap bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.RED);
        bgPixmap.fill();
        TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        controlsTable.setBackground(textureRegionDrawableBg);
        bgPixmap.dispose(); //https://stackoverflow.com/questions/39081993/libgdx-scene2d-set-background-color-of-table

        //play/pause
        controlsTable.add(playPauseButton);
        controlsTable.setSize(Constants.NOTES_WIDTH * 2, Constants.WORLD_HEIGHT);
        controlsTable.setPosition(Constants.WORLD_WIDTH - Constants.NOTES_WIDTH * 2, 0);

        //left
        controlsTable.row();
        controlsTable.add(leftHandButton);

        //right
        controlsTable.row();
        controlsTable.add(rightHandButton);

        controlsTable.debug();
        stage.addActor(controlsTable);
    }

    private void initializeButtonListeners() {
        playPauseButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                isPlaying = !isPlaying;
            }
        });
        leftHandButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Preferences playPrefs = getPrefs();
                boolean toggle = !playPrefs.getBoolean("left_hand");
                playPrefs.putBoolean("left_hand", toggle).flush();   //https://www.badlogicgames.com/forum/viewtopic.php?f=11&t=18544
            }
        });
        rightHandButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Preferences playPrefs = getPrefs();
                boolean toggle = !playPrefs.getBoolean("right_hand");
                playPrefs.putBoolean("right_hand", toggle).flush();
            }
        });
    }

    private void initializeGameName() {
        Texture texture = new Texture(Gdx.files.internal(FallingNotesScreen.getTheme().getFntPngName()), true); // true enables mipmaps
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image
        font = new BitmapFont(Gdx.files.internal(FallingNotesScreen.getTheme().getFntFileName()), new TextureRegion(texture), false);
    }

    private void setPreferences() {
        Gdx.app.getPreferences("play_prefrences").putBoolean("right_hand", true).flush();
        Gdx.app.getPreferences("play_prefrences").putBoolean("left_hand", true).flush();
    }

    private void initializeTheButtons() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/controls.pack"));
        skin.addRegions(buttonAtlas);
        playPausebuttonStyle = new Button.ButtonStyle();
        playPausebuttonStyle.up = skin.getDrawable("play");
        playPausebuttonStyle.checked = skin.getDrawable("pause");
        playPauseButton = new Button(playPausebuttonStyle);

        leftHandbuttonStyle = new Button.ButtonStyle();
        leftHandbuttonStyle.up = skin.getDrawable("hand_left");
        leftHandbuttonStyle.checked = skin.getDrawable("hand_right");
        leftHandButton = new Button(leftHandbuttonStyle);

        rightHandbuttonStyle = new Button.ButtonStyle();
        rightHandbuttonStyle.up = skin.getDrawable("hand_right");
        rightHandbuttonStyle.checked = skin.getDrawable("hand_left");
        rightHandButton = new Button(rightHandbuttonStyle);
    }

    private void initializeTheTheme() {
        theme = new HpTheme(app);
    }

    private void initializeTheCamera() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        cam = new OrthographicCamera();
        viewport = new ExtendViewport(w/2,h/2,cam);
        cam.position.set(cam.viewportWidth/2f,cam.viewportHeight/2f,0);
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
        bbatch.draw(backgroundSprite,0,0,Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        renderGameName();
        bbatch.end();




        //Draw vertical guide line
        lineRenderer.begin(ShapeRenderer.ShapeType.Line);
        renderVerticalLines();
        lineRenderer.end();


        //Render Reference line
        blinerenderer.begin(ShapeRenderer.ShapeType.Line);
        blinerenderer.line(0, Constants.OFFSET*2, Constants.WORLD_WIDTH, Constants.OFFSET*2);
        blinerenderer.end();



        //Draw the Notes
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        notes.render(renderer);
        renderer.end();



        //Draw the piano
        batch.begin();
        piano.render(sprite,batch);
        batch.end();

        stage.draw();

    }

    private void settingUpCamera() {
        handleInput();
        cam.update();
        viewport.apply();
        batch.setProjectionMatrix(cam.combined);
        renderer.setProjectionMatrix(cam.combined);
        lineRenderer.setProjectionMatrix(cam.combined);
    }

    private void renderVerticalLines() {
        lineRenderer.setColor(theme.getVerticalLineColor());   //color alpha not working
        for(int i = Constants.STARTING_OCTAVE; i<Constants.ENDING_OCTAVE; i++) {
            lineRenderer.line(Note.mapCoordinates("C"+i), Constants.OFFSET, Note.mapCoordinates("C"+i), Constants.WORLD_HEIGHT);
            lineRenderer.line(Note.mapCoordinates("F"+i), Constants.OFFSET, Note.mapCoordinates("F"+i), Constants.WORLD_HEIGHT);
        }
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void renderGameName() {
        font.setColor(theme.getGameNameColor());
        font.getData().setScale(FallingNotesScreen.getTheme().getGameNameScale());
        final GlyphLayout layout = new GlyphLayout(font,Constants.GAME_NAME);
        // or for non final texts: layout.setText(font, text);
        final float fontX = 0 + (Constants.WORLD_WIDTH - layout.width) / 2;
        font.draw(bbatch, Constants.GAME_NAME, fontX, Constants.OFFSET*1.5f );//Constants.NOTES_WIDTH*36/2,Constants.OFFSET/2+20);
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

    }

    @Override
    public void resize(int width, int height) {
        //notesViewport.update(width, height, true);
        //notes.init(); //required?
        viewport.update(width, height);
        cam.position.set(cam.viewportWidth/2f,cam.viewportHeight/2f,0);
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

    public static Theme getTheme()
    {
        return theme;
    }
}
