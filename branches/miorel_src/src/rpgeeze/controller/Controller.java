package rpgeeze.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

import rpgeeze.GameManager;

/**
 * Abstract listener interface implementer providing empty handlers for most event types. Subclass to do interesting things.
 *
 */
public abstract class Controller implements KeyListener, MouseListener, MouseMotionListener, WindowListener, WindowFocusListener {
	private GameManager manager;
	
	/**
	 * Create a new Controller which answers to the specified GameManager. 
	 * 
	 * @param manager the GameManager in charge
	 */
	public Controller(GameManager manager) {
		this.manager = manager;
	}
	
	protected GameManager getManager() {
		return manager;
	}

	/**
	 * Called once every display frame. Does nothing by default. Use for animation effects.
	 */
	public void idleCycle() {
	}
	
	/**
	 * Ignores the event.
	 */
	public void keyPressed(KeyEvent e) {
	}

	/**
	 * Ignores the event.
	 */
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Ignores the event.
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Ignores the event.
	 */
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Ignores the event.
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Ignores the event.
	 */
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Ignores the event.
	 */
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Ignores the event.
	 */
	public void mouseReleased(MouseEvent e) {
	}
	
	/**
	 * Ignores the event.
	 */
	public void mouseDragged(MouseEvent e) {
	}

	/**
	 * Ignores the event.
	 */
	public void mouseMoved(MouseEvent e) {
	}
	
	/**
	 * Ignores the event.
	 */
	public void windowActivated(WindowEvent e) {
	}

	/**
	 * Ignores the event.
	 */
	public void windowClosed(WindowEvent e) {
	}

	/**
	 * Tells the manager to stop and exits the application.
	 */
	public void windowClosing(WindowEvent e) {
		getManager().stop();
		System.exit(0);
	}

	/**
	 * Ignores the event.
	 */
	public void windowDeactivated(WindowEvent e) {
	}

	/**
	 * Ignores the event.
	 */
	public void windowDeiconified(WindowEvent e) {
	}

	/**
	 * Ignores the event.
	 */
	public void windowIconified(WindowEvent e) {
	}

	/**
	 * Ignores the event.
	 */
	public void windowOpened(WindowEvent e) {
	}
	
	/**
	 * Ignores the event.
	 */
	public void windowGainedFocus(WindowEvent e) {
	}
	
	/**
	 * Ignores the event.
	 */
	public void windowLostFocus(WindowEvent e) {
	}
}
