package controller;

import model.*;

public class ItemCommand implements Command{
	private final Model m;
	private final int cmd;
	
	public ItemCommand(Model m, int cmd) {
	 this.m = m;
	 this.cmd = cmd;
	}
	
	public void execute() {
		switch(cmd){
		case 0: m.dropItem(); break;
		default: break;	
	   }
	}
}