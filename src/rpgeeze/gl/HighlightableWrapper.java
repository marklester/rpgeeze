package rpgeeze.gl;

import java.awt.Color;
import javax.media.opengl.GL;

import rpgeeze.math.Scalar;
import rpgeeze.math.Vector;
import rpgeeze.util.Pair;

public class HighlightableWrapper implements Highlightable {
	public Vector getXYZ() {
		return object.getXYZ();
	}

	private final Color plain;
	private final Color highlighted;
	private GLObject object;

	private boolean hi = false;
	
	public HighlightableWrapper(GLObject object, Color plain, Color highlighted) {
		this.plain = plain;
		this.highlighted = highlighted;
		this.object = object.clone();
	}
	
	public Pair<Scalar, Vector> getPostTranslateRotation() {
		return object.getPostTranslateRotation();
	}

	public Pair<Scalar, Vector> getPreTranslateRotation() {
		return object.getPreTranslateRotation();
	}

	public void setPostTranslationRotation(double angle, double x, double y,
			double z) {
		object.setPostTranslationRotation(angle, x, y, z);
	}

	public void setPreTranslationRotation(double angle, double x, double y,
			double z) {
		object.setPreTranslationRotation(angle, x, y, z);
	}

	public void highlight() {
		hi = true;
	}

	public void unhighlight() {
		hi = false;
	}

	public int getGLName() {
		return object.getGLName();
	}

	public double getX() {
		return object.getX();
	}

	public double getY() {
		return object.getY();
	}

	public double getZ() {
		return object.getZ();
	}

	public boolean hasGLName() {
		return object.hasGLName();
	}

	public void render(GL gl) {
		GLUtil glutil = new GLUtil(gl);
		glutil.color(hi ? highlighted : plain);
		object.render(gl);
	}

	public void setGLName(int newName) {
		object.setGLName(newName);
	}

	public void removeGLName() {
		object.removeGLName();
	}

	public void setX(double newX) {
		object.setX(newX);
	}

	public void setXY(double newX, double newY) {
		object.setXY(newX, newY);
	}

	public void setXYZ(double newX, double newY, double newZ) {
		object.setXYZ(newX, newY, newZ);
	}

	public void setY(double newY) {
		object.setY(newY);
	}

	public void setZ(double newZ) {
		object.setZ(newZ);
	}
	
	public HighlightableWrapper clone() {
		HighlightableWrapper ret = null;
		try {
			ret = (HighlightableWrapper) super.clone();
		}
		catch (CloneNotSupportedException e) {
		}
		ret.object = object.clone();
		return ret;
	}
}
