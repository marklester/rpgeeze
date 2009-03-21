package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.media.opengl.glu.GLU;

import rpgeeze.GameManager;
import rpgeeze.view.KeyBindingsView;
import rpgeeze.view.OptionsMenuView;
import rpgeeze.view.SoundOptionsView;

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
					keyBindingsView();
				}
				else if(name.equals("Sound Options")) {	
					soundOptionsView();
				}
				else if(name.equals("Video Options")) {
				}
				else if(name.equals("Back")) {
					getManager().popState();
				}
			}
		}
	}
	
	protected void keyBindingsView(){
		KeyBindingsView kbv = new KeyBindingsView(getManager());
		KeyBindingsViewController kbc = new KeyBindingsViewController(getManager(), kbv);
		getManager().pushState(kbv,kbc);	
	}
	
	protected void soundOptionsView(){
		SoundOptionsView sov = new SoundOptionsView(getManager());
		SoundOptionsController soc = new SoundOptionsController(getManager(), sov);
		getManager().pushState(sov,soc);
	}
}