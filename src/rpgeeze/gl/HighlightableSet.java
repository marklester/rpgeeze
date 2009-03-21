package rpgeeze.gl;

import java.util.HashMap;
import javax.media.opengl.GL;

public class HighlightableSet {
	private HashMap<Integer, Highlightable> hashMap = new HashMap<Integer, Highlightable>();
	private Highlightable highlighted;
	
	public synchronized void put(Highlightable highlightable) {
		highlightable = highlightable.clone();
		highlightable.unhighlight();
		hashMap.put(highlightable.getGLName(), highlightable);
		if(highlighted != null)
			highlight(highlighted.getGLName());
	}
	
	public synchronized void remove(int glName) {
		Highlightable removed = hashMap.remove(glName);
		if(highlighted == removed)
			highlighted = null;
	}
	
	private synchronized Highlightable get(int glName) {
		return hashMap.get(glName);
	}
	
	public synchronized void highlight(int glName) {
		unhighlight();
		Highlightable h = get(glName);
		if(h != null)
			h.highlight();
		highlighted = h;
	}
	
	public synchronized void unhighlight() {
		if(highlighted != null)
			highlighted.unhighlight();
		highlighted = null;
	}
	
	
	public synchronized void render(GL gl) {
		for(Highlightable h: hashMap.values())
			h.render(gl);
	}
}
