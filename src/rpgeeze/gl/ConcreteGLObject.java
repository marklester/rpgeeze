package rpgeeze.gl;

import java.awt.Color;

public abstract class ConcreteGLObject implements Colorable {
	private double x, y, z;
	private Color color;
	private int glName = -1;
	private boolean hasName = false;
	private boolean visible = true;

	public ConcreteGLObject() {
		this(0, 0, 0, null);
	}

	public ConcreteGLObject(Color color) {
		this(0, 0, 0, color);
	}

	public ConcreteGLObject(double x, double y, double z) {
		this(x, y, z, null);
	}	

	public ConcreteGLObject(double x, double y, double z, Color color) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.color = color;
	}

	public final void render() {
		if(isVisible()) {
			GL gl = GL.getCurrent();
			gl.glPushMatrix();
			gl.glTranslated(x, y, z);
			if(color != null)
				gl.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
			if(hasName)
				gl.glLoadName(glName);
			doRender();
			gl.glPopMatrix();
		}
	}

	protected abstract void doRender();

	public final Color getColor() {
		return color;
	}

	public final void setColor(Color newColor) {
		color = newColor;
	}

	public final int getGLName() {
		return glName;
	}

	public final void setGLName(int newGLName) {
		glName = newGLName;
		hasName = true;
	}

	public final boolean hasGLName() {
		return hasName;
	}

	public final void removeGLName() {
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

	public final boolean isVisible() {
		return visible;
	}

	public final void setVisible(boolean vis) {
		visible = vis;
	}

	public ConcreteGLObject clone() {
		ConcreteGLObject ret = null;
		try {
			ret = (ConcreteGLObject) super.clone();
		}
		catch (CloneNotSupportedException e) {
		}
		return ret;
	}
}
