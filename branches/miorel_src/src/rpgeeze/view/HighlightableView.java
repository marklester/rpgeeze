package rpgeeze.view;

import rpgeeze.GameManager;
import rpgeeze.gl.Highlightable;
import rpgeeze.gl.HighlightableSet;

import javax.media.opengl.GL;

public abstract class HighlightableView<T extends View.State> extends View<T> {
	private HighlightableSet highlightables = new HighlightableSet();
	
	public HighlightableView(GameManager manager) {
		super(manager);
	}
	
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

	protected void renderHighlightables(GL gl) {
		highlightables.render(gl);
	}

	public void unhighlight() {
		highlightables.unhighlight();
	}
}
