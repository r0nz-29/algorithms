package com.raunits.algorithms;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.raunits.algorithms.Algorithms;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
//		config.setWindowedMode(800, 600);
		config.setBackBufferConfig(0,0,0,0,16,0,4);
		config.setTitle("Algorithms");
		new Lwjgl3Application(new Algorithms(), config);
	}
}
