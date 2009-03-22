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
		hover(e.getPoint());
	}

	public void mouseMoved(MouseEvent e) {
		hover(e.getPoint());
	}

	public void mouseExited(MouseEvent e) {
		getView().clear();
	}

	public void windowLostFocus(WindowEvent e) {
		getView().clear();
	}

	public void windowActivated(WindowEvent e) {
		getView().clear();
	}

	public void windowDeactivated(WindowEvent e) {
		getView().clear();
	}
	
	protected void hover(Point p) {
		getView().clear();
		String name = getView().pickClosest(GLU.getCurrentGL(), p);
		getView().hover(name);
	}
}
