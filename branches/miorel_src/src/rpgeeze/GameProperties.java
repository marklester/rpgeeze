package rpgeeze;

import java.io.IOException;
import java.util.HashMap;
import java.util.Enumeration;
import java.util.Properties;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.util.ResourceLoader;

// WARNING! This file is auto-generated! Any changes will be overwritten.
// To add a constant, edit res/properties/const.properties
// To add an option configurable via the command line, edit res/properties/opts.properties
// To add a key-value pair, edit res/properties/keyval.properties

/**
 * Holds constants needed throughout the game.
 * 
 */
public class GameProperties {
	public GameProperties(String... arg) {
		LogManager lm = LogManager.getInstance();
		Properties prop = new Properties();
		try {
			prop.load(ResourceLoader.getInstance().getStream(
					"properties/keyval.properties"));
		}
		catch (IOException e) {
		}
		Enumeration<?> en = prop.propertyNames();
		while(en.hasMoreElements()) {
			String key = en.nextElement().toString();
			map.put(key, prop.getProperty(key));
		}
		for(String s: arg) {
			try {
				if(s.matches("--goal-ups=.+"))
					goalUPS = parseInt(s);
				else if(s.matches("--goal-fps=.+"))
					goalFPS = parseInt(s);
				else if(s.matches("--full-screen"))
					fullScreen = true;
				else if(s.matches("--no-full-screen"))
					fullScreen = false;
			}
			catch (Exception e) {
				lm.log("Error parsing argument: " + s, "PARSER",
						Message.Type.ERROR);
			}
		}
	}

	private int goalUPS = 80;

	public int getGoalUPS() {
		return goalUPS;
	}

	private int goalFPS = 80;

	public int getGoalFPS() {
		return goalFPS;
	}

	private boolean fullScreen = true;

	public boolean getFullScreen() {
		return fullScreen;
	}

	private HashMap<String, String> map = new HashMap<String, String>();

	public String getProperty(String key) {
		return map.get(key);
	}

	private static String parseString(String s) {
		return s.replaceFirst("^[^=]=", "");
	}

	private static int parseInt(String s) {
		return Integer.parseInt(parseString(s));
	}
}
