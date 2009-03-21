package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;

import rpgeeze.GameManager;
import rpgeeze.log.LogManager;
import rpgeeze.view.CreditsView;
import rpgeeze.view.MainMenuView;
import rpgeeze.view.CharacterCreationView;
import rpgeeze.view.HelpView;

import javax.media.opengl.glu.GLU;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import rpgeeze.view.OptionsMenuView;

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
		Point p = e.getPoint();
		if(e.getButton() == MouseEvent.BUTTON1) {
			int glName = getView().pickClosest(GLU.getCurrentGL(), p);
			MainMenuView.Button button = MainMenuView.Button.fromGLName(glName);
			if(button != null)
				switch(	button) {
				case NEW_GAME:
					CharacterCreationView ccv = new CharacterCreationView(getManager());
					CharacterCreationController ccc = new CharacterCreationController(getManager(), ccv);
					getManager().pushState(ccv, ccc);
					break;
				case LOAD_GAME:
					JFileChooser chooser = new JFileChooser();
					chooser.addChoosableFileFilter(new FileNameExtensionFilter("XML files", "xml"));
					int status = chooser.showOpenDialog(null);
					LogManager.getInstance().log("File chooser returned status " + status, "CONTROLLER");
					break;
				case OPTIONS:
					OptionsMenuView omv = new OptionsMenuView(getManager());
					OptionsMenuController omc = new OptionsMenuController(getManager(), omv);
					getManager().pushState(omv, omc);
					break;
				case HELP:
					HelpView hv = new HelpView(getManager());
					HelpViewController hc = new HelpViewController(getManager(), hv);
					getManager().pushState(hv, hc);
					break;
				case CREDITS:
					CreditsView cv = new CreditsView(getManager());
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
