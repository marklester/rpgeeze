package rpgeeze.view;

import java.awt.Point;
import java.nio.IntBuffer;
import java.util.HashSet;

import rpgeeze.gl.GL;
import rpgeeze.dp.Iterator;
import rpgeeze.dp.Observer;
import rpgeeze.dp.Subject;
import rpgeeze.dp.State;

import com.sun.opengl.util.BufferUtil;

/**
 * Superclass for the various screens that the game will have to display. The
 * most important method is display(), which will be called whenever the screen
 * must be painted. Additionally, each View must provide a CommandHandler so
 * that it may participate in the command framework. The methods changeFrom()
 * and changeTo() are called when the game state changes from and to this view,
 * respectively. They should be used, for example, to pause/resume the game when
 * moving away from the game screen.
 */

public abstract class View<T extends State> implements Subject<View<?>> {
	private HashSet<Observer<View<?>>> observers = new HashSet<Observer<View<?>>>();
	private T state;
	
	/**
	 * OpenGL "picking" with a default buffer size.
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
		GL gl = GL.getCurrent();
		
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
	 * Renders this View in the current OpenGL context. If the argument is not null, sets up a pick matrix around the specified location, for use in selection mode.  
	 * 
	 * @param point coordinates to pick around
	 */
	public abstract void render(Point point);

	/**
	 * Called whenever the GameManager changes away from this state. This is where you should, for example, pause any timers that are specific to this View.
	 */
	public void changeFrom() {
	}

	/**
	 * Called whenever the GameManager changes to this state. If, for example, you paused some timer in changeFrom(), this is where you should resume it.
	 */
	public void changeTo() {
	}
	
	public void attach(Observer<View<?>> observer) {
		observers.add(observer);
	}
	
	public void detach(Observer<View<?>> observer) {
		observers.remove(observer);
	}
	
	protected void notifyObservers() {
		for(Observer<View<?>> observer: observers)
			observer.update();
	}
	
	public final T getState() {
		return state;
	}
	
	protected final void changeState(T newState) {
		state = newState;
		notifyObservers();
	}
}
