package rpgeeze.view;

import java.awt.Point;
import java.nio.IntBuffer;
import java.util.HashSet;

import rpgeeze.GameManager;
import rpgeeze.gl.GL;
import rpgeeze.log.LogManager;
import rpgeeze.dp.Iterator;
import rpgeeze.dp.Observer;

import com.sun.opengl.util.BufferUtil;

/**
 * Superclass for the various screens that the game will have to display. The
 * most important method is <code>render()</code>, which will be called whenever
 * the screen must be painted. The <code>changeFrom()</code> and
 * <code>changeTo()</code> are invoked whenever the game state changes from and
 * to this view, respectively. Each view additionally has its own sub-states (it
 * will at the very least have a normal and a hidden state). Changes among these
 * can be monitored by registering an observer.
 *
 * @param <T> the type of state that describes this view (should probably be
 * an enum)
 */
public abstract class View<T extends View.State> {
	private HashSet<Observer<View<?>>> observers = new HashSet<Observer<View<?>>>();
	private T state;
	private GameManager manager;

	/**
	 * Label for types which represent a state.
	 *
	 */
	public interface State {
	}
	
	/**
	 * Constructs a view that answers to the specified game manager.
	 * 
	 * @param manager the game manager
	 */
	public View(GameManager manager) {
		this.manager = manager;
	}
	
	/**
	 * Retrieves the game manager in charge of this view.
	 * 
	 * @return the game manager
	 */
	protected GameManager getManager() {
		return manager;
	}
	
	/**
	 * OpenGL "picking" with a (small) default buffer size. An error in this
	 * method most likely means that a large buffer size is necessary.
	 * 
	 * @param pickPoint point around which to set up the picking matrix
	 * @return an iterator over the name constants that registered as hits at the specified point
	 */
	public Iterator<Integer> pick(Point pickPoint) {
		return pick(pickPoint, 16);
	}

	/**
	 * Friendly wrapper for OpenGL "picking" of rendered elements. 
	 * 
	 * @param pickPoint point around which to set up the picking matrix
	 * @param bufSize size to use for the selection buffer
	 * @return an iterator over the name constants that registered as hits at the specified point
	 */
	public Iterator<Integer> pick(Point pickPoint, int bufSize) {
		GL gl = new GL();
		
		final int[] selectBuf = new int[bufSize];
		IntBuffer selectBuffer = BufferUtil.newIntBuffer(bufSize);

		gl.glSelectBuffer(bufSize, selectBuffer);
		gl.glRenderMode(GL.GL_SELECT);

		gl.glInitNames();
		gl.glPushName(-1);

		render(pickPoint);

		final int hits = gl.glRenderMode(GL.GL_RENDER);
		
		selectBuffer.get(selectBuf);
		
		return new Iterator<Integer>() {
			private int hit;
			private int name;
			private int ptr;
			
			public void advance() {
				++name;
				if(name == selectBuf[ptr]) {
					++hit;
					ptr += 3 + selectBuf[ptr];
					name = 0;
				}
			}

			public Integer current() {
				return selectBuf[ptr + 3 + name];
			}

			public boolean isDone() {
				return hit == hits;
			}

			public void reset() {
				hit = 0;
				name = 0;
				ptr = 0;
			}
		};
	}

	/**
	 * Renders this view in the current OpenGL context. If the argument is not
	 * null, sets up a pick matrix around the specified location, for use in
	 * selection mode.  
	 * 
	 * @param point coordinates to pick around
	 */
	public abstract void render(Point point);

	/**
	 * Sets up a depth buffer, enables blending, sets up a picking matrix and a
	 * frustum view, then loads the identity modelview matrix.
	 * 
	 * @param gl OpenGL interface to use
	 * @param point coordinates to pick around
	 */
	protected void setup(GL gl, Point point) {
		gl.glShadeModel(GL.GL_SMOOTH);

		// depth buffer
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);

		// blending
		gl.glEnable(GL.GL_BLEND);

		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		if(point != null)
			gl.pickMatrix(point.getX(), gl.getViewportHeight() - point.getY());

		double aspectRatio = gl.getViewportAspectRatio();
		gl.glFrustum(-aspectRatio, aspectRatio, -1, 1, 1, 128);
		gl.glMatrixMode(GL.GL_MODELVIEW);

		gl.glLoadIdentity();
	}
	
	/**
	 * Prepares this view for no longer being displayed. This method is invoked
	 * by the game manager whenever it changes away from this view, and it
	 * should therefore be thought of as a pause rather than a destroy
	 * operation, since the game manager may return to this view. 
	 *  
	 */
	public abstract void changeFrom();

	/**
	 * Prepares this view for being displayed. This method is invoked by the
	 * game manager whenever it changes to this view.
	 *  
	 */
	public abstract void changeTo();
	
	/**
	 * Attaches an observer to this view.
	 * 
	 * @param observer the observer to attach
	 */
	public void attachObserver(Observer<View<?>> observer) {
		observers.add(observer);
	}

	/**
	 * Detaches an observer from this view.
	 * 
	 * @param observer the observer to detach
	 */
	public void detachObserver(Observer<View<?>> observer) {
		observers.remove(observer);
	}
	
	/**
	 * Notifies observes of a change in the state of this view.
	 * 
	 */
	protected void notifyObservers() {
		for(Observer<View<?>> observer: observers)
			observer.reactToChange();
	}
	
	/**
	 * Gets the current state of this view.
	 * 
	 * @return the current state
	 */
	public T getState() {
		return state;
	}
	
	/**
	 * Changes to the specified state and notifies any observers of this change.
	 * 
	 * @param newState the state to which to change
	 */
	protected void changeState(T newState) {
		LogManager.getInstance().log(this + " changing state to " + newState, "VIEW");
		state = newState;
		notifyObservers();
	}
	
	/**
	 * Returns a string which describes this type of view.
	 * 
	 */
	public String toString() {
		return getClass().getSimpleName();
	}
}
