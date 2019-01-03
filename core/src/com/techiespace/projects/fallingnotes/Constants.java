package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class Constants {
    // TODO: Add a constant for the world size
    public static final float WORLD_SIZE = 36.0f;

    public static final float WORLD_WIDTH = Gdx.graphics.getWidth();

    public static final float WORLD_HEIGHT = Gdx.graphics.getHeight();

    // TODO: Add a constant for the background color of the world
    public static final Color BACKGROUND_COLOR = Color.BLACK;

    // TODO: Add a constant for the height of the icicle
    public static final float NOTES_HEIGHT = 50.0f;

    public static final int NUM_WHITE_KEYS = 36;

    // TODO: Add a constant for the width of the icicle
    public static final float NOTES_WIDTH = Constants.WORLD_WIDTH / NUM_WHITE_KEYS;//1/36f;//1/Gdx.graphics.getWidth();

    public static final float BLACK_NOTE_WIDTH = 0.58f * NOTES_WIDTH;

    // TODO: Add a constant for the color of the icicles
    public static final Color NOTE_COLOR = Color.WHITE;

    public static final float TEMPO = 100.0f;

    public static final float SPEED = 1f;

    public static final int STARTING_OCTAVE = 2;

    public static final int ENDING_OCTAVE = 6;

    public final static int BLACK_PIANO_KEY_GROUPS = 8;
    public final static int WHITE_PIANO_KEY_GROUPS = 9;

    public final static String BLACK_DOWN = "piano/black_down.png";
    public final static String BLACK_UP = "piano/black_up.png";

    public final static String WHITE_UP = "piano/white_up.png";
    public final static String WHITE_DOWN = "piano/white_down.png";


    public final static String GAME_NAME = "ChordSwift";





    //white piano key height will also be the height of guitar

    public static final float WHITE_PIANO_KEY_HEIGHT = 120;

    public static final int OFFSET = 50;


    public static final float BLACK_KEY_HEIGHT = (float)(0.7f*WHITE_PIANO_KEY_HEIGHT);


    public static final float HEIGTH_MULTIPLIER = Constants.TEMPO / 1000f - 0.015f;    //by hit and trial. 0.015 to show gap between two adjacent notes.

}
