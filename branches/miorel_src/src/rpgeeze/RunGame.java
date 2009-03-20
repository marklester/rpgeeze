package rpgeeze;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import javax.swing.UIManager;

import rpgeeze.log.LogManager;
import rpgeeze.log.PrintStreamLogger;
import rpgeeze.log.Message;

/**
 * Entry point for the game. Houses the main method.
 * 
 */
public class RunGame {	
	private RunGame() {
	}
	
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
	
		GameProperties prop = new GameProperties(arg);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			LogManager.getInstance().log("Successfully set native look and feel", "MAIN");
		}
		catch(Exception e) {
			LogManager.getInstance().log("Problem setting look and feel: " + e.getMessage(), "MAIN", Message.Type.ERROR);
		}
		
		lm.log("Creating frame", "MAIN");
		Frame frame = new Frame("rpgeeze");
		frame.setResizable(false);
		
		lm.log("Preparing game manager", "MAIN");
		final GameManager gm = new GameManager(frame, prop);
		
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    GraphicsDevice dev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	    
		if(prop.getFullScreen() && dev.isFullScreenSupported()) {
			lm.log("Going fullscreen", "MAIN");
			frame.setUndecorated(true);
			dev.setFullScreenWindow(frame);
		}
		else {
			lm.log("Staying windowed, setting frame size", "MAIN");
			double scaleFactor = 2.0 / 3;
			frame.setSize((int) (screen.getWidth() * scaleFactor), (int) (screen.getHeight() * scaleFactor));
		}
		
		lm.log("Starting game manager", "MAIN");
		gm.start();
	}
}
