package rpgeeze;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.util.Stack;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLContext;
import javax.media.opengl.GLEventListener;

import com.sun.opengl.util.FPSAnimator;

import rpgeeze.gl.GL;
import rpgeeze.controller.Controller;
import rpgeeze.controller.MainMenuController;
import rpgeeze.util.Pair;
import rpgeeze.view.MainMenuView;
import rpgeeze.view.View;

/**
 * Primary implementer of the event-listening interfaces. This class does some basic work but for the most part delegates to the View and Controller that represent its current state.
 */

public class GameManager implements GLEventListener, KeyListener, MouseListener, MouseMotionListener, WindowListener, WindowFocusListener {
	private Stack<Pair<View<?>, Controller<? extends View<?>>>> stateStack = new Stack<Pair<View<?>, Controller<? extends View<?>>>>();
	private GLContext spareContext;
	private GLCanvas canvas;
	private FPSAnimator animator;
	private Frame frame;

	private boolean initialized = false;

	private void grabNewContext(GLAutoDrawable drawable) {
		if(spareContext != null)
			spareContext.destroy();
		spareContext = drawable.createContext(GLContext.getCurrent());		
	}
	
	private void releaseContext() {
		if(spareContext == GLContext.getCurrent())
			spareContext.release();
	}
	
	private void lockContext() {
		spareContext.makeCurrent();
	}

	/**
	 * Creates a new GameManager.
	 * 
	 */
	public GameManager(Frame frame) {
		this.frame = frame;
		frame.setBackground(Color.BLACK);
		frame.enableInputMethods(false);
		frame.setFocusTraversalKeysEnabled(false);
		canvas = new GLCanvas();
		canvas.setFocusTraversalKeysEnabled(false);
		frame.add(canvas);
		animator = new FPSAnimator(canvas, RunGame.GOAL_FPS);
	}

