package rpgeeze.controller;

import java.awt.event.MouseEvent;

import rpgeeze.GameManager;
import rpgeeze.dp.Iterator;
import rpgeeze.view.KeyBindingsView;

/**
 * Controls the main menu screen.
 */
public class KeyBindingsViewController extends HighlightableViewController<KeyBindingsView> {
	public KeyBindingsViewController(GameManager manager, KeyBindingsView view) {
		super(manager, view);
	}

	/**
	 * Executes the action corresponding to the clicked button, if any.
	 */
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			Iterator<Integer> iter = getView().pick(e.getPoint());
			for(iter.reset(); !iter.isDone(); iter.advance()) {
				KeyBindingsView.Button button = KeyBindingsView.Button.fromGLName(iter.current());
				if(button != null)
					switch(	button) {
					case OK:
						getManager().popState();
						break;
					case CANCEL:
						getManager().popState();
						break;
					case N_ARROW:
						break;
					case S_ARROW:
						
						break;			
					}
			}
		}
	}
	
	/*private void changeColor(Point p) {
		getView().unhighlight();
		Iterator<Integer> iter = getView().pick(p);
		iter.reset();
		for(iter.reset(); !iter.isDone(); iter.advance())
			getView().highlight(iter.current());
	}*/
}
