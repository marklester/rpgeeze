package rpgeeze.model;

import rpgeeze.GameProperties;
import rpgeeze.log.LogManager;

public class ModelThread extends Thread {
	private final Model model;
	
	public ModelThread(Model model) {
		this.model = model;
	}
	
	public void run() {
		LogManager.getInstance().log("Updater thread started running", "UPDATER");
		while(!Thread.interrupted() && model.isActive()) {
			synchronized(model) {
				while(model.isActive() && model.isPaused()) {
					LogManager.getInstance().log("Model is paused, sleeping", "UPDATER");
					try {
						model.wait();
					}
					catch (InterruptedException e) {
					}
					LogManager.getInstance().log("Woke up, getting back to work", "UPDATER");
				}
			}
			long start = System.nanoTime();
			model.update();
			long duration = System.nanoTime() - start;
			int ups = GameProperties.getInstance().getGoalUPS();
			long nap = (1000000000 - duration * ups) / ups / 1000000;
			if(nap > 0) {
				try {
					Thread.sleep(nap);
				}
				catch (InterruptedException e) {
					interrupt();
				}
			}
		}
	}
}
