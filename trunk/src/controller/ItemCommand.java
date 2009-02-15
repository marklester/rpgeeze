package controller;

import model.Command;
import model.Model;

public class ItemCommand implements Command {
	private final int cmd;

	public ItemCommand(int cmd) {
		this.cmd = cmd;
	}

	public void execute(Model m) {
		switch(this.cmd) {
		case 0:
			m.dropItem();
			break;
		default:
			break;
		}
	}
}