package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.techiespace.projects.fallingnotes.pianoHelpers.LoadingScreen;

public class FallingNotesGame extends Game {

    public String midiName = "inappmidi/perfect.mid";
    public AssetManager assets;
    public String instructions;

    public FallingNotesGame(String midiName) {
        if (midiName != null)
            this.midiName = midiName;

    }

    public FallingNotesGame() {

    }
    public FallingNotesGame(String midiName,String instructions) {
        if (midiName != null)
            this.midiName = midiName;

        if(instructions!=null)
            this.instructions = instructions;

    }



    @Override
    public void create() {
        assets = new AssetManager();

        if(instructions==null)
        setScreen(new LoadingScreen(this, midiName));
//        Gdx.app.log("FallingNotesGame Constructor",midiName);

        else if(instructions!=null)
        {
            System.out.println("Game "+instructions);
            setScreen(new LoadingScreen(this, midiName,instructions));
        }
    }

    @Override
    public void dispose() {
        assets.dispose();
        this.getScreen().dispose();
    }
}
