package rpgeeze.gl;

import java.awt.Point;

/**
 * A standard set of OpenGL view port setup operations. 
 *
 */
public class StandardSetup implements Renderable {
	private Point point;

	/**
	 * Prepare a set of operations which will set up a standard viewport with
	 * a picking matrix at the specified point in screen coordinates, if any. 
	 * 
	 * @param point
	 */
	public StandardSetup(Point point) {
		this.point = point;
	}
	
	/**
	 * Sets up a depth buffer, enables blending, sets up a picking matrix and a
	 * frustum view, then loads the identity modelview matrix.
	 * 
	 */
	public void render(GL gl) {
		gl.glShadeModel(GL.GL_SMOOTH);

		// depth buffer
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LEQUAL);

		// blending
		gl.glEnable(GL.GL_BLEND);

		gl.glHint(GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);

		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		if(point != null)
			gl.pickMatrix(point.getX(), gl.getViewportHeight() - point.getY());

		double aspectRatio = gl.getViewportAspectRatio();
		gl.glFrustum(-aspectRatio, aspectRatio, -1, 1, 1, 128);
		gl.glMatrixMode(GL.GL_MODELVIEW);

		gl.glLoadIdentity();
	}
}
