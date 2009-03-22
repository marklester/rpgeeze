package rpgeeze.controller;

import static rpgeeze.RunGame.KEY_CONTROLS;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import rpgeeze.GameManager;
import rpgeeze.RunGame;
import rpgeeze.dp.Command;
import rpgeeze.view.GameplayView;


public class GameplayController extends Controller<GameplayView> {
	private MouseEvent prev = null;
	
	private double ZOOM_STEP = 0.025;
	
	private HashMap<String, Command> actions;
	
	public GameplayController(GameManager manager, GameplayView view) {
		super(manager, view);
		actions = new HashMap<String, Command>();
		actions.put("Pause Game", new Command() {
			public void execute() {
				getManager().getModel().togglePaused();
			}
		});
		
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
		Command cmd = actions.get(RunGame.KEY_CONTROLS.get(KeyEvent.getKeyText(e.getKeyCode())));
		if(cmd != null)
			cmd.execute();
	}
}
