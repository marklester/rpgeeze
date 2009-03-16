package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import rpgeeze.GameManager;
import rpgeeze.util.Iterator;
import rpgeeze.view.CreditsView;
import rpgeeze.view.MainMenuView;

/**
 * Controls the main menu screen.
 */
public class MainMenuController extends Controller {
	private MainMenuView view;
	private float intensity = 0.0f;
	
	public MainMenuController(GameManager manager, MainMenuView view) {
		super(manager);
		this.view = view;
	}

	public void idleCycle() {
		if(intensity <= 0.75f) {
			view.setBackgroundIntensity(intensity);
			intensity += 0.02f;
		}
	}
	
	/**
	 * Highlights the button at the cursor position, if any.
	 */
	public void mouseEntered(MouseEvent e) {
		highlight(e.getPoint());
	}

	/**
	 * Highlights the button at the cursor position, if any.
	 */
	public void mouseMoved(MouseEvent e) {
		highlight(e.getPoint());
	}

	/**
	 * Turns off highlighting on all buttons.
	 */
	public void mouseExited(MouseEvent e) {
		unhighlight();		
	}

	/**
	 * Turns off highlighting on all buttons.
	 */
	public void windowLostFocus(WindowEvent e) {
		unhighlight();
	}

	/**
	 * Turns off highlighting on all buttons.
	 */
	public void windowActivated(WindowEvent e) {
		unhighlight();
	}

	/**
	 * Turns off highlighting on all buttons.
	 */
	public void windowDeactivated(WindowEvent e) {
		unhighlight();
	}

	/**
	 * Executes the action corresponding to the clicked button, if any.
	 */
	public void mouseClicked(MouseEvent e) {
		Iterator<Integer> iter = view.pick(e.getPoint());
		for(iter.reset(); !iter.isDone(); iter.advance())
			switch(iter.current().intValue()) {
			case MainMenuView.NEW_GAME_BUTTON:
				break;
			case MainMenuView.LOAD_GAME_BUTTON:
				break;
			case MainMenuView.OPTIONS_BUTTON:
				break;
			case MainMenuView.HELP_BUTTON:
				break;
			case MainMenuView.CREDITS_BUTTON:
				CreditsView cv = new CreditsView();
				CreditsController cc = new CreditsController(getManager(), cv);
				getManager().pushState(cv, cc);
				break;
			case MainMenuView.QUIT_BUTTON:
				getManager().stop();
				System.exit(0);
				break;
			}
	}

	private void highlight(Point p) {
		int hi = 0;
		Iterator<Integer> iter = view.pick(p);
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			int cur = iter.current();
			if(cur > 0)
				hi = cur;
		}
		view.setHighlightedButton(hi);
	}

	private void unhighlight() {
		view.setHighlightedButton(0);		
	}
}
