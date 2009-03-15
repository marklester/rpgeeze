package rpgeeze;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLDrawableFactory;

import rpgeeze.controller.MainMenuController;
import rpgeeze.view.MainMenuView;

import com.sun.opengl.util.FPSAnimator;

/**
 * Entry point for the game. Houses the main method.
 */

public class RunGame {	
	public final static int GOAL_FPS = 80;

	/**
	 * Creates the game frame and gives it a game manager.	
	 * 
	 * @param arg command-line options
	 */
	public static void main(String[] arg) {
		// we should pass the app name to the frame constructor 
		final Frame frame = new Frame("");

		GLCapabilities glc = new GLCapabilities();
		glc.setDoubleBuffered(true);
		GLDrawableFactory.getFactory();
		
		new GLCanvas();

		// create a few useful objects
		final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		final GLCanvas canvas = new GLCanvas();
		final FPSAnimator animator = new FPSAnimator(canvas, GOAL_FPS);

		GameManager gm = new GameManager(canvas);

		MainMenuView mmv = new MainMenuView(gm);

		EventProcessor ev = EventProcessor.getInstance();
		ev.addController(new MainMenuController(mmv));
		ev.start();

		gm.pushState(mmv);

		frame.add(canvas);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				synchronized(frame) {
					if(frame.isVisible()) {
						animator.stop();
						gd.setFullScreenWindow(null);
						frame.setVisible(false);
						frame.dispose();
					}
				}
				EventProcessor.getInstance().interrupt();
			}
		});

		frame.enableInputMethods(false);
		frame.setUndecorated(true);

		gd.setFullScreenWindow(frame);

		canvas.requestFocus();

		animator.start();

		frame.setVisible(true);
	}
}
