package controller;

import model.*;

public class MoveCommand implements Command {
	private final Direction d;
	private final Model m;
	
	public MoveCommand(Model m, Direction d) {
		this.m = m;
		this.d = d;
	}
	
	public void execute() {
		m.moveAvatarRequest(d);
	}
}
