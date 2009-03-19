package rpgeeze;

// WARNING: This file is auto-generated!
// Edit config.txt if you need to add a property otherwise your change will be overwritten.

/**
 * Holds global variables needed throughout the game.
 *
 */
public class GlobalProperties {
	public GlobalProperties() {}

	public GlobalProperties(String[] arg) {
		for(String s: arg) {
			if(s.matches("--goalFPS=.+"))
				goalFPS = parseInt(s);
			else if(s.matches("--goalUPS=.+"))
				goalUPS = parseInt(s);
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

	private int parseInt(String s) {
		return Integer.parseInt(s.replaceFirst("^[^=]=", ""));
	}
}
