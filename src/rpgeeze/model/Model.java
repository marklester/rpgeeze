package rpgeeze.model;

import rpgeeze.log.LogManager;
import rpgeeze.model.map.Map;

public class Model {
	private boolean paused;
	private boolean active;
	
	private Map map;
	private Entity avatar;
	
	private LogManager lm;
	
	public Model(Map map, Entity avatar) {
		this.map = map;
		this.avatar = avatar;
		active = true;
		paused = true;
		lm = LogManager.getInstance();
	}
	
	public synchronized boolean isPaused() {
		return paused;
	}
	
	public synchronized void togglePaused() {
		setPaused(!isPaused());
	}
	
	public synchronized void setPaused(boolean value) {
		lm.log("Setting model paused value to " + value, "MODEL");
		paused = value;
		if(!isPaused())
			notifyAll();
	}
	
	public synchronized boolean isActive() {
		return active;
	}
	
	public synchronized void stop() {
		lm.log("Stopping model", "MODEL");
		active = false;
	}
	
	public synchronized void start() {
		lm.log("Starting model", "MODEL");
		active = true;
	}
	
	public synchronized void update() {
		lm.log("Updating...", "MODEL");
	}
}
