package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.media.opengl.glu.GLU;

import rpgeeze.GameManager;
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
		Point p = e.getPoint();
		if(e.getButton() == MouseEvent.BUTTON1) {
			String name = getView().pickClosest(GLU.getCurrentGL(), p);
			if(name != null) {
				if(name.equals("OK"))
					executeOK();
				else if(name.equals("Cancel"))
					executeCancel();
				else if(name.equals("Left Arrow"))
					getView().previousOccupation();
				else if(name.equals("Right Arrow"))
					getView().nextOccupation();
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
