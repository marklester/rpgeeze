package rpgeeze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Stack;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLContext;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.FPSAnimator;

import rpgeeze.controller.Controller;
import rpgeeze.controller.MainMenuController;
import rpgeeze.util.Pair;
import rpgeeze.view.MainMenuView;
import rpgeeze.view.View;

/**
 * Primary implementer of the event-listening interfaces. This class does very little work itself.
 */

public class GameManager implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
	private Stack<Pair<View, Controller>> stateStack = new Stack<Pair<View, Controller>>();
	private GLContext spareContext;
	private GLCanvas canvas;
	private FPSAnimator animator;

	private void grabNewContext(GLAutoDrawable drawable) {
		if(spareContext != null)
			spareContext.destroy();
		spareContext = drawable.createContext(GLContext.getCurrent());		
	}
	
	public GameManager(GLCanvas canvas) {
	    canvas.addGLEventListener(this);
	    this.canvas = canvas;
	    animator = new FPSAnimator(canvas, RunGame.GOAL_FPS);
	}
	
	public void keyPressed(KeyEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.keyPressed(e);
			spareContext.release();
		}
	}

	public void keyReleased(KeyEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.keyReleased(e);
			spareContext.release();
		}
	}

	public void keyTyped(KeyEvent e) {
		spareContext.makeCurrent();
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.keyTyped(e);
			spareContext.release();
		}
	}

	public void mouseClicked(MouseEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.mouseClicked(e);
			spareContext.release();
		}
	}

	public void mouseEntered(MouseEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.mouseEntered(e);
			spareContext.release();
		}
	}

	public void mouseExited(MouseEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.mouseExited(e);
			spareContext.release();
		}
	}

	public void mousePressed(MouseEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.mousePressed(e);
			spareContext.release();
		}
	}

	public void mouseReleased(MouseEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.mouseReleased(e);
			spareContext.release();
		}
	}

	public void mouseDragged(MouseEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.mouseDragged(e);
			spareContext.release();
		}
	}

	public void mouseMoved(MouseEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.mouseMoved(e);
			spareContext.release();
		}
	}
	
	public void display(GLAutoDrawable drawable) {
		final View view = getView();
		if(view != null)
			view.render(null);
	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
	}

	public void init(GLAutoDrawable drawable) {
		grabNewContext(drawable);
		
	    canvas.addKeyListener(this);
	    canvas.addMouseListener(this);
	    canvas.addMouseMotionListener(this);

	    if(stateStack.isEmpty()) {
			MainMenuView mmv = new MainMenuView();
			MainMenuController mmc = new MainMenuController(this, mmv);
			pushState(mmv, mmc);
	    }
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		grabNewContext(drawable);
		
		// prevent division by zero
		if(height <= 0)
			height = 1;
		
		GLU.getCurrentGL().glViewport(0, 0, width, height);
	}

	private View getView() {
		return stateStack.isEmpty() ? null : stateStack.peek().getFirst();
	}

	private Controller getController() {
		return stateStack.isEmpty() ? null : stateStack.peek().getSecond();
	}
	
	public void pushState(View newView, Controller newController) {
		pushState(new Pair<View, Controller>(newView, newController));
	}
	
	public void pushState(Pair<View, Controller> newState) {
		View view = getView();
		View newView = newState.getFirst();
		
		if(view != null)
			view.changeFrom();
		if(newView != null) 
			newView.changeTo();
		
		stateStack.push(newState);
	}
	
	public void popState() {
		stateStack.pop();
	}

	public void start() {
		animator.start();
		canvas.requestFocus();
	}
	
	public void stop(int exitCode) {
		animator.stop();
		if(spareContext != null) {
			if(GLContext.getCurrent() == spareContext)
				spareContext.release();
			spareContext.destroy();	
		}
		System.exit(exitCode);
	}
}
