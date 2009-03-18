package rpgeeze.controller;

import java.awt.event.KeyEvent;

import rpgeeze.GameManager;
import rpgeeze.view.CreditsView;

public class CreditsController extends Controller<CreditsView> {
	public CreditsController(GameManager manager, CreditsView view) {
		super(manager, view);
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ESCAPE)
			getManager().popState();
	}
}
