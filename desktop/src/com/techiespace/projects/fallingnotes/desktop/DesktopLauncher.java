package com.techiespace.projects.fallingnotes.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.techiespace.projects.fallingnotes.FallingNotesGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//        config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
//        config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
//        config.fullscreen = true;
        new LwjglApplication(new FallingNotesGame(), config);
//        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
	}
}
