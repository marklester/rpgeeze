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
import javax.media.opengl.glu.GLU;

import com.sun.opengl.util.FPSAnimator;

import rpgeeze.controller.Controller;
import rpgeeze.controller.MainMenuController;
import rpgeeze.util.Pair;
import rpgeeze.view.MainMenuView;
import rpgeeze.view.View;

/**
 * Primary implementer of the event-listening interfaces. This class does some basic work but for the most part delegates to the View and Controller that represent its current state.
 */

public class GameManager implements GLEventListener, KeyListener, MouseListener, MouseMotionListener, WindowListener, WindowFocusListener {
	private Stack<Pair<View, Controller>> stateStack = new Stack<Pair<View, Controller>>();
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

	/**
	 * Creates a new GameManager.
	 * 
	 * @param frame the frame which will be used to display the game
	 */
	public GameManager(Frame frame) {
		this.frame = frame;
		frame.setBackground(Color.BLACK);
		frame.enableInputMethods(false);
		canvas = new GLCanvas();
		frame.add(canvas);
		animator = new FPSAnimator(canvas, RunGame.GOAL_FPS);
	}

	/**
	 * Required by the KeyListener interface. Delegates to the Controller, if there is one. 
	 */
	public void keyPressed(KeyEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.keyPressed(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the KeyListener interface. Delegates to the Controller, if there is one. 
	 */
	public void keyReleased(KeyEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.keyReleased(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the KeyListener interface. Delegates to the Controller, if there is one. 
	 */
	public void keyTyped(KeyEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.keyTyped(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the KeyListener interface. Delegates to the Controller, if there is one. 
	 */
	public void mouseClicked(MouseEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.mouseClicked(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the MouseListener interface. Delegates to the Controller, if there is one. 
	 */
	public void mouseEntered(MouseEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.mouseEntered(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the MouseListener interface. Delegates to the Controller, if there is one. 
	 */
	public void mouseExited(MouseEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.mouseExited(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the MouseListener interface. Delegates to the Controller, if there is one. 
	 */
	public void mousePressed(MouseEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.mousePressed(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the MouseListener interface. Delegates to the Controller, if there is one. 
	 */
	public void mouseReleased(MouseEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.mouseReleased(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the MouseMotionListener interface. Delegates to the Controller, if there is one. 
	 */
	public void mouseDragged(MouseEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.mouseDragged(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the MouseMotionListener interface. Delegates to the Controller, if there is one. 
	 */
	public void mouseMoved(MouseEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.mouseMoved(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the WindowListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowActivated(WindowEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.windowActivated(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the WindowListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowClosed(WindowEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.windowClosed(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the WindowListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowClosing(WindowEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.windowClosing(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the WindowListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowDeactivated(WindowEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.windowDeactivated(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the WindowListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowDeiconified(WindowEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.windowDeiconified(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the WindowListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowIconified(WindowEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.windowIconified(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the WindowListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowOpened(WindowEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.windowOpened(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the WindowFocusListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowGainedFocus(WindowEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.windowGainedFocus(e);
			spareContext.release();
		}
	}

	/**
	 * Required by the WindowFocusListener interface. Delegates to the Controller, if there is one. 
	 */
	public void windowLostFocus(WindowEvent e) {
		final Controller controller = getController();
		if(controller != null) {
			spareContext.makeCurrent();
			controller.windowLostFocus(e);
			spareContext.release();
		}
	}

	/**
	 * Called whenever the canvas needs to be repainted. Delegates to the View, if there is one, for drawing. Informs the Controller, if there is one, via its idleCycle() method. 
	 */
	public void display(GLAutoDrawable drawable) {
		Pair<View, Controller> state = getState();
		Controller controller = state.getSecond();
		View view = state.getFirst();
		if(controller != null)
			controller.idleCycle();
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
		GLU.getCurrentGL().glViewport(0, 0, width, height);

		grabNewContext(drawable);
	}

	private View getView() {
		return stateStack.isEmpty() ? null : stateStack.peek().getFirst();
	}

	private Controller getController() {
		return stateStack.isEmpty() ? null : stateStack.peek().getSecond();
	}

	private Pair<View, Controller> getState() {
		return stateStack.isEmpty() ? new Pair<View, Controller>(null, null) : stateStack.peek();
	}

	/**
	 * Creates a state from the given View and Controller and adds it to the top of the state stack.
	 * 
	 * @param newView the new state's View
	 * @param newController the new state's Controller
	 */
	public void pushState(View newView, Controller newController) {
		pushState(new Pair<View, Controller>(newView, newController));
	}

	/**
	 * Adds a new state to the top of the state stack. 
	 * 
	 * @param newState the new state
	 */
	public void pushState(Pair<View, Controller> newState) {
		Pair<View, Controller> state = getState();
		Controller controller = state.getSecond();
		View view = state.getFirst();
		
		Controller newController = newState.getSecond();
		View newView = newState.getFirst();

		if(controller != null)
			controller.changeFrom();
		if(view != null)
			view.changeFrom();
		
		if(newController != null)
			newController.changeTo();
		if(newView != null)
			newView.changeTo();

		stateStack.push(newState);
	}

	/**
	 * Removes the top state from the state stack. 
	 */
	public void popState() {
		Pair<View, Controller> state = getState();
		Controller controller = state.getSecond();
		View view = state.getFirst();
		
		stateStack.pop();
		
		Pair<View, Controller> newState = getState();
		Controller newController = newState.getSecond();
		View newView = newState.getFirst();

		if(controller != null)
			controller.changeFrom();
		if(view != null)
			view.changeFrom();
		
		if(newController != null)
			newController.changeTo();
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
				spareContext.release();
			spareContext.destroy();	
		}
		frame.dispose();
	}
}
