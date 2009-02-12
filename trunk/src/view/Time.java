package view;

import model.*;
import controller.*;

public class Time extends Thread{
//CurrentTime
	private long start;
	private Model model;
	private View view;
	
	private int framePeriod_ms;
	
	private boolean gameEnd = false;
	
	public Time(Model model, View view)
	{
		this(model, view, 80);
	}
	public Time(Model model, View view, int fps)
	{
		this.model = model;
		this.view = view;
		framePeriod_ms = 1/fps * 1000;
	}
	/*
	private void timerStart()
	{
		start = System.nanoTime();
	}
	
	public long timerStop()
	{
		long stop = System.nanoTime();
		return stop - start;
	}
	*/
	
	public void run()
	{
		view.start();
		try
		{
			while(!Thread.interrupted())
			{
				start = System.nanoTime();
				model.update();
				synchronized(view)
				{
					view.notify();
				}
				long timeDiff_ms = framePeriod_ms - (System.nanoTime() - start)/1000000L;			
				Thread.sleep(timeDiff_ms);
			}
		}catch(InterruptedException e)
		{}
		
	}
	
}

