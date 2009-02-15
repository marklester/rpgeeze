package controller;

import model.*;

public class MouseRightClickCommand implements Command {
	private final int x;
	private final int y;
	
	MouseRightClickCommand(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void execute(Model m) {
		m.mouseRightClickAt(x, y);
	}
	
}
