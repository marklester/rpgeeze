package rpgeeze;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GLCanvas;

import rpgeeze.view.MainMenu;

import com.sun.opengl.util.FPSAnimator;

public class RunGame {	
	public final static int GOAL_FPS = 80;
	
	public static void main(String[] arg) {
		// we should pass the app name to the frame constructor 
		final Frame frame = new Frame("");
		
		// create a few useful objects
		final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	    final GLCanvas canvas = new GLCanvas();
	    final FPSAnimator animator = new FPSAnimator(canvas, GOAL_FPS);
	    
	    GameManager gm = new GameManager();
	    
	    canvas.addGLEventListener(gm);
	    canvas.addKeyListener(gm);
	    canvas.addMouseListener(gm);
	    
	    gm.changeView(new MainMenu());
	    
	    frame.add(canvas);
	    
	    frame.addWindowListener(new WindowAdapter() {
	    	public void windowClosing(WindowEvent e) {
	    		animator.stop();
	    		gd.setFullScreenWindow(null);
	    		frame.setVisible(false);
	    		frame.dispose();
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
