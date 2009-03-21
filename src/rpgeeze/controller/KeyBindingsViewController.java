package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
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
	Iterator<Integer> iter;
	Integer clicked[] = new Integer[1];
	boolean firstTime = true;
	KeyBindingsView.Button button;
	Point p;
	int compare = 0;
	
	
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			//getView().unhighlight();
			p = e.getPoint();
			iter = getView().pick(e.getPoint());
			for(iter.reset(); !iter.isDone(); iter.advance()) {
				button = KeyBindingsView.Button.fromGLName(iter.current());
				
				if(button != null){
					
					if(compare != button.getGLName() || (compare == button.getGLName() && !button.getHighlighted())){
					
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
						button.setHighlighted(true);
						highlight(e.getPoint());
						break;	
					case E_ARROW:
						button.setHighlighted(true);
						highlight(e.getPoint());
						break;
					case W_ARROW:
						button.setHighlighted(true);
						highlight(e.getPoint());
						break;
					case NE_ARROW:
						button.setHighlighted(true);
						highlight(e.getPoint());
						break;
					case NW_ARROW:
						button.setHighlighted(true);
						highlight(e.getPoint());
						break;
					case SE_ARROW:
						button.setHighlighted(true);
						highlight(e.getPoint());
						break;
					case SW_ARROW:
						button.setHighlighted(true);
						highlight(e.getPoint());
						break;
					case SAVE_GAME:
						button.setHighlighted(true);
						highlight(e.getPoint());
						break;
					case LOAD_GAME:
						button.setHighlighted(true);
						highlight(e.getPoint());
						break;
					case NEW_GAME:
						button.setHighlighted(true);
						highlight(e.getPoint());
						break;
					case INVENTORY_VIEW:
						button.setHighlighted(true);
						highlight(e.getPoint());
						break;
					case STATS_VIEW:
						button.setHighlighted(true);
						highlight(e.getPoint());
						break;
					case SKILLS_VIEW:
						button.setHighlighted(true);
						highlight(e.getPoint());
						break;
					}
					}
					else{
						button.setHighlighted(false);
						getView().unhighlight();
					}
				}
			}
			}	
		compare = button.getGLName();
	}
	
	
	
	public void keyPressed(KeyEvent e) {
		if(e.getID() == KeyEvent.KEY_PRESSED) {
			for(iter.reset(); !iter.isDone(); iter.advance()) {
				KeyBindingsView.Button button = KeyBindingsView.Button.fromGLName(iter.current());
				char c = e.getKeyChar();
				if(button != null && (c == ' ' || Character.isLetter(c) || Character.isDigit(c)) && button.getHighlighted())
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
						getView().setNorthEastCommand(c);
						break;
					case NW_ARROW:
						getView().setNorthWestCommand(c);
						break;
					case SE_ARROW:
						getView().setSouthEastCommand(c);
						break;
					case SW_ARROW:
						getView().setSouthWestCommand(c);
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
	
	public void mouseEntered(MouseEvent e) {
			p = e.getPoint();
			iter = getView().pick(e.getPoint());
			for(iter.reset(); !iter.isDone(); iter.advance()) {
				KeyBindingsView.Button button = KeyBindingsView.Button.fromGLName(iter.current());
				if(button != null)
					switch(	button) {
					case OK:
						highlight(e.getPoint());
						break;
					case CANCEL:
						highlight(e.getPoint());
						break;
					case DEFAULTS:
						highlight(e.getPoint());
						break;
					}
			}
	}
		
	

	public void mouseMoved(MouseEvent e) {
		p = e.getPoint();
		iter = getView().pick(e.getPoint());
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			KeyBindingsView.Button button = KeyBindingsView.Button.fromGLName(iter.current());
			if(button != null)
				switch(	button) {
				case OK:
					highlight(e.getPoint());
					break;
				case CANCEL:
					highlight(e.getPoint());
					break;
				case DEFAULTS:
					highlight(e.getPoint());
					break;
				}
		}
		
	}

	public void mouseExited(MouseEvent e) {
			p = e.getPoint();
			iter = getView().pick(e.getPoint());
			for(iter.reset(); !iter.isDone(); iter.advance()) {
				KeyBindingsView.Button button = KeyBindingsView.Button.fromGLName(iter.current());
				if(button != null)
					switch(	button) {
					case OK:
						getView().unhighlight();
						break;
					case CANCEL:
						getView().unhighlight();
						break;
					case DEFAULTS:
						getView().unhighlight();
						break;
					default:
						getView().unhighlight();
						break;
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
}
