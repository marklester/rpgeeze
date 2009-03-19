package rpgeeze;

import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;

import javax.swing.UIManager;

import rpgeeze.log.LogManager;
import rpgeeze.log.MessageType;
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
			lm.registerLogger(new PrintStreamLogger(new PrintStream(new File("log/log.txt"))));
		}
		catch(FileNotFoundException e) {
			lm.log(new Message("Failed to open log file for writing.", "MAIN", MessageType.ERROR));
		}
		
		lm.log(new Message("Running with arguments: " + Arrays.toString(arg), "MAIN"));
		lm.log(new Message("Working directory is " + System.getProperty("user.dir"), "MAIN"));
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			LogManager.getInstance().log(new Message("Sucessfully set native look and feel", "MAIN"));
		}
		catch(Exception e) {
			LogManager.getInstance().log(new Message("Problem setting look and feel: " + e.getMessage(), "MAIN", MessageType.ERROR));
		}
		
		lm.log(new Message("Creating frame", "MAIN"));
		Frame frame = new Frame("rpgeeze");
		
		lm.log(new Message("Preparing game manager", "MAIN"));
		final GameManager gm = new GameManager(frame);
		
		if(FULL_SCREEN) {
			lm.log(new Message("Going fullscreen", "MAIN"));
			frame.setUndecorated(true);
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
		}
		else {
			lm.log(new Message("Staying windowed, setting frame size", "MAIN"));
			frame.setSize(768, 576);
		}
		
		lm.log(new Message("Starting game manager", "MAIN"));
		gm.start();
	}
}
