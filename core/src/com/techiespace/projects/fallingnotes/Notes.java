package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.techiespace.projects.fallingnotes.pianoHelpers.MidiParser;
import com.techiespace.projects.fallingnotes.pianoHelpers.RoundRectShapeRenderer;

import java.util.Arrays;
import java.util.HashMap;

public class Notes {

    private final FallingNotesGame app;
    HashMap<Integer, Long> soundIdArray = new HashMap<>();
    Array<Note> noteArray;
    Array<Note> notesToRemove;
    Note[] noteArrayPool;
    int poolIndex;
    float initialTime;
    String midiName;
    Vector2 velocity;
    Preferences preferences;
    //This is the end time of the midi file
    int midiEndTime;

    //This is the actual end Time of the animation.
    //This should be calculated after midiEndTime is Calculated
    float animationEndTime;


    public Notes(FallingNotesGame app, String midiName, Stage stage) {
        this.app = app;
        this.midiName = midiName;
        init();


    }

    protected Preferences getPrefs() {
        if (preferences == null)
            preferences = Gdx.app.getPreferences("play_prefrences");
        return preferences;
    }

    public void initNoteId() {
        for (int i = 0; i < noteArrayPool.length; i++) {
            noteArrayPool[i].id = i;
        }
    }


    public void init() {
        poolIndex = 0;
        preferences = getPrefs();
        velocity = new Vector2(0, -preferences.getFloat("tempo_multiplier") * 100);
        noteArray = new Array<Note>(true, 88);
        notesToRemove = new Array<Note>(true, 88);
        MidiParser midiParser = new MidiParser();
        //Girls_Like_You_Maroon_5, broken_dreams
        noteArrayPool = midiParser.parse("midi/CScale.mid");
        initNoteId();


        Arrays.sort(noteArrayPool);

        Arrays.toString(noteArrayPool);
        initialTime = 0;


        midiEndTime = noteArrayPool[noteArrayPool.length - 1].endTime;

        initAnimationEndTime();

//        Gdx.app.log("Notes",animationEndTime+" ");

    }


    public void update(float delta) {
        initialTime += delta * preferences.getFloat("tempo_multiplier");

        //Applying the reset Condition here

        //This should be updated because change in tempo will also change this
        initAnimationEndTime();



        while (poolIndex < noteArrayPool.length && noteArrayPool[poolIndex].startTime <= initialTime * 1000) {
            noteArray.add(noteArrayPool[poolIndex]);
            poolIndex++;
        }
        for (Note note : noteArray) {
            Sound sound;
            long soundId = 0;
            note.update(delta, new Vector2(0, -preferences.getFloat("tempo_multiplier") * 100));
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
                    soundIdArray.put(note.id, soundId);
                }


                if (note.position.y + note.noteLength < Constants.WHITE_PIANO_KEY_HEIGHT + (float) Constants.OFFSET) {
                    soundId = soundIdArray.get(note.id);
                    //sound.stop(soundId);
                    notesToRemove.add(note);
                    soundIdArray.remove(note.id);
                    }
            }


            //for handling the key
            if (note.position.y < Constants.WHITE_PIANO_KEY_HEIGHT + Constants.OFFSET && !key.getIsPressed()) {
                key.updateTextureDown(note.track);
            }

            if (note.position.y + note.noteLength < Constants.WHITE_PIANO_KEY_HEIGHT + Constants.OFFSET && key.getIsPressed()) {
                key.updateTextureUp();
            }
        }

        for (int i = 0; i < notesToRemove.size; i++) {
            noteArray.removeValue(notesToRemove.get(i), true);

        }
        notesToRemove.clear();
    }

    public int getMidiEndTime() {
        return midiEndTime;
    }

    public float getAnimationEndTime()
    {
        return animationEndTime;
    }



    public float getInitialTime() {
        return initialTime;
    }


    public void render(RoundRectShapeRenderer renderer) {
        // TODO: Render each note
        for (Note note : noteArray) {
            note.render(renderer);
            }
    }


    public void reset()
    {
        initialTime = 0;
        init();
    }


    public void initAnimationEndTime()
    {
        //The actual end time of the animation will be calculated on the bases of current Tempo

        animationEndTime =  (midiEndTime + 1000*((Constants.WORLD_HEIGHT-Constants.OFFSET)/(preferences.getFloat("tempo_multiplier") * 100)));


    }
}
