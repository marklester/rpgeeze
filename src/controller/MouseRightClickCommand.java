package controller;

import java.awt.Point;

import model.Command;
import model.Model;

public class MouseRightClickCommand implements Command {
	private final int x;
	private final int y;

	public MouseRightClickCommand(int x, int y) {
		throw new RuntimeException("Why are you creating me? I'm deprecated.");
	}

	public void execute(Model m) {
		// Why is the Model dealing with mouse clicks?
		//m.mouseRightClickAt(new Point(this.x, this.y));
	}

}
