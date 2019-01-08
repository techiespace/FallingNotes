package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Camera extends InputAdapter {
    public static final String TAG = Camera.class.getName();

    private static final float SCALE_RATE = 100;
    private static final float MOVE_RATE = 100;
    private static final float ROTATION_RATE = 45;
    private static final float INITIAL_ZOOM = 0.5f;

    private OrthographicCamera overviewCamera;
    private OrthographicCamera closeupCamera;
    private boolean inCloseupMode = true;

    public Camera() {
        overviewCamera = new OrthographicCamera();
        closeupCamera = new OrthographicCamera();
        closeupCamera.setToOrtho(false, Gdx.graphics.getWidth() * INITIAL_ZOOM, Gdx.graphics.getHeight() * INITIAL_ZOOM);
        overviewCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void resize(float width, float height) {
        overviewCamera.setToOrtho(false, width, height);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            inCloseupMode = !inCloseupMode;
        }
        // Reset
        if (keycode == Input.Keys.R) {
            closeupCamera.setToOrtho(false, Gdx.graphics.getWidth() * INITIAL_ZOOM, Gdx.graphics.getHeight() * INITIAL_ZOOM);
        }
        if (keycode == Input.Keys.F) {
            fixAspectRatio();
        }
        return super.keyUp(keycode);
    }

    public void update() {
        float delta = Gdx.graphics.getDeltaTime();

        // Movement
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            closeupCamera.translate(-MOVE_RATE * delta, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            closeupCamera.translate(MOVE_RATE * delta, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            closeupCamera.translate(0, -MOVE_RATE * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            closeupCamera.translate(0, MOVE_RATE * delta);
        }

        // Rotation
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            closeupCamera.rotate(-ROTATION_RATE * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            closeupCamera.rotate(ROTATION_RATE * delta);
        }

        // Viewport size (ignoring aspect ratio)
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            closeupCamera.viewportHeight += SCALE_RATE * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            closeupCamera.viewportHeight -= SCALE_RATE * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            closeupCamera.viewportWidth -= SCALE_RATE * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            closeupCamera.viewportWidth += SCALE_RATE * delta;
        }

        // Zoom
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            proportionalZoom(-delta);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            proportionalZoom(delta);
        }
        closeupCamera.update();
    }

    private void proportionalZoom(float delta) {
        float aspectRatio = overviewCamera.viewportWidth / overviewCamera.viewportHeight;
        closeupCamera.viewportWidth += SCALE_RATE * delta;
        closeupCamera.viewportHeight += SCALE_RATE / aspectRatio * delta;
    }

    private void fixAspectRatio() {
        float aspectRatio = overviewCamera.viewportWidth / overviewCamera.viewportHeight;
        closeupCamera.viewportHeight = closeupCamera.viewportWidth / aspectRatio;
        closeupCamera.update();
    }

    /**
     * Set's the ShapeRenderer's projection matrix depending on the mode of the demo camera.
     */
    public void setCamera(ShapeRenderer renderer, Sprite backgroundSprite, SpriteBatch batch) {
        if (inCloseupMode) {
            closeupCamera.update();
            renderer.setProjectionMatrix(closeupCamera.combined);
        } else {
            overviewCamera.update();
            renderer.setProjectionMatrix(overviewCamera.combined);
        }
    }

    /**
     * Renders a blue rectangle showing the field of view of the closeup camera
     */
    public void render(ShapeRenderer renderer) {
        if (!inCloseupMode) {
            // Figure out the location of the camera corners in the world
            Vector2 bottomLeft = myUnproject(closeupCamera, 0, closeupCamera.viewportHeight);
            Vector2 bottomRight = myUnproject(closeupCamera, closeupCamera.viewportWidth, closeupCamera.viewportHeight);
            Vector2 topRight = myUnproject(closeupCamera, closeupCamera.viewportWidth, 0);
            Vector2 topLeft = myUnproject(closeupCamera, 0, 0);

            // Draw a rectangle showing the closeup camera's field of view
            renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.setColor(Color.BLUE);
            float[] poly = {bottomLeft.x, bottomLeft.y,
                    bottomRight.x, bottomRight.y,
                    topRight.x, topRight.y,
                    topLeft.x, topLeft.y
            };
            renderer.set(ShapeRenderer.ShapeType.Line);
            renderer.polygon(poly);
            renderer.end();
        }
    }

    /**
     * Helper function to deal with the fact that unproject expects coordinates with positive y
     * pointing down.
     */
    private Vector2 myUnproject(OrthographicCamera camera, float x, float y) {
        Vector3 raw = camera.unproject(new Vector3(x, y + overviewCamera.viewportHeight - camera.viewportHeight, 0), 0, 0, camera.viewportWidth, camera.viewportHeight);
        return new Vector2(raw.x, raw.y);
    }
}
