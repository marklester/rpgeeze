package rpgeeze.gl.geom;

import java.awt.Color;

import javax.media.opengl.GL;

import rpgeeze.gl.Colorable;
import rpgeeze.gl.GLObjectImpl;
import rpgeeze.gl.GLUtil;
import rpgeeze.math.Vector;

public class Triangle extends GLObjectImpl implements Colorable {
	private final Vector[] vertex;
	private Color color;
	
	public Triangle(Vector v0, Vector v1, Vector v2) {
		vertex = new Vector[] {v0, v1, v2};
	}
	
	protected void doRender(GL gl) {
		if(getColor() != null) {
			GLUtil glutil = new GLUtil(gl);
			glutil.color(getColor());
		}
		gl.glBegin(GL.GL_TRIANGLES);
		gl.glTranslated(getX(), getY(), getZ());
		for(Vector v: vertex)
			gl.glVertex3d(v.getX(), v.getY(), v.getZ());
		gl.glEnd();
	}
	
	public Triangle clone() {
		return (Triangle) super.clone();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color newColor) {
		color = newColor;		
	}
}
