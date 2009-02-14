package view;

import model.*;
import controller.*;

public class Time extends Thread{
//CurrentTime	
	private Model model;
	private View view;
	
	private int framePeriod_ms;
	
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
	
	public void run()
	{
		view.start();
		try
		{
			while(!Thread.interrupted())
			{
				long start = System.nanoTime();
				model.update();
				synchronized(view)
				{
					view.notify();
				}
				long timeDiff_ms = framePeriod_ms - (System.nanoTime() - start)/1000000L;		
				if(timeDiff_ms > 0)
					Thread.sleep(timeDiff_ms);
			}
		}catch(InterruptedException e)
		{}
	}
}