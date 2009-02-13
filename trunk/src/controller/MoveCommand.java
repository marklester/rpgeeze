package controller;

import model.*;

public class MoveCommand implements Command {
	private final Distance d;
	private final Model m;
	
	public MoveCommand(Model m, Distance d) {
		this.m = m;
		this.d = d;
	}
	
	public void execute() {
		m.moveEntity(d);
	}
}
