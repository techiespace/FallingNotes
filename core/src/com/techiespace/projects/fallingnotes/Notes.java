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
    HashMap<Integer,Long> soundIdArray  = new HashMap<>();
    Array<Note> noteArray;
    Array<Note> notesToRemove;
    Note[] noteArrayPool;
    int poolIndex;
    float initialTime;
    Sound[] sound = new Sound[88];
    String midiName;

    public Notes(FallingNotesGame app,String midiName) {
        this.app = app;
//        loadNotes();
        this.midiName = midiName;
        init();
        poolIndex = 0;
    }

    private void loadNotes() {
        for (int i = 0; i < 88; i++) {
            sound[i] = Gdx.audio.newSound(Gdx.files.internal("audio/" + getMidiNoteName(i + 21) + ".ogg"));
        }
    }
    public void initNoteId()
    {
        for(int i=0;i<noteArrayPool.length;i++)
        {
            noteArrayPool[i].id = i;
        }
    }



    public void init(){
        noteArray = new Array<Note>(true,88);
        notesToRemove = new Array<Note>(true, 88);
        MidiParser midiParser = new MidiParser();
        //Girls_Like_You_Maroon_5, broken_dreams
        noteArrayPool = midiParser.parse("midi/Interstellar_Main_Theme.mid");
        initNoteId();


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
//                    Gdx.app.log("Volume: ", "" + note.pressVelocity);
                    note.soundOnce = true;
                    soundIdArray.put(note.id,soundId);
                }


//                Gdx.app.log("TestOut", "" + noteArray.size);
//                Gdx.app.log("Condition",(Constants.WHITE_PIANO_KEY_HEIGHT + (float)Constants.OFFSET) + " "+note.position.y + " " + note.noteLength);
                if (note.position.y + note.noteLength < Constants.WHITE_PIANO_KEY_HEIGHT + (float) Constants.OFFSET) {
                    soundId = soundIdArray.get(note.id);
                    //sound.stop(soundId);
                    notesToRemove.add(note);
                    soundIdArray.remove(note.id);
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

}
