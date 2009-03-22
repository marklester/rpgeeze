package rpgeeze.model;

import rpgeeze.GameProperties;

public class ModelThread extends Thread {
	private final Model model;
	
	public ModelThread(Model model) {
		this.model = model;
	}
	
	public void run() {
		while(!Thread.interrupted() && model.isActive()) {
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
