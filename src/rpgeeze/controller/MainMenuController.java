package rpgeeze.controller;

import java.awt.event.MouseEvent;

import rpgeeze.GameManager;
import rpgeeze.dp.Iterator;
import rpgeeze.log.LogManager;
import rpgeeze.log.Message;
import rpgeeze.view.CreditsView;
import rpgeeze.view.MainMenuView;
import rpgeeze.view.CharacterCreationView;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
                        chooser.addChoosableFileFilter(new FileNameExtensionFilter("XML files", "xml"));
                        int status = chooser.showOpenDialog(null);
                        LogManager.getInstance().log(new Message("File chooser returned status " + status, "CONTROLLER"));
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
}
