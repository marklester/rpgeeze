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
 * Primary implementer of the event-listening interfaces. This class does some basic work but for the most part delegates to the View and Controller that represent its current state.
 */

public class GameManager extends DelegatingEventAdapter implements GLEventListener {
	private Stack<Pair<View<?>, Controller<? extends View<?>>>> stateStack = new Stack<Pair<View<?>, Controller<? extends View<?>>>>();

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
	 * @param frame	 the <code>Frame</code> that will be used for displaying the game 
	 * @param properties the initial game properties
	 */
	public GameManager(Frame frame, GameProperties properties) {
		this.frame = frame;
		this.properties = properties;
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
	 * Displays the game. Is called by the animator whenever the canvas needs to be repainted. Delegates display to the <code>View</code>, if there is one.
	 * 
	 * @param drawable the OpenGL canvas
	 */
	public void display(GLAutoDrawable drawable) {
		View<?> view = getView();
		if(view != null)
			view.render(null);
	}

	/**
	 * Required by the GLEventListener interface. Doesn't actually do anything.
	 * 
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

			MainMenuView mmv = new MainMenuView(this);
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
		synchronized(stateStack) {
			return stateStack.isEmpty() ? null : stateStack.peek().getFirst();
		}
	}

	protected Controller<? extends View<?>> getController() {
		synchronized(stateStack) {
			return stateStack.isEmpty() ? null : stateStack.peek().getSecond();
		}
	}

	protected Pair<View<?>, Controller<? extends View<?>>> getState() {
		synchronized(stateStack) {
			return stateStack.isEmpty() ? new Pair<View<?>, Controller<? extends View<?>>>(null, null) : stateStack.peek();
		}
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
		synchronized(stateStack) {
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
	 * Removes the top state from the state stack. 
	 */
	public Pair<View<?>, Controller<? extends View<?>>> popState() {
		Pair<View<?>, Controller<? extends View<?>>> ret = null;
		synchronized(stateStack) {
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
	 * Registers the proper event listeners, starts an animator, and displays the game <code>Frame</code>.
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
	 * Stops the backing animator, destroys the spare OpenGL context, and disposes of the <code>Frame</code> that was used for the game.
	 */
	public void stop() {
		animator.stop();
		frame.setVisible(false);
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
		frame.dispose();
		replaceContext(null);
	}

	/**
	 * Gets the current controller if there is one, or a no-action <code>EventAdapter</code> otherwise. 
	 * 
	 */
	protected EventAdapter getDelegate() {
		EventAdapter delegate = getController();
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
	 * Replaces the spare OpenGL context maintained by this <code>GameManager</code>.
	 * 
	 * @param drawable the <code>GLAutoDrawable</code> to ask for a new context
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
