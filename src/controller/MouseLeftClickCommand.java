package controller;

import java.awt.Point;

import model.Command;
import model.Model;

public class MouseLeftClickCommand implements Command {
	private final int x;
	private final int y;

	MouseLeftClickCommand(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void execute(Model m) {
		m.mouseLeftClickAt(new Point(this.x, this.y));
	}

}
