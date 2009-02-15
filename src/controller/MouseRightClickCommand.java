package controller;

import java.awt.Point;

import model.Command;
import model.Model;

public class MouseRightClickCommand implements Command {
	private final int x;
	private final int y;

	MouseRightClickCommand(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void execute(Model m) {
		// Why is the Model dealing with mouse clicks?
		//m.mouseRightClickAt(new Point(this.x, this.y));
	}

}
