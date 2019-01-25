package com.techiespace.projects.fallingnotes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

public class GestureHandler implements GestureDetector.GestureListener{
    private OrthographicCamera camera;
    private FallingNotesScreen game;

    public GestureHandler(FallingNotesScreen game,OrthographicCamera camera)
    {
        this.camera = camera;
        this.game = game;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Gdx.app.log("GestureHandler","Tap");

        game.playPauseToggle();
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {

        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Gdx.app.log("GestureHandler","Fling");
        game.translate(deltaX);
        game.settingUpCamera();
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        float ratio = 1f;

        if(distance<initialDistance)
        {
          Gdx.app.log("GestureHandler","Pinch");
          ratio = -ratio;
        }
        else{

            Gdx.app.log("GestureHandler","Zoom");

        }

        game.zoom(ratio);

         game.settingUpCamera();
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {

       // Gdx.app.log("GestureHandler","Pinch");
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
