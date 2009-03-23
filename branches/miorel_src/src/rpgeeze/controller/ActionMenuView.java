package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.media.opengl.glu.GLU;

import rpgeeze.GameManager;
import rpgeeze.view.KeyBindingsView;
import rpgeeze.view.OptionsMenuView;
import rpgeeze.view.VideoOptionsView;
import rpgeeze.view.SoundOptionsView;

/**
 * Controls the options menu screen.
 */
public class ActionMenuView extends HighlightableViewController<OptionsMenuView> {
	public ActionMenuView(GameManager manager, OptionsMenuView view) {
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
				if(name.equals("Talk"))
					System.out.println("TALK");
				else if(name.equals("Attack")) 	
					System.out.println("ATTACK");
				else if(name.equals("Use Skill"))
					System.out.println("SKILL");
				else if(name.equals("Use Item")) {
					System.out.println("USE");
				}
			}
		}
	}
	
	protected void keyBindingsView(){
		KeyBindingsView kbv = new KeyBindingsView(getManager());
		KeyBindingsViewController kbc = new KeyBindingsViewController(getManager(), kbv);
		getManager().pushState(kbv,kbc);	
	}
	
	protected void videoOptionsView(){
		VideoOptionsView vov = new VideoOptionsView(getManager());
		VideoOptionsController voc = new VideoOptionsController(getManager(), vov);
		getManager().pushState(vov, voc);	
	}
	
	protected void soundOptionsView() {
		SoundOptionsView sov = new SoundOptionsView(getManager());
		SoundOptionsController soc = new SoundOptionsController(getManager(), sov);
		getManager().pushState(sov, soc);
	}
}