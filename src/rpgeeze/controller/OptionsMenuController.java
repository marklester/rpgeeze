package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.media.opengl.glu.GLU;

import rpgeeze.GameManager;
import rpgeeze.view.KeyBindingsView;
import rpgeeze.view.OptionsMenuView;

/**
 * Controls the options menu screen.
 */
public class OptionsMenuController extends HighlightableViewController<OptionsMenuView> {
	public OptionsMenuController(GameManager manager, OptionsMenuView view) {
		super(manager, view);
	}

	/**
	 * Executes the action corresponding to the clicked button, if any.
	 */
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		if(e.getButton() == MouseEvent.BUTTON1) {
			String name = getView().pickClosest(GLU.getCurrentGL(), p);
			if(name != null) {
				if(name.equals("Key Bindings")) {
					KeyBindingsView kbv = new KeyBindingsView(getManager());
					KeyBindingsViewController kbc = new KeyBindingsViewController(getManager(), kbv);
					getManager().pushState(kbv,kbc);					
				}
				else if(name.equals("Sound Options")) {	
				}
				else if(name.equals("Video Options")) {
				}
				else if(name.equals("Back")) {
					getManager().popState();
				}
			}
		}
	}
}