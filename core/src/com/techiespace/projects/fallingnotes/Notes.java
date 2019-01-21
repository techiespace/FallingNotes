package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.techiespace.projects.fallingnotes.pianoHelpers.MidiParser;
import com.techiespace.projects.fallingnotes.pianoHelpers.RoundRectShapeRenderer;

import java.util.Arrays;
import java.util.HashMap;


import static com.techiespace.projects.fallingnotes.pianoHelpers.HelperFunctions.getMidiNoteName;

public class Notes {

    private final FallingNotesGame app;
    Array<Note> noteArray;
    Array<Note> notesToRemove;
    Note[] noteArrayPool;
    HashMap<Integer,Long> soundIdArray;
    int poolIndex;
    float initialTime;
    Sound[] sound = new Sound[88];
    String midiName;

    // Constructor
    public Notes(FallingNotesGame app,String midiName) {
        this.app = app;
        poolIndex = 0;
        this.midiName = midiName;
        Gdx.app.log("Notes Constructor ",midiName);
        init();
    }

    public void init(){
        noteArray = new Array<Note>(true,88);
        notesToRemove = new Array<Note>(true, 88);

        //Parsing the supplied Midi
        MidiParser midiParser = new MidiParser();
        noteArrayPool = midiParser.parse("midi/"+midiName);


        initNoteId();


        //Sorting According to Time
        Arrays.sort(noteArrayPool);

        Arrays.toString(noteArrayPool);
        initialTime = 0;
    }

    public void initNoteId()
    {
        for(int i=0;i<noteArrayPool.length;i++)
        {
            noteArrayPool[i].id = i;
        }
    }


    public void update(float delta){
        initialTime += delta * Constants.SPEED;
        while (poolIndex < noteArrayPool.length && noteArrayPool[poolIndex].startTime <= initialTime * 1000) {
            noteArray.add(noteArrayPool[poolIndex]);
            poolIndex++;
        }


        for (Note note : noteArray) {
            Sound sound;
            long soundId = 0;
            note.update(delta);
            PianoKey key = Piano.findKey(note.noteName);
            sound = app.assets.get("audio/" + note.noteName + ".ogg");//note.sound;

            if (note.position.y < (Constants.WHITE_PIANO_KEY_HEIGHT + Constants.OFFSET)) {


                // Handling the corner case  of a really long note
                //updating the y position and simultaneously reducing the height
                if (note.position.y < Constants.OFFSET) {
                    note.position.y = note.position.y + Constants.WHITE_PIANO_KEY_HEIGHT;
                    note.noteLength = note.noteLength - Constants.WHITE_PIANO_KEY_HEIGHT;
                }

                if (!note.soundOnce) {
                    soundId = sound.play(note.pressVelocity / 100f);   //https://stackoverflow.com/questions/31990997/libgdx-not-playing-sound-android  (takes a while to load the sound)
                    note.soundOnce = true;
                    soundIdArray.put(note.id,soundId);
                }


                if (note.position.y + note.noteLength < Constants.WHITE_PIANO_KEY_HEIGHT + (float) Constants.OFFSET) {
                        soundId = soundIdArray.get(note.id);
                        sound.stop(soundId);
                        notesToRemove.add(note);
                        soundIdArray.remove(note.id);
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

}
