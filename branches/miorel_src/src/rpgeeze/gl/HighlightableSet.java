package rpgeeze.gl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rpgeeze.dp.Iterator;
import rpgeeze.util.ListIterator;

public class HighlightableSet {
	private HashMap<String, Highlightable> map;
	private Highlightable activated;
	private String activatedStr;
	
	public HighlightableSet() {
		map = new HashMap<String, Highlightable>();
		activated = null;
	}
	
	public void put(Highlightable h, String name) {
		map.put(name, h);
	}
	
	public void hover(String name) {
		clear();
		Highlightable h = map.get(name);
		if(h != null)
			h.highlight();
	}
	
	public void inactivate(String name) {
		if(activated != null)
			activated.unhighlight();
		activated = null;
		activatedStr = null;
	}
	
	public void activate(String name) {
		Highlightable h = map.get(name);
		activated = h;
		activatedStr = name;
		clear();
	}
	
	public void clear() {
		for(Highlightable h: map.values())
			h.unhighlight();
		if(activated != null)
			activated.highlight();
	}
	
	public String getActivated() {
		return activatedStr;
	}
	
	public Iterator<Highlightable> getHighlightables() {
		List<Highlightable> list = new ArrayList<Highlightable>();
		list.addAll(map.values());
		return new ListIterator<Highlightable>(list);
	}
}
