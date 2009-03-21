package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.media.opengl.glu.GLU;

import rpgeeze.GameManager;
import rpgeeze.view.HighlightableView;

public abstract class HighlightableViewController<T extends HighlightableView<?>> extends Controller<T> {
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
		getView().unhighlight();
		int glName = getView().pickClosest(GLU.getCurrentGL(), p);
		getView().highlight(glName);
	}
}
