package rpgeeze.controller;

import static rpgeeze.RunGame.KEY_CONTROLS;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import rpgeeze.GameManager;
import rpgeeze.RunGame;
import rpgeeze.dp.Command;
import rpgeeze.log.LogManager;
import rpgeeze.view.GameplayView;
import rpgeeze.view.OptionsMenuView;


public class GameplayController extends Controller<GameplayView> {
	private MouseEvent prev = null;

	private double ZOOM_STEP = 0.025;

	private HashMap<String, Command> actions;

	private boolean wasPaused;

	public GameplayController(GameManager manager, GameplayView view) {
		super(manager, view);
		actions = new HashMap<String, Command>();
		actions.put("Pause Game", new Command() {
			public void execute() {
				getManager().getModel().togglePaused();
			}
		});
		actions.put("Options", new Command() {
			public void execute() {
				wasPaused = getManager().getModel().isPaused();
				LogManager.getInstance().log("Model paused state is " + wasPaused, "CONTROLLER");
				OptionsMenuView omv = new OptionsMenuView(getManager());
				OptionsMenuController omc = new OptionsMenuController(getManager(), omv);
				getManager().pushState(omv, omc);
			}
		});
	}

	public void reactToChange() {
		switch(getView().getState()) {
		case NORMAL:
			getManager().getModel().start();
			LogManager.getInstance().log("Restoring paused state to " + wasPaused, "CONTROLLER");
			getManager().getModel().setPaused(wasPaused);
			break;
		case HIDDEN:
			getManager().getModel().setPaused(true);
			break;
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
		LogManager lm = LogManager.getInstance();
		String keyPress = KeyEvent.getKeyText(e.getKeyCode());
		String action = null;
		for(Entry<String, String> entry: RunGame.KEY_CONTROLS.entrySet())
			if(entry.getValue().equals(keyPress)) {
				action = entry.getKey();
				break;
			}
		lm.log("Pressed " + keyPress + ", " + (action == null ? "no action" : "action is " + action) , "CONTROLLER");
		if(action != null) {
			Command cmd = actions.get(action);
			if(cmd != null)
				cmd.execute();
		}
	}
}
