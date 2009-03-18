package rpgeeze.gl;

public interface GLObject extends Cloneable {
	public void render();
	
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
	
	public GLObject clone();
}
