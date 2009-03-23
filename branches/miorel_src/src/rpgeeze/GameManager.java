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
import rpgeeze.log.LogManager;
import rpgeeze.model.Location;
import rpgeeze.model.Map;
import rpgeeze.model.Model;
import rpgeeze.model.ModelThread;
import rpgeeze.model.Tile;
import rpgeeze.model.entity.Entity;
import rpgeeze.model.entity.Occupation;
import rpgeeze.model.entity.PC;
import rpgeeze.model.decal.*;
import rpgeeze.model.item.*;
import rpgeeze.model.terrain.GrassTerrain;
import rpgeeze.util.DelegatingEventAdapter;
import rpgeeze.util.EventAdapter;
import rpgeeze.util.Pair;
import rpgeeze.util.SimpleMovingAverageTimer;
import rpgeeze.util.Timer;
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

	private Frame frame;
	private GLCanvas canvas;
	private FPSAnimator animator;
	private GLContext spareContext;
	private Timer fpsTimer;

	private Model model;
	private ModelThread thread;
	
	/**
	 * Constructs a game manager that will display in the specified
	 * <code>Frame</code>.
	 * 
	 * @param frame
	 *            the <code>Frame</code> that will be used for displaying the
	 *            game
	 */
	public GameManager(Frame frame) {
		this.frame = frame;
		stateStack = new Stack<Pair<View<?>, Controller<? extends View<?>>>>();
		frame.setBackground(Color.BLACK);
		frame.enableInputMethods(false);
		frame.setFocusTraversalKeysEnabled(false);
		canvas = new GLCanvas();
		canvas.setFocusTraversalKeysEnabled(false);
		replaceContext(canvas);
		frame.add(canvas);
		fpsTimer = new SimpleMovingAverageTimer();
		animator = new FPSAnimator(canvas, GameProperties.getInstance().getGoalFPS());
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
		fpsTimer.mark();
		if(view != null)
			view.render(drawable.getGL(), null);
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
		synchronized(stateStack) {
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
	public void pushState(View<?> newView, Controller<? extends View<?>> newController) {
		pushState(new Pair<View<?>, Controller<? extends View<?>>>(newView, newController));
	}

	/**
	 * Adds a new state to the top of the state stack.
	 * 
	 * @param newState
	 *            the new state
	 */
	public void pushState(Pair<View<?>, Controller<? extends View<?>>> newState) {
		synchronized(stateStack) {
			LogManager.getInstance().log("State stack is " + stateStack, "MANAGER");
			LogManager.getInstance().log("Pushing state " + newState, "MANAGER");
			
			Pair<View<?>, Controller<? extends View<?>>> oldState = null;
			if(!stateStack.isEmpty())
				oldState = stateStack.peek();
			if(oldState != null) {
				View<?> oldView = oldState.getFirst();	
				if(oldView != null)
					oldView.changeFrom();
			}
			
			View<?> newView = newState.getFirst();
			if(newView != null)
				newView.changeTo();

			stateStack.push(newState);
			
			LogManager.getInstance().log("State stack is " + stateStack, "MANAGER");
		}
	}

	
	public void replaceState(View<?> newView, Controller<? extends View<?>> newController) {
		replaceState(new Pair<View<?>, Controller<? extends View<?>>>(newView, newController));
	}
	
	public void replaceState(Pair<View<?>, Controller<? extends View<?>>> newState) {
		synchronized(stateStack) {
			LogManager.getInstance().log("State stack is " + stateStack, "MANAGER");
			LogManager.getInstance().log("Replacing top state with " + newState, "MANAGER");
			
			Pair<View<?>, Controller<? extends View<?>>> oldState = null;
			if(!stateStack.isEmpty())
				oldState = stateStack.pop();
			if(oldState != null) {
				View<?> oldView = oldState.getFirst();	
				if(oldView != null)
					oldView.changeFrom();
			}
			
			View<?> newView = newState.getFirst();
			if(newView != null)
				newView.changeTo();

			stateStack.push(newState);
			
			LogManager.getInstance().log("State stack is " + stateStack, "MANAGER");
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
		synchronized(stateStack) {
			LogManager.getInstance().log("State stack is " + stateStack, "MANAGER");
			LogManager.getInstance().log("Popping state", "MANAGER");
			
			if(!stateStack.isEmpty()) {
				ret = stateStack.pop();
				View<?> oldView = ret.getFirst();
				if(oldView != null)
					oldView.changeFrom();
				
				Pair<View<?>, Controller<? extends View<?>>> newState = null;
				if(!stateStack.isEmpty())
					newState = stateStack.peek();
				if(newState != null) {
					View<?> newView = newState.getFirst();	
					if(newView != null)
						newView.changeTo();
				}
			}
			
			LogManager.getInstance().log("State stack is " + stateStack, "MANAGER");
		}
		return ret;
	}

	/**
	 * Starts the managed game. Signs up as an event listener, starts an
	 * animator and a FPS timer, and displays the game <code>Frame</code>.
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
		fpsTimer.start();
		animator.start();
		canvas.requestFocus();
		frame.setVisible(true);
	}

	/**
	 * Stops the managed game. Stops the backing animator and FPS timer,
	 * destroys the spare OpenGL context, and disposes of the <code>Frame</code>
	 * that was used for the game.
	 * 
	 */
	public void stop() {
		if(model != null)
			model.stop();
		animator.stop();
		fpsTimer.stop();
		frame.setVisible(false);
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(null);
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
	
	/**
	 * Returns the current FPS.
	 * 
	 * @return the current FPS
	 */
	public double getFPS() {
		return fpsTimer.marksPerSecond();
	}
	
	public void createModel(Occupation occupation) {
		LogManager.getInstance().log("Creating model using " + occupation.getName() + " occupation", "MANAGER");
		
		// this needs to be nice
		Tile[][] matrix = new Tile[10][10];
		for(int i = 0; i != 10; ++i) {
			for(int j = 0; j != 10; ++j) {
				matrix[i][j] = new Tile(GrassTerrain.getInstance(), new Location(j, i));
			}
		}
		Map map = new Map(matrix);
		PC avatar = new PC(occupation, map);
		Tile pos = matrix[8][6];
		avatar.setTile(pos);
		pos.setEntity(avatar);
		
		matrix[2][2].setItem(new Arrows());
		matrix[3][2].setItem(new Boots());
		matrix[3][3].setItem(new Boulder());
		matrix[2][3].setItem(new Crossbow());
		matrix[5][5].setItem(new HealthPack());
		matrix[2][6].setItem(new HealthPack());
		matrix[1][6].setItem(new Mana());
		matrix[1][0].setItem(new Portal());
		matrix[1][8].setItem(new RedArmor());
		matrix[1][9].setItem(new Shield());
		matrix[2][9].setItem(new Sword());
		matrix[2][9].setDecal(new GoldStar());
		matrix[5][7].setDecal(new Fire());
		matrix[5][8].setDecal(new SkullAndCrossbones());
		matrix[5][9].setDecal(new RedCross());
		
		model = new Model(map, avatar);
		// end of stuff that needs to be nice
		
		thread = new ModelThread(model);
		thread.start();
	}
	
	public Model getModel() {
		return model;
	}
}
