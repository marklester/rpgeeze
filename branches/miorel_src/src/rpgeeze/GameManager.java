package rpgeeze;

import java.awt.Color;
import java.awt.Frame;
import java.util.Stack;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLContext;
import javax.media.opengl.GLEventListener;

import com.sun.opengl.util.FPSAnimator;

import rpgeeze.controller.Controller;
import rpgeeze.controller.MainMenuController;
import rpgeeze.util.DelegatingEventAdapter;
import rpgeeze.util.EventAdapter;
import rpgeeze.util.Pair;
import rpgeeze.view.MainMenuView;
import rpgeeze.view.View;

/**
 * Primary implementer of the event-listening interfaces. This class does some basic work but for the most part delegates to the View and Controller that represent its current state.
 */

public class GameManager extends DelegatingEventAdapter implements GLEventListener {
	private Stack<Pair<View<?>, Controller<? extends View<?>>>> stateStack = new Stack<Pair<View<?>, Controller<? extends View<?>>>>();
	private GLContext spareContext;
	private GLCanvas canvas;
	private FPSAnimator animator;
	private Frame frame;

	private boolean initialized = false;

	protected void replaceContext(GLAutoDrawable drawable) {
		GLContext current = GLContext.getCurrent();
		if(spareContext == current && spareContext != null)
			spareContext.release();
		spareContext.destroy();
		if(drawable != null)
			spareContext = drawable.createContext(current);
		else
			spareContext = null;
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
		spareContext = canvas.createContext(null);
		frame.add(canvas);
		animator = new FPSAnimator(canvas, RunGame.GOAL_FPS);
	}

	/**
	 * Called whenever the canvas needs to be repainted. Delegates to the View, if there is one, for drawing. 
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
		replaceContext(drawable);

		if(!initialized) {
			initialized = true;

			MainMenuView mmv = new MainMenuView();
			MainMenuController mmc = new MainMenuController(this, mmv);
			pushState(mmv, mmc);
		}
	}

	/**
	 * Called whenever the backing GLAutoDrawable is reshaped. Reserves a spare GLContext for the event thread. 
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		replaceContext(drawable);
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
		canvas.addMouseWheelListener(this);
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
		frame.dispose();
		replaceContext(null);
	}

	protected EventAdapter getDelegate() {
		EventAdapter delegate = getController();
		if(delegate == null)
			return new EventAdapter() {};
		return delegate;
	}

	protected void preEventDelegate() {
		spareContext.makeCurrent();
	}

	protected void postEventDelegate() {
		if(spareContext == GLContext.getCurrent())
			spareContext.release();
	}
}
