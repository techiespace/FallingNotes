package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.techiespace.projects.fallingnotes.pianoHelpers.MidiParser;
import com.techiespace.projects.fallingnotes.pianoHelpers.RoundRectShapeRenderer;

import java.util.Arrays;

public class Notes {

    Array<Note> noteArray;
    Array<Note> notesToRemove;
    Note[] noteArrayPool;
    int poolIndex;
    float initialTime;
    Sound[] sound = new Sound[88];

    public Notes(){
        loadNotes();
        init();
        poolIndex = 0;
    }

    private void loadNotes() {
        for (int i = 0; i < 88; i++) {
            sound[i] = Gdx.audio.newSound(Gdx.files.internal("audio/" + getMidiNoteName(i + 21) + ".ogg"));
        }
    }

    public void init(){
        noteArray = new Array<Note>(true,88);
        notesToRemove = new Array<Note>(true, 88);
        MidiParser midiParser = new MidiParser();
        //Girls_Like_You_Maroon_5, broken_dreams
        noteArrayPool = midiParser.parse("midi/Interstellar_Main_Theme.mid", sound);


        Arrays.sort(noteArrayPool);

        Arrays.toString(noteArrayPool);
        initialTime = 0;
    }

    public void update(float delta){
        initialTime += delta * Constants.SPEED;
//        Gdx.app.log("Init time: ", ""+initialTime+" Delta time: "+ noteArrayPool[poolIndex].startTime);
//        for (int i = poolIndex; i < noteArrayPool.length ; i++) {
        while (poolIndex < noteArrayPool.length && noteArrayPool[poolIndex].startTime <= initialTime * 1000) {
            noteArray.add(noteArrayPool[poolIndex]);
            poolIndex++;
        }
//        }

        for (Note note : noteArray) {
            Sound sound;
            long soundId = 0;
            note.update(delta);
            PianoKey key = Piano.findKey(note.noteName);
            sound = note.sound;

            if (note.position.y < (Constants.WHITE_PIANO_KEY_HEIGHT + Constants.OFFSET)) {


                // Handling the corner case  of a really long note
                //updating the y position and simultaneously reducing the height
                if (note.position.y < Constants.OFFSET) {
                    note.position.y = note.position.y + Constants.WHITE_PIANO_KEY_HEIGHT;
                    note.noteLength = note.noteLength - Constants.WHITE_PIANO_KEY_HEIGHT;
                }

                if (!note.soundOnce) {
                    soundId = sound.play(note.pressVelocity / 100f);   //https://stackoverflow.com/questions/31990997/libgdx-not-playing-sound-android  (takes a while to load the sound)
//                    Gdx.app.log("Volume: ", "" + note.pressVelocity);
                    note.soundOnce = true;
                }


//                Gdx.app.log("TestOut", "" + noteArray.size);
//                Gdx.app.log("Condition",(Constants.WHITE_PIANO_KEY_HEIGHT + (float)Constants.OFFSET) + " "+note.position.y + " " + note.noteLength);
                if (note.position.y + note.noteLength < Constants.WHITE_PIANO_KEY_HEIGHT + (float) Constants.OFFSET) {
                    sound.stop(soundId);
                    notesToRemove.add(note);
//                    noteArray.removeValue(note, false);
//                    Gdx.app.log("Test",""+noteArray.size);
                }
            }



            //for handling the key
            if (note.position.y < Constants.WHITE_PIANO_KEY_HEIGHT + Constants.OFFSET && !key.getIsPressed())
            {
                key.updateTextureDown(note.track);
            }

            if (note.position.y + note.noteLength < Constants.WHITE_PIANO_KEY_HEIGHT + Constants.OFFSET && key.getIsPressed())
            {
                key.updateTextureUp();
            }
        }

        for (int i = 0; i < notesToRemove.size; i++) {
            noteArray.removeValue(notesToRemove.get(i), true);

        }
        notesToRemove.clear();
    }

    public void render(RoundRectShapeRenderer renderer) {
        // TODO: Render each note
        for (Note note : noteArray) {
            note.render(renderer);
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
