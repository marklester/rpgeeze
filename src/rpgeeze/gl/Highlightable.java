package rpgeeze.gl;

public interface Highlightable extends GLObject {
	public void highlight();
	public void unhighlight();
	
	public Highlightable clone();
}
