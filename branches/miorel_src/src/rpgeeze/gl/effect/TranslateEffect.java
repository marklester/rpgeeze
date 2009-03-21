package rpgeeze.gl.effect;

import javax.media.opengl.GL;

import rpgeeze.gl.GLObject;
import rpgeeze.math.Vector;
import rpgeeze.math.VectorMath;

/**
 * Translate an object from one position to another over a specified period of
 * time.
 * 
 */
public class TranslateEffect implements Effect {
	private long duration;
	private long startTime;
	private boolean started;
	private boolean done;

	private final GLObject object;
	private final Vector begin;
	private final Vector end;

	/**
	 * Constructs an effect which translates an object from one position to
	 * another over the specified number of seconds from the moment it's first
	 * applied.
	 * 
	 * @param begin
	 *            the initial color
	 * @param end
	 *            the final color
	 * @param seconds
	 *            the duration of the pause in seconds
	 */
	public TranslateEffect(GLObject object, Vector begin, Vector end, double seconds) {
		duration = (long) (seconds * 1e9);
		started = false;
		done = false;
		this.begin = begin;
		this.end = end;
		this.object = object;
	}

	/**
	 * Retrieves the initial position of this effect.
	 * 
	 * @return the initial position
	 */
	public Vector getInitialPosition() {
		return begin;
	}

	/**
	 * Retrieves the final position of this effect.
	 * 
	 * @return the final position
	 */
	public Vector getFinalPosition() {
		return end;
	}

	public void apply(GL gl) {
		long lasted = 0;
		if(started) {
			lasted = System.nanoTime() - startTime;
			if(lasted > duration)
				done = true;
		}
		else {
			startTime = System.nanoTime();
			started = true;
		}
		if(!done) {
			double endFactor = (double) lasted / duration;
			double beginFactor = 1 - endFactor;
			VectorMath vm = new VectorMath();
			Vector pos = vm.add(vm.multiply(begin, beginFactor), vm.multiply(end, endFactor));
			object.setXYZ(pos.getX(), pos.getY(), pos.getZ());
		}
	}

	public boolean isDone() {
		return done;
	}
}
