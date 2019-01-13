package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.techiespace.projects.fallingnotes.Themes.RedTheme;
import com.techiespace.projects.fallingnotes.Themes.Theme;
import com.techiespace.projects.fallingnotes.pianoHelpers.RoundRectShapeRenderer;

import javax.print.attribute.standard.OrientationRequested;

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

    private boolean isPlaying = false;


    @Override
    public void show() {
        //notesViewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        theme = new RedTheme();

        rotationSpeed = 0.5f;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(350,350*(h/w));
        cam.position.set(cam.viewportWidth/2f,cam.viewportHeight/2f-50,0);
        cam.update();









        renderer = new RoundRectShapeRenderer();
        renderer.setAutoShapeType(true);
        lineRenderer = new ShapeRenderer();
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
        bbatch.end();




        //Draw vertical guide line
        lineRenderer.begin(ShapeRenderer.ShapeType.Line);
        lineRenderer.setColor(theme.getVerticalLineColor());   //color alpha not working
        lineRenderer.line(Note.mapCoordinates("C3"), Constants.OFFSET, Note.mapCoordinates("C3"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("C4"), Constants.OFFSET, Note.mapCoordinates("C4"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("C5"), Constants.OFFSET, Note.mapCoordinates("C5"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("C6"), Constants.OFFSET, Note.mapCoordinates("C6"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("F2"), Constants.OFFSET, Note.mapCoordinates("F2"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("F3"), Constants.OFFSET, Note.mapCoordinates("F3"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("F4"), Constants.OFFSET, Note.mapCoordinates("F4"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("F5"), Constants.OFFSET, Note.mapCoordinates("F5"), Constants.WORLD_HEIGHT);
        lineRenderer.line(Note.mapCoordinates("F6"), Constants.OFFSET, Note.mapCoordinates("F6"), Constants.WORLD_HEIGHT);
        Gdx.gl.glDisable(GL20.GL_BLEND);
        lineRenderer.end();



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

    private void handleInput() {
//        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            cam.zoom += 0.02;
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
//            cam.zoom -= 0.02;
//        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(cam.position.x>350/2)
            cam.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

            if(cam.position.x<Gdx.graphics.getWidth()/2)

            cam.translate(3, 0, 0);
        }
//        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            cam.translate(0, -3, 0);
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            cam.translate(0, 3, 0);
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            cam.rotate(-rotationSpeed, 0, 0, 1);
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
//            cam.rotate(rotationSpeed, 0, 0, 1);
//        }

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
        cam.viewportWidth = 350f;
        cam.viewportHeight = 350f * height/width;
        cam.update();
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
