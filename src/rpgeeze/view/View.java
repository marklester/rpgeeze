package rpgeeze.view;

import java.awt.Point;
import java.nio.IntBuffer;
import java.util.HashSet;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import rpgeeze.GameManager;
import rpgeeze.log.LogManager;
import rpgeeze.dp.Iterator;
import rpgeeze.dp.Observer;
import rpgeeze.gl.GLUtil;

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

	private final int bufferSize;
	
	/**
	 * Label for types which represent a state.
	 *
	 */
	public interface State {
	}
	
	/**
	 * Constructs a view that answers to the specified game manager and uses the specified buffer size for OpenGL selecting.
	 * 
	 * @param manager the game manager
	 * @param bufferSize the selection buffer size
	 */
	public View(GameManager manager, int bufferSize) {
		this.manager = manager;
		this.bufferSize = bufferSize;
	}

	/**
	 * Constructs a view that answers to the specified game manager and uses a default buffer size for OpenGL selecting.
	 * 
	 * @param manager the game manager
	 */
	public View(GameManager manager) {
		this(manager, 16);
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
	 * Friendly wrapper for OpenGL "picking" of rendered elements. 
	 * 
	 * @param the OpenGL interface to use for picking
	 * @param pickPoint point around which to set up the picking matrix
	 * @param bufSize size to use for the selection buffer
	 * @return an iterator over the name constants that registered as hits at the specified point
	 */
	public Iterator<Integer> pickAll(GL gl, Point pickPoint) {
		IntBuffer selectBuffer = BufferUtil.newIntBuffer(bufferSize);
		
		gl.glSelectBuffer(bufferSize, selectBuffer);
		gl.glRenderMode(GL.GL_SELECT);
		
		gl.glInitNames();
		gl.glPushName(-1);

		render(gl, pickPoint);

		final int hits = gl.glRenderMode(GL.GL_RENDER);
		
		GLUtil glutil = new GLUtil(gl);
		final Iterator<GLUtil.Hit> iter = glutil.hitsFromBuffer(selectBuffer, hits);
		
		return new Iterator<Integer>() {
			public void advance() {
				iter.advance();
			}

			public Integer current() {
				return iter.current().getGLName();
			}

			public boolean isDone() {
				return iter.isDone();
			}

			public void reset() {
				iter.reset();
			}
		};
	}

	
	protected String mapGLNameToString(int glName) {
		return null;
	}
	
	/**
	 * Renders this view in the current OpenGL context. If the argument is not
	 * null, sets up a pick matrix around the specified location, for use in
	 * selection mode.  
	 * 
	 * @param point coordinates to pick around
	 */
	public abstract void render(GL gl, Point point);
	
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
