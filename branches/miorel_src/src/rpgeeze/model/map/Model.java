package rpgeeze.model.map;

import rpgeeze.log.LogManager;

public class Model extends Thread {
	private boolean paused = false;
	private int ups;
	
	public Model(int ups) {
		setUPS(ups);
	}
	
	public void setUPS(int ups) {
		this.ups = ups;
	}
	
	public void update() {
		
	}
	
	public synchronized void pauseGame() {
		LogManager.getInstance().log("Pausing game", "MODEL");
		paused = true;
	}

	public synchronized void resumeGame() {
		LogManager.getInstance().log("Resuming game", "MODEL");
		paused = false;
		notifyAll();
	}

	public synchronized void stopGame() {
		interrupt();
	}
	
	public synchronized boolean isPaused() {
		return paused;
	}

	public void run() {
		LogManager lm = LogManager.getInstance();
		long prev = System.nanoTime();
		try {
			while(!interrupted()) {
				synchronized(this) {
					if(isPaused()) {
						lm.log("Game is paused, going to sleep", "MODEL");
						wait();
						lm.log("Woke up, getting back to work", "MODEL");
					}
				}
				update();
				long time = System.nanoTime();
				long nanoDiff = time - prev;
				prev = time;
				long nap = (1000000000 - nanoDiff * ups) / ups;
				if(nap > 0)
					sleep(nap);
			}
		}
		catch(InterruptedException e) {
		}
	}
}
