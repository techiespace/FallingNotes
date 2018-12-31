package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.techiespace.projects.fallingnotes.pianoHelpers.RoundRectShapeRenderer;

public class Note implements Comparable<Note> {

    public static final String TAG = Note.class.getName();

    int id;
    int startTime;
    int endTime;
    float noteLength;
    String noteName;
    Vector2 position;
    Vector2 velocity;
    Sound sound;
    boolean soundOnce = false;

    public Note(int midiNoteNum, int startTime, int endTime) {
        this.noteName = getMidiNoteName(midiNoteNum);
        this.position = new Vector2(mapCoordinates(this.noteName), Constants.WORLD_HEIGHT);
        this.velocity = new Vector2(0,-Constants.TEMPO);
        this.startTime = startTime;
        this.endTime = endTime;
        this.noteLength = (endTime - startTime) * Constants.HEIGTH_MULTIPLIER;
        this.sound = Gdx.audio.newSound(Gdx.files.internal("audio/" + noteName + ".ogg"));  //TODO: Imp - This causes skewed first note and takes time to start activity.
    }

    public void render(RoundRectShapeRenderer renderer) {
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(153 / 255f, 51 / 255f, 255 / 255f, 1);
        if(noteName.contains("#")){
            renderer.roundedRect(renderer,
                    position.x, position.y,
                    Constants.BLACK_NOTE_WIDTH,
                    noteLength, 2,
                    new Color(153 / 255f, 51 / 255f, 255 / 255f, 1),
                    new Color(178 / 255f, 102 / 255f, 255 / 255f, 1),
                    new Color(178 / 255f, 102 / 255f, 255 / 255f, 1),
                    new Color(153 / 255f, 51 / 255f, 255 / 255f, 1)
            );
        }
        else {
            renderer.roundedRect(renderer,
                    position.x, position.y,
                    Constants.NOTES_WIDTH,
                    noteLength, 2,
                    new Color(102 / 255f, 0 / 255f, 204 / 255f, 1),
                    new Color(127 / 255f, 0 / 255f, 255 / 255f, 1),
                    new Color(127 / 255f, 0 / 255f, 255 / 255f, 1),
                    new Color(102 / 255f, 0 / 255f, 204 / 255f, 1)
            );
        }
    }

