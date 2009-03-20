package rpgeeze.view.overlay;

import rpgeeze.gl.GL;

/**
 * A related set of OpenGL elements which may be rendered together in a frustum.
 * 
 */
public interface Overlay {
	/**
	 * Renders this overlay in the frustum specified by the near and far planes.
	 * The width and height state that the allowed coordinate system at the near
	 * plane ranges from -width/2 to width/2 horizontally and from -height/2 to
	 * height/2 vertically. The corresponding range at any other z value can be
	 * obtained through scaling by a factor of z/near. The contract specifies
	 * that the overlay needn't render unpickable objects if picking is not
	 * desired.
	 * 
	 * @param gl
	 *            OpenGL interface to use
	 * @param width
	 *            the width of the frustum in the near plane
	 * @param height
	 *            the height of the frustum in the far plane
	 * @param near
	 *            the z value of the near clipping plane
	 * @param far
	 *            the z value of the far clipping plane
	 * @param pick
	 *            whether or not picking is desired
	 */
	public void render(GL gl, double width, double height, double near,
			double far, boolean pick);
}
