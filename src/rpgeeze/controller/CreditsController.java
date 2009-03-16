package rpgeeze.controller;

import java.awt.event.KeyEvent;

import rpgeeze.GameManager;
import rpgeeze.view.CreditsView;

public class CreditsController extends Controller {
	private CreditsView view;
	private int frames = 0;
	
	public CreditsController(GameManager manager, CreditsView view) {
		super(manager);
		this.view = view;
	}

	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ESCAPE)
			getManager().popState();
	}
	
	public void idleCycle() {
		++frames;
		if(frames >= 160) {
			frames = 0;
			view.nextDeveloper();
		}
	}
}
