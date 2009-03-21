package rpgeeze.gl.geom;

import javax.media.opengl.GL;

import rpgeeze.gl.GLObjectImpl;

public class Rectangle extends GLObjectImpl {
	private double width;
	private double height;

	public Rectangle() {
		this(1, 1);
	}
	
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(double width, double height, double x, double y, double z) {
		super(x, y, z);
		this.width = width;
		this.height = height;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double newWidth) {
		width = newWidth;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double newHeight) {
		height = newHeight;
	}
	
	protected void doRender(GL gl) {
		gl.glBegin(GL.GL_QUADS);
		gl.glTranslated(getX(), getY(), getZ());
        gl.glVertex2d(0, getHeight());
        gl.glVertex2i(0, 0);
        gl.glVertex2d(getWidth(), 0);
        gl.glVertex2d(getWidth(), getHeight());
		gl.glEnd();
	}
}
