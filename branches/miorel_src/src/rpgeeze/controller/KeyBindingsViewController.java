package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

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
	Iterator<Integer> iter;
	Point p;
	public void mouseClicked(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON1) {
			getView().unhighlight();
			p = e.getPoint();
			iter = getView().pick(e.getPoint());
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
						getView().defaults();
						break;
					case N_ARROW:
						highlight(e.getPoint());
						break;
					case S_ARROW:
						highlight(e.getPoint());
						break;	
					case E_ARROW:
						highlight(e.getPoint());
						break;
					case W_ARROW:
						highlight(e.getPoint());
						break;
					case NE_ARROW:
						highlight(e.getPoint());
						break;
					case NW_ARROW:
						highlight(e.getPoint());
						break;
					case SE_ARROW:
						highlight(e.getPoint());
						break;
					case SW_ARROW:
						highlight(e.getPoint());
						break;
					case SAVE_GAME:
						highlight(e.getPoint());
						break;
					case LOAD_GAME:
						highlight(e.getPoint());
						break;
					case NEW_GAME:
						highlight(e.getPoint());
						break;
					case INVENTORY_VIEW:
						highlight(e.getPoint());
						break;
					case STATS_VIEW:
						highlight(e.getPoint());
						break;
					case SKILLS_VIEW:
						highlight(e.getPoint());
						break;
					}
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getID() == KeyEvent.KEY_PRESSED) {
			for(iter.reset(); !iter.isDone(); iter.advance()) {
				KeyBindingsView.Button button = KeyBindingsView.Button.fromGLName(iter.current());
				char c = e.getKeyChar();
				if(button != null && (c == ' ' || Character.isLetter(c) || Character.isDigit(c)))
					switch(	button) {
					case N_ARROW:
						getView().setNorthCommand(c);
						break;
					case S_ARROW:
						getView().setSouthCommand(c);
						break;	
					case E_ARROW:
						getView().setEastCommand(c);
						break;
					case W_ARROW:
						getView().setWestCommand(c);
						break;
					case NE_ARROW:
						break;
					case NW_ARROW:
						break;
					case SE_ARROW:
						break;
					case SW_ARROW:
						break;
					case SAVE_GAME:
						getView().setSaveGameCommand(c);
						break;
					case LOAD_GAME:
						getView().setLoadGameCommand(c);
						break;
					case NEW_GAME:
						getView().setNewGameCommand(c);
						break;
					case INVENTORY_VIEW:
						getView().setInventoryView(c);
						break;
					case STATS_VIEW:
						getView().setStatsView(c);
						break;
					case SKILLS_VIEW:
						getView().setSkillsView(c);
						break;
					
					}
			}
		}
	}
	
	private void highlight(Point p) {
		getView().unhighlight();
		Iterator<Integer> iter = getView().pick(p);
		iter.reset();
		for(iter.reset(); !iter.isDone(); iter.advance())
			getView().highlight(iter.current());
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void windowLostFocus(WindowEvent e) {
		
	}

	public void windowActivated(WindowEvent e) {
		
	}

	public void windowDeactivated(WindowEvent e) {
		
	}
}
