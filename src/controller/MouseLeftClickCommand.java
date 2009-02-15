package controller;

import model.*;
import java.awt.Point;

public class MouseLeftClickCommand implements Command {
	private final int x;
	private final int y;
	
	MouseLeftClickCommand(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void execute(Model m) {
		m.mouseLeftClickAt(new Point(x, y));
	}
	
}
