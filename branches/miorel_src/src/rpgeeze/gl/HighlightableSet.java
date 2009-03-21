package rpgeeze.gl;

import java.util.HashMap;

public class HighlightableSet {
	private HashMap<String, Highlightable> map;
	private Highlightable activated;
	
	public HighlightableSet() {
		map = new HashMap<String, Highlightable>();
		activated = null;
	}
	
	public void put(Highlightable h, String name) {
		map.put(name, h);
	}
	
	public void hover(String name) {
		clearAll();
		Highlightable h = map.get(name);
		if(h != null)
			h.highlight();
		if(activated != null)
			activated.highlight();
	}
	
	public void activate(String name) {
		clearAll();
		Highlightable h = map.get(name);
		activated = h;
		if(activated != null)
			activated.highlight();		
	}
	
	public void clearAll() {
		for(Highlightable h: map.values())
			h.unhighlight();
	}
}
