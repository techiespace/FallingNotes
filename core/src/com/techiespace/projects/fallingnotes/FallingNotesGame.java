package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.techiespace.projects.fallingnotes.pianoHelpers.LoadingScreen;

public class FallingNotesGame extends Game {

    public AssetManager assets;

	@Override
	public void create () {
        assets = new AssetManager();
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void dispose() {
        assets.dispose();
        this.getScreen().dispose();
    }
}
