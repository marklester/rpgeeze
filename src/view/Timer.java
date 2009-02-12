

public class Time {
//CurrentTime
	private long start;
	
	public Time()
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

