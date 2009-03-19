package rpgeeze.gl;

import rpgeeze.math.Vector;

public class Triangle extends GLObjectImpl {
	private final Vector[] vertex;
	
	public Triangle(Vector v0, Vector v1, Vector v2) {
		vertex = new Vector[] {v0, v1, v2};
	}
	
	protected void doRender() {
		GL gl = new GL();
		gl.glBegin(GL.GL_TRIANGLES);
		gl.glTranslated(getX(), getY(), getZ());
		for(Vector v: vertex)
			gl.glVertex3d(v.getX(), v.getY(), v.getZ());
		gl.glEnd();
	}
	
	public Triangle clone() {
		return (Triangle) super.clone();
	}
}
