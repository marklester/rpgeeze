package controller;

import model.*;
//Used to control Menu Popups
public class MenuCommand implements Command {
	private final int viewport;
	
	public MenuCommand(int viewport) {
		this.viewport=viewport;
	}
	
	public void execute(Model m) {
		switch(viewport){
			case Controller.STAT_VIEW:
				m.setStatsVisible(!m.isStatsUp());
				break;
			case Controller.INVENTORY_VIEW:
				m.setInventoryVisible(!m.isInventoryUp());
				break;
			default:
				break;
		}
		
	}
}