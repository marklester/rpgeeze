package rpgeeze.gl;

import java.awt.Color;

public interface Colorable extends GLObject {
	public Color getColor();
	public void setColor(Color newColor);
	
	public Colorable clone();
}
