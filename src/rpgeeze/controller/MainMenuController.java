package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import rpgeeze.GameManager;
import rpgeeze.util.Iterator;
import rpgeeze.view.MainMenuView;

/**
 * Controls the main menu screen.
 */
public class MainMenuController extends Controller {
	private MainMenuView view;
	
	public MainMenuController(GameManager manager, MainMenuView view) {
		super(manager);
		this.view = view;
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
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			if(iter.current() == MainMenuView.NEW_GAME_BUTTON) {
				// new game	
			}
			else if(iter.current() == MainMenuView.LOAD_GAME_BUTTON) {
				// load game
			}
			else if(iter.current() == MainMenuView.QUIT_GAME_BUTTON) {
				getManager().stop();
				System.exit(0);
			}
		}
	}
	
	private void highlight(Point p) {
		boolean newGame = false;
		boolean loadGame = false;
		boolean quitGame = false;
		Iterator<Integer> iter = view.pick(p);
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			if(iter.current() == MainMenuView.NEW_GAME_BUTTON)
				newGame = true;
			else if(iter.current() == MainMenuView.LOAD_GAME_BUTTON)
				loadGame = true;
			else if(iter.current() == MainMenuView.QUIT_GAME_BUTTON)
				quitGame = true;
		}
		view.setNewGameHighlight(newGame);
		view.setLoadGameHighlight(loadGame);
		view.setQuitGameHighlight(quitGame);
	}
	
	private void unhighlight() {
		view.setNewGameHighlight(false);
		view.setLoadGameHighlight(false);
		view.setQuitGameHighlight(false);		
	}
}
