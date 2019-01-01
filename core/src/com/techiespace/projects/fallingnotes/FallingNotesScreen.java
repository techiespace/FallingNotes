package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.techiespace.projects.fallingnotes.pianoHelpers.RoundRectShapeRenderer;

public class FallingNotesScreen implements Screen, InputProcessor {

    public static final String TAG = FallingNotesScreen.class.getName();
    // TODO: Add an ExtendViewport
//    ExtendViewport notesViewport;

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

    private boolean isPlaying = false;


    @Override
    public void show() {
        //notesViewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        renderer = new RoundRectShapeRenderer();
        renderer.setAutoShapeType(true);
        lineRenderer = new ShapeRenderer();
        //notes = new Notes(notesViewport);
        notes = new Notes();
        batch = new SpriteBatch();
        sprite = new Sprite();
        piano = new Piano();
        backgroundTexture = new Texture("black-background.jpg");
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
        // TODO: Clear the screen to the background color
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // TODO: Set the ShapeRenderer's projection matrix
        //renderer.setProjectionMatrix(notesViewport.getCamera().combined);

        backgroundSprite.setAlpha(0.5f);

        batch.begin();
        // backgroundSprite.draw(batch);
        batch.draw(backgroundSprite,0,0,Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        batch.end();


        //Draw vertical guide line
        lineRenderer.begin(ShapeRenderer.ShapeType.Line);
        lineRenderer.setColor(0.5f, 0.5f, 0.5f, 0.5f);   //color alpha not working
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

    @Override
    public void resize(int width, int height) {
        //notesViewport.update(width, height, true);
        //notes.init(); //required?
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
}
