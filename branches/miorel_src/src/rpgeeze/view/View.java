package rpgeeze.view;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import rpgeeze.controller.Controller;

public abstract class View implements Iterable<Controller> {
	private Queue<Controller> eventListeners = new LinkedList<Controller>();
	
	public abstract void display();
	
	public void changeFrom() {
	}
	
	public void changeTo() {
	}
	
	public void registerEventListener(Controller c) {
		if(c == null)
			throw new NullPointerException("Can't register null event listener.");
		if(eventListeners.contains(c))
			throw new IllegalArgumentException("Event listener already registered.");
		eventListeners.add(c);
	}
	
	public void unregisterEventListener(Controller c) {
		if(c == null)
			throw new NullPointerException("Can't unregister null event listener.");
		if(!eventListeners.contains(c))
			throw new IllegalArgumentException("Event listener wasn't registered, can't unregister.");
		eventListeners.remove(c);
	}
	
	public Iterator<Controller> iterator() {
		return eventListeners.iterator();
	}
}
