package rpgeeze.gl;

import rpgeeze.math.Vector;
import rpgeeze.util.Pair;

public interface NewGLObject extends Cloneable {
	public void render(GL gl);
	
	public Vector getTranslation();
	public void setTranslation(Vector newTranslation);
	public Pair<Double, Vector> getPreTranslateRotation();
	public void setPreTranslationRotation(double newPreAngle, Vector newPreAxis);
	public Pair<Vector, Double> getPostTranslateRotation();
	public void setPostTranslationRotation(double newPostAngle, Vector newPostAxis);
	
	public NewGLObject clone(); 
}
