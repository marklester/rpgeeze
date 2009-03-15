package rpgeeze.controller;

import java.awt.event.MouseEvent;

import rpgeeze.GameManager;
import rpgeeze.util.Iterator;
import rpgeeze.view.MainMenuView;

public class MainMenuController extends Controller {
	private MainMenuView view;
	private GameManager manager;
	
	public MainMenuController(GameManager manager, MainMenuView view) {
		this.manager = manager;
		this.view = view;
	}

	public void mouseMoved(MouseEvent e) {
		boolean newGame = false;
		boolean loadGame = false;
		boolean quitGame = false;
		Iterator<Integer> iter = view.pick(e.getPoint());
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
	
	public void mouseClicked(MouseEvent e) {
		Iterator<Integer> iter = view.pick(e.getPoint());
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			if(iter.current() == MainMenuView.NEW_GAME_BUTTON) {
				// new game	
			}
			else if(iter.current() == MainMenuView.LOAD_GAME_BUTTON) {
				// load game
			}
			else if(iter.current() == MainMenuView.QUIT_GAME_BUTTON)
				manager.stop(0);
		}
	}
}
