package rpgeeze.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

/**
 * Utility event handler class that provides empty implementations for all of
 * its event-handling methods.
 * 
 */
public abstract class EventAdapter implements KeyListener, MouseListener,
		MouseMotionListener, MouseWheelListener, WindowListener,
		WindowFocusListener {

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void keyPressed(KeyEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mouseDragged(MouseEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mouseMoved(MouseEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void mouseWheelMoved(MouseWheelEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowActivated(WindowEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowClosed(WindowEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowClosing(WindowEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowDeactivated(WindowEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowDeiconified(WindowEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowIconified(WindowEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowOpened(WindowEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowGainedFocus(WindowEvent e) {
	}

	/**
	 * Ignores the event.
	 * 
	 * @param e
	 *            the event
	 * 
	 */
	public void windowLostFocus(WindowEvent e) {
	}
}
