package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import rpgeeze.GameManager;
import rpgeeze.dp.Iterator;
import rpgeeze.view.HighlightableView;

public abstract class HighlightableViewController<T extends HighlightableView> extends Controller<T> {
	public HighlightableViewController(GameManager manager, T view) {
		super(manager, view);
	}
	
	public void mouseEntered(MouseEvent e) {
		highlight(e.getPoint());
	}

	public void mouseMoved(MouseEvent e) {
		highlight(e.getPoint());
	}

	public void mouseExited(MouseEvent e) {
		getView().unhighlight();
	}

	public void windowLostFocus(WindowEvent e) {
		getView().unhighlight();
	}

	public void windowActivated(WindowEvent e) {
		getView().unhighlight();
	}

	public void windowDeactivated(WindowEvent e) {
		getView().unhighlight();
	}
	
	private void highlight(Point p) {
		Iterator<Integer> iter = getView().pick(p);
		iter.reset();
		for(iter.reset(); !iter.isDone(); iter.advance())
			getView().highlight(iter.current());
	}
}
