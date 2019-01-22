package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.techiespace.projects.fallingnotes.pianoHelpers.LoadingScreen;

public class FallingNotesGame extends Game {

    public String midiName = "perfect.mid";
    public AssetManager assets;
    public FallingNotesGame(String midiName){
        if(midiName!=null)
        this.midiName = midiName;

    }
    public FallingNotesGame()
    {

    }


	@Override
	public void create () {
        assets = new AssetManager();
        setScreen(new LoadingScreen(this,midiName));
//        Gdx.app.log("FallingNotesGame COnstructor",midiName);
    }

    @Override
    public void dispose() {
        assets.dispose();
        this.getScreen().dispose();
    }
}
