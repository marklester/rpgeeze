package controller;

import model.*;

public class MousePressedCommand implements Command {
	private final int x;
	private final int y;
	
	MousePressedCommand(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void execute(Model m) {
		m.mousePressAt(x, y);
	}
	
}
