package com.garaperree.guazo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.garaperree.guazo.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();	
		config.width = Main.V_WIDTH;
		config.height = Main.V_HEIGHT;
		config.title = "Cliente";
		new LwjglApplication(new Main(), config);
	}
}
