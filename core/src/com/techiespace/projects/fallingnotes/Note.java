package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.techiespace.projects.fallingnotes.pianoHelpers.RoundRectShapeRenderer;

import static com.techiespace.projects.fallingnotes.pianoHelpers.HelperFunctions.getMidiNoteName;

public class Note implements Comparable<Note> {

    public static final String TAG = Note.class.getName();

    int id;
    int startTime;
    int endTime;
    float noteLength;
    String noteName;
    Vector2 position;
    Sound sound;
    int track;
    Preferences preferences;

    boolean soundOnce = false;
    int pressVelocity;
    Preferences playPref;

    public Note(int midiNoteNum, int startTime, int endTime, int pressVelocity, int track) {
        playPref = getPrefs();//Gdx.app.getPreferences("play_prefrences");
        this.noteName = getMidiNoteName(midiNoteNum);
        this.position = new Vector2(mapCoordinates(this.noteName), Constants.WORLD_HEIGHT);
        this.startTime = startTime;
        this.endTime = endTime;
        this.noteLength = (endTime - startTime) * Constants.HEIGTH_MULTIPLIER;// / Constants.SPEED;
//        this.sound = sound;//Gdx.audio.newSound(Gdx.files.internal("audio/" + noteName + ".ogg"));  //TODO: Imp - This causes skewed first note and takes time to start activity.
        this.pressVelocity = pressVelocity;
        this.track = track;
    }

    protected Preferences getPrefs() {
        if (preferences == null)
            preferences = Gdx.app.getPreferences("play_prefrences");
        return preferences;
    }

    public void render(RoundRectShapeRenderer renderer) {
        boolean rightHand = playPref.getBoolean("right_hand");
        boolean leftHand = playPref.getBoolean("left_hand");
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(153 / 255f, 51 / 255f, 255 / 255f, 1);
        if(noteName.contains("#")){

            if (this.track == 0 && rightHand) {  //Right Hand
                renderer.roundedRect(renderer,
                        position.x, position.y,
                        Constants.BLACK_NOTE_WIDTH,
                        noteLength, 2,
                        FallingNotesScreen.getTheme().getRH_lightBlackKeyColor(),
                        FallingNotesScreen.getTheme().getRH_darkBlackKeyColor(),
                        FallingNotesScreen.getTheme().getRH_darkBlackKeyColor(),
                        FallingNotesScreen.getTheme().getRH_lightBlackKeyColor()
                );
            } else if (this.track == 1 && leftHand)
            {
                renderer.roundedRect(renderer,
                        position.x, position.y,
                        Constants.BLACK_NOTE_WIDTH,
                        noteLength, 2,
                        FallingNotesScreen.getTheme().getLH_lightBlackKeyColor(),
                        FallingNotesScreen.getTheme().getLH_darkBlackKeyColor(),
                        FallingNotesScreen.getTheme().getLH_darkBlackKeyColor(),
                        FallingNotesScreen.getTheme().getLH_lightBlackKeyColor()
                );
            }
        }
        else {   // if it is a white key

            if (track == 0 && rightHand) {
                renderer.roundedRect(renderer,
                        position.x, position.y,
                        Constants.NOTES_WIDTH,
                        noteLength, 2,
                        FallingNotesScreen.getTheme().getRH_lightWhiteKeyColor(),
                        FallingNotesScreen.getTheme().getRH_darkWhiteKeyColor(),
                        FallingNotesScreen.getTheme().getRH_darkWhiteKeyColor(),
                        FallingNotesScreen.getTheme().getRH_lightWhiteKeyColor()
                );
            } else if (track == 1 && leftHand)
            {
                renderer.roundedRect(renderer,
                        position.x, position.y,
                        Constants.NOTES_WIDTH,
                        noteLength, 2,
                        FallingNotesScreen.getTheme().getLH_lightWhiteKeyColor(),
                        FallingNotesScreen.getTheme().getLH_darkWhiteKeyColor(),
                        FallingNotesScreen.getTheme().getLH_darkWhiteKeyColor(),
                        FallingNotesScreen.getTheme().getLH_lightWhiteKeyColor()
                );
            }
        }
    }

    @Override
    public String toString() {
        //Gdx.app.log("Note "+this.noteName,"Track "+this.track);


        return super.toString();
    }

    public void update(float delta, Vector2 velocity) {
        position.mulAdd(velocity, delta);
    }

