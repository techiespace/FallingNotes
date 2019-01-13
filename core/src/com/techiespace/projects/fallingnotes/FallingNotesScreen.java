package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.techiespace.projects.fallingnotes.Themes.RedTheme;
import com.techiespace.projects.fallingnotes.Themes.Theme;
import com.techiespace.projects.fallingnotes.pianoHelpers.RoundRectShapeRenderer;

import javax.print.attribute.standard.OrientationRequested;
import javax.swing.Renderer;

public class FallingNotesScreen implements Screen, InputProcessor {

    public static final String TAG = FallingNotesScreen.class.getName();
    // TODO: Add an ExtendViewport
//    ExtendViewport notesViewport;
    private OrthographicCamera cam;
    private float rotationSpeed;

    // TODO: Add a ShapeRenderer
    RoundRectShapeRenderer renderer;
    ShapeRenderer lineRenderer;

    // TODO: Add an Icicle
    Notes notes;

    SpriteBatch batch;
    Sprite sprite;
    Piano piano;
    Texture backgroundTexture;
    Sprite backgroundSprite;
    public static Theme theme;
    SpriteBatch bbatch;
    BitmapFont font;
    ShapeRenderer blinerenderer;

    Viewport viewport;

    private boolean isPlaying = false;


    @Override
    public void show() {

        theme = new RedTheme();

        rotationSpeed = 0.5f;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera();

        viewport = new ExtendViewport(w/2,h/2,cam);


        cam.position.set(cam.viewportWidth/2f,cam.viewportHeight/2f,0);
        cam.update();



        Texture texture = new Texture(Gdx.files.internal(FallingNotesScreen.getTheme().getFntPngName()), true); // true enables mipmaps
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image
        font = new BitmapFont(Gdx.files.internal(FallingNotesScreen.getTheme().getFntFileName()), new TextureRegion(texture), false);










        renderer = new RoundRectShapeRenderer();
        renderer.setAutoShapeType(true);
        lineRenderer = new ShapeRenderer();
        blinerenderer = new ShapeRenderer();
        //notes = new Notes(notesViewport);
        notes = new Notes();
        batch = new SpriteBatch();
        bbatch = new SpriteBatch();
        sprite = new Sprite();
        piano = new Piano();
        backgroundTexture = theme.getBackgroundTexture();
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegion region = new TextureRegion(backgroundTexture,backgroundTexture.getWidth(),backgroundTexture.getHeight());
        backgroundSprite = new Sprite(region);

      //  backgroundSprite.setSize(1f,1f*backgroundSprite.getHeight()/backgroundSprite.getWidth());
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float delta) {
        /*if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            // your actions

        }*/
        if (isPlaying) {
            notes.update(delta);
            //notesViewport.apply(true);

        }


        handleInput();
        cam.update();
        viewport.apply();
        batch.setProjectionMatrix(cam.combined);
        renderer.setProjectionMatrix(cam.combined);
        lineRenderer.setProjectionMatrix(cam.combined);




        // TODO: Clear the screen to the background color
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // TODO: Set the ShapeRenderer's projection matrix
        //renderer.setProjectionMatrix(notesViewport.getCamera().combined);


        bbatch.begin();
        // backgroundSprite.draw(batch);
        bbatch.draw(backgroundSprite,0,0,Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        renderGameName();
        bbatch.end();




        //Draw vertical guide line
        lineRenderer.begin(ShapeRenderer.ShapeType.Line);
        lineRenderer.setColor(theme.getVerticalLineColor());   //color alpha not working

        for(int i=Constants.STARTING_OCTAVE;i<Constants.ENDING_OCTAVE;i++) {
            lineRenderer.line(Note.mapCoordinates("C"+i), Constants.OFFSET, Note.mapCoordinates("C"+i), Constants.WORLD_HEIGHT);
            lineRenderer.line(Note.mapCoordinates("F"+i), Constants.OFFSET, Note.mapCoordinates("F"+i), Constants.WORLD_HEIGHT);
        }


        Gdx.gl.glDisable(GL20.GL_BLEND);
        lineRenderer.end();



        blinerenderer.begin(ShapeRenderer.ShapeType.Line);
        blinerenderer.line(0, Constants.OFFSET*2, Constants.WORLD_WIDTH, Constants.OFFSET*2);

        blinerenderer.end();



        //Draw the Note
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        notes.render(renderer);
        renderer.end();



        //Draw the piano
        batch.begin();
        piano.render(sprite,batch);
        batch.end();

//        renderer.begin(ShapeRenderer.ShapeType.Filled);
//        renderer.rect(0,0,Constants.WORLD_WIDTH,Constants.OFFSET,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLACK);
//        renderer.end();


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
            cam.translate(0, -cam.zoom, 0);


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

        //cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100/cam.viewportWidth);

        float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

       // cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        //cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
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

    }

    @Override
    public void dispose() {

    }

    //hangling input
    @Override
    public boolean keyDown(int keycode) {
        //https://www.reddit.com/r/libgdx/comments/4223lq/how_will_i_know_when_i_need_to_implement_an/
        if (keycode == Input.Keys.SPACE) {
            isPlaying = !isPlaying;
        }

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
        isPlaying = !isPlaying;
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


    public static Theme getTheme()
    {
        return theme;
    }
}
