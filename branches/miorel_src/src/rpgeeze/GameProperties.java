package rpgeeze;

import rpgeeze.log.LogManager;
import rpgeeze.log.Message;

// WARNING! This file is auto-generated! Any changes will be overwritten.
// To add a constant, edit res/properties/const.properties
// To add an option configurable via the command line, edit res/properties/opts.properties

/**
 * Holds constants needed throughout the game.
 *
 */
public class GameProperties {
	public GameProperties() {}

	public GameProperties(String[] arg) {
		LogManager lm = LogManager.getInstance();
		for(String s: arg) {
			try {
				if(s.matches("--goal-fps=.+"))
					goalFPS = parseInt(s);
				else if(s.matches("--goal-ups=.+"))
					goalUPS = parseInt(s);
				else if(s.matches("--full-screen"))
					fullScreen = true;
				else if(s.matches("--no-full-screen"))
					fullScreen = false;
			}
			catch(Exception e) {
				lm.log("Error parsing argument: " + s, "PARSER", Message.Type.ERROR);
			}
		}
	}

	private int goalFPS = 80;
	public int getGoalFPS() {
		return goalFPS;
	}

	private int goalUPS = 80;
	public int getGoalUPS() {
		return goalUPS;
	}

	private boolean fullScreen = true;
	public boolean getFullScreen() {
		return fullScreen;
	}

	private String parseString(String s) {
		return s.replaceFirst("^[^=]=", "");
	}

	private int parseInt(String s) {
		return Integer.parseInt(parseString(s));
	}
}
