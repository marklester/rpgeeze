package rpgeeze.controller;

import java.awt.event.KeyEvent;

import rpgeeze.GameManager;
import rpgeeze.view.HelpView;

public class HelpViewController extends Controller<HelpView> {
	public HelpViewController(GameManager manager, HelpView view) {
		super(manager, view);
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ESCAPE)
			getManager().popState();
	}
}
