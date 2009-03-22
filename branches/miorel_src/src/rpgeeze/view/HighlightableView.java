package rpgeeze.view;

import rpgeeze.GameManager;
import rpgeeze.dp.Iterator;
import rpgeeze.gl.Highlightable;
import rpgeeze.gl.HighlightableSet;

public abstract class HighlightableView<T extends View.State> extends View<T> {
	private HighlightableSet set;
	
	public HighlightableView(GameManager manager) {
		super(manager);
		set = new HighlightableSet();
	}
	
	protected void putHighlightable(Highlightable h, String name) {
		set.put(h, name);
	}
	
	public void hover(String name) {
		set.hover(name);
	}

	public void activate(String name) {
		set.activate(name);
	}
	
	public void clear() {
		set.clear();
	}
	
	public String getActivated() {
		return set.getActivated();
	}
	
	protected Iterator<Highlightable> getHighlightables() {
		return set.getHighlightables();
	}
}
