package controller;

import model.*;

public class MousePressedCommand implements Command {

	Model m;
	int x;
	int y;
	
	MousePressedCommand(Model m, int x, int y)
	{
		this.m = m;
		this.x = x;
		this.y = y;
	}
	@Override
	public void execute() {
		m.mousePressAt(x, y);
	}
	
}
