package rpgeeze.controller;

import java.awt.event.WindowEvent;

import rpgeeze.GameManager;
import rpgeeze.dp.Observer;
import rpgeeze.util.EventAdapter;
import rpgeeze.view.View;

/**
 * Superclass for the game's event-handling mechanisms.
 *
 * @param <T> the type of the controlled view
 */
public abstract class Controller<T extends View<?>> extends EventAdapter implements Observer<View<?>> {
	private GameManager manager;
	private T view;
	
	/**
	 * Constructs a controller that is in charge of the specified view and
	 * answers to the specified game manager.
	 * 
	 * @param manager the game manager
	 * @param view the controlled view
	 */
	public Controller(GameManager manager, T view) {
		this.manager = manager;
		this.view = view;
		view.attachObserver(this);
	}
	
	/**
	 * Retrieves the game manager that employs this controller.
	 * 
	 * @return the game manager
	 */
	protected GameManager getManager() {
		return manager;
	}

	/**
	 * Retrieves the view controlled by this controller. 
	 * 
	 * @return the controlled view
	 */
	protected T getView() {
		return view;
	}
	
	/**
	 * Stops the game manager and exits the application.
	 * 
	 */
	public void windowClosing(WindowEvent e) {
		getManager().stop();
		System.exit(0);
	}

	/**
	 * Reacts to a change in the state of the controlled <code>View</code>.
	 * Does nothing by default.
	 * 
	 */
	public void reactToChange() {
	}
}
