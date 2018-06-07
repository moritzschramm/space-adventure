package com.comet_commit.space_adventure.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.comet_commit.space_adventure.SpaceAdventure;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = SpaceAdventure.WIDTH;
		config.height = SpaceAdventure.HEIGHT;
		config.title = SpaceAdventure.TITLE;
		new LwjglApplication(new SpaceAdventure(), config);
	}
}
