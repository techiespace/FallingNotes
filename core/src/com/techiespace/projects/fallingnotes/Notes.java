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
    HashMap<Integer,Long> soundIdArray  = new HashMap<>();
    Array<Note> noteArray;
    Array<Note> notesToRemove;
    Note[] noteArrayPool;
    int poolIndex;
    float initialTime;
    String midiName;
    Vector2 velocity;
    Preferences preferences;
    Slider seekSlider;


    public Notes(FallingNotesGame app, String midiName, Stage stage) {
        this.app = app;
//        loadNotes();
        this.midiName = midiName;
        init(stage);
        poolIndex = 0;
        preferences = getPrefs();
        velocity = new Vector2(0, -preferences.getFloat("tempo_multiplier") * 100);
    }

    protected Preferences getPrefs() {
        if (preferences == null)
            preferences = Gdx.app.getPreferences("play_prefrences");
        return preferences;
    }

    public void initNoteId()
    {
        for(int i=0;i<noteArrayPool.length;i++)
        {
            noteArrayPool[i].id = i;
        }
    }


    public void init(Stage stage) {
        noteArray = new Array<Note>(true,88);
        notesToRemove = new Array<Note>(true, 88);
        MidiParser midiParser = new MidiParser();
        //Girls_Like_You_Maroon_5, broken_dreams
        noteArrayPool = midiParser.parse("midi/Interstellar_Main_Theme.mid");
        initNoteId();


        Arrays.sort(noteArrayPool);

        Arrays.toString(noteArrayPool);
        initialTime = 0;

        initSeekBar(stage);
    }

    private void initSeekBar(Stage stage) {
        Table seekTable = new Table();
        //seek bar
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        seekSlider = new Slider(0, noteArrayPool[noteArrayPool.length - 1].endTime, 0.05f, false, skin);
        //bg
        Pixmap bgPixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        bgPixmap.setColor(new Color(1, 0, 0, 0.8f));
        bgPixmap.fill();
        TextureRegionDrawable textureRegionDrawableBg = new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap)));
        seekTable.setBackground(textureRegionDrawableBg);
        bgPixmap.dispose(); //https://stackoverflow.com/questions/39081993/libgdx-scene2d-set-background-color-of-table

        seekTable.setBackground(textureRegionDrawableBg);
        seekTable.setSize(Constants.WORLD_WIDTH * 2 / 3, Constants.MENU_OFFSET);
        seekTable.setPosition(Constants.WORLD_WIDTH / 6, 0);
        seekTable.add(seekSlider);
        stage.addActor(seekTable);

        seekSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //might be useful when implementing actual seek functionality
            }
        });
    }

    public void update(float delta){
        initialTime += delta * preferences.getFloat("tempo_multiplier");
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
                    soundIdArray.put(note.id,soundId);
                }


                if (note.position.y + note.noteLength < Constants.WHITE_PIANO_KEY_HEIGHT + (float) Constants.OFFSET) {
                    soundId = soundIdArray.get(note.id);
                    //sound.stop(soundId);
                    notesToRemove.add(note);
                    soundIdArray.remove(note.id);
//                    noteArray.removeValue(note, false);
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
            seekSlider.setValue(note.startTime);
        }
    }

}
