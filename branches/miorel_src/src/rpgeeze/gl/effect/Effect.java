package rpgeeze.gl.effect;

import javax.media.opengl.GL;

/**
 * A dynamic set of related OpenGL commands that combine to create an effect.
 * 
 */
public interface Effect {
	/**
	 * Applies this effect using the specified OpenGL interface.
	 * 
	 * @param gl
	 *            OpenGL interface to use
	 */
	public void apply(GL gl);
	
	/**
	 * 
	 * @return whether or not this effect has finished rendering
	 */
	public boolean isDone();
}
