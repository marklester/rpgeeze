package controller;

import model.*;

public class MouseClickedCommand implements Command {
	private final int x;
	private final int y;
	
	MouseClickedCommand(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void execute(Model m) {
		m.mouseClickedAt(x, y);
	}
	
}
