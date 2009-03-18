package rpgeeze.controller;

import java.awt.event.MouseEvent;

import rpgeeze.GameManager;
import rpgeeze.util.SimpleMovingAverageTimer;
import rpgeeze.util.Timer;
import rpgeeze.view.GameplayView;

public class GameplayController extends Controller<GameplayView> {
	private MouseEvent prev = null;
	
	private Timer fpsTimer = new SimpleMovingAverageTimer();
	
	private double ZOOM_STEP = 0.025;
	
	public GameplayController(GameManager manager, GameplayView view) {
		super(manager, view);
	}
	
	public void idleCycle() {
		fpsTimer.mark();
		GameplayView view = getView();
		view.setFpsText(String.format("FPS: %.1f", fpsTimer.marksPerSecond()));
		view.changeIntensity(0.01f);
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
	
	public void changeFrom() {
		fpsTimer.stop();
	}
	
	public void changeTo() {
		fpsTimer.start();
	}
}
