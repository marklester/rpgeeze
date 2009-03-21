package rpgeeze.gl.effect;

import java.awt.Color;

import javax.media.opengl.GL;

public class ClearColorChange implements Effect {
	private long duration;
	private long startTime;
	private boolean started;
	private boolean done;

	private final Color begin;
	private final Color end;

	/**
	 * Constructs an effect which changes the OpenGL clear color over the
	 * specified number of seconds from the moment it's first applied.
	 * 
	 * @param begin
	 *            the initial color
	 * @param end
	 *            the final color
	 * @param seconds
	 *            the duration of the pause in seconds
	 */
	public ClearColorChange(Color begin, Color end, double seconds) {
		duration = (long) (seconds * 1e9);
		started = false;
		done = false;
		this.begin = begin;
		this.end = end;
	}

	/**
	 * Retrieves the initial color of this effect.
	 * 
	 * @return the initial color
	 */
	public Color getInitialColor() {
		return begin;
	}

	/**
	 * Retrieves the final color of this effect.
	 * 
	 * @return the final color
	 */
	public Color getFinalColor() {
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
			double r = begin.getRed() * beginFactor + end.getRed() * endFactor;
			double g = begin.getGreen() * beginFactor + end.getGreen()
					* endFactor;
			double b = begin.getBlue() * beginFactor + end.getBlue()
					* endFactor;
			double a = begin.getAlpha() * beginFactor + end.getAlpha()
					* endFactor;
			gl.glClearColor((float) r / 255f, (float) g / 255f,
					(float) b / 255f, (float) a / 255);
		}
	}

	public boolean isDone() {
		return done;
	}
}
