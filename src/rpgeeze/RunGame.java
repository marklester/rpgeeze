package rpgeeze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.UIManager;

import rpgeeze.log.LogManager;
import rpgeeze.log.PrintStreamLogger;
import rpgeeze.log.Message;
import rpgeeze.util.ResourceLoader;

/**
 * Entry point for the game. Houses the main method.
 * 
 */
public class RunGame {
	public final static double LOGO_Z = -100;
	public final static double LOGO_Y = -LOGO_Z / 5;
	public final static double LOGO_SIZE = -LOGO_Z * 1.7;
	
	public final static HashMap<String,String> keyControls = new HashMap<String,String>();
	
	public final static Color BACKGROUND_COLOR = new Color(0.0f, 0.75f, 0.0f, 1.0f);
	
	public final static Font APP_FONT = ResourceLoader.getInstance().getFont(GameProperties.getInstance().getProperty("app.font"), Font.PLAIN, 100);
	
	private RunGame() {
	}

	/**
	 * Creates the game frame and gives it a game manager.
	 * 
	 * @param arg
	 *            command-line options
	 */
	public static void main(String[] arg) {
		LogManager lm = LogManager.getInstance();
		lm.registerLogger(new PrintStreamLogger(System.out));
		try {
			new File("log").mkdir();
			lm.registerLogger(new PrintStreamLogger("log/log.txt"));
		}
		catch (FileNotFoundException e) {
			lm.log("Failed to open log file for writing.", "MAIN",
					Message.Type.ERROR);
		}

		lm.log(String.format("Running on %s with arguments: %s",
				System.getProperty("os.name"), Arrays.toString(arg)), "MAIN");
		lm.log("Working directory is " + System.getProperty("user.dir"),
				"MAIN");

		GameProperties prop = GameProperties.getInstance();
		prop.load(arg);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			lm.log("Successfully set native look and feel", "MAIN");
		}
		catch (Exception e) {
			lm.log("Problem setting look and feel: " + e.getMessage(), "MAIN",
					Message.Type.ERROR);
		}

		lm.log("Creating frame", "MAIN");
		Frame frame = new Frame("rpgeeze");
		frame.setResizable(false);

		lm.log("Preparing game manager", "MAIN");
		final GameManager gm = new GameManager(frame);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		GraphicsDevice dev = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();

		if(prop.getFullScreen() && dev.isFullScreenSupported()) {
			lm.log("Going fullscreen", "MAIN");
			frame.setUndecorated(true);
			dev.setFullScreenWindow(frame);
		}
		else {
			lm.log("Staying windowed, setting frame size", "MAIN");
			double scaleFactor = 2.0 / 3;
			frame.setSize(
					(int) (screen.getWidth() * scaleFactor),
					(int) (screen.getHeight() * scaleFactor));
		}

		lm.log("Starting game manager", "MAIN");
		gm.start();
	}
}
