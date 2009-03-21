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
			String name = getView().pickClosest(GLU.getCurrentGL(), p);
			if(name != null) {
				if(name.equals("New Game")) {
					CharacterCreationView ccv = new CharacterCreationView(getManager());
					CharacterCreationController ccc = new CharacterCreationController(getManager(), ccv);
					getManager().pushState(ccv, ccc);
				}
				else if(name.equals("Load Game")) {
					JFileChooser chooser = new JFileChooser();
					chooser.addChoosableFileFilter(new FileNameExtensionFilter("XML files", "xml"));
					int status = chooser.showOpenDialog(null);
					LogManager.getInstance().log("File chooser returned status " + status, "CONTROLLER");
				}
				else if(name.equals("Options")) {
					OptionsMenuView omv = new OptionsMenuView(getManager());
					OptionsMenuController omc = new OptionsMenuController(getManager(), omv);
					getManager().pushState(omv, omc);
				}
				else if(name.equals("Help")) {
					HelpView hv = new HelpView(getManager());
					HelpViewController hc = new HelpViewController(getManager(), hv);
					getManager().pushState(hv, hc);
				}
				else if(name.equals("Credits")) {
					CreditsView cv = new CreditsView(getManager());
					CreditsController cc = new CreditsController(getManager(), cv);
					getManager().pushState(cv, cc);		
				}
				else if(name.equals("Quit")) {
					getManager().stop();
					System.exit(0);
				}
			}
		}
	}
}
