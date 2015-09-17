package com.bomberman.core.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.bomberman.core.Engine;

public class BomberMain {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "BomberMan";
		config.useGL30 = true;
		//config.fullscreen = true;
		config.width = 1366;
		config.height = 768;
		
		new LwjglApplication(new Engine(), config);
	}
}
