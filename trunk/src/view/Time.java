package view;

import model.*;
import controller.*;

public class Time extends Thread{
//CurrentTime	
	private Model model;
	private View view;
	
	private long framePeriod_ms;
	
	public Time(Model model, View view)
	{
		this(model, view, 80);
	}
	public Time(Model model, View view, int fps)
	{
		this.model = model;
		this.view = view;
		framePeriod_ms = 1000/fps;
	}
	
	public void run()
	{
		view.start();

		while(!Thread.interrupted())
		{
			long start = System.nanoTime();
		
			model.update();
			synchronized(view)
			{
				view.notify();
			}						
			long timeDiff_ms = framePeriod_ms - (long)((System.nanoTime() - start)/1000000);		
			if(timeDiff_ms > 0)
				try{	
					Thread.sleep(timeDiff_ms);
				}catch (InterruptedException ie)
				{ this.interrupt();	}
		}
		view.interrupt();
	
	}
}