    public void update(float delta){
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
            case "C2":
                return Constants.NOTES_WIDTH * 0;
            case "C#2":
                return Constants.NOTES_WIDTH * 0 + Constants.NOTES_WIDTH * 0.7f;
            case "D2":
                return Constants.NOTES_WIDTH * 1;
            case "D#2":
                return Constants.NOTES_WIDTH * 1 + Constants.NOTES_WIDTH * 0.7f;
            case "E2":
                return Constants.NOTES_WIDTH * 2;
            case "F2":
                return Constants.NOTES_WIDTH * 3;
            case "F#2":
                return Constants.NOTES_WIDTH * 3 + Constants.NOTES_WIDTH * 0.7f;
            case "G2":
                return Constants.NOTES_WIDTH * 4;
            case "G#2":
                return Constants.NOTES_WIDTH * 4 + Constants.NOTES_WIDTH * 0.7f;
            case "A2":
                return Constants.NOTES_WIDTH * 5;
            case "A#2":
                return Constants.NOTES_WIDTH * 5 + Constants.NOTES_WIDTH * 0.7f;
            case "B2":
                return Constants.NOTES_WIDTH * 6;
            case "C3":
                return Constants.NOTES_WIDTH * 7;
            case "C#3":
                return Constants.NOTES_WIDTH * 7 + Constants.NOTES_WIDTH * 0.7f;
            case "D3":
                return Constants.NOTES_WIDTH * 8;
            case "D#3":
                return Constants.NOTES_WIDTH * 8 + Constants.NOTES_WIDTH * 0.7f;
            case "E3":
                return Constants.NOTES_WIDTH * 9;
            case "F3":
                return Constants.NOTES_WIDTH * 10;
            case "F#3":
                return Constants.NOTES_WIDTH * 10 + Constants.NOTES_WIDTH * 0.7f;
            case "G3":
                return Constants.NOTES_WIDTH * 11;
            case "G#3":
                return Constants.NOTES_WIDTH * 11 + Constants.NOTES_WIDTH * 0.7f;
            case "A3":
                return Constants.NOTES_WIDTH * 12;
            case "A#3":
                return Constants.NOTES_WIDTH * 12 + Constants.NOTES_WIDTH * 0.7f;
            case "B3":
                return Constants.NOTES_WIDTH * 13;
            case "C4":
                return Constants.NOTES_WIDTH * 14;
            case "C#4":
                return Constants.NOTES_WIDTH * 14 + Constants.NOTES_WIDTH * 0.7f;
            case "D4":
                return Constants.NOTES_WIDTH * 15;
            case "D#4":
                return Constants.NOTES_WIDTH * 15 + Constants.NOTES_WIDTH * 0.7f;
            case "E4":
                return Constants.NOTES_WIDTH * 16;
            case "F4":
                return Constants.NOTES_WIDTH * 17;
            case "F#4":
                return Constants.NOTES_WIDTH * 17 + Constants.NOTES_WIDTH * 0.7f;
            case "G4":
                return Constants.NOTES_WIDTH * 18;
            case "G#4":
                return Constants.NOTES_WIDTH * 18 + Constants.NOTES_WIDTH * 0.7f;
            case "A4":
                return Constants.NOTES_WIDTH * 19;
            case "A#4":
                return Constants.NOTES_WIDTH * 19 + Constants.NOTES_WIDTH * 0.7f;
            case "B4":
                return Constants.NOTES_WIDTH * 20;
            case "C5":
                return Constants.NOTES_WIDTH * 21;
            case "C#5":
                return Constants.NOTES_WIDTH * 21 + Constants.NOTES_WIDTH * 0.7f;
            case "D5":
                return Constants.NOTES_WIDTH * 22;
            case "D#5":
                return Constants.NOTES_WIDTH * 22 + Constants.NOTES_WIDTH * 0.7f;
            case "E5":
                return Constants.NOTES_WIDTH * 23;
            case "F5":
                return Constants.NOTES_WIDTH * 24;
            case "F#5":
                return Constants.NOTES_WIDTH * 24 + Constants.NOTES_WIDTH * 0.7f;
            case "G5":
                return Constants.NOTES_WIDTH * 25;
            case "G#5":
                return Constants.NOTES_WIDTH * 25 + Constants.NOTES_WIDTH * 0.7f;
            case "A5":
                return Constants.NOTES_WIDTH * 26;
            case "A#5":
                return Constants.NOTES_WIDTH * 26 + Constants.NOTES_WIDTH * 0.7f;
            case "B5":
                return Constants.NOTES_WIDTH * 27;
            case "B#5":
                return Constants.NOTES_WIDTH * 27 + Constants.NOTES_WIDTH * 0.7f;
            case "C6":
                return Constants.NOTES_WIDTH * 28;
            case "C#6":
                return Constants.NOTES_WIDTH * 28 + Constants.NOTES_WIDTH * 0.7f;
            case "D6":
                return Constants.NOTES_WIDTH * 29;
            case "D#6":
                return Constants.NOTES_WIDTH * 29 + Constants.NOTES_WIDTH * 0.7f;
            case "E6":
                return Constants.NOTES_WIDTH * 30;
            case "F6":
                return Constants.NOTES_WIDTH * 31;
            case "F#6":
                return Constants.NOTES_WIDTH * 31 + Constants.NOTES_WIDTH * 0.7f;
            case "G6":
                return Constants.NOTES_WIDTH * 32;
            case "G#6":
                return Constants.NOTES_WIDTH * 32 + Constants.NOTES_WIDTH * 0.7f;
            case "A6":
                return Constants.NOTES_WIDTH * 33;
            case "A#6":
                return Constants.NOTES_WIDTH * 33 + Constants.NOTES_WIDTH * 0.7f;
            case "B6":
                return Constants.NOTES_WIDTH * 34;
            case "C7":
                return Constants.NOTES_WIDTH * 35;
            default:
                return -1;
        }
    }

    public String getMidiNoteName(int midiNumber) {
        //TODO: Map midiNoteNumber to midiNoteName - Implement efficient solution
        switch (midiNumber) {
            case 21:
                return "A0";
            case 22:
                return "A#0";
            case 23:
                return "B0";
            case 24:
                return "C1";
            case 25:
                return "C#1";
            case 26:
                return "D1";
            case 27:
                return "D#1";
            case 28:
                return "E1";
            case 29:
                return "F1";
            case 30:
                return "F#1";
            case 31:
                return "G1";
            case 32:
                return "G#1";
            case 33:
                return "A1";
            case 34:
                return "A#1";
            case 35:
                return "B1";
            case 36:
                return "C2";
            case 37:
                return "C#2";
            case 38:
                return "D2";
            case 39:
                return "D#2";
            case 40:
                return "E2";
            case 41:
                return "F2";
            case 42:
                return "F#2";
            case 43:
                return "G2";
            case 44:
                return "G#2";
            case 45:
                return "A2";
            case 46:
                return "A#2";
            case 47:
                return "B2";
            case 48:
                return "C3";
            case 49:
                return "C#3";
            case 50:
                return "D3";
            case 51:
                return "D#3";
            case 52:
                return "E3";
            case 53:
                return "F3";
            case 54:
                return "F#3";
            case 55:
                return "G3";
            case 56:
                return "G#3";
            case 57:
                return "A3";
            case 58:
                return "A#3";
            case 59:
                return "B3";
            case 60:
                return "C4";
            case 61:
                return "C#4";
            case 62:
                return "D4";
            case 63:
                return "D#4";
            case 64:
                return "E4";
            case 65:
                return "F4";
            case 66:
                return "F#4";
            case 67:
                return "G4";
            case 68:
                return "G#4";
            case 69:
                return "A4";
            case 70:
                return "A#4";
            case 71:
                return "B4";
            case 72:
                return "C5";
            case 73:
                return "C#5";
            case 74:
                return "D5";
            case 75:
                return "D#5";
            case 76:
                return "E5";
            case 77:
                return "F5";
            case 78:
                return "F#5";
            case 79:
                return "G5";
            case 80:
                return "G#5";
            case 81:
                return "A5";
            case 82:
                return "A#5";
            case 83:
                return "B5";
            case 84:
                return "C6";
            case 85:
                return "C#6";
            case 86:
                return "D6";
            case 87:
                return "D#6";
            case 88:
                return "E6";
            case 89:
                return "F6";
            case 90:
                return "F#6";
            case 91:
                return "G6";
            case 92:
                return "G#6";
            case 93:
                return "A6";
            case 94:
                return "A#6";
            case 95:
                return "B6";
            case 96:
                return "C7";
            case 97:
                return "C#7";
            case 98:
                return "D7";
            case 99:
                return "D#7";
            case 100:
                return "E7";
            case 101:
                return "F7";
            case 102:
                return "F#7";
            case 103:
                return "G7";
            case 104:
                return "G#7";
            case 105:
                return "A7";
            case 106:
                return "A#7";
            case 107:
                return "B7";
            case 108:
                return "C8";
            default:
                return "Error";
        }
    }
}
