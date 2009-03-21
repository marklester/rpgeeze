package rpgeeze.gl;

import javax.media.opengl.GL;

import rpgeeze.math.Scalar;
import rpgeeze.math.Vector;
import rpgeeze.util.Pair;

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
	public Vector getXYZ();
	
	public Pair<Scalar, Vector> getPreTranslateRotation();
	public void setPreTranslationRotation(double angle, double x, double y, double z);
	public Pair<Scalar, Vector> getPostTranslateRotation();
	public void setPostTranslationRotation(double angle, double x, double y, double z);
	
	public GLObject clone();
}
