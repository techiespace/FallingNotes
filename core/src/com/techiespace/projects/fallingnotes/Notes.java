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
            if (note.position.y < 70 && !note.soundOnce) {
                sound = note.sound;
                soundId = sound.play();
                sound.play();   //https://stackoverflow.com/questions/31990997/libgdx-not-playing-sound-android  (takes a while to load the sound)
                note.soundOnce = true;
                if (note.position.y + note.noteLength < 70) {
                    sound.stop(soundId);
                }
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
