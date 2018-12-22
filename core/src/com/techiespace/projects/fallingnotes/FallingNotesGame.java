package com.techiespace.projects.fallingnotes;

import com.badlogic.gdx.Game;

public class FallingNotesGame extends Game {

	@Override
	public void create () {
		setScreen(new FallingNotesScreen());
	}
}
