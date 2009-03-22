package rpgeeze.model;

public class Model {
	private boolean paused;
	private boolean active;
	
	public Model() {
		active = false;
		paused = false;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public void setPaused(boolean value) {
		paused = value;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void stop() {
		active = false;
	}
	
	public void start() {
		active = true;
	}
	
	public void update() {
		
	}
}
