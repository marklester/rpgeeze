package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.media.opengl.glu.GLU;

import rpgeeze.GameManager;
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
	Integer clicked[] = new Integer[1];
	boolean firstTime = true;
	KeyBindingsView.Button button;
//	Point p;
	int compare = 0;
	int mouseMoved = 0;

	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		if(e.getButton() == MouseEvent.BUTTON1) {
			int glName = getView().pickClosest(GLU.getCurrentGL(), p);
			button = KeyBindingsView.Button.fromGLName(glName);

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
						button.setHighlighted(true);
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
					case OPTIONS_VIEW:
						button.setHighlighted(true);
						highlight(e.getPoint());
						break;
					}
				}
				else{
					button.setHighlighted(false);
					getView().unhighlight();
				}
				compare = button.getGLName();
			}
		}	
		
	}



	public void keyPressed(KeyEvent e) {
		if(e.getID() == KeyEvent.KEY_PRESSED) {
			if(button != null && button.getHighlighted())
				getView().setCommand(button.toString(),e.getKeyText(e.getKeyCode()));
		}
	}


	public void mouseMoved(MouseEvent e) {
		/*p = e.getPoint();
		iter = getView().pick(e.getPoint());
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			KeyBindingsView.Button button = KeyBindingsView.Button.fromGLName(iter.current());
			if(button != null){
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
		}*/
	}

	private void highlight(Point p) {
		getView().unhighlight();
		int glName = getView().pickClosest(GLU.getCurrentGL(), p);
		getView().highlight(glName);
	}
}
