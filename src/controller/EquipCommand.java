package controller;

import model.*;

public class EquipCommand implements Command {
	private final int index;
	
	public EquipCommand(int i) {
		this.index = i;
	}
	
	public void execute(Model m) {
		m.equipItem(index);
	}
}
