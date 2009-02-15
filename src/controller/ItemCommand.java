package controller;

import model.*;

public class ItemCommand implements Command{
	private final int cmd;
	
	public ItemCommand(int cmd) {
		this.cmd = cmd;
	}
	
	public void execute(Model m) {
		switch(cmd) {
		case 0:
			m.dropItem();
			break;
		default:
			break;	
	   }
	}
}