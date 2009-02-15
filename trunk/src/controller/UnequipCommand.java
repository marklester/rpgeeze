package controller;

import model.Command;
import model.Model;

public class UnequipCommand implements Command {
	private final int where; // eg head, left/right hand, etc.

	public UnequipCommand(int i) {
		this.where = i;
	}

	public void execute(Model m) {
		//m.uneqipItem(this.where);
	}
}
