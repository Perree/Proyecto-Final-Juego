package com.garaperree.guazo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.garaperree.guazo.Main;
import com.garaperree.guazo.utiles.Config;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "El mejor juego del mundo papa";
		config.resizable = false;
		config.width = Config.SCREEN_WIDTH;
		config.height = Config.SCREEN_HEIGHT;
		
		new LwjglApplication(new Main(), config);
	}
}
