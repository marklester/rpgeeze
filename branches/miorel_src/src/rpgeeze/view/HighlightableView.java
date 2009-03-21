package rpgeeze.view;

import rpgeeze.GameManager;

public abstract class HighlightableView<T extends View.State> extends View<T> {
	public HighlightableView(GameManager manager) {
		super(manager);
	}
	
	public abstract void highlight(String name);

	public abstract void unhighlight();
}
