package rpgeeze.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import rpgeeze.util.Command;
import rpgeeze.util.NoOp;

public abstract class Controller {
	public void keyPressed(KeyEvent e) {
	}

	public Command keyReleased(KeyEvent e) {
		return new NoOp();
	}

	public Command keyTyped(KeyEvent e) {
		return new NoOp();
	}

	public Command mouseClicked(MouseEvent e) {
		return new NoOp();
	}

	public Command mouseEntered(MouseEvent e) {
		return new NoOp();
	}

	public Command mouseExited(MouseEvent e) {
		return new NoOp();
	}

	public Command mousePressed(MouseEvent e) {
		return new NoOp();
	}

	public Command mouseReleased(MouseEvent e) {
		return new NoOp();
	}
}
