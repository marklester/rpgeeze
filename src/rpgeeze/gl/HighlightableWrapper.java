package rpgeeze.gl;

import java.awt.Color;

public class HighlightableWrapper implements Highlightable {
	private final Color plain;
	private final Color highlighted;
	private Colorable object;
	
	public HighlightableWrapper(Colorable object, Color plain, Color highlighted) {
		this.plain = plain;
		this.highlighted = highlighted;
		this.object = object.clone();
	}
	
	public void highlight() {
		object.setColor(highlighted);
	}

	public void unhighlight() {
		object.setColor(plain);
	}
	
	public Color getColor() {
		return object.getColor();
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

	public void render() {
		object.render();
	}

	public void setColor(Color newColor) {
		object.setColor(newColor);
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

	public boolean isVisible() {
		return object.isVisible();
	}

	@Override
	public void setVisible(boolean vis) {
		object.setVisible(vis);
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
