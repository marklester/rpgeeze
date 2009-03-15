package rpgeeze;

import java.util.ArrayList;
import java.util.List;

import rpgeeze.controller.Controller;
import rpgeeze.util.cmd.Command;

public class EventProcessor extends Thread {
	private ArrayList<Command<Controller>> events = new ArrayList<Command<Controller>>();
	
	private List<Controller> controllers = new ArrayList<Controller>();
	
	private static EventProcessor instance;
	
	private EventProcessor() {
	}
	
	public static EventProcessor getInstance() {
		if(instance == null)
			instance = new EventProcessor();
		return instance;
	}
	
	public void addController(Controller c) {
		controllers.add(c);
	}

	public void removeController(Controller c) {
		controllers.remove(c);
	}
	
	public void queueEvent(Command<Controller> e) {
		synchronized(events) {
			events.add(e);
			events.notifyAll();
		}
	}
	
	public void run() {
		boolean keepRunning = true;
		while(keepRunning && !Thread.interrupted()) {
			List<Command<Controller>> list = null;
			synchronized(events) {
				try {
					while(events.isEmpty())
						events.wait();
				}
				catch (InterruptedException e) {
					break;
				}
				list = (ArrayList<Command<Controller>>) events.clone();
				events.clear();
			}
			for(Command<Controller> cmd: list)
				for(Controller c: controllers)
					cmd.execute(c);
		}
	}
}
