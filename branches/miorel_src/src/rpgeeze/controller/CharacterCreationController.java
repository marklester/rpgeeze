package rpgeeze.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import rpgeeze.GameManager;
import rpgeeze.dp.Iterator;
import rpgeeze.view.GameplayView;
import rpgeeze.view.CharacterCreationView;
import rpgeeze.view.CharacterCreationView.OccupationSelectionButton;
import rpgeeze.model.map.FiniteMatrixMap;

/**
 * Controls the main menu screen.
 */
public class CharacterCreationController extends HighlightableViewController<CharacterCreationView> {
	public CharacterCreationController(GameManager manager, CharacterCreationView view) {
		super(manager, view);
	}
	
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_TAB:
			getView().randomName();
			break;
		case KeyEvent.VK_BACK_SPACE:
			String name = getView().getCharacterName();
			if(name.length() != 0)
				name = name.substring(0, name.length() - 1);
			getView().setCharacterName(name);
			break;
		case KeyEvent.VK_ENTER:
			executeOK();
			break;
		case KeyEvent.VK_ESCAPE:
			executeCancel();
			break;
		default:
			char c = e.getKeyChar();
			if(c == ' ' || Character.isLetter(c) || Character.isDigit(c))
				getView().setCharacterName(getView().getCharacterName() + c);
		}
	}
	
	/**
	 * Executes the action corresponding to the clicked button, if any.
	 */
	public void mouseClicked(MouseEvent e) {
		Iterator<Integer> iter = getView().pick(e.getPoint());
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			OccupationSelectionButton button = OccupationSelectionButton.fromGLName(iter.current());
			if(button != null)
			switch(button) {
			case OK:
				executeOK();
				break;
			case CANCEL:
				executeCancel();
				break;
			case LEFT_ARROW:
				getView().previousOccupation();
				break;
			case RIGHT_ARROW:
				getView().nextOccupation();
				break;
			}
		}
	}

	private void executeOK() {
		GameplayView gv = new GameplayView(new FiniteMatrixMap());
		GameplayController gc = new GameplayController(getManager(), gv);
		getManager().pushState(gv, gc);
	}
	
	private void executeCancel() {
		getManager().popState();		
	}
}
