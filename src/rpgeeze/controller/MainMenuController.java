package rpgeeze.controller;

import java.awt.event.MouseEvent;

import rpgeeze.view.MainMenuView;

public class MainMenuController extends Controller {
	private MainMenuView view;
	
	public MainMenuController(MainMenuView view) {
		this.view = view;
	}

	public void mousePressed(MouseEvent e) {
		view.pickQueue.add(e.getPoint());
	}
}
