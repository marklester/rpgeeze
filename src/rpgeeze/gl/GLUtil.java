package rpgeeze.gl;

import java.awt.Color;
import java.awt.Rectangle;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

import rpgeeze.math.Vector;

/**
 * Utility class for accomplishing common OpenGL tasks not provided in the
 * low-level interface.
 * 
 */
public class GLUtil {
	private final GL gl; 
	
	/**
	 * Constructs a <code>GLUtil</code> which acts on the current OpenGL
	 * context's interface.
	 * 
	 */
	public GLUtil() {
		this(GLU.getCurrentGL());
	}
	
	/**
	 * Constructs a <code>GLUtil</code> which acts on the specified OpenGL
	 * interface.
	 * 
	 * @param gl OpenGL interface to use
	 */
	public GLUtil(GL gl) {
		this.gl = gl;
	}
	
	private int[] getViewportDimensionsAsArray() {
		int[] vp = new int[4];
		gl.glGetIntegerv(GL.GL_VIEWPORT, vp, 0);

		// protect against divides by zero
		if(vp[2] <= 0)
			vp[2] = 1;
		if(vp[3] <= 0)
			vp[3] = 1;

		return vp;
	}

	/**
	 * Gets a rectangle that represents the current viewport.
	 * 
	 * @return the current viewport
	 */
	public Rectangle getViewportDimensions() {
		int[] vp = getViewportDimensionsAsArray();
		return new Rectangle(vp[0], vp[1], vp[2], vp[3]);
	}

	/**
	 * Gets the current viewport height.
	 * 
	 * @return the current viewport height
	 */
	public int getViewportHeight() {
		return getViewportDimensionsAsArray()[3];
	}

	/**
	 * Gets the current viewport width.
	 * 
	 * @return the current viewport width
	 */
	public int getViewportWidth() {
		return getViewportDimensionsAsArray()[2];
	}

	/**
	 * Gets a magic vector whose x coordinate will always be the viewport aspect
	 * ratio at the time of the method call. Such a vector can be used in
	 * combination with <code>VectorMath</code> to get vectors that update with
	 * the vieport!
	 * 
	 * @return the vector (aspectRatio, 0, 0)
	 */
	public Vector getAspectRatioVector() {
		return new Vector() {
			public double getX() {
				return new GLUtil().getViewportAspectRatio();
			}

			public double getY() {
				return 0;
			}

			public double getZ() {
				return 0;
			}
		};
	}

	/**
	 * Gets the width to height ratio of the current viewport.
	 * 
	 * @return the aspect ratio
	 */
	public double getViewportAspectRatio() {
		return (double) getViewportWidth() / getViewportHeight();
	}

	/**
	 * Sets up a picking matrix around the specified coordinates.
	 * 
	 * @param x abscissa
	 * @param y ordinate
	 */
	public void pickMatrix(double x, double y) {
		GLU glu = new GLU();
		glu.gluPickMatrix(x, y, 1e-3, 1e-3, getViewportDimensionsAsArray(), 0);
	}

	/**
	 * Translates the OpenGL coordinate system.
	 * 
	 * @param v
	 *            translation vector
	 */
	public void translate(Vector v) {
		gl.glTranslated(v.getX(), v.getY(), v.getZ());
	}

	/**
	 * Rotates the OpenGL coordinate system the specified angle about the
	 * specified vector. The rotation follows the right-hand rule, so if the
	 * vector points toward the user, the rotation will be counterclockwise.
	 * 
	 * @param angle
	 *            rotation angle
	 * @param axis
	 *            rotation axis
	 */
	public void rotate(double angle, Vector axis) {
		gl.glRotated(angle, axis.getX(), axis.getY(), axis.getZ());
	}

	/**
	 * Changes the OpenGL brush color.
	 * 
	 * @param c
	 *            new brush color
	 */
	public void color(Color c) {
		gl.glColor4f(c.getRed() / 255f, c.getGreen() / 255f, c.getBlue() / 255f,
				c.getAlpha() / 255f);
	}
	
	/**
	 * Changes the OpenGL clear color.
	 * 
	 * @param c
	 *            new brush color
	 */
	public void clearColor(Color c) {
		gl.glClearColor(c.getRed() / 255f, c.getGreen() / 255f,
				c.getBlue() / 255f, c.getAlpha() / 255f);
	}
}
