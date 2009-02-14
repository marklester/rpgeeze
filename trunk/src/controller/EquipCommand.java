package controller;

import model.*;

public class EquipCommand implements Command {
	private final int index;
	private final Model m;
	
	public EquipCommand(Model m, int i) {
		this.m = m;
		this.index = i;
	}
	
	public void execute() {
		m.equipItem(index);
	}
}
