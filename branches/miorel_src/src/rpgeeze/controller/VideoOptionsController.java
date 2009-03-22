package rpgeeze.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.media.opengl.glu.GLU;
import rpgeeze.view.VideoOptionsView;
import rpgeeze.GameManager;

/**
 * Controls the options menu screen.
 */
public class VideoOptionsController extends HighlightableViewController<VideoOptionsView> {
	public VideoOptionsController(GameManager manager, VideoOptionsView view) {
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
				if(name.equals("Full Screen")) {	
					//getManager().changeToFullScreen();
				}
				else if(name.equals("Window Screen")) {	
					//getManager().changeToWindowScreen();
				}
				else if(name.equals("Back")) {
					getManager().popState();
				}
			}
		}
	}
}