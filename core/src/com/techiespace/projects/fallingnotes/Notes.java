package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Notes {

    public static final String TAG = Notes.class.getName();

    Array<Note> noteArray;
    Note[] noteArrayPool;
    int poolIndex;
    long initialTime;
//    Viewport viewport;

    public Notes(){
//        this.viewport = viewport;
        init();
        poolIndex = 0;
    }

    public void init(){
        noteArray = new Array<Note>(true,88);
        noteArrayPool = new Note[]{
                new Note("C4",new Vector2(mapCoordinates("C4"), Constants.WORLD_HEIGHT), 0, 474),
                new Note("C#4",new Vector2(mapCoordinates("C#4"), Constants.WORLD_HEIGHT), 500, 974),
                new Note("E4",new Vector2(mapCoordinates("D4"), Constants.WORLD_HEIGHT), 1000, 1474),
                new Note("F4",new Vector2(mapCoordinates("F4"), Constants.WORLD_HEIGHT), 1500, 1974),
                new Note("G4",new Vector2(mapCoordinates("G4"), Constants.WORLD_HEIGHT), 2000, 2474),
                new Note("A4",new Vector2(mapCoordinates("A4"), Constants.WORLD_HEIGHT), 2500, 2974),
                new Note("B4",new Vector2(mapCoordinates("B4"), Constants.WORLD_HEIGHT), 3000, 3474),
                new Note("C5",new Vector2(mapCoordinates("C5"), Constants.WORLD_HEIGHT), 3500, 3974),
                new Note("B4",new Vector2(mapCoordinates("B4"), Constants.WORLD_HEIGHT), 4000, 4474),
                new Note("A4",new Vector2(mapCoordinates("A4"), Constants.WORLD_HEIGHT), 4500, 4974),
                new Note("G4",new Vector2(mapCoordinates("G4"), Constants.WORLD_HEIGHT), 5000, 5474),
                new Note("F4",new Vector2(mapCoordinates("F4"), Constants.WORLD_HEIGHT), 5500, 5974),
                new Note("E4",new Vector2(mapCoordinates("E4"), Constants.WORLD_HEIGHT), 6000, 6474),
                new Note("D4",new Vector2(mapCoordinates("D4"), Constants.WORLD_HEIGHT), 6500, 6974),
                new Note("C4",new Vector2(mapCoordinates("C4"), Constants.WORLD_HEIGHT), 7000, 7474)
        };

        initialTime = TimeUtils.nanoTime();
        /*noteArrayPool = new Note[]{new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 24, Constants.WORLD_HEIGHT), 0, 600),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 24, Constants.WORLD_HEIGHT), 600, 1200),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 12, Constants.WORLD_HEIGHT), 0, 1200),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 31, Constants.WORLD_HEIGHT), 1200, 1800),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 31, Constants.WORLD_HEIGHT), 1800, 2400),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 16, Constants.WORLD_HEIGHT), 1200, 2400),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 33, Constants.WORLD_HEIGHT), 2400, 3000),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 33, Constants.WORLD_HEIGHT), 3000, 3600),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 17, Constants.WORLD_HEIGHT), 2400, 3600),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 31, Constants.WORLD_HEIGHT), 3600, 4800),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 16, Constants.WORLD_HEIGHT), 3600, 4800),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 29, Constants.WORLD_HEIGHT), 4800, 5400),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 29, Constants.WORLD_HEIGHT), 5400, 6000),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 14, Constants.WORLD_HEIGHT), 4800, 6000),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 28, Constants.WORLD_HEIGHT), 6000, 6600),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 28, Constants.WORLD_HEIGHT), 6600, 7200),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 12, Constants.WORLD_HEIGHT), 6000, 7200),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 26, Constants.WORLD_HEIGHT), 7200, 7800),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 17, Constants.WORLD_HEIGHT), 7200, 7800),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 26, Constants.WORLD_HEIGHT), 7800, 8400),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 19, Constants.WORLD_HEIGHT), 7800, 8400),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 16, Constants.WORLD_HEIGHT), 8400, 9000),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 24, Constants.WORLD_HEIGHT), 8400, 9600),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 12, Constants.WORLD_HEIGHT), 9000, 9600),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 31, Constants.WORLD_HEIGHT), 9600, 10200),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 31, Constants.WORLD_HEIGHT), 10200, 10800),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 16, Constants.WORLD_HEIGHT), 9600, 10800),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 29, Constants.WORLD_HEIGHT), 10800, 11400),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 29, Constants.WORLD_HEIGHT), 11400, 12000),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 17, Constants.WORLD_HEIGHT), 10800, 12000),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 28, Constants.WORLD_HEIGHT), 12000, 12600),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 28, Constants.WORLD_HEIGHT), 12600, 13200),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 19, Constants.WORLD_HEIGHT), 12000, 13200),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 26, Constants.WORLD_HEIGHT), 13200, 14400),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 19, Constants.WORLD_HEIGHT), 13200, 14400),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 31, Constants.WORLD_HEIGHT), 14400, 15000),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 31, Constants.WORLD_HEIGHT), 15000, 15600),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 16, Constants.WORLD_HEIGHT), 14400, 15600),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 29, Constants.WORLD_HEIGHT), 15600, 16200),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 29, Constants.WORLD_HEIGHT), 16200, 16800),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 17, Constants.WORLD_HEIGHT), 15600, 16800),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 28, Constants.WORLD_HEIGHT), 16800, 17400),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 28, Constants.WORLD_HEIGHT), 17400, 18000),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 19, Constants.WORLD_HEIGHT), 16800, 18000),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 26, Constants.WORLD_HEIGHT), 18000, 19200),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 19, Constants.WORLD_HEIGHT), 18000, 19200),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 24, Constants.WORLD_HEIGHT), 19200, 19800),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 24, Constants.WORLD_HEIGHT), 19800, 20400),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 12, Constants.WORLD_HEIGHT), 19200, 20400),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 31, Constants.WORLD_HEIGHT), 20400, 21000),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 31, Constants.WORLD_HEIGHT), 21000, 21600),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 16, Constants.WORLD_HEIGHT), 20400, 21600),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 33, Constants.WORLD_HEIGHT), 21600, 22200),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 33, Constants.WORLD_HEIGHT), 22200, 22800),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 17, Constants.WORLD_HEIGHT), 21600, 22800),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 31, Constants.WORLD_HEIGHT), 22800, 24000),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 16, Constants.WORLD_HEIGHT), 22800, 24000),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 29, Constants.WORLD_HEIGHT), 24000, 24600),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 29, Constants.WORLD_HEIGHT), 24600, 25200),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 14, Constants.WORLD_HEIGHT), 24000, 25200),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 28, Constants.WORLD_HEIGHT), 25200, 25800),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 28, Constants.WORLD_HEIGHT), 25800, 26400),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 12, Constants.WORLD_HEIGHT), 25200, 26400),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 26, Constants.WORLD_HEIGHT), 26400, 27000),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 17, Constants.WORLD_HEIGHT), 26400, 27000),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 26, Constants.WORLD_HEIGHT), 27000, 27600),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 19, Constants.WORLD_HEIGHT), 27000, 27600),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 24, Constants.WORLD_HEIGHT), 27600, 28800),
                new Note(new Vector2((Constants.WORLD_WIDTH / 36) * 12, Constants.WORLD_HEIGHT), 27600, 28800)};*/
    }

    public void update(float delta){
        float elapsedNanoseconds = TimeUtils.nanoTime() - initialTime;
        float elapsedMillis = elapsedNanoseconds * 0.000001f;
//        System.out.println(elapsedMillis);
//        if (noteArray.size >= 1)
//            System.out.println(elapsedMillis );
//        if (MathUtils.random() < delta * Constants.ICICLE_SPAWNS_PER_SECOND) {

        if (poolIndex<noteArrayPool.length && noteArrayPool[poolIndex].startTime <= elapsedMillis) {
//            noteArray[poolIndex] = noteArrayPool[poolIndex];
//            poolIndex++;
            // TODO: Add a new note at the top of the viewport at a random x position
            Vector2 newNotePosition = new Vector2(
                    (Constants.WORLD_WIDTH / 36) * MathUtils.random(36),
                    Constants.WORLD_HEIGHT
            );
//            Note newNote = new Note(newNotePosition);
            if(poolIndex < noteArrayPool.length){
                noteArray.add(noteArrayPool[poolIndex]);
                poolIndex++;
            }
        }

        for (Note note : noteArray) {
            note.update(delta);
        }
    }

    public void render(ShapeRenderer renderer) {
        // TODO: Set ShapeRenderer Color
        renderer.setColor(Constants.ICICLE_COLOR);

        // TODO: Render each icicle
        for (Note note : noteArray) {
            note.render(renderer);
        }
    }

    public float mapCoordinates(String noteName){
        switch (noteName) {
            /*case "A0":
                return 0f;
            case "A#0":
                return "A#0";
            case "B0":
                return "B0";
            case "C1":
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
                case "B1":
            case "C2":*/
            case  "C2":
                return  Constants.NOTES_WIDTH * 0;
            case "C#2":
                return Constants.NOTES_WIDTH * 0 + Constants.NOTES_WIDTH * 0.7f;
            case "D2":
                return Constants.NOTES_WIDTH * 1;
            case "D#2":
                return Constants.NOTES_WIDTH * 1 + Constants.NOTES_WIDTH * 0.7f;
            case "E2":
                return  Constants.NOTES_WIDTH * 2;
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
                return  Constants.NOTES_WIDTH * 8;
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
                return Constants.NOTES_WIDTH * 0 + Constants.NOTES_WIDTH * 0.7f;
            case "B4":
                return Constants.NOTES_WIDTH * 20;
            case "C5":
                return Constants.NOTES_WIDTH * 21;
            case "C#5":
                return Constants.NOTES_WIDTH * 0 + Constants.NOTES_WIDTH * 0.7f;
            case "D5":
                return Constants.NOTES_WIDTH * 22;
            case "D#5":
                return Constants.NOTES_WIDTH * 0 + Constants.NOTES_WIDTH * 0.7f;
            case "E5":
                return Constants.NOTES_WIDTH * 23;
            case "F5":
                return Constants.NOTES_WIDTH * 24;
            case "F#5":
                return Constants.NOTES_WIDTH * 0 + Constants.NOTES_WIDTH * 0.7f;
            case "G5":
                return Constants.NOTES_WIDTH * 25;
            case "G#5":
                return Constants.NOTES_WIDTH * 0 + Constants.NOTES_WIDTH * 0.7f;
            case "A5":
                return Constants.NOTES_WIDTH * 26;
            case "A#5":
                return Constants.NOTES_WIDTH * 0 + Constants.NOTES_WIDTH * 0.7f;
            case "B5":
                return Constants.NOTES_WIDTH * 27;
            case "B#5":
                return Constants.NOTES_WIDTH * 0 + Constants.NOTES_WIDTH * 0.7f;
            case "C6":
                return Constants.NOTES_WIDTH * 28;
            case "C#6":
                return Constants.NOTES_WIDTH * 0 + Constants.NOTES_WIDTH * 0.7f;
            case "D6":
                return Constants.NOTES_WIDTH * 29;
            case "D#6":
                return Constants.NOTES_WIDTH * 0 + Constants.NOTES_WIDTH * 0.7f;
            case "E6":
                return Constants.NOTES_WIDTH * 30;
            case "F6":
                return Constants.NOTES_WIDTH * 31;
            case "F#6":
                return Constants.NOTES_WIDTH * 0 + Constants.NOTES_WIDTH * 0.7f;
            case "G6":
                return Constants.NOTES_WIDTH * 32;
            case "G#6":
                return Constants.NOTES_WIDTH * 0 + Constants.NOTES_WIDTH * 0.7f;
            case "A6":
                return Constants.NOTES_WIDTH * 33;
            case "A#6":
                return Constants.NOTES_WIDTH * 0 + Constants.NOTES_WIDTH * 0.7f;
            case "B6":
                return Constants.NOTES_WIDTH * 34;
            case "C7":
                return Constants.NOTES_WIDTH * 35;
             /*   return "C#7";
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
                return "C8";*/
            default:
                return -1;
        }
    }
}
