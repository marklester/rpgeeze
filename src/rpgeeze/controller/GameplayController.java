package rpgeeze.controller;

import static rpgeeze.RunGame.KEY_CONTROLS;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Set;

import rpgeeze.GameManager;
import rpgeeze.view.GameplayView;


public class GameplayController extends Controller<GameplayView> {
	private MouseEvent prev = null;
	
	private double ZOOM_STEP = 0.025;
	
	public GameplayController(GameManager manager, GameplayView view) {
		super(manager, view);
	}
	
	public void reactToChange() {
		if(getView().getState() == GameplayView.State.NORMAL) {
			getManager().getModel().start();
			getManager().getModel().setPaused(false);
		}
	}

	public void mouseReleased(MouseEvent e) {
		prev = null;
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3)
			prev = e;
	}
	
	public void mouseDragged(MouseEvent e) {
		if(prev != null) {
			double dy = e.getPoint().getY() - prev.getPoint().getY();
			getView().zoom(dy * ZOOM_STEP);
			prev = e;
		}
	}
	
	public void keyPressed(KeyEvent e){
		
		if(e.getID() == KeyEvent.KEY_PRESSED) {
			String action = e.getKeyText(e.getKeyCode());
			
			Set s = KEY_CONTROLS.entrySet();
			Iterator a =  s.iterator();
			
			while(a.hasNext()){
				StringBuilder tempKey = new StringBuilder(a.next().toString());
				String key = tempKey.substring(tempKey.length()-1);
				
				if(action.equals(key)) {
					System.out.println(tempKey.delete(tempKey.length()-2,tempKey.length()));//prints action for now
				}
			}
			
		}
		
	}
}
