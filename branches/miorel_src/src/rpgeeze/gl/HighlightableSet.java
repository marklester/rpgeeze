package rpgeeze.gl;

import java.util.HashMap;

public class HighlightableSet {
	private HashMap<Integer, Highlightable> hashMap = new HashMap<Integer, Highlightable>();
	private Highlightable highlighted;
	
	public void put(Highlightable highlightable) {
		highlightable = highlightable.clone();
		highlightable.unhighlight();
		hashMap.put(highlightable.getGLName(), highlightable);
	}
	
	public void remove(int glName) {
		Highlightable removed = hashMap.remove(glName);
		if(highlighted == removed)
			highlighted = null;
	}
	
	private Highlightable get(int glName) {
		return hashMap.get(glName);
	}
	
	public void highlight(int glName) {
		unhighlight();
		Highlightable h = get(glName);
		if(h != null)
			h.highlight();
		highlighted = h;
	}
	
	public void unhighlight() {
		if(highlighted != null)
			highlighted.unhighlight();
	}
	
	public void render() {
		for(Highlightable h: hashMap.values())
			h.render();
	}
}
