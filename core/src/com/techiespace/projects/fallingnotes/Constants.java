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

    public static final int NUM_WHITE_KEYS = 52;

    // TODO: Add a constant for the width of the icicle
    public static final float NOTES_WIDTH = Constants.WORLD_WIDTH / NUM_WHITE_KEYS;//1/36f;//1/Gdx.graphics.getWidth();

    public static final float BLACK_NOTE_WIDTH = 0.58f * NOTES_WIDTH;

    // TODO: Add a constant for the color of the icicles
    public static final Color NOTE_COLOR = Color.WHITE;

    public static final int STARTING_OCTAVE = 0;

    public static final int ENDING_OCTAVE = 8;

    public final static int BLACK_PIANO_KEY_GROUPS = 8;
    public final static int WHITE_PIANO_KEY_GROUPS = 9;

    public final static String BLACK_DOWN = "piano/black_down.png";
    public final static String BLACK_UP = "piano/black_up.png";

    public final static String WHITE_UP = "piano/white_up.png";
    public final static String WHITE_DOWN = "piano/white_down.png";


    public final static String GAME_NAME = "ChordSwift";


    public final static float MENU_OFFSET = Constants.NOTES_WIDTH * 2;


    //white piano key height will also be the height of guitar

    public static final float WHITE_PIANO_KEY_HEIGHT = 100;

    public static int OFFSET = 50;


    public static final float BLACK_KEY_HEIGHT = (0.7f * WHITE_PIANO_KEY_HEIGHT);


    public static final float HEIGTH_MULTIPLIER = 0.1f - 0.007f; //by hit and trial. 0.007 to show gap between two adjacent notes.

}
