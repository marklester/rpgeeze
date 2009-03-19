package rpgeeze.model.map;

import rpgeeze.RunGame;
import rpgeeze.log.LogManager;
import rpgeeze.log.Message;

public class Model extends Thread {
	private boolean paused = false;

	public void update() {
		
	}
	
	public synchronized void pauseGame() {
		LogManager.getInstance().log(new Message("Pausing game", "MODEL"));
		paused = true;
	}

	public synchronized void resumeGame() {
		LogManager.getInstance().log(new Message("Resuming game", "MODEL"));
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
						lm.log(new Message("Game is paused, going to sleep", "MODEL"));
						wait();
						lm.log(new Message("Woke up, getting back to work", "MODEL"));
					}
				}
				update();
				long time = System.nanoTime();
				long nanoDiff = time - prev;
				prev = time;
				long nap = (1000000000 - nanoDiff * RunGame.GOAL_UPS) / RunGame.GOAL_UPS;
				if(nap > 0)
					sleep(nap);
			}
		}
		catch(InterruptedException e) {
		}
	}
}
