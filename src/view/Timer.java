package view;

public class Timer {
//CurrentTime
	private long start;
	
	public Timer()
	{
	}
	
	public void start()
	{
		start = System.nanoTime();
	}
	
	public long stop()
	{
		long stop = System.nanoTime();
		return stop - start;
	}
}

