package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class FallingNotesScreen implements Screen {

    public static final String TAG = FallingNotesScreen.class.getName();
    // TODO: Add an ExtendViewport
//    ExtendViewport notesViewport;

    // TODO: Add a ShapeRenderer
    ShapeRenderer renderer;

    // TODO: Add an Icicle
    Notes notes;

    @Override
    public void show() {
//        notesViewport = new ExtendViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
//        notes = new Notes(notesViewport);
        notes = new Notes();
    }

    @Override
    public void render(float delta) {
        notes.update(delta);
//        notesViewport.apply(true);

        // TODO: Clear the screen to the background color
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // TODO: Set the ShapeRenderer's projection matrix
//        renderer.setProjectionMatrix(notesViewport.getCamera().combined);

        // TODO: Draw the Note

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        notes.render(renderer);
        renderer.end();
    }

    @Override
    public void resize(int width, int height) {
//        notesViewport.update(width, height, true);
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
    }

    @Override
    public void dispose() {

    }
}
