package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.techiespace.projects.fallingnotes.pianoHelpers.MidiParser;
import com.techiespace.projects.fallingnotes.pianoHelpers.RoundRectShapeRenderer;

import java.util.Arrays;

public class Notes {

    Array<Note> noteArray;
    Note[] noteArrayPool;
    int poolIndex;
    long initialTime;
    Sound sound;

    public Notes(){
        init();
        poolIndex = 0;
    }

    public void init(){
        noteArray = new Array<Note>(true,88);
        MidiParser midiParser = new MidiParser();
        noteArrayPool = midiParser.parse("moonlight_sonata.mid");
        Arrays.sort(noteArrayPool);
        initialTime = TimeUtils.nanoTime();
    }

    public void update(float delta){
        float elapsedNanoseconds = TimeUtils.nanoTime() - initialTime;
        float elapsedMillis = elapsedNanoseconds * 0.000001f;

        if (poolIndex<noteArrayPool.length && noteArrayPool[poolIndex].startTime <= elapsedMillis) {
                noteArray.add(noteArrayPool[poolIndex]);
                poolIndex++;
        }

        for (Note note : noteArray) {
            Sound sound;
            long soundId;
            note.update(delta);
            PianoKey key = Piano.findKey(note.noteName);

            if (note.position.y < Constants.WHITE_PIANO_KEY_HEIGHT && !note.soundOnce) {
                sound = note.sound;
                soundId = sound.play();
                sound.play();   //https://stackoverflow.com/questions/31990997/libgdx-not-playing-sound-android  (takes a while to load the sound)
                note.soundOnce = true;



                if (note.position.y + note.noteLength < Constants.WHITE_PIANO_KEY_HEIGHT) {
                    sound.stop(soundId);
                    }
            }



            //for handling the key
            if(note.position.y < Constants.WHITE_PIANO_KEY_HEIGHT && !key.getIsPressed())
            {
                key.updateTextureDown();
            }

            if(note.position.y+note.noteLength < Constants.WHITE_PIANO_KEY_HEIGHT && key.getIsPressed())
            {
                key.updateTextureUp();
            }
        }
    }

    public void render(RoundRectShapeRenderer renderer) {
        // TODO: Render each note
        for (Note note : noteArray) {
            note.render(renderer);
        }
    }
}
