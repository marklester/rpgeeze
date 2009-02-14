package controller;

import model.*;
//Used to control Menu Popups
public class MenuCommand implements Command {
	private final String action;
	private final Model m;
	
	public MenuCommand(Model m, String action) {
		this.m = m;
		this.action = action;
	}
	
	public void execute() {
		if(m.isMenuUp()){
			m.setMenuVisible(false);
		}else{
			m.setMenuVisible(true);
		}
	}
}