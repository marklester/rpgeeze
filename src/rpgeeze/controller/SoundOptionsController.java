package rpgeeze.controller;

import java.awt.Point;

import java.awt.event.MouseEvent;

import javax.media.opengl.glu.GLU;

import rpgeeze.GameManager;
import rpgeeze.view.SoundOptionsView;

public class SoundOptionsController extends HighlightableViewController<SoundOptionsView> {
	public SoundOptionsController(GameManager manager, SoundOptionsView view) {
		super(manager, view);
	}

	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		if(e.getButton() == MouseEvent.BUTTON1) {
			String name = getView().pickClosest(GLU.getCurrentGL(), p);
			if(name != null) {
				if(name.equals("Mute All")) {
					
				}
				else if(name.equals("Mute Background")) {	
				
				}
				else if(name.equals("Mute Effects")) {
				}
			}
		}
	}
}