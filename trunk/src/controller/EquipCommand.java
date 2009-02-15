package controller;

import model.Command;
import model.Model;

public class EquipCommand implements Command {
	private final int index;

	public EquipCommand(int i) {
		this.index = i;
	}

	public void execute(Model m) {
		//m.equipItem(this.index);
	}
}
