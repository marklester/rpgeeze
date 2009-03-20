package rpgeeze.gl;

import java.awt.Rectangle;

import javax.media.opengl.glu.GLU;

public class GL extends DelegatingGL {
	public GL(javax.media.opengl.GL delegate) {
		super(delegate);
	}
	
	public GL() {
		this(GLU.getCurrentGL());
	}
	
	private int[] getViewportDimensionsAsArray() {
		int[] vp = new int[4];
		glGetIntegerv(GL_VIEWPORT, vp, 0);
		
		// protect against divides by zero 
		if(vp[2] <= 0)
			vp[2] = 1;
		if(vp[3] <= 0)
			vp[3] = 1;
		
		return vp;
	}
	
	public Rectangle getViewportDimensions() {
		int[] vp = getViewportDimensionsAsArray();
		return new Rectangle(vp[0], vp[1], vp[2], vp[3]);
	}
	
	public int getViewportHeight() {
		return getViewportDimensionsAsArray()[3];
	}
	
	public int getViewportWidth() {
		return getViewportDimensionsAsArray()[2];
	}
	
	public double getViewportAspectRatio() {
		return (double) getViewportWidth() / getViewportHeight();
	}
	
	public void pickMatrix(double x, double y) {
		GLU glu = new GLU();
		glu.gluPickMatrix(x, y, 1e-3, 1e-3, getViewportDimensionsAsArray(), 0);
	}	
}
