package rpgeeze.gl;

/**
 * A sequence of related OpenGL commands.
 *
 */
public interface Renderable {
	/**
	 * Renders using the specified OpenGL interface.
	 * 
	 * @param gl OpenGL interface to be used for rendering
	 */
	public void render(GL gl);
}
