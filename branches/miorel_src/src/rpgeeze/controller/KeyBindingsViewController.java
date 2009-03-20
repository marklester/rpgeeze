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
					case DEFAULTS:
						break;
					case N_ARROW:
						break;
					case S_ARROW:
						break;	
					case E_ARROW:
						break;
					case W_ARROW:
						break;
					case NE_ARROW:
						break;
					case NW_ARROW:
						break;
					case SE_ARROW:
						break;
					case SW_ARROW:
						break;
					case DROP_ITEM:
						break;
					case EQUIP_ITEM:
						break;
					case SAVE_GAME:
						break;
					case LOAD_GAME:
						break;
					case NEW_GAME:
						break;
					case INVENTORY_VIEW:
						break;
					case STATS_VIEW:
						break;
					case SKILLS_VIEW:
						break;
					
					}
			}
		}
	}
}
