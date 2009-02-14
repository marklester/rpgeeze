package controller;

import model.*;
//Used to control Menu Popups
public class MenuCommand implements Command {
	private final Controller controller;
	private final Model m;
	private final int viewport;
	public MenuCommand(Model m, Controller controller,int viewport) {
		this.m = m;
		this.controller = controller;
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
			default:
			break;
		}
		
	}
}