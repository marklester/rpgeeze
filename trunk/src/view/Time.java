package view;

import model.Model;

public class Time extends Thread {
	// CurrentTime
	private final Model model;
	private final View view;

	private final long framePeriod_ms;

	public Time(Model model, View view) {
		this(model, view, 80);
	}

	public Time(Model model, View view, int fps) {
		this.model = model;
		this.view = view;
		this.framePeriod_ms = 1000 / fps;
	}

	public void run() {
		this.view.start();

		while(!Thread.interrupted() && !model.isPaused()) {
			long start = System.nanoTime();

			this.model.update();

			synchronized(this.view) {
				this.view.notify();
			}
			long timeDiff_ms = this.framePeriod_ms - (System.nanoTime() - start) / 1000000;
			// System.out.println(timeDiff_ms);
			if(timeDiff_ms > 0)
				try {
					Thread.sleep(timeDiff_ms);
				}
				catch(InterruptedException ie) {
					interrupt();
				}
		}
		this.view.interrupt();

	}
}