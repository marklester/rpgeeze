package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.media.opengl.glu.GLU;

import rpgeeze.GameManager;
import rpgeeze.view.HelpView;

public class HelpViewController extends HighlightableViewController<HelpView> {
	public HelpViewController(GameManager manager, HelpView view) {
		super(manager, view);
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ESCAPE)
			getManager().popState();
	}
	
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		if(e.getButton() == MouseEvent.BUTTON1) {
			String name = getView().pickClosest(GLU.getCurrentGL(), p);
			if(name != null) {
				if(name.equals("Back")) {
					getManager().popState();
				}
			}
		}
	}
}
