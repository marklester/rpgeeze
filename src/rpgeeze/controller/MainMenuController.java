package rpgeeze.controller;

import java.awt.event.MouseEvent;

import rpgeeze.GameManager;
import rpgeeze.dp.Iterator;
import rpgeeze.view.CreditsView;
import rpgeeze.view.MainMenuView;
import rpgeeze.view.CharacterCreationView;
import javax.swing.JFileChooser;
import rpgeeze.util.GameFilter;
import javax.swing.UIManager;

/**
 * Controls the main menu screen.
 */
public class MainMenuController extends HighlightableViewController<MainMenuView> {
	public MainMenuController(GameManager manager, MainMenuView view) {
		super(manager, view);
	}

	/**
	 * Executes the action corresponding to the clicked button, if any.
	 */
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			Iterator<Integer> iter = getView().pick(e.getPoint());
			for(iter.reset(); !iter.isDone(); iter.advance()) {
				MainMenuView.Button button = MainMenuView.Button.fromGLName(iter.current());
				if(button != null)
					switch(	button) {
					case NEW_GAME:
						CharacterCreationView ccv = new CharacterCreationView();
						CharacterCreationController ccc = new CharacterCreationController(getManager(), ccv);
						getManager().pushState(ccv, ccc);
						break;
					case LOAD_GAME:
						JFileChooser chooser = new JFileChooser(); 
                        chooser.addChoosableFileFilter(new GameFilter());
                        int status = chooser.showOpenDialog(null);
						break;
					case OPTIONS:
						break;
					case HELP:
						break;
					case CREDITS:
						CreditsView cv = new CreditsView();
						CreditsController cc = new CreditsController(getManager(), cv);
						getManager().pushState(cv, cc);
						break;
					case QUIT:
						getManager().stop();
						System.exit(0);
						break;			
					}
			}
		}
	}
	{
		try {
			UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
