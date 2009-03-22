package rpgeeze.gl;

import java.awt.Color;
import javax.media.opengl.GL;

import rpgeeze.math.Vector;
import rpgeeze.util.Pair;

public class HighlightableWrapper<T extends Colorable> implements Highlightable {
	public Vector getXYZ() {
		return object.getXYZ();
	}
	private final Color plain;
	private final Color highlighted;
	private T object;
	
	public HighlightableWrapper(T object, Color plain, Color highlighted) {
		this.plain = plain;
		this.highlighted = highlighted;
		this.object = object;
		unhighlight();
	}
	
	public T getWrappedObject() {
		return object;
	}
	
	public Pair<Double, Vector> getPostTranslateRotation() {
		return object.getPostTranslateRotation();
	}

	public Pair<Double, Vector> getPreTranslateRotation() {
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
		object.setColor(highlighted);
	}

	public void unhighlight() {
		object.setColor(plain);
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

	public void render(GL gl) {
		object.render(gl);
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
	
	public HighlightableWrapper<T> clone() {
		HighlightableWrapper<T> ret = null;
		try {
			ret = (HighlightableWrapper<T>) super.clone();
		}
		catch (CloneNotSupportedException e) {
		}
		ret.object = (T) object.clone();
		return ret;
	}

	public boolean getVisible() {
		return object.getVisible(); 
	}

	public void setVisible(boolean newVisible) {
		object.setVisible(newVisible);
	}
}
