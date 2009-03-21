package rpgeeze.gl.effect;

import java.awt.Color;

import javax.media.opengl.GL;

public class BrushColorChange implements Effect {
	private long duration;
	private long startTime;
	private boolean started;
	private boolean done;

	private final Color begin;
	private final Color end;

	/**
	 * Constructs an effect which changes the OpenGL brush color over the
	 * specified number of seconds from the moment it's first applied.
	 * 
	 * @param begin
	 *            the initial color
	 * @param end
	 *            the final color
	 * @param seconds
	 *            the duration of the pause in seconds
	 */
	public BrushColorChange(Color begin, Color end, double seconds) {
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
			double g = begin.getGreen() * beginFactor + end.getGreen() 	* endFactor;
			double b = begin.getBlue() * beginFactor + end.getBlue() * endFactor;
			double a = begin.getAlpha() * beginFactor + end.getAlpha() 	* endFactor;
			gl.glColor4f((float) r / 255, (float) g / 255, (float) b / 255, (float) a / 255);
		}
	}

	public boolean isDone() {
		return done;
	}
}
