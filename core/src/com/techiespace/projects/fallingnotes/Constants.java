package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class Constants {
    // TODO: Add a constant for the world size
    public static final float WORLD_SIZE = 36.0f;

    public static final float WORLD_WIDTH = Gdx.graphics.getWidth();

    public static final float WORLD_HEIGHT = Gdx.graphics.getHeight();

    // TODO: Add a constant for the background color of the world
    public static final Color BACKGROUND_COLOR = Color.BLUE;

    // TODO: Add a constant for the height of the icicle
    public static final float NOTES_HEIGHT = 50.0f;

    // TODO: Add a constant for the width of the icicle
    public static final float NOTES_WIDTH = Constants.WORLD_WIDTH/36;//1/36f;//1/Gdx.graphics.getWidth();

    public static final float BLACK_NOTE_WIDTH = 0.58f * NOTES_WIDTH;

    // TODO: Add a constant for the color of the icicles
    public static final Color NOTE_COLOR = Color.WHITE;

    public static final float TEMPO = 50.0f;

    public static final float ICICLE_SPAWNS_PER_SECOND = 10.0f;

    public static final Color ICICLE_COLOR = Color.WHITE;
    public static final float HEIGTH_MULTIPLIER = 0.05f;    //by hit and trial. Maybe works becoz the quarter note is 500millis

    public static final int NUM_WHITE_KEYS = 31;
}
