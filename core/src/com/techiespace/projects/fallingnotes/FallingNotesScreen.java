package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.techiespace.projects.fallingnotes.pianoHelpers.RoundRectShapeRenderer;

public class FallingNotesScreen implements Screen {

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
        backgroundTexture = new Texture("background.jpg");
        backgroundTexture.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);
        backgroundSprite = new Sprite(backgroundTexture, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void render(float delta) {
        notes.update(delta);
        //notesViewport.apply(true);

        // TODO: Clear the screen to the background color
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // TODO: Set the ShapeRenderer's projection matrix
        //renderer.setProjectionMatrix(notesViewport.getCamera().combined);
//        backgroundSprite.setAlpha(0.5f);
//        batch.begin();
//        backgroundSprite.draw(batch);
//        batch.end();

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
}
