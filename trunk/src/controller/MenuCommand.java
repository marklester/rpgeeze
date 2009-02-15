package controller;

import model.*;
//Used to control Menu Popups
public class MenuCommand implements Command {
	private final Model m;
	private final int viewport;
	public MenuCommand(Model m, int viewport) {
		this.m = m;
		this.viewport=viewport;
	}
	
	public void execute() {
		switch(viewport){
			case Controller.STAT_VIEW:
				if(m.isStatsUp()){
					m.setStatsVisible(false);
				}else{
					m.setStatsVisible(true);
				}
			break;
			case Controller.INVENTORY_VIEW:
				if(m.isInventoryUp()){
					m.setInventoryVisible(false);
				}else{
					m.setInventoryVisible(true);
				}
			default:
			break;
		}
		
	}
}