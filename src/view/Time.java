package view;

import model.*;
import controller.*;

public class Time extends Thread{
//CurrentTime
	private long start;
	private Model model;
	private View view;
	
	public Time(Model model, View view)
	{
		this.model = model;
		this.view = view;
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
	/*
	public void run()
	{
		view.start();
		while(!gameEnd)
		{
			start = System.nanoTime();
			model.update();
			synchronized(view)
			{
				view.notify();
			}
			long timeDiff = System.nanoTime() - start;			
		}
		
	}
	*/
	public void run()
	{
		
	}
}

