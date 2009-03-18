package rpgeeze.view;

import rpgeeze.gl.Highlightable;
import rpgeeze.gl.HighlightableSet;

public abstract class HighlightableView extends View {
	private HighlightableSet highlightables = new HighlightableSet();
		
	public void changeFrom() {
		unhighlight();
	}
	
	public void changeTo() {
		unhighlight();
	}

	public void highlight(int glName) {
		highlightables.highlight(glName);
	}

	protected void putHighlightable(Highlightable highlightable) {
		highlightables.put(highlightable);
	}

	protected void removeHighlightable(int glName) {
		highlightables.remove(glName);
	}

	protected void renderHighlightables() {
		highlightables.render();
	}

	public void unhighlight() {
		highlightables.unhighlight();
	}
}
