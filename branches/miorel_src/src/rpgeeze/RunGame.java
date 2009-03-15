package rpgeeze;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCanvas;

/**
 * Entry point for the game. Houses the main method.
 */

public class RunGame {	
	public final static int GOAL_FPS = 80;
	public final static boolean FULL_SCREEN = false;
	
	/**
	 * Creates the game frame and gives it a game manager.	
	 * 
	 * @param arg command-line options
	 */
	public static void main(String[] arg) { 
		Frame frame = new Frame("");
		GLCanvas canvas = new GLCanvas();
		final GameManager gm = new GameManager(canvas);
		
		frame.setBackground(Color.BLACK);
		frame.add(canvas);
		frame.enableInputMethods(false);

		if(FULL_SCREEN) {
			frame.setUndecorated(true);
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame);
		}
		else {;
			frame.setSize(768, 576);
		}

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				gm.stop(0);
			}
		});
		
		gm.start();
		
		frame.setVisible(true);
	}
}
