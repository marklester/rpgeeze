package rpgeeze.gl;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Rectangle {
	private double width;
	private double height;
	
	public Rectangle(double width, double height) {
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
	
	public void render() {
		GL gl = GLU.getCurrentGL();
		gl.glBegin(GL.GL_QUADS);
        gl.glVertex2d(0, getHeight());
        gl.glVertex2i(0, 0);
        gl.glVertex2d(getWidth(), 0);
        gl.glVertex2d(getWidth(), getHeight());
		gl.glEnd();
	}
}
