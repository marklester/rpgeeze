package rpgeeze;

import java.awt.DisplayMode;
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
 */

public class RunGame {	
	
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
		
		DisplayMode mode = new DisplayMode(1024, 768, 32, DisplayMode.REFRESH_RATE_UNKNOWN);
		for(DisplayMode m: GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayModes())
			System.out.println(m.getWidth() + "x" + m.getHeight() + " " + m.getBitDepth() + " " + m.getRefreshRate());

		if(prop.getFullScreen()) {
			lm.log("Going fullscreen", "MAIN");
			frame.setSize(mode.getWidth(), mode.getHeight());
			GraphicsDevice dev = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			frame.setUndecorated(true);
			if(dev.isDisplayChangeSupported()) {
				lm.log("Desired display mode is supported", "MAIN");
				dev.setDisplayMode(mode);
			}
			System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
			if(System.getProperty("os.name").equals("Linux"))
				dev.setFullScreenWindow(frame);
			System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
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