	/**
	 * Required by the KeyListener interface. Delegates to the Controller, if there is one. 
	 */
	public void keyPressed(KeyEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.keyPressed(e);
			releaseContext();
		}
	}

	/**
	 * Required by the KeyListener interface. Delegates to the Controller, if there is one. 
	 */
	public void keyReleased(KeyEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.keyReleased(e);
			releaseContext();
		}
	}

	/**
	 * Required by the KeyListener interface. Delegates to the Controller, if there is one. 
	 */
	public void keyTyped(KeyEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.keyTyped(e);
			releaseContext();
		}
	}

	/**
	 * Required by the KeyListener interface. Delegates to the Controller, if there is one. 
	 */
	public void mouseClicked(MouseEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.mouseClicked(e);
			releaseContext();
		}
	}

	/**
	 * Required by the MouseListener interface. Delegates to the Controller, if there is one. 
	 */
	public void mouseEntered(MouseEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.mouseEntered(e);
			releaseContext();
		}
	}

	/**
	 * Required by the MouseListener interface. Delegates to the Controller, if there is one. 
	 */
	public void mouseExited(MouseEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.mouseExited(e);
			releaseContext();
		}
	}

	/**
	 * Required by the MouseListener interface. Delegates to the Controller, if there is one. 
	 */
	public void mousePressed(MouseEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.mousePressed(e);
			releaseContext();
		}
	}

	/**
	 * Required by the MouseListener interface. Delegates to the Controller, if there is one. 
	 */
	public void mouseReleased(MouseEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.mouseReleased(e);
			releaseContext();
		}
	}

	/**
	 * Required by the MouseMotionListener interface. Delegates to the Controller, if there is one. 
	 */
	public void mouseDragged(MouseEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.mouseDragged(e);
			releaseContext();
		}
	}

	/**
	 * Required by the MouseMotionListener interface. Delegates to the Controller, if there is one. 
	 */
	public void mouseMoved(MouseEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.mouseMoved(e);
			releaseContext();
		}
	}

	/**
	 * Required by the WindowListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowActivated(WindowEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.windowActivated(e);
			releaseContext();
		}
	}

	/**
	 * Required by the WindowListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowClosed(WindowEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.windowClosed(e);
			releaseContext();
		}
	}

	/**
	 * Required by the WindowListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowClosing(WindowEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.windowClosing(e);
			releaseContext();
		}
	}

	/**
	 * Required by the WindowListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowDeactivated(WindowEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.windowDeactivated(e);
			releaseContext();
		}
	}

	/**
	 * Required by the WindowListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowDeiconified(WindowEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.windowDeiconified(e);
			releaseContext();
		}
	}

	/**
	 * Required by the WindowListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowIconified(WindowEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.windowIconified(e);
			releaseContext();
		}
	}

	/**
	 * Required by the WindowListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowOpened(WindowEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.windowOpened(e);
			releaseContext();
		}
	}

	/**
	 * Required by the WindowFocusListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowGainedFocus(WindowEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.windowGainedFocus(e);
			releaseContext();
		}
	}

	/**
	 * Required by the WindowFocusListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowLostFocus(WindowEvent e) {
		final Controller<? extends View<?>> controller = getController();
		if(controller != null) {
			lockContext();
			controller.windowLostFocus(e);
			releaseContext();
		}
	}

	/**
	 * Called whenever the canvas needs to be repainted. Delegates to the View, if there is one, for drawing. Informs the Controller, if there is one, via its idleCycle() method. 
	 */
	public void display(GLAutoDrawable drawable) {
		View<?> view = getView();
		if(view != null)
			view.render(null);
	}

	/**
	 * Required by the GLEventListener interface. Doesn't actually do anything.
	 */
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
	}

	/**
	 * Called to do any initialization work required by a GLAutoDrawable used for rendering. Reserves a spare GLContext for the event thread. The first time it's called it also pushes the main menu onto the state stack.
	 */
	public void init(GLAutoDrawable drawable) {
		grabNewContext(drawable);

		if(!initialized) {
			initialized = true;

			MainMenuView mmv = new MainMenuView();
			MainMenuController mmc = new MainMenuController(this, mmv);
			pushState(mmv, mmc);
		}
	}

	/**
	 * Called whenever the backing GLAutoDrawable is reshaped. Changes the OpenGL viewport to account for the new size and reserves a spare GLContext for the event thread. 
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		if(height <= 0) // prevent division by zero
			height = 1;
		GL.getCurrent().glViewport(0, 0, width, height);

		grabNewContext(drawable);
	}

	protected View<?> getView() {
		return stateStack.isEmpty() ? null : stateStack.peek().getFirst();
	}

	protected Controller<? extends View<?>> getController() {
		return stateStack.isEmpty() ? null : stateStack.peek().getSecond();
	}

	protected Pair<View<?>, Controller<? extends View<?>>> getState() {
		return stateStack.isEmpty() ? new Pair<View<?>, Controller<? extends View<?>>>(null, null) : stateStack.peek();
	}

	/**
	 * Creates a state from the given View and Controller and adds it to the top of the state stack.
	 * 
	 * @param newView the new state's View
	 * @param newController the new state's Controller
	 */
	public void pushState(View<?> newView, Controller<? extends View<?>> newController) {
		pushState(new Pair<View<?>, Controller<? extends View<?>>>(newView, newController));
	}

	/**
	 * Adds a new state to the top of the state stack. 
	 * 
	 * @param newState the new state
	 */
	public void pushState(Pair<View<?>, Controller<? extends View<?>>> newState) {
		Pair<View<?>, Controller<? extends View<?>>> state = getState();
		View<?> view = state.getFirst();
		
		View<?> newView = newState.getFirst();

		if(view != null)
			view.changeFrom();
		
		if(newView != null)
			newView.changeTo();

		stateStack.push(newState);
	}

	/**
	 * Removes the top state from the state stack. 
	 */
	public void popState() {
		Pair<View<?>, Controller<? extends View<?>>> state = getState();
		View<?> view = state.getFirst();
		
		stateStack.pop();
		
		Pair<View<?>, Controller<? extends View<?>>> newState = getState();
		View<?> newView = newState.getFirst();

		if(view != null)
			view.changeFrom();
		
		if(newView != null)
			newView.changeTo();
	}

	/**
	 * Registers the proper event listeners and displays the game Frame.
	 */
	public void start() {
		frame.addWindowListener(this);
		frame.addWindowFocusListener(this);
		canvas.addKeyListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);
		canvas.addGLEventListener(this);
		animator.start();
		canvas.requestFocus();
		frame.setVisible(true);
	}

	/**
	 * Stops the backing animator, destroys the event thread's GLContext, and disposes of the Frame that was used for the game. You will probably want to exit the application completely after calling this method, but this isn't done automatically just in case you don't.
	 */
	public void stop() {
		animator.stop();
		if(spareContext != null) {
			if(GLContext.getCurrent() == spareContext)
				releaseContext();
			spareContext.destroy();	
		}
		frame.dispose();
	}
}
