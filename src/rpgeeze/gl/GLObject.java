package rpgeeze.gl;

import java.awt.Color;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public abstract class GLObject {
	private double x, y, z;
	private Color color;
	private int name;
	private boolean hasName;
	
	public GLObject() {
		this(0, 0, 0, null, -1);
	}
	
	public GLObject(Color color) {
		this(0, 0, 0, color, -1);
	}
	
	public GLObject(double x, double y, double z) {
		this(x, y, z, null, -1);
	}	

	public GLObject(double x, double y, double z, int name) {
		this(x, y, z, null, name);
	}
	
	public GLObject(double x, double y, double z, Color color, int name) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.color = color;
		this.name = name;
	}
	
	public final void render() {
		GL gl = GLU.getCurrentGL();
		gl.glPushMatrix();
		gl.glTranslated(x, y, z);
		if(color != null)
			gl.glColor4d(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		if(hasName)
			gl.glLoadName(name);
		doRender();
		gl.glPopMatrix();
	}
	
	protected abstract void doRender();
	
	public final Color getColor() {
		return color;
	}
	
	public final void setColor(Color newColor) {
		color = newColor;
	}
	
	public final int getName() {
		return name;
	}
	
	public final void setName(int newName) {
		name = newName;
		hasName = true;
	}
	
	public final boolean hasName() {
		return hasName;
	}
	
	public final void setNameless() {
		hasName = false;
	}
	
	public final double getX() {
		return x;
	}

	public final void setX(double newX) {
		x = newX;
	}
	
	public final double getY() {
		return y;
	}
	
	public final void setY(double newY) {
		y = newY;
	}
	
	public final double getZ() {
		return z;
	}
	
	public final void setZ(double newZ) {
		z = newZ;
	}
	
	public final void setXY(double newX, double newY) {
		setX(newX);
		setY(newY);
	}
	
	public final void setXYZ(double newX, double newY, double newZ) {
		setX(newX);
		setY(newY);
		setZ(newZ);
	}
}
