package rpgeeze.util;

public class RunningAverageTimer extends Timer {	
	private long marks;
	private long startTime;
	private long accumulator;
	
	protected void doMark() {
		++marks;
	}

	protected void doStart() {
		startTime = System.nanoTime();
	}
	
	protected void doStop() {
		accumulator = nanosecondsElapsed();
	}
	
	protected long marksElapsed() {
		return marks;
	}

	public void reset() {
		marks = 0;
		startTime = System.nanoTime();
		accumulator = 0;
	}

	protected long nanosecondsElapsed() {
		return accumulator + System.nanoTime() - startTime;
	}
}