    @Override
    public int compareTo(Note note) {
        if (startTime > note.startTime)
            return 1;
        else if (startTime < note.startTime)
            return -1;
        else
            return 0;
    }

    public static float mapCoordinates(String noteName) {
        switch (noteName) {


            case "A0":
                return Constants.NOTES_WIDTH * 0;
            case "A#0":
                return Constants.NOTES_WIDTH * 0.7f;
            case "B0":
                return Constants.NOTES_WIDTH * 1;

            case "C1":
                return Constants.NOTES_WIDTH * 2;
            case "C#1":
                return Constants.NOTES_WIDTH * 2 + Constants.NOTES_WIDTH * 0.7f;
            case "D1":
                return Constants.NOTES_WIDTH * 3;
            case "D#1":
                return Constants.NOTES_WIDTH * 3 + Constants.NOTES_WIDTH * 0.7f;
            case "E1":
                return Constants.NOTES_WIDTH * 4;
            case "F1":
                return Constants.NOTES_WIDTH * 5;
            case "F#1":
                return Constants.NOTES_WIDTH * 5 + Constants.NOTES_WIDTH * 0.7f;
            case "G1":
                return Constants.NOTES_WIDTH * 6;
            case "G#1":
                return Constants.NOTES_WIDTH * 6 + Constants.NOTES_WIDTH * 0.7f;
            case "A1":
                return Constants.NOTES_WIDTH * 7;
            case "A#1":
                return Constants.NOTES_WIDTH * 7 + Constants.NOTES_WIDTH * 0.7f;
            case "B1":
                return Constants.NOTES_WIDTH * 8;
            case "C2":
                return Constants.NOTES_WIDTH * 9;
            case "C#2":
                return Constants.NOTES_WIDTH * 9 + Constants.NOTES_WIDTH * 0.7f;
            case "D2":
                return Constants.NOTES_WIDTH * 10;
            case "D#2":
                return Constants.NOTES_WIDTH * 10 + Constants.NOTES_WIDTH * 0.7f;
            case "E2":
                return Constants.NOTES_WIDTH * 11;
            case "F2":
                return Constants.NOTES_WIDTH * 12;
            case "F#2":
                return Constants.NOTES_WIDTH * 12 + Constants.NOTES_WIDTH * 0.7f;
            case "G2":
                return Constants.NOTES_WIDTH * 13;
            case "G#2":
                return Constants.NOTES_WIDTH * 13 + Constants.NOTES_WIDTH * 0.7f;
            case "A2":
                return Constants.NOTES_WIDTH * 14;
            case "A#2":
                return Constants.NOTES_WIDTH * 14 + Constants.NOTES_WIDTH * 0.7f;
            case "B2":
                return Constants.NOTES_WIDTH *15;
            case "C3":
                return Constants.NOTES_WIDTH * 16;
            case "C#3":
                return Constants.NOTES_WIDTH * 16 + Constants.NOTES_WIDTH * 0.7f;
            case "D3":
                return Constants.NOTES_WIDTH * 17;
            case "D#3":
                return Constants.NOTES_WIDTH * 17 + Constants.NOTES_WIDTH * 0.7f;
            case "E3":
                return Constants.NOTES_WIDTH * 18;
            case "F3":
                return Constants.NOTES_WIDTH * 19;
            case "F#3":
                return Constants.NOTES_WIDTH * 19 + Constants.NOTES_WIDTH * 0.7f;
            case "G3":
                return Constants.NOTES_WIDTH * 20;
            case "G#3":
                return Constants.NOTES_WIDTH * 20 + Constants.NOTES_WIDTH * 0.7f;
            case "A3":
                return Constants.NOTES_WIDTH * 21;
            case "A#3":
                return Constants.NOTES_WIDTH * 21 + Constants.NOTES_WIDTH * 0.7f;
            case "B3":
                return Constants.NOTES_WIDTH * 22;
            case "C4":
                return Constants.NOTES_WIDTH * 23;
            case "C#4":
                return Constants.NOTES_WIDTH * 23 + Constants.NOTES_WIDTH * 0.7f;
            case "D4":
                return Constants.NOTES_WIDTH * 24;
            case "D#4":
                return Constants.NOTES_WIDTH * 24 + Constants.NOTES_WIDTH * 0.7f;
            case "E4":
                return Constants.NOTES_WIDTH * 25;
            case "F4":
                return Constants.NOTES_WIDTH * 26;
            case "F#4":
                return Constants.NOTES_WIDTH * 26 + Constants.NOTES_WIDTH * 0.7f;
            case "G4":
                return Constants.NOTES_WIDTH * 27;
            case "G#4":
                return Constants.NOTES_WIDTH * 27 + Constants.NOTES_WIDTH * 0.7f;
            case "A4":
                return Constants.NOTES_WIDTH * 28;
            case "A#4":
                return Constants.NOTES_WIDTH * 28 + Constants.NOTES_WIDTH * 0.7f;
            case "B4":
                return Constants.NOTES_WIDTH * 29;
            case "C5":
                return Constants.NOTES_WIDTH * 30;
            case "C#5":
                return Constants.NOTES_WIDTH * 30 + Constants.NOTES_WIDTH * 0.7f;
            case "D5":
                return Constants.NOTES_WIDTH * 31;
            case "D#5":
                return Constants.NOTES_WIDTH * 31 + Constants.NOTES_WIDTH * 0.7f;
            case "E5":
                return Constants.NOTES_WIDTH * 32;
            case "F5":
                return Constants.NOTES_WIDTH * 33;
            case "F#5":
                return Constants.NOTES_WIDTH * 33 + Constants.NOTES_WIDTH * 0.7f;
            case "G5":
                return Constants.NOTES_WIDTH * 34;
            case "G#5":
                return Constants.NOTES_WIDTH * 34 + Constants.NOTES_WIDTH * 0.7f;
            case "A5":
                return Constants.NOTES_WIDTH * 35;
            case "A#5":
                return Constants.NOTES_WIDTH * 35 + Constants.NOTES_WIDTH * 0.7f;
            case "B5":
                return Constants.NOTES_WIDTH * 36;
            case "B#5":
                return Constants.NOTES_WIDTH * 36 + Constants.NOTES_WIDTH * 0.7f;
            case "C6":
                return Constants.NOTES_WIDTH * 37;
            case "C#6":
                return Constants.NOTES_WIDTH * 37 + Constants.NOTES_WIDTH * 0.7f;
            case "D6":
                return Constants.NOTES_WIDTH * 38;
            case "D#6":
                return Constants.NOTES_WIDTH * 38 + Constants.NOTES_WIDTH * 0.7f;
            case "E6":
                return Constants.NOTES_WIDTH * 39;
            case "F6":
                return Constants.NOTES_WIDTH * 40;
            case "F#6":
                return Constants.NOTES_WIDTH * 40 + Constants.NOTES_WIDTH * 0.7f;
            case "G6":
                return Constants.NOTES_WIDTH * 41;
            case "G#6":
                return Constants.NOTES_WIDTH * 41 + Constants.NOTES_WIDTH * 0.7f;
            case "A6":
                return Constants.NOTES_WIDTH * 42;
            case "A#6":
                return Constants.NOTES_WIDTH * 42 + Constants.NOTES_WIDTH * 0.7f;
            case "B6":
                return Constants.NOTES_WIDTH * 43;
            case "C7":
                return Constants.NOTES_WIDTH * 44;

            case "C#7":
                return Constants.NOTES_WIDTH * 44 + Constants.NOTES_WIDTH * 0.7f;
            case "D7":
                return Constants.NOTES_WIDTH * 45;
            case "D#7":
                return Constants.NOTES_WIDTH * 45 + Constants.NOTES_WIDTH * 0.7f;
            case "E7":
                return Constants.NOTES_WIDTH * 46;
            case "F7":
                return Constants.NOTES_WIDTH * 47;
            case "F#7":
                return Constants.NOTES_WIDTH * 47 + Constants.NOTES_WIDTH * 0.7f;
            case "G7":
                return Constants.NOTES_WIDTH * 48;
            case "G#7":
                return Constants.NOTES_WIDTH * 48 + Constants.NOTES_WIDTH * 0.7f;
            case "A7":
                return Constants.NOTES_WIDTH * 49;
            case "A#7":
                return Constants.NOTES_WIDTH * 49 + Constants.NOTES_WIDTH * 0.7f;
            case "B7":
                return Constants.NOTES_WIDTH * 50;
            case "C8":
                return Constants.NOTES_WIDTH * 51;
            default:
                return -1;
        }
    }
}
