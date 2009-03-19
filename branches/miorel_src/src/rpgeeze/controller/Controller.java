package rpgeeze.controller;

import java.awt.event.WindowEvent;

import rpgeeze.GameManager;
import rpgeeze.dp.Observer;
import rpgeeze.util.EventAdapter;
import rpgeeze.view.View;

/**
 * Abstract listener interface implementer providing empty handlers for most event types. Subclass to do interesting things.
 *
 */
public abstract class Controller<T extends View<?>> extends EventAdapter implements Observer<View<?>> {
	private GameManager manager;
	private T view;
	
	public Controller(GameManager manager, T view) {
		this.manager = manager;
		this.view = view;
		view.attach(this);
	}
	
	protected GameManager getManager() {
		return manager;
	}

	protected T getView() {
		return view;
	}
	
	/**
	 * Stops the game manager and exits the application.
	 */
	public void windowClosing(WindowEvent e) {
		getManager().stop();
		System.exit(0);
	}

	public void update() {
	}
}
