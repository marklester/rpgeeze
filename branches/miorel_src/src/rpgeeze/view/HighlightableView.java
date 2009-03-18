package rpgeeze.view;

public abstract class HighlightableView extends View {
	public abstract void highlight(int glName);
	public abstract void unhighlight();

	public void changeFrom() {
		unhighlight();
	}
	
	public void changeTo() {
		unhighlight();
	}
}
