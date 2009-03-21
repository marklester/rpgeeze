package rpgeeze.gl.effect;

import rpgeeze.gl.GL;

/**
 * A pause effect. Probably useless by itself, this effect could be interesting
 * when combined with others.
 * 
 */
public class Pause implements Effect {
	private long duration;
	private long startTime;
	private boolean started;
	private boolean done;

	/**
	 * Constructs a pause effect which lasts for the specified number of seconds from the moment it's
	 * first applied.
	 * 
	 * @param seconds
	 *            the duration of the pause in seconds
	 */
	public Pause(double seconds) {
		duration = (long) (seconds * 1e9);
		started = false;
		done = false;
	}

	public void apply(GL gl) {
		if(started) {
			if(System.nanoTime() > startTime + duration)
				done = true;
		}
		else {
			startTime = System.nanoTime();
			started = true;
		}
	}

	public boolean isDone() {
		return done;
	}
}
