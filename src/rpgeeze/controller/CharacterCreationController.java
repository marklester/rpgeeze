package rpgeeze.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.media.opengl.glu.GLU;

import rpgeeze.GameManager;
import rpgeeze.dp.Iterator;
import rpgeeze.view.GameplayView;
import rpgeeze.view.CharacterCreationView;
import rpgeeze.model.Entity;
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
		if(e.getButton() == MouseEvent.BUTTON1) {
			Iterator<Integer> iter = getView().pickAll(GLU.getCurrentGL(), e.getPoint());
			for(iter.reset(); !iter.isDone(); iter.advance()) {
				CharacterCreationView.Button button = CharacterCreationView.Button.fromGLName(iter.current());
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
	}

	protected void executeOK() {
		getView().startZoom();
	}

	protected void executeCancel() {
		getManager().popState();		
	}
	
	public void reactToChange() {
		if(getView().getState() == CharacterCreationView.State.ZOOMED) {
			Entity avatar = new FiniteMatrixMap().getAvatar();
			GameplayView gv = new GameplayView(getManager(), avatar);
			GameplayController gc = new GameplayController(getManager(), gv);
			getManager().pushState(gv, gc);
		}
	}
}
