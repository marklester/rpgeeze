package rpgeeze.controller;

import java.awt.event.MouseEvent;

import rpgeeze.GameManager;
import rpgeeze.dp.Iterator;
import rpgeeze.view.CharacterCreationView;
import javax.swing.JFileChooser;
import rpgeeze.util.GameFilter;
import javax.swing.UIManager;
import rpgeeze.view.OptionsMenuView;

/**
 * Controls the main menu screen.
 */
public class OptionsMenuController extends HighlightableViewController<OptionsMenuView> {
	public OptionsMenuController(GameManager manager, OptionsMenuView view) {
		super(manager, view);
	}

	/**
	 * Executes the action corresponding to the clicked button, if any.
	 */
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			Iterator<Integer> iter = getView().pick(e.getPoint());
			for(iter.reset(); !iter.isDone(); iter.advance()) {
				OptionsMenuView.Button button = OptionsMenuView.Button.fromGLName(iter.current());
				if(button != null)
					switch(	button) {
					case KEY_BINDINGS:
						break;
					case SOUND_OPTIONS:
						break;
					case VIDEO_OPTIONS:
						break;
					case BACK:
						getManager().popState();
						break;			
					}
			}
		}
	}
}
