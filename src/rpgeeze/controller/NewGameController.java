package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import rpgeeze.GameManager;
import rpgeeze.dp.Iterator;
import rpgeeze.view.GameplayView;
import rpgeeze.view.OccupationSelectionView;
import rpgeeze.view.OccupationSelectionView.OccupationSelectionButton;
import rpgeeze.model.map.FiniteMatrixMap;

/**
 * Controls the main menu screen.
 */
public class NewGameController extends Controller {
	private OccupationSelectionView view;
	
	public NewGameController(GameManager manager, OccupationSelectionView view) {
		super(manager);
		this.view = view;
	}
	
	/**
	 * Highlights the button at the cursor position, if any.
	 */
	public void mouseEntered(MouseEvent e) {
		highlight(e.getPoint());
	}

	/**
	 * Highlights the button at the cursor position, if any.
	 */
	public void mouseMoved(MouseEvent e) {
		highlight(e.getPoint());
	}

	/**
	 * Turns off highlighting on all buttons.
	 */
	public void mouseExited(MouseEvent e) {
		unhighlight();		
	}

	/**
	 * Turns off highlighting on all buttons.
	 */
	public void windowLostFocus(WindowEvent e) {
		unhighlight();
	}

	/**
	 * Turns off highlighting on all buttons.
	 */
	public void windowActivated(WindowEvent e) {
		unhighlight();
	}

	/**
	 * Turns off highlighting on all buttons.
	 */
	public void windowDeactivated(WindowEvent e) {
		unhighlight();
	}

	/**
	 * Executes the action corresponding to the clicked button, if any.
	 */
	public void mouseClicked(MouseEvent e) {
		Iterator<Integer> iter = view.pick(e.getPoint());
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			OccupationSelectionButton button = OccupationSelectionButton.fromGLName(iter.current());
			if(button != null)
			switch(button) {
			case OK:
				GameplayView gv = new GameplayView(new FiniteMatrixMap());
				GameplayController gc = new GameplayController(getManager(), gv);
				getManager().pushState(gv, gc);
				break;
			case CANCEL:
				getManager().popState();
				break;
			}
		}
	}

	private void highlight(Point p) {
		int hi = 0;
		Iterator<Integer> iter = view.pick(p);
		for(iter.reset(); !iter.isDone(); iter.advance()) {
			int cur = iter.current();
			if(cur > 0)
				hi = cur;
		}
		view.setHighlightedButton(hi);
	}

	private void unhighlight() {
		view.setHighlightedButton(0);		
	}
}
