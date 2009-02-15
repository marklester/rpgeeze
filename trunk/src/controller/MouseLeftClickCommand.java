package controller;

import java.awt.Point;

import model.Command;
import model.Model;

public class MouseLeftClickCommand implements Command {
	private final int x;
	private final int y;

	public MouseLeftClickCommand(int x, int y) {
		throw new RuntimeException("Why are you creating me? I'm deprecated.");
	}

	public void execute(Model m) {
		// Why is the Model dealing with mouse clicks?
		//m.mouseLeftClickAt(new Point(this.x, this.y));
	}

}
