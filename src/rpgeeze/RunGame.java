package rpgeeze;

import java.awt.DisplayMode;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import javax.swing.UIManager;

import rpgeeze.log.LogManager;
import rpgeeze.log.PrintStreamLogger;
import rpgeeze.log.Message;

/**
 * Entry point for the game. Houses the main method.
 */

public class RunGame {	
	public final static int GOAL_FPS = 80;
	public final static int GOAL_UPS = 80;
	public final static boolean FULL_SCREEN = false;
	
	/**
	 * Creates the game frame and gives it a game manager.	
	 * 
	 * @param arg command-line options
	 */
	public static void main(String[] arg) {
		LogManager lm = LogManager.getInstance();
		lm.registerLogger(new PrintStreamLogger(System.out));
		try {
			new File("log").mkdir();
			lm.registerLogger(new PrintStreamLogger("log/log.txt"));
		}
		catch(FileNotFoundException e) {
			lm.log("Failed to open log file for writing.", "MAIN", Message.Type.ERROR);
		}
		
		lm.log("Running on " + System.getProperty("os.name") + " with arguments: " + Arrays.toString(arg), "MAIN");
		lm.log("Working directory is " + System.getProperty("user.dir"), "MAIN");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			LogManager.getInstance().log("Successfully set native look and feel", "MAIN");
		}
		catch(Exception e) {
			LogManager.getInstance().log("Problem setting look and feel: " + e.getMessage(), "MAIN", Message.Type.ERROR);
		}
		
		lm.log("Creating frame", "MAIN");
		Frame frame = new Frame("rpgeeze");
		
		lm.log("Preparing game manager", "MAIN");
		final GameManager gm = new GameManager(frame);
		
		DisplayMode mode = new DisplayMode(1024, 768, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
		if(FULL_SCREEN) {
			lm.log("Going fullscreen", "MAIN");
			frame.setUndecorated(true);
			frame.setSize(mode.getWidth(), mode.getHeight());
			GraphicsDevice dev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			if(dev.isDisplayChangeSupported()) {
				lm.log("Desired display mode is supported", "MAIN");
				dev.setDisplayMode(mode);
			}
			if(System.getProperty("os.name").equals("Linux"))
				dev.setFullScreenWindow(frame);
		}
		else {
			lm.log("Staying windowed, setting frame size", "MAIN");
			double scaleFactor = 2.0 / 3;
			frame.setSize((int) (mode.getWidth() * scaleFactor), (int) (mode.getHeight() * scaleFactor));
		}
		
		lm.log("Starting game manager", "MAIN");
		gm.start();
	}
}
