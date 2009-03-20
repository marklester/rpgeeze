package rpgeeze;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
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
 * Primary event listener and access point for game operations. Like a true
 * manager, does little work itself and instead functions by delegating tasks to
 * subordinates, namely the <code>View</code> and <code>Controller</code> that
 * represent its current state.
 * 
 */
public class GameManager extends DelegatingEventAdapter
		implements GLEventListener {
	private Stack<Pair<View<?>, Controller<? extends View<?>>>> stateStack;

	private boolean initialized = false;

	private GameProperties properties;
	private Frame frame;
	private GLCanvas canvas;
	private FPSAnimator animator;
	private GLContext spareContext;

	/**
	 * Constructs a game manager that will display in the specified
	 * <code>Frame</code> and will be initialized with the specified game
	 * properties.
	 * 
	 * @param frame
	 *            the <code>Frame</code> that will be used for displaying the
	 *            game
	 * @param properties
	 *            the initial game properties
	 */
	public GameManager(Frame frame, GameProperties properties) {
		this.frame = frame;
		this.properties = properties;
		stateStack = new Stack<Pair<View<?>, Controller<? extends View<?>>>>();
		frame.setBackground(Color.BLACK);
		frame.enableInputMethods(false);
		frame.setFocusTraversalKeysEnabled(false);
		canvas = new GLCanvas();
		canvas.setFocusTraversalKeysEnabled(false);
		replaceContext(canvas);
		frame.add(canvas);
		animator = new FPSAnimator(canvas, properties.getGoalFPS());
	}

	/**
	 * Retrieves the game properties that were used to initialize this manager.
	 * 
	 * @return the game properties used to initialize this manager
	 */
	public GameProperties getProperties() {
		return properties;
	}

	/**
	 * Displays the game. This method is called by the animator whenever the
	 * canvas needs to be repainted. Delegates display to the <code>View</code>,
	 * if there is one.
	 * 
	 * @param drawable
	 *            the OpenGL canvas
	 */
	public void display(GLAutoDrawable drawable) {
		View<?> view = getState().getFirst();
		if(view != null)
			view.render(null);
	}

	/**
	 * Reacts to a change in display. This method is required by the
	 * <code>GLEventListener</code> interface. Its implementation currently
	 * doesn't do anything.
	 * 
	 * @param drawable
	 *            the canvas
	 * @param modeChanged
	 *            whether or not the display mode was changed
	 * @param deviceChanged
	 *            whether or not the display device was changed
	 */
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
	}

	/**
	 * Initializes for rendering on the specified canvas. Reserves a spare
	 * <code>GLContext</code> for the event thread. The first time it's called
	 * it also pushes the main menu onto the state stack.
	 * 
	 * @param drawable
	 *            the canvas to initialize
	 */
	public void init(GLAutoDrawable drawable) {
		replaceContext(drawable);

		if(!initialized) {
			initialized = true;

			MainMenuView mmv = new MainMenuView(this);
			MainMenuController mmc = new MainMenuController(this, mmv);
			pushState(mmv, mmc);
		}
	}

	/**
	 * Reacts to a change in the shape of the canvas. Reserves a spare
	 * <code>GLContext</code> for the event thread.
	 * 
	 * @param drawable
	 *            the reshaped canvas
	 * @param x
	 *            the new abscissa of the bottom left corner of the canvas
	 * @param y
	 *            the new ordinate of the bottom left corner of the canvas
	 * @param width
	 *            the new width of the canvas
	 * @param height
	 *            the new height of the canvas
	 */
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		replaceContext(drawable);
	}

	/**
	 * Retrieves the current state of this game manager. Never returns
	 * <code>null</code>: if there is no <code>View</code> /
	 * <code>Controller</code> pair, a pair of <code>null</code>s is returned
	 * instead.
	 * 
	 * @return the current state of this game manager
	 */
	protected Pair<View<?>, Controller<? extends View<?>>> getState() {
		
		synchronized (stateStack) {
			return stateStack.isEmpty()
					? new Pair<View<?>, Controller<? extends View<?>>>(null, null)
					: stateStack.peek();
		}
	}

	/**
	 * Creates a state from the given <code>View</code> and
	 * <code>Controller</code> and adds it to the top of the state stack.
	 * 
	 * @param newView
	 *            the new state's <code>View</code>
	 * @param newController
	 *            the new state's <code>Controller</code>
	 */
	public void pushState(View<?> newView,
			Controller<? extends View<?>> newController) {
		pushState(new Pair<View<?>, Controller<? extends View<?>>>(newView,
				newController));
	}

	/**
	 * Adds a new state to the top of the state stack.
	 * 
	 * @param newState
	 *            the new state
	 */
	public void pushState(Pair<View<?>, Controller<? extends View<?>>> newState) {
		synchronized (stateStack) {
			Pair<View<?>, Controller<? extends View<?>>> oldState = popState();

			View<?> newView = newState.getFirst();
			if(newView != null)
				newView.changeTo();

			if(oldState != null)
				stateStack.push(oldState);
			stateStack.push(newState);
		}
	}

	/**
	 * Removes the top state from the state stack. If the state stack is empty,
	 * does nothing.
	 * 
	 * @return the state which was removed
	 */
	public Pair<View<?>, Controller<? extends View<?>>> popState() {
		Pair<View<?>, Controller<? extends View<?>>> ret = null;
		synchronized (stateStack) {
			if(!stateStack.isEmpty()) {
				ret = stateStack.pop();
				View<?> view = ret.getFirst();
				if(view != null)
					view.changeFrom();
			}
		}
		return ret;
	}

	/**
	 * Signs up as an event listener, starts an animator, and displays the game
	 * <code>Frame</code>.
	 * 
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
	 * Stops the backing animator, destroys the spare OpenGL context, and
	 * disposes of the <code>Frame</code> that was used for the game.
	 * 
	 */
	public void stop() {
		animator.stop();
		frame.setVisible(false);
		GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice().setFullScreenWindow(null);
		frame.dispose();
		replaceContext(null);
	}

	/**
	 * Gets the current controller if there is one, or a no-action
	 * <code>EventAdapter</code> otherwise.
	 * 
	 */
	protected EventAdapter getDelegate() {
		EventAdapter delegate = getState().getSecond();
		if(delegate == null)
			return new EventAdapter() {};
		return delegate;
	}

	/**
	 * Reserves an OpenGL context.
	 * 
	 */
	protected void preEventDelegate() {
		spareContext.makeCurrent();
	}

	/**
	 * Releases the previously-reserved OpenGL context.
	 * 
	 */
	protected void postEventDelegate() {
		if(spareContext == GLContext.getCurrent())
			spareContext.release();
	}

	/**
	 * Replaces the spare OpenGL context maintained by this
	 * <code>GameManager</code>.
	 * 
	 * @param drawable
	 *            the <code>GLAutoDrawable</code> to ask for a new context
	 */
	protected void replaceContext(GLAutoDrawable drawable) {
		GLContext current = GLContext.getCurrent();
		if(spareContext != null) {
			if(spareContext == current)
				spareContext.release();
			spareContext.destroy();
		}
		if(drawable != null)
			spareContext = drawable.createContext(current);
		else
			spareContext = null;
	}
}
