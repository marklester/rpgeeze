package rpgeeze.util;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.WindowEvent;

/**
 * Utility event handler class that delegates all event handling to another
 * <code>EventAdapter</code>.
 * 
 */
public abstract class DelegatingEventAdapter extends EventAdapter {
	/**
	 * Retrieves the <code>EventAdapter</code> to which event-handling is
	 * delegated.
	 * 
	 * @return the delegate <code>EventAdapter</code>
	 */
	protected abstract EventAdapter getDelegate();

	/**
	 * Executes prior to every event delegation. Does nothing by default. May be
	 * overridden to provide uniform prior before event delegations.
	 * 
	 */
	protected void preEventDelegate() {
	}

	/**
	 * Executes after every event delegation. Does nothing by default. May be
	 * overridden to provide uniform behavior after event delegations.
	 * 
	 */
	protected void postEventDelegate() {
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void keyPressed(KeyEvent e) {
		preEventDelegate();
		getDelegate().keyPressed(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void keyReleased(KeyEvent e) {
		preEventDelegate();
		getDelegate().keyReleased(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void keyTyped(KeyEvent e) {
		preEventDelegate();
		getDelegate().keyTyped(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mouseClicked(MouseEvent e) {
		preEventDelegate();
		getDelegate().mouseClicked(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mouseEntered(MouseEvent e) {
		preEventDelegate();
		getDelegate().mouseEntered(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mouseExited(MouseEvent e) {
		preEventDelegate();
		getDelegate().mouseExited(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mousePressed(MouseEvent e) {
		preEventDelegate();
		getDelegate().mousePressed(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mouseReleased(MouseEvent e) {
		preEventDelegate();
		getDelegate().mouseReleased(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mouseDragged(MouseEvent e) {
		preEventDelegate();
		getDelegate().mouseDragged(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mouseMoved(MouseEvent e) {
		preEventDelegate();
		getDelegate().mouseMoved(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mouseWheelMoved(MouseWheelEvent e) {
		preEventDelegate();
		getDelegate().mouseWheelMoved(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowActivated(WindowEvent e) {
		preEventDelegate();
		getDelegate().windowActivated(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowClosed(WindowEvent e) {
		preEventDelegate();
		getDelegate().windowClosed(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowClosing(WindowEvent e) {
		preEventDelegate();
		getDelegate().windowClosing(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowDeactivated(WindowEvent e) {
		preEventDelegate();
		getDelegate().windowDeactivated(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowDeiconified(WindowEvent e) {
		preEventDelegate();
		getDelegate().windowDeiconified(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowIconified(WindowEvent e) {
		preEventDelegate();
		getDelegate().windowIconified(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowOpened(WindowEvent e) {
		preEventDelegate();
		getDelegate().windowOpened(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowGainedFocus(WindowEvent e) {
		preEventDelegate();
		getDelegate().windowGainedFocus(e);
		postEventDelegate();
	}

	/**
	 * Delegates the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowLostFocus(WindowEvent e) {
		preEventDelegate();
		getDelegate().windowLostFocus(e);
		postEventDelegate();
	}
}
