package controller;

import model.*;

public class UnequipCommand implements Command {
	private final int where; // eg head, left/right hand, etc.
	private final Model m;
	
	public UnequipCommand(Model m, int i) {
		this.m = m;
		this.where = i;
	}
	
	public void execute() {
		m.uneqipItem(where);
	}
}
