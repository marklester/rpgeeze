package rpgeeze.gl;

import javax.media.opengl.GL;

public interface GLObject extends Cloneable {
	public void render(GL gl);
	
	public int getGLName();
	public void setGLName(int newName);
	public boolean hasGLName();
	public void removeGLName();
	
	public double getX();
	public void setX(double newX);
	public double getY();
	public void setY(double newY);
	public double getZ();
	public void setZ(double newZ);
	public void setXY(double newX, double newY);
	public void setXYZ(double newX, double newY, double newZ);
	
	public void setVisible(boolean vis);
	public boolean isVisible();
	
	public GLObject clone();
}
