package rpgeeze;

import java.awt.Frame;
import java.awt.GraphicsEnvironment;

/**
 * Entry point for the game. Houses the main method.
 */

public class RunGame {	
	public final static int GOAL_FPS = 80;
	public final static boolean FULL_SCREEN = true;
	
	/**
	 * Creates the game frame and gives it a game manager.	
	 * 
	 * @param arg command-line options
	 */
	public static void main(String[] arg) { 
		Frame frame = new Frame("rpgeeze");

		final GameManager gm = new GameManager(frame);

		if(FULL_SCREEN) {
			frame.setUndecorated(true);
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
		}
		else {;
			frame.setSize(768, 576);
		}
		
		gm.start();
	}
}
