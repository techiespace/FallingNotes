package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.techiespace.projects.fallingnotes.pianoHelpers.LoadingScreen;
import com.techiespace.projects.fallingnotes.pianoHelpers.LoadingScreenPlayMidi;

public class FallingNotesGame extends Game {

    public String midiName = "inappmidi/perfect.mid";
    public AssetManager assets;
    public String instructions;
    boolean playMidi;
    public dbInterface dbInterface;

    public FallingNotesGame(String midiName, boolean playMidi) {
        if (midiName != null)
            this.midiName = midiName;
        this.playMidi = playMidi;
    }

    public FallingNotesGame() {

    }

    public FallingNotesGame(String midiName, String instructions, boolean playMidi) {
        if (midiName != null)
            this.midiName = midiName;

        if(instructions!=null)
            this.instructions = instructions;
        this.playMidi = playMidi;

    }
    public FallingNotesGame(String midiName, String instructions, boolean playMidi, dbInterface dbInterface) {
        if (midiName != null)
            this.midiName = midiName;

        if(instructions!=null)
            this.instructions = instructions;
        this.playMidi = playMidi;

        if(dbInterface!=null)
            this.dbInterface = dbInterface;

    }





    @Override
    public void create() {
        assets = new AssetManager();

        if(instructions==null)
            if (playMidi)
                setScreen(new LoadingScreenPlayMidi(this, midiName));
            else
                setScreen(new LoadingScreen(this, midiName));

            //        Gdx.app.log("FallingNotesGame Constructor",midiName);

        else if(instructions!=null)
        {
            System.out.println("Game "+instructions);
            if (playMidi)
                setScreen(new LoadingScreenPlayMidi(this, midiName, instructions));
            else
                setScreen(new LoadingScreen(this, midiName, instructions));
        }
    }

    @Override
    public void dispose() {
        assets.dispose();
        this.getScreen().dispose();
    }
}
