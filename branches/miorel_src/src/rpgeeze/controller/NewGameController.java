package rpgeeze.controller;

import java.awt.event.MouseEvent;

import rpgeeze.GameManager;
import rpgeeze.dp.Iterator;
import rpgeeze.view.GameplayView;
import rpgeeze.view.OccupationSelectionView;
import rpgeeze.view.OccupationSelectionView.OccupationSelectionButton;
import rpgeeze.model.map.FiniteMatrixMap;

/**
 * Controls the main menu screen.
 */
public class NewGameController extends HighlightableViewController<OccupationSelectionView> {
	public NewGameController(GameManager manager, OccupationSelectionView view) {
		super(manager, view);
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
}
